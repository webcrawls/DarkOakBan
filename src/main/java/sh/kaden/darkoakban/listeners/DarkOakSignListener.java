package sh.kaden.darkoakban.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class DarkOakSignListener implements Listener {

    @EventHandler
    public void onSign(SignChangeEvent event) {
        Player player = event.getPlayer();

        Block block = event.getBlock();

        if (block.getType() == Material.DARK_OAK_WALL_SIGN) {
            player.banPlayerFull("being a little bitch");
        }
    }

}
