package pt.flawless.fapi.friends;

/** Lançada quando o serviço de amigos (plugin fFriends) não está disponível. */
public class FriendsUnavailableException extends IllegalStateException {
    public FriendsUnavailableException(String message) {
        super(message);
    }
}
