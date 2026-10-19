package pt.flawless.fapi.ranks;

/** Lançada quando o serviço de ranks (plugin fRankup) não está disponível. */
public class RanksUnavailableException extends RuntimeException {
    public RanksUnavailableException(String message) {
        super(message);
    }
}
