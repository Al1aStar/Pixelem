package Dev.Pixelem.Net;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.xml.stream.Location;

/**
 * Created by Robert on 11/4/2014.
 */
public class NPC implements Listener, CommandExecutor {

    public static void spawnCow(Player p) {
        Cow cow = (Cow) p.getWorld().spawn(p.getLocation(), Cow.class);
        cow.setBaby();
        cow.setAgeLock(true);
        cow.setCustomName("§c§lMoo");
        cow.setCustomNameVisible(true);

        Cow cow1 = (Cow) p.getWorld().spawn(p.getLocation(), Cow.class);
        cow1.setBaby();
        cow1.setAgeLock(true);
        cow1.setCustomName("§c§lMr.Cow");
        cow1.setCustomNameVisible(true);

        Cow cow2 = (Cow) p.getWorld().spawn(p.getLocation(), Cow.class);
        cow2.setBaby();
        cow2.setAgeLock(true);
        cow2.setCustomName("§c§lI iz Cow");
        cow2.setCustomNameVisible(true);

        Cow cow3 = (Cow) p.getWorld().spawn(p.getLocation(), Cow.class);
        cow3.setBaby();
        cow3.setAgeLock(true);
        cow3.setCustomName("§c§lBaby");
        cow3.setCustomNameVisible(true);


        Skeleton skeleton = (Skeleton) p.getWorld().spawn(p.getLocation(), Skeleton.class);
        skeleton.setCustomName("§c§l#Al1aStar");
        skeleton.setCustomNameVisible(true);
        skeleton.getEquipment().setItemInHand(new ItemStack(Material.AIR));
}
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public static void spawnPvp(Player p) {
        Villager pvp = (Villager) p.getWorld().spawn(p.getLocation(), Villager.class);
        pvp.setCustomName("§6§lPvP");
        pvp.setCustomNameVisible(true);
        pvp.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000000, 1000000000));
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("test")) {
            org.bukkit.Location loc = player.getLocation();

            spawnCow(player);
        }
        return false;
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public boolean onPvP(CommandSender sender, Command cmd, String label, String[] a) {

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("pvp")) {
            org.bukkit.Location loc1 = player.getLocation();

            spawnPvp(player);
        }
        return false;
    }
}
