package sh.kaden.darkoakban;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class DarkOakBanPlugin extends JavaPlugin implements Listener {

    private List<String> triggers = new ArrayList<>();
    private List<String> chat = new ArrayList<>();
    private List<String> commands = new ArrayList<>();
    private boolean killPlayer = false;
    private boolean smitePlayer = false;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        new BukkitRunnable() {
            @Override
            public void run() {
                loadConfig();
            }
        }.runTask(this);
    }

    @EventHandler
    public void onSign(SignChangeEvent event) {
        if (!this.triggers.contains("change")) return;
        if (event.getBlock().getType() == Material.DARK_OAK_WALL_SIGN
                || event.getBlock().getType() == Material.DARK_OAK_HANGING_SIGN
                || event.getBlock().getType() == Material.DARK_OAK_SIGN
                || event.getBlock().getType() == Material.DARK_OAK_WALL_HANGING_SIGN) {
            this.punish(event.getPlayer());
        }
    }

    @EventHandler
    public void onSignPlace(BlockPlaceEvent event) {
        if (!this.triggers.contains("place")) return;
        if (event.getBlock().getType() == Material.DARK_OAK_WALL_SIGN
                || event.getBlock().getType() == Material.DARK_OAK_HANGING_SIGN
                || event.getBlock().getType() == Material.DARK_OAK_SIGN
                || event.getBlock().getType() == Material.DARK_OAK_WALL_HANGING_SIGN) {
            this.punish(event.getPlayer());
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll((JavaPlugin) this);
    }

    public void punish(final Player target) {
        // run chat
        for (final String preformatted : this.chat) {
            TagResolver tags = TagResolver.builder()
                    .tag("target", Tag.inserting(target.name()))
                    .build();

            Bukkit.broadcast(MiniMessage
                    .miniMessage()
                    .deserialize(preformatted, tags));
        }

        // smite?
        if (this.smitePlayer) {
            target.getWorld().strikeLightning(target.getLocation());
        }

        // kill?
        if (this.killPlayer) {
            target.setHealth(0);
        }

        // commands?
        for (final String command : this.commands) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("<target>", target.getName()));
        }
    }

    private void loadConfig() {
        // load files and config into object
        saveResource("config.yml", false);
        FileConfiguration config = getConfig();

        // reset config
        this.chat = new ArrayList<>();

        // 'chat' property
        if (config.isString("chat")) {
            this.chat.add(config.getString("chat"));
        } else if (config.isList("chat")) {
            this.chat.addAll(config.getStringList("chat"));
        }

        // 'command' property
        if (config.isString("command")) {
            this.commands.add(config.getString("command"));
        }

        // 'commands' property
        if (config.isList("commands")) {
            this.commands.addAll(config.getStringList("commands"));
        } else if (config.isString("commands")) {
            this.commands.add(config.getString("commands"));
        }

        // 'smite' property
        if (config.isBoolean("smite")) {
            this.smitePlayer = config.getBoolean("smite");
        }

        // 'kill' property
        if (config.isBoolean("kill")) {
            this.killPlayer = config.getBoolean("kill");
        }

        if (config.isList("triggers")) {
            this.triggers = new ArrayList<>(config.getStringList("triggers"));
        }
    }
}
