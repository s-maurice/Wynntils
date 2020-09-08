package com.wynntils.modules.market.commands;

import com.wynntils.Reference;
import com.wynntils.core.framework.FrameworkManager;
import com.wynntils.core.framework.enums.ClassType;
import com.wynntils.core.framework.instances.PlayerInfo;
import com.wynntils.modules.market.managers.MarketManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.IClientCommand;


public class CommandStartMarketSearch extends CommandBase implements IClientCommand {

    @Override
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
        return false;
    }

    @Override
    public String getName() {
        return "startmarketsearch";
    }

    @Override
    public String getUsage(ICommandSender sender) { return "startmarketsearch"; }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        ITextComponent command = new TextComponentString("/startmarketsearch");

        if (!Reference.onWorld)
            throw new CommandException("You need to be in a Wynncraft world to run %s", command);
        if (PlayerInfo.getPlayerInfo().getCurrentClass() == ClassType.NONE)
            throw new CommandException("You need to select a class to run %s", command);

        FrameworkManager.getEventBus().register(this);
        MarketManager.scanMarket();
    }
}
