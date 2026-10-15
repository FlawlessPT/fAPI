package pt.flawless.fapi.database;

/** Lançada quando o serviço de base de dados (plugin fDatabase) não está disponível. */
public class DatabaseUnavailableException extends RuntimeException {
    public DatabaseUnavailableException(String message) {
        super(message);
    }
}
