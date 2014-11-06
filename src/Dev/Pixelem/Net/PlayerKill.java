package Dev.Pixelem.Net;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Robert on 11/4/2014.
 */
public class PlayerKill implements Listener {


    @EventHandler
    public static void onKill(EntityDeathEvent e) {

        if (e.getEntity() instanceof Zombie) {
            Zombie pp = (Zombie) e.getEntity();
            if (pp.getKiller() instanceof Player) {
                Player p = pp.getKiller();
                if (p == pp) {
                    api.giveCoin(p, 0);
                } else {
                    api.giveCoin(p, 2);
                    p.sendMessage(ChatColor.GREEN + "Double Coins!");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 50));
                    pp.getWorld().spawnEntity(pp.getLocation(), EntityType.FIREWORK);

                }
            }
        }
    }
}
