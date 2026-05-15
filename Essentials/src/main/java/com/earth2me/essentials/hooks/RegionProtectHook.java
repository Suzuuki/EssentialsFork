package com.earth2me.essentials.hooks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ru.kaingross.regionprotect.data.Region;
import ru.kaingross.regionprotect.data.RegionManager;

/**
 * Slon fork: optional RegionProtect integration used to block /sethome inside
 * other players' protected regions. Falls back to "no restriction" when the
 * plugin is not installed.
 *
 * Enable in EssentialsX config:
 *   slon-fork:
 *     sethome-region-protect-enabled: true
 */
public final class RegionProtectHook {
    private RegionProtectHook() {
    }

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().isPluginEnabled("RegionProtect");
    }

    /** @return true if the player can set a home at this location (or RegionProtect is absent). */
    public static boolean hasAccess(final Player player, final Location location) {
        if (!isAvailable()) {
            return true;
        }
        // RegionProtect 1.0 has no single hasAccess(Player, Location) helper, so we
        // resolve the region at the location and ask whether the player is allowed in it.
        // A null region means unprotected ground.
        final Region region = RegionManager.matchRegion(location);
        return region == null || region.isAllowedUser(player);
    }
}
