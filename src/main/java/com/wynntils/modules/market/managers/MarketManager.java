package com.wynntils.modules.market.managers;

import com.wynntils.modules.core.instances.inventory.FakeInventory;
import com.wynntils.modules.core.instances.inventory.InventoryOpenByInteract;

import java.util.regex.Pattern;

public class MarketManager {
    private static final Pattern MARKET_WINDOW_TITLE_PATTERN = Pattern.compile(".*");

    private static FakeInventory lastInventory = null;

    public static void scanMarket() {
        FakeInventory inv = new FakeInventory(MARKET_WINDOW_TITLE_PATTERN, new InventoryOpenByInteract());
        inv.setLimitTime(15000); // 15 seconds

        inv.onReceiveItems((i) -> {
            System.out.println(i);
        });

        inv.open();
    }
}
