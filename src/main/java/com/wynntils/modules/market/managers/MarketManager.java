package com.wynntils.modules.market.managers;

import com.wynntils.ModCore;
import com.wynntils.Reference;
import com.wynntils.core.framework.FrameworkManager;
import com.wynntils.core.framework.enums.wynntils.WynntilsSound;
import com.wynntils.modules.chat.overlays.ChatOverlay;
import com.wynntils.modules.core.enums.InventoryResult;
import com.wynntils.modules.core.instances.inventory.FakeInventory;
import com.wynntils.modules.core.instances.inventory.InventoryOpenByInteract;
import com.wynntils.modules.questbook.events.custom.QuestBookUpdateEvent;
import com.wynntils.modules.questbook.managers.QuestManager;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.util.text.TextComponentString;

import java.util.regex.Pattern;

import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraft.util.text.TextFormatting.RED;

public class MarketManager {
    private static final Pattern MARKET_WINDOW_TITLE_PATTERN = Pattern.compile(".*");
    private static final int MESSAGE_ID = 423475494;

    private static boolean hasInterrupted = false;

    private static FakeInventory lastInventory = null;

    public static void scanMarket() {
        if (!Reference.onWorld) return;

        if (ModCore.mc().player.openContainer != null && !(ModCore.mc().player.openContainer instanceof ContainerPlayer)) {
            interrupt();
            return;
        }

        synchronized (QuestManager.class) {
            if (lastInventory != null && lastInventory.isOpen()) return;
        }

        sendMessage(GRAY + "[Analysing market...]");
        hasInterrupted = false;

        FakeInventory inv = new FakeInventory(MARKET_WINDOW_TITLE_PATTERN, new InventoryOpenByInteract());
        inv.setLimitTime(15000); // 15 seconds

        // receive items
        inv.onReceiveItems((i) -> {
            System.out.println(i); // number 35 for search itemstack
        });

        // closing
        inv.onClose((i, result) -> {
            lastInventory = null;

            if (result != InventoryResult.CLOSED_SUCCESSFULLY) {
                interrupt();
                return;
            }

            sendMessage(GRAY + "[Market analysed]");
        });

        // synchronise entry point for open
        synchronized (QuestManager.class) {
            if (lastInventory == null || !lastInventory.isOpen()) {
                lastInventory = inv;
                lastInventory.open();
            }
        }
    }

    private static void sendMessage(String msg) {
        // Can be called from nio thread by FakeInventory
        Minecraft.getMinecraft().addScheduledTask(() ->
                ChatOverlay.getChat().printChatMessageWithOptionalDeletion(new TextComponentString(msg), MESSAGE_ID)
        );
    }

    private static void interrupt() {
        hasInterrupted = true;
        sendMessage(RED + "[Market  analysis failed]");
    }
}
