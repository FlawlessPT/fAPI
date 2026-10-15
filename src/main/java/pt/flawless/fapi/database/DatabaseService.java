package pt.flawless.fapi.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.bukkit.plugin.Plugin;

/**
 * Contrato da camada de base de dados partilhada, implementado pelo plugin fDatabase.
 *
 * <p>Obtenha a instância com {@link Databases#get()}, uma vez, no {@code onEnable}.
 *
 * <h2>Falhas dos futuros</h2>
 * <ul>
 *   <li>Se o trabalho lançar {@link SQLException}, o futuro completa excecionalmente com essa mesma
 *       exceção: {@code get()} devolve-a como causa de {@code ExecutionException} e {@code join()} como
 *       causa de {@code CompletionException}. Qualquer outra exceção do trabalho é tratada da mesma forma.</li>
 *   <li>Depois de o serviço fechar (fDatabase desativado), o futuro completa excecionalmente com
 *       {@link java.util.concurrent.RejectedExecutionException}; os métodos nunca lançam.</li>
 *   <li>Trabalho ainda em fila quando o serviço encerra à força completa com
 *       {@link java.util.concurrent.CancellationException}.</li>
 *   <li>A fila de trabalho não tem limite: durante uma falha da base de dados as tarefas acumulam-se
 *       (cada uma pode esperar até ~10 s por uma ligação). Trate sempre o falhanço dos futuros
 *       ({@code exceptionally}/{@code handle}).</li>
 * </ul>
 *
 * <h2>Regras para o código passado em {@code work}</h2>
 * <ul>
 *   <li><b>Nunca</b> chame {@code join()}/{@code get()} sobre outro futuro da base de dados dentro de um
 *       {@code work}: o executor tem no máximo 4 threads, e esperas aninhadas podem bloquear tudo
 *       (deadlock). Faça tudo numa só lambda, ou encadeie com {@code thenCompose}.</li>
 *   <li><b>Transações</b>: dentro de uma única lambda, {@code connection.setAutoCommit(false)}, as
 *       operações e {@code connection.commit()}. Se a lambda falhar antes do {@code commit()}, o pool
 *       faz rollback e repõe o auto-commit quando a ligação volta ao pool.</li>
 *   <li>Não guarde a {@link Connection} fora da lambda: é devolvida ao pool quando esta termina.</li>
 * </ul>
 */
public interface DatabaseService {

    /**
     * Executa {@code work} numa ligação do pool, fora da thread principal, e devolve o resultado.
     * A ligação é devolvida ao pool quando {@code work} termina. Ver a secção de falhas na descrição
     * da interface.
     *
     * @param work trabalho a executar; recebe a ligação e pode lançar {@link SQLException}
     * @return futuro com o resultado de {@code work}; nunca é {@code null} nem lança
     */
    <T> CompletableFuture<T> supplyAsync(SqlFunction<Connection, T> work);

    /**
     * Executa {@code work} numa ligação do pool, fora da thread principal.
     * Mesmas regras e contrato de falha que {@link #supplyAsync(SqlFunction)}.
     *
     * @param work trabalho a executar; recebe a ligação e pode lançar {@link SQLException}
     * @return futuro que completa quando {@code work} termina; nunca é {@code null} nem lança
     */
    CompletableFuture<Void> runAsync(SqlConsumer<Connection> work);

    /**
     * Aplica as migrações SQL (recursos do jar de {@code owner}) ainda não aplicadas, por ordem.
     *
     * <p><b>Bloqueia a thread que chama</b> e prende uma ligação do pool até ~30 s à espera do lock de
     * migrações: chame apenas no {@code onEnable}, nunca em código de jogo nem dentro de um {@code work}.
     * Lança {@link SQLException} se o nome de um ficheiro, a ordem das versões ou a execução forem inválidos
     * (a mensagem indica o caminho); {@code owner} nulo lança {@link NullPointerException}.
     *
     * <h3>Regras dos ficheiros de migração</h3>
     * <ul>
     *   <li>O nome é {@code V<n>__descricao.sql} e a versão é o número {@code n}; a lista
     *       {@code resources} tem de ter versões estritamente crescentes.</li>
     *   <li>Cada instrução termina com {@code ;} no <b>fim da linha</b> e <b>nada</b> pode seguir o
     *       {@code ;} na mesma linha (nem um comentário {@code --}): essa linha não seria dividida e
     *       juntava-se à instrução seguinte.</li>
     *   <li>Linhas que começam por {@code --} são descartadas. Não há suporte para {@code DELIMITER} nem
     *       para stored procedures/triggers.</li>
     *   <li>As migrações têm de ser idempotentes ({@code CREATE TABLE IF NOT EXISTS}, ...): o DDL do MySQL
     *       não é transacional, por isso uma migração que falhe a meio pode ter aplicado parte das instruções
     *       e é repetida do início no arranque seguinte.</li>
     * </ul>
     *
     * @param owner     plugin dono das migrações (o seu nome identifica o histórico); não pode ser {@code null}
     * @param resources caminhos dos recursos no jar de {@code owner}, em ordem crescente de versão
     * @throws SQLException se a lista for inválida ou uma migração falhar
     */
    void migrate(Plugin owner, List<String> resources) throws SQLException;
}
