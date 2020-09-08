package com.wynntils.modules.market;

import com.wynntils.core.framework.instances.Module;
import com.wynntils.core.framework.interfaces.annotations.ModuleInfo;
import com.wynntils.modules.market.commands.CommandStartMarketSearch;
import com.wynntils.modules.market.configs.MarketSearchConfig;
import com.wynntils.modules.market.events.ClientEvents;

@ModuleInfo(name = "market_search", displayName = "Market Search")
public class MarketModule extends Module {

    public void onEnable() {
        registerEvents(new ClientEvents());

        registerSettings(MarketSearchConfig.class);

        registerCommand(new CommandStartMarketSearch());
    }

}
