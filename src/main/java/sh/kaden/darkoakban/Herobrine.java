package sh.kaden.darkoakban;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Herobrine {

    private static String[] choices = {"HerobrineSkeleton", "HerobrineZombie", "HerobrineEnderman"};

    public static void run(JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {

                    Location location = player.getLocation().clone();

                    Random random = new Random();

                    int x = random.nextInt(10)-5;
                    int z = random.nextInt(10)-5;

                    location.add(x, 0, z);

                    try {
                        MythicMobs.inst().getAPIHelper().spawnMythicMob(randomChoice(), location);
                    } catch (InvalidMobTypeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    private static String randomChoice() {
        Random random = new Random();

        int choice = random.nextInt(choices.length);

        return choices[choice];
    }

}
