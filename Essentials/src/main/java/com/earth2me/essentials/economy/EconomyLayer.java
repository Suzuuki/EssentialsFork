package com.earth2me.essentials.economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import ru.kaingross.xeconomy.XEconomy;

import java.math.BigDecimal;

public interface EconomyLayer {
    String getName();

    String getBackendName();

    void enable(Plugin plugin);

    boolean onServerLoad();

    void disable();

    String getPluginName();

    String getPluginVersion();

    boolean hasAccount(OfflinePlayer player);

    boolean createPlayerAccount(OfflinePlayer player);

    BigDecimal getBalance(OfflinePlayer player);

    boolean deposit(OfflinePlayer player, BigDecimal amount);

    boolean withdraw(OfflinePlayer player, BigDecimal amount);

    default boolean set(OfflinePlayer player, BigDecimal amount) {
        // Slon fork: write directly to XEconomy so the value is exact and we don't
        // round-trip via withdraw(balance) + deposit — that pattern fails for
        // accounts in overdraft because withdraw refuses negative balances.
        XEconomy.getPlayer(player.getName()).set(amount.doubleValue());
        return true;
    }
}
