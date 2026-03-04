/*     */ package org.zendev.SupperSeason.Utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.zendev.SupperSeason.BossBar.TimeBar;
/*     */ import org.zendev.SupperSeason.Files.File_config;
/*     */ import org.zendev.SupperSeason.Files.File_effect;
/*     */ import org.zendev.SupperSeason.Files.File_time;
/*     */ import org.zendev.SupperSeason.SupperSeason;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Bright;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Cool_Water;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Darkness;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Dead_Plant;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Disable_Seeds;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Double_Crops;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Double_Ores;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Freezing;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Hot_Weather;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Icy;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Regenaration;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Sand_Burn;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Slow_Growing;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Sweating;
/*     */ import org.zendev.SupperSeason.Utils.Effects.Wind_Boost;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Utils
/*     */ {
/*  43 */   public static Plugin plugin = Bukkit.getPluginManager().getPlugin("SupperSeason");
/*     */ 
/*     */   
/*     */   public static String chat(String s) {
/*  47 */     return ChatColor.translateAlternateColorCodes('&', s);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean alwayDay() {
/*  52 */     return File_config.isAlwayDay();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean alwayNight() {
/*  57 */     return File_config.isAlwaysNight();
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> color(List<String> start) {
/*  62 */     List<String> toReturn = new ArrayList<>();
/*  63 */     for (String s : start)
/*  64 */       toReturn.add(chat(s)); 
/*  65 */     return toReturn;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean roll(double chance) {
/*  70 */     double randomNum = random(0, 1, 1);
/*  71 */     if (randomNum >= 1.0D - chance) return true; 
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double random(int start, int end, int decimal) {
/*  77 */     int range = end - start;
/*  78 */     double random = Math.random() * range + start;
/*  79 */     int number = 10;
/*  80 */     for (int i = 1; i <= decimal; ) { number *= 10; i++; }
/*  81 */      return Math.round(random * number) / number;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int random(int start, int end) {
/*  86 */     int range = end - start;
/*  87 */     int random = Math.round((float)Math.random() * range) + start;
/*  88 */     return random;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String randomPlayer() {
/*  93 */     if (Bukkit.getOnlinePlayers() != null) {
/*     */       
/*  95 */       List<String> allP = new ArrayList<>();
/*  96 */       for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers())
/*     */       {
/*  98 */         allP.add(p.getName());
/*     */       }
/* 100 */       return allP.get(random(0, allP.size() - 1));
/* 101 */     }  return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void decreaseDurability(Player p) {
/* 107 */     int a = 1;
/* 108 */     ItemStack item = p.getInventory().getItemInMainHand();
/* 109 */     if (SupperSeason.legacy && !SupperSeason.mc_ver.equalsIgnoreCase("1.13")) {
/*     */       
/* 111 */       short maxD = item.getType().getMaxDurability();
/* 112 */       short curD = item.getDurability();
/* 113 */       if (curD + a <= maxD) {
/*     */         
/* 115 */         item.setDurability((short)(curD + a));
/*     */       } else {
/*     */         
/* 118 */         p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
/* 119 */         item.setAmount(0);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 125 */       if (item == null)
/* 126 */         return;  ItemMeta meta = item.getItemMeta();
/* 127 */       Damageable dMeta = (Damageable)meta;
/*     */       
/* 129 */       int maxD = item.getType().getMaxDurability();
/* 130 */       int curD = dMeta.getDamage();
/*     */       
/* 132 */       if (curD + a <= maxD) {
/*     */         
/* 134 */         dMeta.setDamage(curD + a);
/* 135 */         item.setItemMeta((ItemMeta)dMeta);
/*     */       } else {
/*     */         
/* 138 */         p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
/* 139 */         item.setAmount(0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<World> ActiveWorld() {
/* 146 */     List<World> allWorld = Bukkit.getWorlds();
/* 147 */     List<World> toReturn = new ArrayList<>();
/* 148 */     List<World> disableWorld = new ArrayList<>();
/* 149 */     for (String s : (Iterable<String>)File_config.BlackListWorld()) {
/*     */       
/* 151 */       if (Bukkit.getWorld(s) != null) disableWorld.add(Bukkit.getWorld(s)); 
/*     */     } 
/* 153 */     for (World w : allWorld) {
/*     */       
/* 155 */       if (!disableWorld.contains(w)) toReturn.add(w); 
/*     */     } 
/* 157 */     return toReturn;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendMessage(Player p, String message) {
/* 162 */     p.sendMessage(chat(message));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendTitle(Player p, String main, String sub) {
/* 168 */     p.sendTitle(chat(main), chat(sub));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int FortuneLevel(ItemStack item) {
/* 173 */     if (item.getType() == Material.AIR) return 0; 
/* 174 */     if (item.getItemMeta() == null) return 0; 
/* 175 */     ItemMeta meta = item.getItemMeta();
/* 176 */     if (!meta.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) return 0; 
/* 177 */     return meta.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isDay() {
/* 182 */     if ((!alwayNight() && !alwayDay()) || (alwayNight() && alwayDay()))
/*     */     
/* 184 */     { if (TimeBar.count >= 0.0D && TimeBar.count <= (File_config.getDayParts() / 2)) return true;  }
/* 185 */     else { if (alwayDay()) return true; 
/* 186 */       if (alwayNight()) return false;  }
/* 187 */      return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> loadDeadCrop(Season ss) {
/* 192 */     String id = File_time.loadId(ss);
/* 193 */     List<String> toReturn = new ArrayList<>();
/* 194 */     if (File_effect.efConfig.getStringList("dead_plant.type." + id) != null)
/* 195 */       toReturn = File_effect.efConfig.getStringList("dead_plant.type." + id); 
/* 196 */     return toReturn;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> loadDoubleOre(Season ss) {
/* 201 */     String id = File_time.loadId(ss);
/* 202 */     List<String> toReturn = new ArrayList<>();
/* 203 */     if (File_effect.efConfig.getStringList("double_ores.type." + id) != null)
/* 204 */       toReturn = File_effect.efConfig.getStringList("double_ores.type." + id); 
/* 205 */     return toReturn;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> loadSeeds(Season ss) {
/* 210 */     String id = File_time.loadId(ss);
/* 211 */     List<String> toReturn = new ArrayList<>();
/* 212 */     if (File_effect.efConfig.getStringList("disable_seeds.type." + id) != null)
/* 213 */       toReturn = File_effect.efConfig.getStringList("disable_seeds.type." + id); 
/* 214 */     return toReturn;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean wearCloth(Player p) {
/*     */     boolean toReturn = false;
/* 220 */     ItemStack[] equip = new ItemStack[4];
/* 221 */     int count = 0;
/* 222 */     if (p.getInventory().getHelmet() != null) { equip[count] = p.getInventory().getHelmet(); count++; }
/* 223 */      if (p.getInventory().getChestplate() != null) { equip[count] = p.getInventory().getChestplate(); count++; }
/* 224 */      if (p.getInventory().getLeggings() != null) { equip[count] = p.getInventory().getLeggings(); count++; }
/* 225 */      if (p.getInventory().getBoots() != null) { equip[count] = p.getInventory().getBoots(); count++; }
/* 226 */      if (count == 0) {
/*     */       
/* 228 */       toReturn = false;
/*     */     }
/*     */     else {
/*     */       
/* 232 */       for (int i = 0; i < count; i++) {
/*     */         
/* 234 */         String type = equip[i].getType().toString();
/* 235 */         if (type.contains("LEATHER")) {
/*     */           
/* 237 */           toReturn = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 242 */     return toReturn;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean wearFullCloth(Player p) {
/*     */     boolean toReturn = false;
/* 248 */     int count = 0;
/* 249 */     if (p.getInventory().getHelmet() != null) count++; 
/* 250 */     if (p.getInventory().getChestplate() != null) count++; 
/* 251 */     if (p.getInventory().getLeggings() != null) count++; 
/* 252 */     if (p.getInventory().getBoots() != null) count++; 
/* 253 */     if (count == 4) toReturn = true; 
/* 254 */     return toReturn;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean nearFire(Player p) {
/*     */     boolean toReturn = false;
/* 260 */     Location loc = p.getLocation();
/* 261 */     Byte light = loc.getBlock().getLightLevel();
/* 262 */     if (light >= 13)
/*     */     {
/* 264 */       toReturn = true;
/*     */     }
/* 266 */     return toReturn;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean tooHot(Player p) {
/*     */     boolean toReturn = false;
/* 272 */     Location loc = p.getLocation();
/* 273 */     Byte light = loc.getBlock().getLightLevel();
/* 274 */     if (light >= 13)
/*     */     {
/* 276 */       toReturn = true;
/*     */     }
/* 278 */     return toReturn;
/*     */   }
/*     */   
/*     */   public static void Register() {
/*     */     Season[] arrayOfSeason;
/* 283 */     for (int j = (arrayOfSeason = Season.values()).length, i = 0; i < j; ) { Season ss = arrayOfSeason[i];
/*     */       
/* 285 */       List<String> effects = File_config.getSeasonEffects(ss);
/* 286 */       for (String s : effects)
/*     */       { String str1;
/* 288 */         switch ((str1 = s).hashCode()) { case -1704304944: if (!str1.equals("dead_plant")) {
/*     */               continue;
/*     */             }
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
/* 304 */             Dead_Plant.RegEvent(ss);case -1617877633: if (!str1.equals("double_ores")) continue;  Double_Ores.RegEvent(ss);case -1537208940: if (!str1.equals("freezing")) continue;  Freezing.RegRunner(ss);case -1380798726: if (!str1.equals("bright")) continue;  Bright.RegRunner(ss);case -1157747023: if (!str1.equals("slow_growing")) continue;  Slow_Growing.RegEvent(ss);case -961708895: if (!str1.equals("cool_water"))
/*     */               continue;  Cool_Water.RegRunner(ss);case -498943989: if (!str1.equals("disable_seeds"))
/*     */               continue;  Disable_Seeds.RegEvent(ss);case -216832786: if (!str1.equals("sweating"))
/*     */               continue;  Sweating.RegRunner(ss);case 104095: if (!str1.equals("icy"))
/*     */               continue;  Icy.RegEvent(ss);case 1374328309: if (!str1.equals("double_crops"))
/*     */               continue;  Double_Crops.RegEvent(ss);case 1385894060: if (!str1.equals("wind_boost"))
/*     */               continue;  Wind_Boost.RegRunner(ss);case 1644887210: if (!str1.equals("sand_burn"))
/*     */               continue;  Sand_Burn.RegEvent(ss);case 1741803213: if (!str1.equals("darkness"))
/*     */               continue;  Darkness.RegRunner(ss);case 1777723015: if (!str1.equals("regenaration"))
/*     */               continue;  Regenaration.RegRunner(ss);case 1832834562: if (!str1.equals("hot_weather"))
/* 314 */               continue;  Hot_Weather.RegRunner(ss); }  }  i++; }  } public static ItemStack createItem(Material type, int amount, int data) { ItemStack item = new ItemStack(type, amount, (short)data);
/* 315 */     return item; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack createItem(String type, int amount, int data) {
/* 321 */     ItemStack item = new ItemStack(Material.getMaterial(type), amount, (short)data);
/* 322 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String GetTitle(InventoryEvent event) {
/* 327 */     String title = "N/A";
/*     */     
/* 329 */     title = event.getView().getTitle();
/*     */     
/* 331 */     return title;
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */