package pt.flawless.fapi.items;

/** Lançada quando o serviço de itens (plugin fCustomItems) não está disponível. */
public class ItemsUnavailableException extends RuntimeException {
    public ItemsUnavailableException(String message) {
        super(message);
    }
}
