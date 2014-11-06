package Dev.Pixelem.Net;

import org.bukkit.entity.Player;

/**
 * Created by Robert on 11/4/2014.
 */
public class api {

    public static void giveCoin(Player p, int i) {
        Core.config.set(p.getName() + ".Coins",
                Core.config.getInt(p.getName() + ".Coins", 0) + i);
        Core.saveFile();
        p.sendMessage("§a+" + i + " Coins");
        return;
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public static void takeCoin(Player p, int i) {
        Core.config.set(p.getName() + ".Coins",
                Core.config.getInt(p.getName() + ".Coins", 0) - i);
        Core.saveFile();
        p.sendMessage("§a-" + i + " Coins");
        return;
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------
    public static boolean hasEnough(Player p, int i) {
        if (Core.config.getInt(p.getName() + ".Coins") >= i)
            return true;
        return false;
    }
}
//---------------------------------------------------------------------------------------------------------------------------------------------------