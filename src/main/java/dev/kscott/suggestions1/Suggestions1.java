package dev.kscott.suggestions1;

import dev.kscott.suggestions1.listeners.DarkOakSignListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Suggestions1 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new DarkOakSignListener(), this);

        //Herobrine.run(this);
        //IronGolem.run(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
