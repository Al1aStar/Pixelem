package Dev.Pixelem.Net;

import Ranks.SetRanks;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Robert on 11/3/2014.
 */
public class Core extends JavaPlugin implements Listener {


    private static Player p;

    private Team admin;
    private Scoreboard adminboard;

    private Team owner;
    private Scoreboard ownerboard;

    private Team mod;
    private Scoreboard modboard;

    private Team helper;
    private Scoreboard helperboard;

    private Team player;
    private Scoreboard playerboard;

    public static FileConfiguration config;

    public static Core plugin = null;

    Block b = getServer().getWorld("world").getBlockAt(-74, 73, 157);

    ItemStack enderpearls = new ItemStack(Material.ENDER_PEARL, 4);
    ItemStack snowballs = new ItemStack(Material.SNOW_BALL, 5);
    ItemStack eggs = new ItemStack(Material.EGG, 8);

//---------------------------------------------------------------------------------------------------------------------------------------------------


    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerKill(), this);
        getCommand("test").setExecutor(new NPC());
        getCommand("pvp").setExecutor(new NPC());
        getCommand("rank").setExecutor(new SetRanks());
        makePlayerScoreboard();
        makeModScoreboard();
        makeAdminScoreboard();
        makeOwnerScoreboard();
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        Items(p);
        e.setJoinMessage("");
        if (p.getName().equalsIgnoreCase("Al1aStar")) {
            admin.addPlayer(p);
            p.setScoreboard(adminboard);
        } else if (p.getName().equalsIgnoreCase("dlange")) {
            owner.addPlayer(p);
            p.setScoreboard(ownerboard);
        } else {
            player.addPlayer(p);
            p.setScoreboard(playerboard);
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();
        e.setFormat(p.getPlayerListName() + " §f» " + e.getMessage());
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public void makePlayerScoreboard() {
        playerboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = playerboard.registerNewObjective("Test", "Test2");
        objective.setDisplayName("§6§lPixelem Network");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        player = playerboard.registerNewTeam("Team");
        player.setPrefix(ChatColor.GRAY + "");

        objective.getScore("§b§lWelcome").setScore(6);
        objective.getScore("§b§lto the").setScore(5);
        objective.getScore("§6§lPixelem").setScore(4);
        objective.getScore("§6§lNetwork!").setScore(3);
        objective.getScore("").setScore(2);
        objective.getScore("§7§lRANK:").setScore(1);
        objective.getScore("§7§lGUEST").setScore(0);
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public void makeAdminScoreboard() {
        adminboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = adminboard.registerNewObjective("Test", "Test2");
        objective.setDisplayName("§6§lPixelem Network");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        admin = adminboard.registerNewTeam("Team");
        admin.setPrefix(ChatColor.RED + "[Admin] ");

        objective.getScore("§b§lWelcome").setScore(6);
        objective.getScore("§b§lto the").setScore(5);
        objective.getScore("§6§lPixelem").setScore(4);
        objective.getScore("§6§lNetwork!").setScore(3);
        objective.getScore("").setScore(2);
        objective.getScore("§7§lRANK:").setScore(1);
        objective.getScore("§c§lADMIN").setScore(0);
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public void makeOwnerScoreboard() {
        ownerboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = ownerboard.registerNewObjective("Test", "Test2");
        objective.setDisplayName("§6§lPixelem Network");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        owner = ownerboard.registerNewTeam("Team");
        owner.setPrefix(ChatColor.RED + "[Owner] ");

        objective.getScore("§b§lWelcome").setScore(6);
        objective.getScore("§b§lto the").setScore(5);
        objective.getScore("§6§lPixelem").setScore(4);
        objective.getScore("§6§lNetwork!").setScore(3);
        objective.getScore("").setScore(2);
        objective.getScore("§7§lRANK:").setScore(1);
        objective.getScore("§c§lOWNER").setScore(0);
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public void makeModScoreboard() {
        modboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = modboard.registerNewObjective("Test", "Test2");
        objective.setDisplayName("§6§lPixelem Network");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        mod = modboard.registerNewTeam("Team");
        mod.setPrefix(ChatColor.DARK_GREEN + "[Mod] ");

        objective.getScore("§b§lWelcome").setScore(6);
        objective.getScore("§b§lto the").setScore(5);
        objective.getScore("§6§lPixelem").setScore(4);
        objective.getScore("§6§lNetwork!").setScore(3);
        objective.getScore("").setScore(2);
        objective.getScore("§7§lRANK:").setScore(1);
        objective.getScore("§2§lMOD").setScore(0);
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void Items(Player p) {

        ItemStack warp = new ItemStack(Material.COMPASS);
        ItemMeta warpm = warp.getItemMeta();
        warpm.setDisplayName("§a§lWARP MENU");
        List<String> lorelist = new ArrayList<String>();
        lorelist.add("§c§lClick to view gamemodes!");
        warpm.setLore(lorelist);
        warp.setItemMeta(warpm);

        ItemStack gadgets = new ItemStack(Material.ENDER_CHEST);
        ItemMeta gadgetsm = gadgets.getItemMeta();
        gadgetsm.setDisplayName("§a§lGADGETS");
        List<String> lorelist1 = new ArrayList<String>();
        lorelist1.add("§c§lClick to view gagets!");
        gadgetsm.setLore(lorelist1);
        gadgets.setItemMeta(gadgetsm);

        ItemStack settings = new ItemStack(Material.TORCH);
        ItemMeta settingsm = gadgets.getItemMeta();
        settingsm.setDisplayName("§a§lSETTINGS");
        List<String> lorelist2 = new ArrayList<String>();
        lorelist2.add("§c§lClick to change settings!");
        settingsm.setLore(lorelist2);
        settings.setItemMeta(settingsm);

        p.getInventory().clear();
        p.getInventory().setItem(0, gadgets);
        p.getInventory().setItem(4, warp);
        p.getInventory().setItem(8, settings);

        p.sendMessage("§7[§6Pixelem§7] §cWelcome to §6Pixelem Network§c!");

        p.getActivePotionEffects().clear();
        p.setGameMode(GameMode.ADVENTURE);
        p.setHealthScale(2.0);
        p.setFoodLevel(20);
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();
        if (e.getMessage().toLowerCase().startsWith("/") && !(p.isOp())) {
            e.setCancelled(true);
            p.sendMessage("§7[§6Pixelem§7] §4Insert denied message here!");
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Player p = e.getPlayer();
        if (p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        if (p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onDamage1(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause().equals(EntityDamageEvent.DamageCause.CONTACT) || e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING) || e.getCause().equals(EntityDamageEvent.DamageCause.FALL) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)) {
            e.setCancelled(true);
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    Inventory settings = Bukkit.createInventory(null, 9, "§a§lSETTINGS");

    {

        ItemStack speed = new ItemStack(Material.SUGAR);
        ItemMeta speedm = speed.getItemMeta();
        speedm.setDisplayName("§a§lSPEED");
        List<String> lorelist = new ArrayList<String>();
        lorelist.add("§c§lClick to get SPEED");
        speedm.setLore(lorelist);
        speed.setItemMeta(speedm);

        ItemStack jump = new ItemStack(Material.FEATHER);
        ItemMeta jumpm = speed.getItemMeta();
        jumpm.setDisplayName("§a§lJUMP");
        List<String> lorelist1 = new ArrayList<String>();
        lorelist1.add("§c§lClick to get JUMP");
        jumpm.setLore(lorelist1);
        jump.setItemMeta(jumpm);

        ItemStack clear = new ItemStack(Material.PAPER);
        ItemMeta clearm = speed.getItemMeta();
        clearm.setDisplayName("§a§lCLEAR");
        List<String> lorelist2 = new ArrayList<String>();
        lorelist2.add("§c§lClick to Clear Effects!");
        clearm.setLore(lorelist2);
        clear.setItemMeta(clearm);

        /*ItemStack bomb= new ItemStack(Material.SLIME_BALL);
        ItemMeta bombm = bomb.getItemMeta();
        bombm.setDisplayName("§a§lFIREWORK BOMB");
        List<String> lorelist3 = new ArrayList<String>();
        lorelist2.add("§c§lClick to Throw BOMB");
        bombm.setLore(lorelist3);
        bomb.setItemMeta(bombm);*/

        settings.setItem(0, speed);
        settings.setItem(1, jump);
        settings.setItem(8, clear);
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------

    Inventory gadgets = Bukkit.createInventory(null, 9, "§a§lGADGETS");

    {

        /*ItemStack speed = new ItemStack(Material.SUGAR);
        ItemMeta speedm = speed.getItemMeta();
        speedm.setDisplayName("§a§lSPEED");
        List<String> lorelist = new ArrayList<String>();
        lorelist.add("§c§lClick to get SPEED");
        speedm.setLore(lorelist);
        speed.setItemMeta(speedm);

        ItemStack jump = new ItemStack(Material.FEATHER);
        ItemMeta jumpm = speed.getItemMeta();
        jumpm.setDisplayName("§a§lJUMP");
        List<String> lorelist1 = new ArrayList<String>();
        lorelist1.add("§c§lClick to get JUMP");
        jumpm.setLore(lorelist1);
        jump.setItemMeta(jumpm);

        ItemStack clear = new ItemStack(Material.PAPER);
        ItemMeta clearm = speed.getItemMeta();
        clearm.setDisplayName("§a§lCLEAR");
        List<String> lorelist2 = new ArrayList<String>();
        lorelist2.add("§c§lClick to Clear Effects!");
        clearm.setLore(lorelist2);
        clear.setItemMeta(clearm);*/

        ItemStack bomb = new ItemStack(Material.SLIME_BALL);
        ItemMeta bombm = bomb.getItemMeta();
        bombm.setDisplayName("§a§lFIREWORK BOMB");
        List<String> lorelist3 = new ArrayList<String>();
        lorelist3.add("§c§lClick to Throw BOMB");
        bombm.setLore(lorelist3);
        bomb.setItemMeta(bombm);

        ItemStack chick = new ItemStack(Material.IRON_SPADE);
        ItemMeta chickm = bomb.getItemMeta();
        chickm.setDisplayName("§a§lCHICK GUN");
        List<String> lorelist2 = new ArrayList<String>();
        lorelist2.add("§c§lClick to Shoot Chickens!");
        chickm.setLore(lorelist2);
        chick.setItemMeta(chickm);

        ItemStack coins = new ItemStack(Material.YELLOW_FLOWER);
        ItemMeta coinsm = bomb.getItemMeta();
        coinsm.setDisplayName("§a§lCOINS");
        List<String> lorelist4 = new ArrayList<String>();
        lorelist4.add("§c§lClick to Check Coins!");
        coinsm.setLore(lorelist4);
        coins.setItemMeta(coinsm);

        //settings.setItem(0, speed);
        //settings.setItem(1, jump);
        //settings.setItem(8, clear);
        gadgets.setItem(0, bomb);
        gadgets.setItem(1, chick);
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onSETTINGS(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getItem().getType() == Material.TORCH) {
                p.openInventory(settings);
            }
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onGADGETS(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getItem().getType() == Material.ENDER_CHEST) {
                p.openInventory(gadgets);
            }
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onClick1(InventoryClickEvent e) {

        List<Player> hasBomb = new ArrayList<Player>();

        ItemStack bomb = new ItemStack(Material.SLIME_BALL);
        ItemMeta bombm = bomb.getItemMeta();
        bombm.setDisplayName("§a§lFIREWORK BOMB");
        List<String> lorelist2 = new ArrayList<String>();
        lorelist2.add("§c§lClick to Throw BOMB");
        bombm.setLore(lorelist2);
        bomb.setItemMeta(bombm);

        ItemStack chick = new ItemStack(Material.IRON_SPADE);
        ItemMeta chickm = bomb.getItemMeta();
        chickm.setDisplayName("§a§lCHICK GUN");
        List<String> lorelist3 = new ArrayList<String>();
        lorelist3.add("§c§lClick to Shoot Chickens!");
        chickm.setLore(lorelist3);
        chick.setItemMeta(chickm);

        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lSPEED")) {
            e.setCancelled(true);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 10));
            p.sendMessage("§7[§6Pixelem§7] §e+Speed");
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lJUMP")) {
            e.setCancelled(true);
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000000, 10));
            p.sendMessage("§7[§6Pixelem§7] §e+Jump");
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lCLEAR")) {
            e.setCancelled(true);
            for (PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
                p.sendMessage("§7[§6Pixelem§7] §eCleared");
            }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lFIREWORK BOMB")) {
            e.setCancelled(true);
            if (p.getInventory().contains(chick)) {
                p.getInventory().remove(chick);
                p.getInventory().addItem(bomb);
            } else if (p.getInventory().contains(bomb)) {
                p.sendMessage("§7[§6Pixelem§7] §cYou already have §a§lFIREWORK BOMB§c!");
            } else {
                p.getInventory().addItem(bomb);
                hasBomb.add(p);
                p.sendMessage("§7[§6Pixelem§7] §e+Firework Bomb");
            }
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        List<Player> hasBomb = new ArrayList<Player>();

        ItemStack bomb = new ItemStack(Material.SLIME_BALL);
        ItemMeta bombm = bomb.getItemMeta();
        bombm.setDisplayName("§a§lFIREWORK BOMB");
        List<String> lorelist2 = new ArrayList<String>();
        lorelist2.add("§c§lClick to Throw BOMB");
        bombm.setLore(lorelist2);
        bomb.setItemMeta(bombm);

        ItemStack chick = new ItemStack(Material.IRON_SPADE);
        ItemMeta chickm = bomb.getItemMeta();
        chickm.setDisplayName("§a§lCHICK GUN");
        List<String> lorelist3 = new ArrayList<String>();
        lorelist3.add("§c§lClick to Shoot Chickens!");
        chickm.setLore(lorelist3);
        chick.setItemMeta(chickm);

        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lCHICK GUN")) {
            e.setCancelled(true);
            if (p.getInventory().contains(bomb)) {
                p.getInventory().remove(bomb);
                p.getInventory().addItem(chick);
            } else if (p.getInventory().contains(chick)) {
                p.sendMessage("§7[§6Pixelem§7] §cYou already have §a§lCHICK GUN§c!");
            } else {
                p.getInventory().addItem(chick);
                hasBomb.add(p);
                p.sendMessage("§7[§6Pixelem§7] §e+Chick Gun");
            }
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------


    List<UUID> droppedItems = new ArrayList<UUID>();

    @EventHandler
    public void onClick(final PlayerInteractEvent event) {


        final Random random = new Random();

        ItemStack bomb = new ItemStack(Material.SLIME_BALL);
        ItemMeta bombm = bomb.getItemMeta();
        bombm.setDisplayName("§a§lFIREWORK BOMB");
        List<String> lorelist2 = new ArrayList<String>();
        lorelist2.add("§c§lClick to Throw BOMB");
        bombm.setLore(lorelist2);
        bomb.setItemMeta(bombm);


        /*for (int i = 0; i < 10; i++) {
            final EnderPearl chicken = event.getPlayer().getWorld().spawn(p.getEyeLocation().getBlock().getLocation(), EnderPearl.class);
            chicken.setVelocity(new Vector(random.nextDouble() - 0.5, random.nextDouble() / 2.0, random.nextDouble() - 0.5));*/
        Player player = event.getPlayer();
        Inventory playerInv = player.getInventory();
        World world = player.getWorld();
        if (playerInv.contains(bomb)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                final Item grenade = world.dropItemNaturally(player.getEyeLocation(),
                        bomb);
                droppedItems.add(grenade.getUniqueId());
                grenade.setVelocity(player.getEyeLocation().getDirection());
                Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                    EntityType bat = EntityType.BAT;

                    final ItemStack pearls = new ItemStack(Material.ENDER_PEARL, 10);
                    final ItemStack snowballs = new ItemStack(Material.SNOW_BALL, 10);
                    final ItemStack eggs = new ItemStack(Material.EGG, 10);
                    public void run() {
                        grenade.getWorld().spawnEntity(grenade.getLocation(), EntityType.FIREWORK);
                        grenade.remove();
                    }
                }, 40L);
            }
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Random random = new Random();
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) {
            return;
        }
        if (!(e.getItem().getType() == Material.IRON_SPADE)) {
            return;
        }

        for (int i = 0; i < 10; i++) {
            final Chicken chicken = e.getPlayer().getWorld().spawn(p.getEyeLocation().getBlock().getLocation(), Chicken.class);
            chicken.setVelocity(new Vector(random.nextDouble() - 0.5, random.nextDouble() / 2.0, random.nextDouble() - 0.5));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    chicken.remove();
                }
            }, 40);
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onPick(PlayerPickupItemEvent e) {

        Player p = e.getPlayer();
        if (e.getItem().getType().equals(Material.NETHER_STAR)) {
            e.setCancelled(false);
            p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 123));
            p.sendMessage("§7[§6Pixelem§7] §e+123 Paint Balls");
        } else {
            e.setCancelled(true);
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public static void saveFile() {
        plugin.saveConfig();
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            e.getDrops().clear();
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onEntitys(EntityTargetLivingEntityEvent e) {
        if (e.getEntity() instanceof Zombie || e.getEntity() instanceof Skeleton || e.getEntity() instanceof IronGolem) {
            if (e.getTarget() instanceof Player || e.getTarget() instanceof Skeleton) {
                e.setCancelled(true);
            }
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Zombie || e.getEntity() instanceof Cow || e.getEntity() instanceof Skeleton) {
            e.setCancelled(true);
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Zombie || e.getEntity() instanceof Skeleton || e.getEntity() instanceof Cow) {
            if (e.getDamager() instanceof Player) {
                e.setCancelled(true);
            }
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent e) {

        final Location blockloc = e.getEntity().getLocation().add(0, -1, 0);
        final Material firstblock = blockloc.getBlock().getType();
        final byte firstblockdata = blockloc.getBlock().getData();

        if (e.getEntityType() == EntityType.ENDER_PEARL || e.getEntityType() == EntityType.SNOWBALL || e.getEntityType() == EntityType.EGG) {
            if (firstblock != Material.WOOL) {
                if (firstblock != Material.AIR) {


                    Random dice = new Random();
                    int number;
                    number = dice.nextInt(15);
                    switch (number) {
                        case 1:


                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 1);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);

                                }
                            }, 40);

                            break;
                        case 2:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 2);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 3:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 3);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 4:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 4);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 5:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 5);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 6:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 6);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 7:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 7);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 8:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 8);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 9:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 9);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 10:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 10);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 11:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 11);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 12:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 12);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 13:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 13);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                        case 14:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 14);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);

                                }
                            }, 40);

                            break;
                        case 15:
                            blockloc.getBlock().setType(Material.WOOL);
                            blockloc.getBlock().setData((byte) 15);

                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                                public void run() {

                                    blockloc.getBlock().setType(firstblock);
                                    blockloc.getBlock().setData(firstblockdata);
                                }
                            }, 40);

                            break;
                    }

                }
            }


        }
    }


    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            event.setCancelled(true);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            e.setCancelled(true);
        }
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------

   /*@EventHandler
    public void onPlayerInteract1(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) { return; }
        if (!(e.getItem().getType() == Material.BLAZE_ROD)) { return; }
        EnderPearl enderPearl = p.launchProjectile(EnderPearl.class);
        enderPearl.setVelocity(e.getPlayer().getLocation().getDirection().normalize().multiply(10));
        return;
    }*/
//---------------------------------------------------------------------------------------------------------------------------------------------------

    /*@EventHandler
    public void onHit(ProjectileHitEvent e) {
        Entity arrow = e.getEntity();
        EnderPearl aarrow = (EnderPearl) arrow;
        World world = arrow.getWorld();
        BlockIterator iterator = new BlockIterator(world, arrow.getLocation().toVector(), arrow.getVelocity().normalize(), 0, 4);

        Block hitBlock = null;

        while (iterator.hasNext()) {

            hitBlock = iterator.next();

            if (hitBlock.getTypeId() != 0) {
                hitBlock.getWorld().
            }


        }
    }*/
}