package pt.flawless.fapi.permissions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;

class PermissionsTest {

    private static final class StubApi implements PermissionsApi {
        @Override
        public boolean has(UUID uuid, String permission) {
            return false;
        }

        @Override
        public CompletableFuture<PermissionCheck> check(UUID uuid, String permission) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Set<String>> getGroups(UUID uuid) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Boolean> addGroup(UUID uuid, String group) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Boolean> removeGroup(UUID uuid, String group) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Set<String>> getPlayerPermissions(UUID uuid) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Boolean> addPlayerPermission(UUID uuid, String permission) {
            return new CompletableFuture<>();
        }

        @Override
        public CompletableFuture<Boolean> removePlayerPermission(UUID uuid, String permission) {
            return new CompletableFuture<>();
        }

        @Override
        public Set<String> getGroupNames() {
            return Collections.emptySet();
        }

        @Override
        public Optional<GroupInfo> getGroup(String name) {
            return Optional.empty();
        }
    }

    @Test
    void get_returnsRegisteredApi() {
        PermissionsApi api = new StubApi();
        assertSame(api, Permissions.get(() -> api));
    }

    @Test
    void get_throwsWhenMissing() {
        PermissionsUnavailableException ex =
                assertThrows(PermissionsUnavailableException.class, () -> Permissions.get(() -> null));
        assertEquals("serviço de permissões indisponível: verifique o plugin fPermissions", ex.getMessage());
    }
}
