package pt.flawless.fapi.chat;

/** Lançada quando o serviço de chat (plugin fChat) não está disponível. */
public class ChatUnavailableException extends IllegalStateException {
    public ChatUnavailableException(String message) {
        super(message);
    }
}
