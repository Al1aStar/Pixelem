package Ranks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashSet;

/**
 * Created by Robert on 11/4/2014.
 */
public class SetRanks implements Listener, CommandExecutor {

    public  HashSet<Player> Admin = new HashSet<Player>();
    public  HashSet<Player> Owner = new HashSet<Player>();
    public  HashSet<Player> Mod = new HashSet<Player>();

//---------------------------------------------------------------------------------------------------------------------------------------------------

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("rank")) {
            p.sendMessage("§7[§6Pixelem§7] §4Too little args");
            if (Admin.contains(p) || Owner.contains(p) || p.isOp()) {
                if (args[1] == "set") {
                    if (args.length != 2) {
                        p.sendMessage("§7[§6Pixelem§7] §4Too little args");
                    } else {
                        Player target = p.getServer().getPlayer(args[1]);
                        if (args[2] == "Admin") {
                            Admin.add(target);
                            p.sendMessage("§7[§6Pixelem§7] §4Added §e" + target.getName() + " §4 to rank §cAdmin!");
                            target.sendMessage("§7[§6Pixelem§7] §4Added to rank §cAdmin §4by §e" + p.getName() + "!");
                        }
                    }
                }
            }
        }
        return true;
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public boolean onCommand1(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("poop") && !(Admin.contains(p))) {
            p.sendMessage("Must be a admin to use this command!");
        } else {
            p.sendMessage("Test");
        }
        return true;
    }
}