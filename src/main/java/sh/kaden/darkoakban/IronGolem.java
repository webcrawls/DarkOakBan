package sh.kaden.darkoakban;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class IronGolem {

    public static void run(JavaPlugin plugin) {

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    List<Entity> entityList = player.getNearbyEntities(10, 10, 10);

                    for (Entity entity : entityList) {
                            if (entity instanceof org.bukkit.entity.IronGolem) {
                            if (hasIron(player)) {
                                Creature creature = (Creature) entity;

                                creature.setTarget(player);
                            } else {

                            }
                        }
                    }

                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    public static boolean hasIron(Player player) {

        PlayerInventory inventory = player.getInventory();

        ItemStack helmet = inventory.getHelmet();
        ItemStack chestplate = inventory.getChestplate();
        ItemStack leggings = inventory.getLeggings();
        ItemStack boots = inventory.getBoots();

        if (helmet != null && chestplate != null && leggings != null && boots != null) {
            System.out.println("uh oh");
            return isIron(helmet) && isIron(chestplate) && isIron(leggings) && isIron(boots);
        }

        return false;

    }

    private static boolean isIron(ItemStack item) {
        return item.getType() == Material.IRON_HELMET || item.getType() == Material.IRON_CHESTPLATE || item.getType() == Material.IRON_LEGGINGS || item.getType() == Material.IRON_BOOTS;
    }


}
