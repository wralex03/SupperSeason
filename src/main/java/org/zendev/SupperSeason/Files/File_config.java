/*     */ package org.zendev.SupperSeason.Files;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.boss.BarColor;
/*     */ import org.bukkit.boss.BarStyle;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.zendev.SupperSeason.Utils.Season;
/*     */ import org.zendev.SupperSeason.Utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class File_config
/*     */ {
/*     */   public static File file;
/*     */   public static FileConfiguration config;
/*     */   
/*     */   public static void save() {
/*     */     try {
/*  24 */       config.save(file);
/*     */     }
/*  26 */     catch (IOException e) {
/*     */       
/*  28 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isLogger() {
/*  36 */     return config.getBoolean("logger");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInvSystem() {
/*  41 */     return config.getBoolean("keep_inventory_system");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isTired() {
/*  46 */     return config.getBoolean("tired");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAlwayDay() {
/*  51 */     return config.getBoolean("always-day");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAlwaysNight() {
/*  56 */     return config.getBoolean("always-night");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBossbarEnable() {
/*  61 */     return config.getBoolean("Bossbar.enable");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canFishing(Season ss) {
/*  66 */     String id = File_time.loadId(ss);
/*  67 */     return config.getBoolean("Season." + id + ".fishing");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isCaughtBroadcast() {
/*  72 */     return config.getBoolean("Fish-caught.broadcast");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String mOnlyPlayer() {
/*  78 */     return Utils.chat(config.getString("Messages.only_player"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mReload() {
/*  83 */     return Utils.chat(config.getString("Messages.reload"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mNoPermission() {
/*  88 */     return Utils.chat(config.getString("Messages.no_permission"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mNewDay() {
/*  93 */     return Utils.chat(config.getString("Messages.new-day"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mAddEnchant() {
/*  98 */     return Utils.chat(config.getString("Messages.add_enchant"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mHandSomeThing() {
/* 103 */     return Utils.chat(config.getString("Messages.hand_something"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mCannotFishing() {
/* 108 */     return Utils.chat(config.getString("Messages.cannot_fishing"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FishFormatName() {
/* 113 */     return Utils.chat(config.getString("Fish-format.name"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FishFormatLength() {
/* 118 */     return Utils.chat(config.getString("Fish-format.length-line"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FishFormatCaught() {
/* 123 */     return Utils.chat(config.getString("Fish-format.caught-line"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mFishCaught() {
/* 128 */     return Utils.chat(config.getString("Fish-caught.message"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSeasonName(Season ss) {
/* 133 */     String id = File_time.loadId(ss);
/* 134 */     return Utils.chat(config.getString("Season." + id + ".name"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> mTired() {
/* 139 */     return config.getStringList("Messages.tired");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> mHelp() {
/* 144 */     return config.getStringList("Messages.season_command.help");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> mSeason() {
/* 149 */     return config.getStringList("Messages.season_command.display");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> mNight() {
/* 154 */     return config.getStringList("Messages.night");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mBossbarColorDay() {
/* 159 */     if (BarColor.valueOf(config.getString("Bossbar.color.day")) == null) {
/*     */       
/* 161 */       System.out.println(Utils.chat("&c" + config.getString("Bossbar.color.day") + "is not a Bossbar Color"));
/* 162 */       return "YELLOW";
/*     */     } 
/* 164 */     return config.getString("Bossbar.color.day");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mBossbarColorNight() {
/* 169 */     if (BarColor.valueOf(config.getString("Bossbar.color.night")) == null) {
/*     */       
/* 171 */       System.out.println(Utils.chat("&c" + config.getString("Bossbar.color.night") + "is not a Bossbar Color"));
/* 172 */       return "PURLE";
/*     */     } 
/* 174 */     return config.getString("Bossbar.color.night");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String mBossbarStyle() {
/* 179 */     if (BarStyle.valueOf(config.getString("Bossbar.style")) == null) {
/*     */       
/* 181 */       System.out.println(Utils.chat("&c" + config.getString("Bossbar.style") + "is not a Bossbar Style"));
/* 182 */       return "SEGMENTED_20";
/*     */     } 
/* 184 */     return config.getString("Bossbar.style");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getSeasonEffects(Season ss) {
/* 189 */     String id = File_time.loadId(ss);
/* 190 */     return config.getStringList("Season." + id + ".effect");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> BlackListWorld() {
/* 195 */     return config.getStringList("Blacklist-World");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> FishFormatLore() {
/* 200 */     List<String> lore = new ArrayList<>();
/* 201 */     lore.add("<length>");
/* 202 */     lore.add("<caught>");
/* 203 */     lore.add("");
/* 204 */     if (config.getStringList("Fish-format.lore") == null)
/*     */     {
/* 206 */       return lore;
/*     */     }
/* 208 */     return config.getStringList("Fish-format.lore");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> cmdNewDay() {
/* 213 */     return config.getStringList("Commands.new-day");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> cmdNight() {
/* 218 */     return config.getStringList("Commands.night");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> cmdSeason(Season ss) {
/* 223 */     String id = File_time.loadId(ss);
/* 224 */     return config.getStringList("Commands." + id + "-come");
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getTimeCycle() {
/* 229 */     if (config.getLong("day-cycle") <= 0L)
/*     */     {
/* 231 */       return 24000L;
/*     */     }
/* 233 */     return config.getLong("day-cycle");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayParts() {
/* 238 */     if (config.getInt("day-parts") <= 0)
/*     */     {
/* 240 */       return 20;
/*     */     }
/* 242 */     return config.getInt("day-parts");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FishShopName() {
/* 247 */     if (config.getString("Fish-shop.gui-name") == null)
/*     */     {
/* 249 */       return Utils.chat("&2&lFish Shop");
/*     */     }
/* 251 */     return Utils.chat(config.getString("Fish-shop.gui-name"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FishShopSold() {
/* 256 */     if (config.getString("Fish-shop.sold") == null)
/*     */     {
/* 258 */       return Utils.chat("&aGained $<amount>");
/*     */     }
/* 260 */     return Utils.chat(config.getString("Fish-shop.sold"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FishShopCloseName() {
/* 265 */     if (config.getString("Fish-shop.close-icon.name") == null)
/*     */     {
/* 267 */       return Utils.chat("&cClick to close");
/*     */     }
/* 269 */     return Utils.chat(config.getString("Fish-shop.close-icon.name"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FishShopSellName() {
/* 274 */     if (config.getString("Fish-shop.sell-icon.name") == null)
/*     */     {
/* 276 */       return Utils.chat("&aClick to sell &e(<amount>$)");
/*     */     }
/* 278 */     return Utils.chat(config.getString("Fish-shop.sell-icon.name"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FishShopCloseID() {
/* 283 */     if (Material.getMaterial(config.getString("Fish-shop.close-icon.id")) == null) {
/*     */       
/* 285 */       System.out.println(Utils.chat("&c" + config.getString("Fish-shop.close-icon.id") + "is not a Material"));
/* 286 */       return "BARRIER";
/*     */     } 
/* 288 */     return config.getString("Fish-shop.close-icon.id");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FishShopSellID() {
/* 293 */     if (Material.getMaterial(config.getString("Fish-shop.sell-icon.id")) == null) {
/*     */       
/* 295 */       System.out.println(Utils.chat("&c" + config.getString("Fish-shop.sell-icon.id") + "is not a Material"));
/* 296 */       return "EMERALD";
/*     */     } 
/* 298 */     return config.getString("Fish-shop.sell-icon.id");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String fishingContestColor() {
/* 304 */     if (BarColor.valueOf(config.getString("Fish-contest.bar.color")) == null) {
/*     */       
/* 306 */       System.out.println(Utils.chat("&c" + config.getString("Fish-contest.bar.color") + "is not a Bossbar Color"));
/* 307 */       return "BLUE";
/*     */     } 
/* 309 */     return config.getString("Fish-contest.bar.color");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String fishingContestStyle() {
/* 314 */     if (BarStyle.valueOf(config.getString("Fish-contest.bar.style")) == null) {
/*     */       
/* 316 */       System.out.println(Utils.chat("&c" + config.getString("Fish-contest.bar.style") + "is not a Bossbar Style"));
/* 317 */       return "SOLID";
/*     */     } 
/* 319 */     return config.getString("Fish-contest.bar.style");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String fishingContestName() {
/* 324 */     return Utils.chat(config.getString("Fish-contest.display"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FCTnotPlayer() {
/* 329 */     return Utils.chat(config.getString("Fish-contest.not-enough-player"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFCTParts() {
/* 334 */     if (config.getInt("Fish-contest.bar.parts") <= 0)
/*     */     {
/* 336 */       return 20;
/*     */     }
/* 338 */     return config.getInt("Fish-contest.bar.parts");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Integer> getFCTHoldon() {
/* 343 */     List<Integer> toSet = new ArrayList<>();
/* 344 */     toSet.add(10);
/* 345 */     toSet.add(20);
/* 346 */     toSet.add(30);
/* 347 */     if (config.getIntegerList("Fish-contest.hold-on") == null)
/*     */     {
/* 349 */       return toSet;
/*     */     }
/* 351 */     return config.getIntegerList("Fish-contest.hold-on");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFCTRare() {
/* 356 */     if (config.getInt("Fish-contest.rare-required") <= 0)
/*     */     {
/* 358 */       return 3;
/*     */     }
/* 360 */     return config.getInt("Fish-contest.rare-required");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFCTPlayer() {
/* 365 */     if (config.getInt("Fish-contest.player-required") <= 0)
/*     */     {
/* 367 */       return 5;
/*     */     }
/* 369 */     return config.getInt("Fish-contest.player-required");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFCTDuration() {
/* 374 */     if (config.getInt("Fish-contest.duration") <= 0)
/*     */     {
/* 376 */       return 48000;
/*     */     }
/* 378 */     return config.getInt("Fish-contest.duration");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FCTstatusOn() {
/* 383 */     return Utils.chat(config.getString("Fish-contest.status.true"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FCTstatusOff() {
/* 388 */     return Utils.chat(config.getString("Fish-contest.status.false"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFCTinfoMSG() {
/* 393 */     List<String> toSet = new ArrayList<>();
/* 394 */     toSet.add("&b&lFISHING CONTEST:");
/* 395 */     toSet.add("&aStatus: <status>");
/* 396 */     toSet.add("&aNext contest: <time> day(s)");
/* 397 */     if (config.getStringList("Fish-contest.info-message") == null)
/*     */     {
/* 399 */       return toSet;
/*     */     }
/* 401 */     return config.getStringList("Fish-contest.info-message");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFCTstartMSG() {
/* 406 */     List<String> toSet = new ArrayList<>();
/* 407 */     toSet.add("&bFishing contest started!");
/* 408 */     toSet.add("&3It last: <time> day(s).");
/* 409 */     toSet.add("&3Catch rarest and longest fish for 1st!");
/* 410 */     toSet.add("&3Let us fishing for &elots of prize!");
/* 411 */     if (config.getStringList("Fish-contest.start-message") == null)
/*     */     {
/* 413 */       return toSet;
/*     */     }
/* 415 */     return config.getStringList("Fish-contest.start-message");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFCTendMSG() {
/* 420 */     List<String> toSet = new ArrayList<>();
/* 421 */     toSet.add("&cTimed up! &bFishing contest is over!");
/* 422 */     toSet.add("&e1st - <1st_player>");
/* 423 */     toSet.add("&e2nd - <2nd_player>");
/* 424 */     toSet.add("&a3rd - <3rd_player>");
/* 425 */     toSet.add("&3The next contest come in: <time> day(s).");
/* 426 */     if (config.getStringList("Fish-contest.end-message") == null)
/*     */     {
/* 428 */       return toSet;
/*     */     }
/* 430 */     return config.getStringList("Fish-contest.end-message");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFCTrw1() {
/* 435 */     List<String> toSet = new ArrayList<>();
/* 436 */     toSet.add("give <player> DIAMOND 16");
/* 437 */     if (config.getStringList("Fish-contest.rewards.first") == null)
/*     */     {
/* 439 */       return toSet;
/*     */     }
/* 441 */     return config.getStringList("Fish-contest.rewards.first");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFCTrw2() {
/* 446 */     List<String> toSet = new ArrayList<>();
/* 447 */     toSet.add("give <player> DIAMOND 8");
/* 448 */     if (config.getStringList("Fish-contest.rewards.second") == null)
/*     */     {
/* 450 */       return toSet;
/*     */     }
/* 452 */     return config.getStringList("Fish-contest.rewards.second");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFCTrw3() {
/* 457 */     List<String> toSet = new ArrayList<>();
/* 458 */     toSet.add("give <player> DIAMOND 4");
/* 459 */     if (config.getStringList("Fish-contest.rewards.third") == null)
/*     */     {
/* 461 */       return toSet;
/*     */     }
/* 463 */     return config.getStringList("Fish-contest.rewards.third");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getFCTrw4() {
/* 468 */     List<String> toSet = new ArrayList<>();
/* 469 */     toSet.add("give <player> DIRT 1");
/* 470 */     if (config.getStringList("Fish-contest.rewards.other") == null)
/*     */     {
/* 472 */       return toSet;
/*     */     }
/* 474 */     return config.getStringList("Fish-contest.rewards.other");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FCTyourRank() {
/* 479 */     if (config.getString("Fish-contest.your-rank") == null)
/*     */     {
/* 481 */       return Utils.chat("&7Your rank: <rank>");
/*     */     }
/* 483 */     return Utils.chat(config.getString("Fish-contest.your-rank"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FCTNotStart() {
/* 488 */     if (config.getString("Fish-contest.not-start") == null)
/*     */     {
/* 490 */       return Utils.chat("&cFishing contest has not started yet!");
/*     */     }
/* 492 */     return Utils.chat(config.getString("Fish-contest.not-start"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String FCTAlreadyStart() {
/* 497 */     if (config.getString("Fish-contest.already-start") == null)
/*     */     {
/* 499 */       return Utils.chat("&cFishing contest has already started!");
/*     */     }
/* 501 */     return Utils.chat(config.getString("Fish-contest.already-start"));
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Files/File_config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */