/*     */ package org.zendev.SupperSeason.Files;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.zendev.SupperSeason.SupperSeason;
/*     */ import org.zendev.SupperSeason.Utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class File_fish
/*     */ {
/*     */   public static File fFile;
/*     */   public static FileConfiguration fConfig;
/*     */   
/*     */   public static void save() {
/*     */     try {
/*  26 */       fConfig.save(fFile);
/*     */     }
/*  28 */     catch (IOException e) {
/*     */       
/*  30 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFishR(String rarity) {
/*  36 */     List<String> fish = allFish();
/*  37 */     List<String> Ffish = allFish();
/*  38 */     for (String s : fish) {
/*     */       
/*  40 */       if (rarity.equalsIgnoreCase(getRarity(s))) Ffish.add(s); 
/*     */     } 
/*  42 */     return Ffish;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFishS(String season) {
/*  47 */     List<String> fish = allFish();
/*  48 */     List<String> Ffish = allFish();
/*  49 */     for (String s : fish) {
/*     */       
/*  51 */       if (season.equalsIgnoreCase(getSeason(s)) || getSeason(s).equalsIgnoreCase("none")) Ffish.add(s); 
/*     */     } 
/*  53 */     return Ffish;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFishSR(String season, String rarity) {
/*  58 */     List<String> fish = allFish();
/*  59 */     List<String> Ffish = new ArrayList<>();
/*  60 */     for (String s : fish) {
/*     */       
/*  62 */       if ((season.equalsIgnoreCase(getSeason(s)) || getSeason(s).equalsIgnoreCase("none")) && 
/*  63 */         rarity.equalsIgnoreCase(getRarity(s)))
/*  64 */         Ffish.add(s); 
/*     */     } 
/*  66 */     return Ffish;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getId(String name) {
/*  71 */     for (String s : allFish()) {
/*     */       
/*  73 */       String rarity = getRarity(s);
/*  74 */       String display = Utils.chat(File_config.FishFormatName()
/*  75 */           .replace("<color>", File_rarity.getColor(rarity))
/*  76 */           .replace("<display>", getDisplay(s)));
/*  77 */       if (name.equalsIgnoreCase(display)) return s; 
/*     */     } 
/*  79 */     return "NULL";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getDisplay(String fish) {
/*  84 */     return fConfig.getString("Fish." + fish + ".display");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getIconDisplay(String fish) {
/*  89 */     String rarity = getRarity(fish);
/*  90 */     String display = Utils.chat(File_config.FishFormatName()
/*  91 */         .replace("<color>", File_rarity.getColor(rarity))
/*  92 */         .replace("<display>", getDisplay(fish)));
/*  93 */     return display;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getRarity(String fish) {
/*  98 */     return fConfig.getString("Fish." + fish + ".rarity");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSeason(String fish) {
/* 103 */     return fConfig.getString("Fish." + fish + ".season");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMaterial(String fish) {
/* 108 */     return fConfig.getString("Fish." + fish + ".icon.id");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getData(String fish) {
/* 113 */     return fConfig.getInt("Fish." + fish + ".icon.data");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getDescription(String fish) {
/* 118 */     return fConfig.getStringList("Fish." + fish + ".icon.description");
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getLength(String fish) {
/* 123 */     double length = 0.0D;
/* 124 */     if (!fConfig.getBoolean("Fish." + fish + ".length.enable")) return length; 
/* 125 */     int min = fConfig.getInt("Fish." + fish + ".length.min");
/* 126 */     int max = fConfig.getInt("Fish." + fish + ".length.max");
/* 127 */     length = Utils.random(min, max, 1);
/* 128 */     return length;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> allFish() {
/* 133 */     List<String> fishes = new ArrayList<>();
/* 134 */     Set<String> fishesS = fConfig.getConfigurationSection("Fish.").getKeys(false);
/* 135 */     for (String s : fishesS)
/*     */     {
/* 137 */       fishes.add(s);
/*     */     }
/* 139 */     return fishes;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack getFishIcon(Player p, String fish) {
/* 144 */     String rarity = getRarity(fish);
/* 145 */     String name = getIconDisplay(fish);
/* 146 */     int data = getData(fish);
/*     */ 
/*     */     
/* 149 */     List<String> lore = File_config.FishFormatLore();
/* 150 */     List<String> toRm = new ArrayList<>();
/* 151 */     List<String> desc = getDescription(fish);
/* 152 */     for (String s : lore) {
/*     */       
/* 154 */       if (s.contains("<length>")) {
/*     */         
/* 156 */         if (!fConfig.getBoolean("Fish." + fish + ".length.enable")) {
/*     */           
/* 158 */           toRm.add(s);
/*     */           
/*     */           continue;
/*     */         } 
/* 162 */         String lengthL = File_config.FishFormatLength();
/* 163 */         String str = s.replace("<length>", String.valueOf(lengthL) + " " + getLength(fish) + " cm");
/* 164 */         lore.set(lore.indexOf(s), str);
/*     */         continue;
/*     */       } 
/* 167 */       if (s.contains("<caught>")) {
/*     */         
/* 169 */         String caughtL = File_config.FishFormatCaught().replace("<color>", File_rarity.getColor(rarity))
/* 170 */           .replace("<player>", p.getName());
/* 171 */         String str = s.replace("<caught>", caughtL);
/* 172 */         lore.set(lore.indexOf(s), str);
/*     */       } 
/*     */     } 
/* 175 */     lore.removeAll(toRm);
/* 176 */     desc = Utils.color(desc);
/* 177 */     lore = Utils.color(lore);
/* 178 */     for (String s : desc)
/*     */     {
/* 180 */       lore.add(s);
/*     */     }
/*     */ 
/*     */     
/* 184 */     ItemStack ICON = Utils.createItem(Material.AIR, 1, 0);
/* 185 */     String material = getMaterial(fish); String str1;
/* 186 */     switch ((str1 = material).hashCode()) { case -1856378258: if (str1.equals("SALMON")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 194 */           if (SupperSeason.legacy) {
/* 195 */             ICON = Utils.createItem("RAW_FISH", 1, 1); break;
/* 196 */           }  ICON = Utils.createItem(material, 1, 0);
/*     */           break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       case -359299510:
/*     */         if (str1.equals("PUFFERFISH"))
/* 204 */         { if (SupperSeason.legacy) {
/* 205 */             ICON = Utils.createItem("RAW_FISH", 1, 3); break;
/* 206 */           }  ICON = Utils.createItem(material, 1, 0); break; } 
/*     */       case 66904: if (str1.equals("COD")) { if (SupperSeason.legacy) { ICON = Utils.createItem("RAW_FISH", 1, 0); break; }  ICON = Utils.createItem(material, 1, 0); break; } 
/*     */       case 78865936:
/* 209 */         if (str1.equals("SHELL")) { if (SupperSeason.legacy) {
/* 210 */             ICON = Utils.createItem("RAW_FISH", 1, 0); break;
/* 211 */           }  ICON = Utils.createItem(Material.NAUTILUS_SHELL, 1, 0); break; } 
/*     */       case 1885275539: if (str1.equals("TROPICAL_FISH")) { if (SupperSeason.legacy) { ICON = Utils.createItem("RAW_FISH", 1, 2); break; }  ICON = Utils.createItem(material, 1, 0); break; } 
/* 213 */       default: ICON = Utils.createItem(material, 1, 0);
/*     */         break; }
/*     */     
/* 216 */     ItemMeta meta = ICON.getItemMeta();
/* 217 */     meta.setDisplayName(Utils.chat(name));
/* 218 */     meta.setLore(lore);
/*     */     
/* 220 */     if (!SupperSeason.legacy)
/*     */     {
/* 222 */       meta.setCustomModelData(data);
/*     */     }
/*     */     
/* 225 */     ICON.setItemMeta(meta);
/* 226 */     return ICON;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getPrice(ItemStack item) {
/* 231 */     double price = 0.0D;
/* 232 */     if (item != null && item.getType() != Material.AIR && item.hasItemMeta()) {
/*     */       
/* 234 */       ItemMeta meta = item.getItemMeta();
/* 235 */       if (meta.hasDisplayName()) {
/*     */         
/* 237 */         String name = meta.getDisplayName();
/* 238 */         String fishID = getId(name);
/* 239 */         String rarity = getRarity(fishID);
/* 240 */         double pricePer = File_rarity.price(rarity);
/* 241 */         double length = getLength(item);
/* 242 */         price = pricePer * length;
/*     */       } 
/*     */     } 
/* 245 */     return price;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getLength(ItemStack icon) {
/* 250 */     double length = 0.0D;
/* 251 */     int vt = -1;
/* 252 */     List<String> format = File_config.FishFormatLore();
/* 253 */     if (icon.getItemMeta() == null) return 0.0D; 
/* 254 */     List<String> lore = icon.getItemMeta().getLore();
/* 255 */     if (lore == null) return 0.0D; 
/* 256 */     for (String s : format) {
/*     */       
/* 258 */       if (s.contains("<length>")) vt = format.indexOf(s); 
/*     */     } 
/* 260 */     if (vt == -1) return 0.0D; 
/* 261 */     String lengthDb = "0";
/* 262 */     String line = lore.get(vt);
/* 263 */     if (Double.parseDouble(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" "))) != 0.0D) lengthDb = line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" ")); 
/* 264 */     length = Double.parseDouble(lengthDb);
/*     */     
/* 266 */     return length;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getCaughtTrigger(String fish) {
/* 271 */     return fConfig.getStringList("Fish." + fish + ".trigger.caught");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getEatTrigger(String fish) {
/* 276 */     return fConfig.getStringList("Fish." + fish + ".trigger.eat");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String replaceTrigger(Player p, String cmd) {
/* 281 */     String cmdFC = cmd;
/* 282 */     while (cmdFC.contains("%ird")) {
/*     */       
/* 284 */       String in4 = cmd.substring(cmd.indexOf("%ird"), cmd.indexOf(" ", cmd.indexOf("%ird")));
/* 285 */       String value = cmd.substring(cmd.indexOf("%ird") + 4, cmd.indexOf(" ", cmd.indexOf("%ird")));
/* 286 */       int Fvalue = Integer.parseInt(value);
/* 287 */       int toRl = Utils.random(0, Fvalue);
/* 288 */       cmd = cmd.replace(in4, new StringBuilder(String.valueOf(toRl)).toString());
/* 289 */       cmdFC = cmdFC.replace(in4, "");
/*     */     } 
/* 291 */     while (cmdFC.contains("%drd")) {
/*     */       
/* 293 */       String in4 = cmd.substring(cmd.indexOf("%drd"), cmd.indexOf(" ", cmd.indexOf("%drd")));
/* 294 */       String value = cmd.substring(cmd.indexOf("%drd") + 4, cmd.indexOf(" ", cmd.indexOf("%drd")));
/* 295 */       int Fvalue = Integer.parseInt(value);
/* 296 */       double toRl = Utils.random(0, Fvalue, 1);
/* 297 */       cmd = cmd.replace(in4, new StringBuilder(String.valueOf(toRl)).toString());
/* 298 */       cmdFC = cmdFC.replace(in4, "");
/*     */     } 
/* 300 */     cmd = cmd.replace("%rp", Utils.randomPlayer());
/* 301 */     String toReturn = cmd.replace("%p", p.getName());
/* 302 */     return toReturn;
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Files/File_fish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */