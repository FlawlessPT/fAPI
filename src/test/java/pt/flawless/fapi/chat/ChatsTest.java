package pt.flawless.fapi.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;

class ChatsTest {

    private static final class StubApi implements ChatApi {
        @Override
        public Channel getDefaultChannel(UUID uuid) {
            return Channel.LOCAL;
        }

        @Override
        public CompletableFuture<Void> setDefaultChannel(UUID uuid, Channel channel) {
            return new CompletableFuture<>();
        }
    }

    @Test
    void get_returnsRegisteredApi() {
        ChatApi api = new StubApi();
        assertSame(api, Chats.get(() -> api));
    }

    @Test
    void get_throwsWhenMissing() {
        ChatUnavailableException ex =
                assertThrows(ChatUnavailableException.class, () -> Chats.get(() -> null));
        assertEquals("serviço de chat indisponível: verifique o plugin fChat", ex.getMessage());
    }
}
