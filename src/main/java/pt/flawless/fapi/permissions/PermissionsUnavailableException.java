package pt.flawless.fapi.permissions;

/** Lançada quando o serviço de permissões (plugin fPermissions) não está disponível. */
public class PermissionsUnavailableException extends RuntimeException {
    public PermissionsUnavailableException(String message) {
        super(message);
    }
}
