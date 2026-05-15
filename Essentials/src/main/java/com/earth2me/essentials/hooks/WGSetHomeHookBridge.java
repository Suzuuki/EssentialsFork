package com.earth2me.essentials.hooks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import ru.sasuke.wgsethomehook.WorldGuardHook;

/**
 * optional WGSetHomeHook integration. Looks up the WorldGuard
 * {@code use-sethome} flag at a location and answers whether /sethome should
 * be allowed there. Returns "allow" by default when the plugin is missing.
 *
 * Enable in EssentialsX config:
 *   custom:
 *     sethome-wg-flag-enabled: true
 */
public final class WGSetHomeHookBridge {
    private WGSetHomeHookBridge() {
    }

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().isPluginEnabled("WGSetHomeHook");
    }

    // Replicates the original check:
    //   isInRegion(loc) AND NOT getFlagValue(loc, "use-sethome")
    // i.e. block /sethome when the location is inside a WG region whose
    // use-sethome flag is not set to allow.
    //
    // @return true if /sethome should be blocked at this location.

    public static boolean isSetHomeBlocked(final Location location) {
        if (!isAvailable()) {
            return false;
        }
        final WorldGuardHook hook = WorldGuardHook.getHook();
        return hook.isInRegion(location) && !hook.getFlagValue(location, "use-sethome");
    }
}
