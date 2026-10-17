package pt.flawless.fapi.economy;

/** Lançada quando o serviço de economia (plugin fEconomy) não está disponível. */
public class EconomyUnavailableException extends RuntimeException {
    public EconomyUnavailableException(String message) {
        super(message);
    }
}
