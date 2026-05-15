package com.earth2me.essentials.hooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.sasuke.kitblocker.Main;

/**
 * Slon fork: optional KitBlocker integration. KitBlocker globally blocks kit usage
 * (typically during a wipe / pre-release window). All access goes through this
 * wrapper so the rest of EssentialsX has no static dependency on KitBlocker —
 * if the plugin is missing, {@link #isAvailable()} returns false and the rest
 * of the methods return safe defaults.
 *
 * Enable in EssentialsX config:
 *   slon-fork:
 *     kit-blocker-enabled: true
 */
public final class KitBlockerHook {
    private KitBlockerHook() {
    }

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().isPluginEnabled("KitBlocker");
    }

    /** @return true if the player is allowed to use kits right now, or KitBlocker is not installed/enabled. */
    public static boolean canUseKits(final Player player) {
        if (!isAvailable()) {
            return true;
        }
        return Main.canUseKits(player);
    }

    /** @return formatted remaining-time string, or empty string when unavailable. */
    public static String getRemainingTime() {
        if (!isAvailable()) {
            return "";
        }
        return Main.getRemainingTime();
    }
}
