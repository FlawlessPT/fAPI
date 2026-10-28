package pt.flawless.fapi.reload;

/** Recarrega a configuração de um plugin; registado com {@link Reloads#register}. */
public interface Reloadable {

    void reload() throws Exception;
}
