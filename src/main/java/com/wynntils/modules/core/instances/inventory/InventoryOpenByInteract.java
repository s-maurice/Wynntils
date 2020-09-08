package com.wynntils.modules.core.instances.inventory;

import com.wynntils.ModCore;
import com.wynntils.modules.core.interfaces.IInventoryOpenAction;
import com.wynntils.modules.core.managers.PacketQueue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.util.EnumHand;

public class InventoryOpenByInteract implements IInventoryOpenAction {

//    public InventoryOpenByInteract(int interactType) {
//        this.interactType = interactType;
//    }

    private static final CPacketPlayerTryUseItem rightClick = new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND);


    @Override
    public void onOpen(FakeInventory inv, Runnable onDrop) {
        Minecraft mc = ModCore.mc();

//        mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
//        System.out.println(mc.objectMouseOver.entityHit);

        PacketQueue.queueComplexPacket(new CPacketUseEntity(mc.objectMouseOver.entityHit), SPacketOpenWindow.class).setSender(NetHandlerPlayClient::sendPacket).onDrop(onDrop);
    }
}
