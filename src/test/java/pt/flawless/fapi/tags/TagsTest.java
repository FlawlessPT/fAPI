package pt.flawless.fapi.tags;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;

class TagsTest {

    private static final class StubApi implements TagsApi {
        TagSurface lastSurface;

        @Override
        public CompletableFuture<List<String>> getVisibleTags(UUID uuid, TagSurface surface) {
            lastSurface = surface;
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Boolean> give(UUID uuid, String tagId) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Boolean> take(UUID uuid, String tagId) {
            return new CompletableFuture<>();
        }
    }

    @Test
    void getVisibleTagsSemSuperficieUsaChat() {
        StubApi api = new StubApi();
        api.getVisibleTags(UUID.randomUUID());
        assertSame(TagSurface.CHAT, api.lastSurface);
    }

    @Test
    void getDevolveApiRegistada() {
        TagsApi api = new StubApi();
        assertSame(api, Tags.get(() -> api));
    }

    @Test
    void getSemServicoLancaTagsUnavailable() {
        TagsUnavailableException ex = assertThrows(TagsUnavailableException.class, () -> Tags.get(() -> null));
        assertTrue(ex.getMessage().contains("fTags"));
    }
}
