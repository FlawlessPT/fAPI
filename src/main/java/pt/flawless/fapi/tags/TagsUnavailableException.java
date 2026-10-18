package pt.flawless.fapi.tags;

/** Lançada quando o serviço de tags (plugin fTags) não está disponível. */
public class TagsUnavailableException extends RuntimeException {
    public TagsUnavailableException(String message) {
        super(message);
    }
}
