/*
 *  * Copyright © Wynntils - 2018 - 2020.
 */

package com.wynntils.modules;


import com.wynntils.core.framework.FrameworkManager;
import com.wynntils.modules.chat.ChatModule;
import com.wynntils.modules.core.CoreModule;
import com.wynntils.modules.cosmetics.CosmeticsModule;
import com.wynntils.modules.map.MapModule;
import com.wynntils.modules.market.MarketModule;
import com.wynntils.modules.music.MusicModule;
import com.wynntils.modules.questbook.QuestBookModule;
import com.wynntils.modules.richpresence.RichPresenceModule;
import com.wynntils.modules.utilities.UtilitiesModule;
import com.wynntils.modules.visual.VisualModule;

public class ModuleManager {

    /**
     * This registers all modules that should be loaded
     */
    public static void initModules() {
        FrameworkManager.registerModule(new QuestBookModule());
        FrameworkManager.registerModule(new CoreModule());
        FrameworkManager.registerModule(new UtilitiesModule());
        FrameworkManager.registerModule(new RichPresenceModule());
        FrameworkManager.registerModule(new CosmeticsModule());
        FrameworkManager.registerModule(new MusicModule());
        FrameworkManager.registerModule(new ChatModule());
        FrameworkManager.registerModule(new MapModule());
        FrameworkManager.registerModule(new VisualModule());
        FrameworkManager.registerModule(new MarketModule());
    }

}
