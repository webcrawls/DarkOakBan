package sh.kaden.darkoakban;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.EventHandler;

public final class DarkOakBanPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onSign(SignChangeEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() == Material.DARK_OAK_WALL_SIGN) {
            player.banPlayerFull("being a little bitch");
        }
    }

    @Override
    public void onDisable() {
//        HandlerList.unre
    }
}
