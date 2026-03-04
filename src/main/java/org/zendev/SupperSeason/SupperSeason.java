/*     */ package org.zendev.SupperSeason;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.zendev.SupperSeason.BossBar.TimeBar;
/*     */ import org.zendev.SupperSeason.Files.File_config;
/*     */ import org.zendev.SupperSeason.Files.File_effect;
/*     */ import org.zendev.SupperSeason.Files.File_fish;
/*     */ import org.zendev.SupperSeason.Files.File_rarity;
/*     */ import org.zendev.SupperSeason.Files.File_time;
/*     */ import org.zendev.SupperSeason.Fishing.Contest.ContestBar;
/*     */ import org.zendev.SupperSeason.Fishing.Contest.FishingCT;
/*     */ import org.zendev.SupperSeason.Hooker.PAPI_hooker;
/*     */ import org.zendev.SupperSeason.Hooker.Vault_hooker;
/*     */ import org.zendev.SupperSeason.Utils.U_BossBar;
/*     */ import org.zendev.SupperSeason.Utils.Updater.UpdateChecker;
/*     */ import org.zendev.SupperSeason.Utils.Updater.UpdateConfig;
/*     */ import org.zendev.SupperSeason.Utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SupperSeason
/*     */   extends JavaPlugin
/*     */ {
/*     */   public static SupperSeason instance;
/*     */   public static String mc_ver;
/*     */   private static int mc;
/*     */   public static boolean legacy = true;
/*     */   
/*     */   public void onEnable() {
/*  51 */     instance = this;
/*     */     
/*  53 */     hook();
/*     */     
/*  55 */     createFile();
/*  56 */     createMechanic();
/*  57 */     registerCommand();
/*  58 */     registerEvent();
/*  59 */     registerRunner(0, 20);
/*  60 */     registerFishing();
/*     */     
/*  62 */     mc_ver = instance.getServer().getBukkitVersion().substring(0, 4);
/*  63 */     String num = "";
/*  64 */     if (mc_ver.indexOf(".") != mc_ver.lastIndexOf(".") && mc_ver.lastIndexOf(".") != 0) { num = mc_ver.substring(mc_ver.indexOf(".") + 1, mc_ver.lastIndexOf(".")); }
/*  65 */     else { num = mc_ver.substring(mc_ver.indexOf(".") + 1, mc_ver.length()); }
/*  66 */      mc = Integer.parseInt(num);
/*  67 */     if (mc > 13) legacy = false;
/*     */     
/*  69 */     if (File_config.isLogger()) {
/*     */       
/*  71 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&3&m|---------&b&m==========&3&m---------|"));
/*  72 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&bPlugin:&a SupperSeason"));
/*  73 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&bStatus:&a Enable"));
/*  74 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&bVersion:&a " + instance.getDescription().getVersion()));
/*  75 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&bAuthor:&a ZenDev"));
/*  76 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&3&m|---------&b&m==========&3&m---------|"));
/*     */     } 
/*     */     
/*  79 */     new UpdateChecker(instance, 97927).getVersion(version -> {
/*     */           if (instance.getDescription().getVersion().equals(version)) {
/*     */             Bukkit.getConsoleSender().sendMessage(Utils.chat("&2[SupperSeason] &7There is not a new update available."));
/*     */           } else {
/*     */             Bukkit.getConsoleSender().sendMessage(Utils.chat("&2[SupperSeason] &7There is a new update available."));
/*     */             Bukkit.getConsoleSender().sendMessage(Utils.chat("&2https://www.spigotmc.org/resources/97927/"));
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void hook() {
/*  93 */     new PAPI_hooker().hook();
/*  94 */     new Vault_hooker().hook();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createFile() {
/* 100 */     File_config.file = new File(getDataFolder(), "config.yml");
/* 101 */     if (!File_config.file.exists()) saveResource("config.yml", true); 
/* 102 */     File configFile = new File(getDataFolder(), "config.yml");
/*     */     try {
/* 104 */       UpdateConfig.update((Plugin)this, "config.yml", configFile, new String[0]);
/* 105 */     } catch (IOException e) {
/* 106 */       e.printStackTrace();
/*     */     } 
/* 108 */     File_config.config = (FileConfiguration)YamlConfiguration.loadConfiguration(File_config.file);
/*     */ 
/*     */     
/* 111 */     File_time.timFile = new File(getDataFolder(), "time.yml");
/* 112 */     if (!File_time.timFile.exists()) saveResource("time.yml", true); 
/* 113 */     File_time.timConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(File_time.timFile);
/*     */ 
/*     */     
/* 116 */     File_effect.efFile = new File(getDataFolder(), "effect.yml");
/* 117 */     if (!File_effect.efFile.exists()) saveResource("effect.yml", true); 
/* 118 */     File_effect.efConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(File_effect.efFile);
/*     */     
/* 120 */     File_rarity.rFile = new File(getDataFolder(), "rarity.yml");
/* 121 */     if (!File_rarity.rFile.exists()) saveResource("rarity.yml", true); 
/* 122 */     File_rarity.rConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(File_rarity.rFile);
/*     */     
/* 124 */     File_fish.fFile = new File(getDataFolder(), "fish.yml");
/* 125 */     if (!File_fish.fFile.exists()) saveResource("fish.yml", true); 
/* 126 */     File_fish.fConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(File_fish.fFile);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerCommand() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerFishing() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerEvent() {
/* 144 */     if (File_config.isBossbarEnable());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (File_config.isInvSystem());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerRunner(int delay, int peirod) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createMechanic() {
/* 163 */     ContestBar.genarate();
/* 164 */     File_time.loadTime();
/* 165 */     TimeBar.timeBar = U_BossBar.createBar(File_time.barName(), "WHITE", "SOLID");
/* 166 */     String style = File_config.mBossbarStyle();
/* 167 */     U_BossBar.setProcess(TimeBar.timeBar, 1.0D);
/* 168 */     U_BossBar.changeStyle(TimeBar.timeBar, style);
/*     */     
/* 170 */     if ((!Utils.alwayNight() && !Utils.alwayDay()) || (Utils.alwayNight() && Utils.alwayDay())) { TimeBar.setMorning(); }
/* 171 */     else if (Utils.alwayNight()) { TimeBar.setMorningAsNight(); }
/* 172 */     else if (Utils.alwayDay()) { TimeBar.setMorning(); }
/*     */     
/* 174 */     if (File_config.isBossbarEnable() && Bukkit.getOnlinePlayers() != null)
/* 175 */       for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/* 176 */         TimeBar.timeBar.addPlayer(p);
/*     */       } 
/* 178 */     Utils.Register();
/* 179 */     if (File_config.isTired());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void reload() {
/* 184 */     Bukkit.getScheduler().cancelTasks(Utils.plugin);
/* 185 */     TimeBar.timeBar.removeAll();
/* 186 */     FishingCT.forceEnd();
/* 187 */     HandlerList.unregisterAll(Utils.plugin);
/* 188 */     Utils.plugin.getPluginLoader().disablePlugin(Utils.plugin);
/* 189 */     Utils.plugin.getPluginLoader().enablePlugin(Utils.plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reload_Mechanic() {
/* 194 */     Bukkit.getScheduler().cancelTasks(Utils.plugin);
/* 195 */     TimeBar.timeBar.removeAll();
/* 196 */     FishingCT.forceEnd();
/* 197 */     HandlerList.unregisterAll(Utils.plugin);
/* 198 */     createMechanic();
/* 199 */     registerEvent();
/* 200 */     registerRunner(0, 20);
/* 201 */     U_BossBar.setProcess(TimeBar.timeBar, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 208 */     Bukkit.getScheduler().cancelTasks(Utils.plugin);
/* 209 */     FishingCT.forceEnd();
/* 210 */     ContestBar.contestBar.removeAll();
/* 211 */     TimeBar.timeBar.removeAll();
/* 212 */     if (File_config.isLogger()) {
/*     */       
/* 214 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&3&m|---------&b&m==========&3&m---------|"));
/* 215 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&bPlugin:&a SuperSeason"));
/* 216 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&bStatus:&c Disable"));
/* 217 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&bVersion:&a " + getDescription().getVersion()));
/* 218 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&bAuthor:&a ZenDev"));
/* 219 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&3&m|---------&b&m==========&3&m---------|"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/SupperSeason.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */