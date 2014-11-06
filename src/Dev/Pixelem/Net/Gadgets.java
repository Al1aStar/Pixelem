package Dev.Pixelem.Net;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 11/5/2014.
 */
public class Gadgets implements Listener {

    @EventHandler
    public void giveFireworkLaucnher(Player p) {
        ItemStack bomb = new ItemStack(Material.SLIME_BALL);
        ItemMeta bombm = bomb.getItemMeta();
        bombm.setDisplayName("§a§lFIREWORK BOMB");
        List<String> lorelist2 = new ArrayList<String>();
        lorelist2.add("§c§lClick to Throw BOMB");
        bombm.setLore(lorelist2);
        bomb.setItemMeta(bombm);
    }
}
