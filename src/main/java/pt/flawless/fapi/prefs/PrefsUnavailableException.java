package pt.flawless.fapi.prefs;

/** Lançada quando o serviço de preferências (plugin fPrefs) não está disponível. */
public class PrefsUnavailableException extends IllegalStateException {
    public PrefsUnavailableException(String message) {
        super(message);
    }
}
