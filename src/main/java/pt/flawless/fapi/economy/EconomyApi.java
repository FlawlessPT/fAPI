package pt.flawless.fapi.economy;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Contrato da economia, registado no ServicesManager pelo plugin fEconomy. Todos os montantes são em cêntimos.
 *
 * <p>Os futuros completam fora da thread principal do servidor. Falham com {@link InsufficientFundsException} (saldo
 * insuficiente), {@link AccountNotFoundException} (conta inexistente) ou {@link IllegalArgumentException} (montante
 * inválido); as exceções podem chegar embrulhadas numa {@code CompletionException}.
 */
public interface EconomyApi {

    /** Saldo em cêntimos; 0 se a conta não existir. */
    CompletableFuture<Long> getBalance(UUID uuid);

    /** Soma {@code cents > 0} ao saldo; devolve o novo saldo. */
    CompletableFuture<Long> deposit(UUID uuid, long cents);

    /** Subtrai {@code cents > 0} ao saldo, sem nunca ficar negativo; devolve o novo saldo. */
    CompletableFuture<Long> withdraw(UUID uuid, long cents);

    /** Transfere {@code cents > 0} de {@code from} para {@code to} atomicamente; devolve o novo saldo de {@code from}. */
    CompletableFuture<Long> transfer(UUID from, UUID to, long cents);

    /** Formata cêntimos como euros, por exemplo 1250 -> "12,50€". */
    String format(long cents);
}
