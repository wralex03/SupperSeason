/*     */ package org.zendev.SupperSeason.Fishing.Contest;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.zendev.SupperSeason.BossBar.TimeBar;
/*     */ import org.zendev.SupperSeason.Files.File_config;
/*     */ import org.zendev.SupperSeason.Files.File_rarity;
/*     */ import org.zendev.SupperSeason.Files.File_time;
/*     */ import org.zendev.SupperSeason.SupperSeason;
/*     */ import org.zendev.SupperSeason.Utils.U_BossBar;
/*     */ import org.zendev.SupperSeason.Utils.Utils;
/*     */ 
/*     */ public class FishingCT {
/*  21 */   public static Map<Player, Double> competer = new HashMap<>();
/*  22 */   public static Map<Integer, Player> ranking = new HashMap<>();
/*  23 */   public static Player top1 = null; public static Player top2 = null; public static Player top3 = null;
/*  24 */   public static String top1Name = ""; public static String top2Name = ""; public static String top3Name = "";
/*     */   
/*     */   public static boolean inCompete = false;
/*     */   
/*     */   private static void setTop1(Player p) {
/*  29 */     top1 = p;
/*  30 */     top1Name = p.getName();
/*     */   }
/*     */   
/*     */   private static void setTop2(Player p) {
/*  34 */     top2 = p;
/*  35 */     top2Name = p.getName();
/*     */   }
/*     */   
/*     */   private static void setTop3(Player p) {
/*  39 */     top3 = p;
/*  40 */     top3Name = p.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> info() {
/*  46 */     List<String> toReturn = new ArrayList<>();
/*  47 */     String toReplaceStatus = "";
/*  48 */     if (inCompete) { toReplaceStatus = File_config.FCTstatusOn(); }
/*  49 */     else { toReplaceStatus = File_config.FCTstatusOff(); }
/*  50 */      for (String s : (Iterable<String>)File_config.getFCTinfoMSG())
/*     */     {
/*  52 */       toReturn.add(s.replace("<time>", new StringBuilder(String.valueOf(timeLeftToNext())).toString()).replace("<status>", toReplaceStatus));
/*     */     }
/*  54 */     return Utils.color(toReturn);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void joinContest(Player p, double length) {
/*  59 */     if (!competer.containsKey(p)) {
/*     */       
/*  61 */       competer.put(p, length);
/*  62 */       sortedRanking();
/*  63 */       p.sendMessage(Utils.chat(File_config.FCTyourRank()
/*  64 */             .replace("<rank>", new StringBuilder(String.valueOf(getRank(p))).toString())));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  69 */     if ((Double)competer.get(p) < length) {
/*     */       
/*  71 */       competer.put(p, length);
/*  72 */       sortedRanking();
/*  73 */       p.sendMessage(Utils.chat(File_config.FCTyourRank()
/*  74 */             .replace("<rank>", new StringBuilder(String.valueOf(getRank(p))).toString())));
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int timeLeftToNext() {
/*  82 */     int curDay = File_time.Day;
/*  83 */     List<Integer> listhold = sortedHoldOn();
/*  84 */     for (int i = curDay; i <= 30; i++) {
/*     */       
/*  86 */       if (listhold.contains(i)) return i - curDay; 
/*     */     } 
/*  88 */     return 30 - curDay + (Integer)listhold.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sortedRanking() {
/*  93 */     int count = 0;
/*  94 */     List<Player> playersCompete = new ArrayList<>();
/*  95 */     for (Player p : competer.keySet()) playersCompete.add(p);
/*     */     
/*  97 */     double curMax = 1000000.0D;
/*  98 */     double Max = 0.0D;
/*  99 */     for (Player p : competer.keySet()) {
/*     */       
/* 101 */       int loc = -1;
/* 102 */       for (Player p1 : competer.keySet()) {
/*     */         
/* 104 */         if ((Double)competer.get(p1) >= Max && (Double)competer.get(p1) < curMax) {
/*     */           
/* 106 */           Max = (Double)competer.get(p1);
/* 107 */           loc = playersCompete.indexOf(p1);
/*     */         } 
/*     */       } 
/* 110 */       curMax = Max;
/* 111 */       Max = 0.0D;
/* 112 */       if (loc >= 0) ranking.put(count, playersCompete.get(loc)); 
/* 113 */       count++;
/*     */     } 
/* 115 */     for (Iterator<Integer> iterator = ranking.keySet().iterator(); iterator.hasNext(); ) { int i = (Integer)iterator.next(); System.out.println(String.valueOf(i) + " " + ((Player)ranking.get(i)).getName()); }
/* 116 */      if (ranking.get(0) != null) setTop1(ranking.get(0)); 
/* 117 */     if (ranking.get(1) != null) setTop2(ranking.get(1)); 
/* 118 */     if (ranking.get(2) != null) setTop3(ranking.get(0));
/*     */   
/*     */   }
/*     */   
/*     */   public static int getRank(Player p) {
/* 123 */     for (Iterator<Integer> iterator = ranking.keySet().iterator(); iterator.hasNext(); ) { int i = (Integer)iterator.next();
/*     */       
/* 125 */       if (ranking.get(i) == p) return i + 1;  }
/*     */     
/* 127 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendRank(CommandSender sender) {
/* 132 */     sender.sendMessage(Utils.chat(String.valueOf(File_config.fishingContestName()) + "- Top 10"));
/* 133 */     for (int i = 1; i <= 10; i++)
/*     */     {
/* 135 */       sender.sendMessage(Utils.chat("&7- &e" + i + '\001' + " | &7" + ((Player)ranking.get(i - 1)).getName()).replace("null", "#"));
/*     */     }
/* 137 */     if (sender instanceof Player) sender.sendMessage(Utils.chat(File_config.FCTyourRank()
/* 138 */             .replace("<rank>", new StringBuilder(String.valueOf(getRank((Player)sender))).toString())));
/*     */   
/*     */   }
/*     */   
/*     */   public static List<Integer> sortedHoldOn() {
/* 143 */     List<Integer> toSort = new ArrayList<>();
/* 144 */     List<Integer> original = File_config.getFCTHoldon();
/*     */     
/* 146 */     int curMin = 0;
/* 147 */     int Min = 101;
/* 148 */     for (Iterator<Integer> iterator = original.iterator(); iterator.hasNext(); ) { int j = (Integer)iterator.next();
/*     */       
/* 150 */       for (Iterator<Integer> iterator1 = original.iterator(); iterator1.hasNext(); ) { int i = (Integer)iterator1.next(); if (i < Min && i > curMin) Min = i;  }
/* 151 */        curMin = Min;
/* 152 */       Min = 101;
/* 153 */       toSort.add(curMin); }
/*     */ 
/*     */     
/* 156 */     return toSort;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> joinableRarity() {
/* 161 */     List<String> toJoin = new ArrayList<>();
/* 162 */     List<String> original = File_rarity.sortedRarity();
/* 163 */     for (int i = File_config.getFCTRare() - 2; i <= original.size() - 1; i++)
/*     */     {
/* 165 */       toJoin.add(original.get(i));
/*     */     }
/* 167 */     return toJoin;
/*     */   }
/*     */   
/* 170 */   private static double percent = 1.0D / File_config.getFCTParts();
/* 171 */   private static Runnable runner = new Runnable()
/*     */     {
/*     */       
/*     */       public void run()
/*     */       {
/* 176 */         if (ContestBar.count > File_config.getFCTParts())
/* 177 */           return;  if (ContestBar.contestBar.getProgress() <= 1.0D && ContestBar.contestBar.getProgress() > 0.0D) {
/*     */           
/* 179 */           ContestBar.contestBar.setProgress((File_config.getFCTParts() - TimeBar.count) * FishingCT.percent);
/* 180 */           ContestBar.count++;
/*     */         }
/* 182 */         else if (ContestBar.contestBar.getProgress() == 0.0D) {
/*     */           
/* 184 */           FishingCT.end();
/*     */         } 
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public static void reward() {
/* 191 */     ContestBar.count = 0.0D;
/* 192 */     if (top1 != null) {
/* 193 */       for (String s : (Iterable<String>)File_config.getFCTrw1())
/* 194 */         Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), s.replace("<player>", top1Name)); 
/*     */     } else {
/*     */       return;
/* 197 */     }  if (top2 != null) {
/* 198 */       for (String s : (Iterable<String>)File_config.getFCTrw2())
/* 199 */         Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), s.replace("<player>", top2Name)); 
/*     */     } else {
/*     */       return;
/* 202 */     }  if (top3 != null) {
/* 203 */       for (String s : (Iterable<String>)File_config.getFCTrw3())
/* 204 */         Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), s.replace("<player>", top3Name)); 
/*     */     } else {
/*     */       return;
/* 207 */     }  List<Player> players = new ArrayList<>();
/* 208 */     for (Player p : competer.keySet()) players.add(p); 
/* 209 */     players.remove(top1);
/* 210 */     players.remove(top2);
/* 211 */     players.remove(top3);
/* 212 */     if (players.isEmpty())
/* 213 */       return;  for (Player p : players) {
/*     */       
/* 215 */       for (String s : (Iterable<String>)File_config.getFCTrw4()) {
/* 216 */         Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), s.replace("<player>", p.getName()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void start() {
/* 222 */     inCompete = true;
/* 223 */     for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) U_BossBar.addPlayer(ContestBar.contestBar, p); 
/* 224 */     for (String s : (Iterable<String>)Utils.color(File_config.getFCTstartMSG())) {
/*     */       
/* 226 */       String message = s.replace("<time>", new StringBuilder(String.valueOf(File_config.getFCTDuration() / 20)).toString());
/* 227 */       Bukkit.broadcastMessage(Utils.chat(message));
/*     */     } 
/* 229 */     Bukkit.getScheduler().runTaskTimer((Plugin)SupperSeason.instance, runner, (
/* 230 */         File_config.getFCTDuration() / File_config.getFCTParts()), (File_config.getFCTDuration() / File_config.getFCTParts()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void end() {
/* 235 */     for (String s : (Iterable<String>)Utils.color(File_config.getFCTendMSG())) {
/*     */       
/* 237 */       String message = s.replace("<1st_player>", top1Name).replace("<time>", new StringBuilder(String.valueOf(timeLeftToNext())).toString())
/* 238 */         .replace("<2nd_player>", top2Name).replace("<3rd_player>", top3Name);
/* 239 */       Bukkit.broadcastMessage(Utils.chat(message));
/*     */     } 
/* 241 */     reward();
/*     */     
/* 243 */     for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) U_BossBar.removePlayer(ContestBar.contestBar, p);
/*     */     
/* 245 */     competer = new HashMap<>();
/* 246 */     ranking = new HashMap<>();
/* 247 */     top1 = null; top1Name = "#";
/* 248 */     top2 = null; top2Name = "#";
/* 249 */     top3 = null; top3Name = "#";
/* 250 */     inCompete = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void forceEnd() {
/* 255 */     for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) U_BossBar.removePlayer(ContestBar.contestBar, p); 
/* 256 */     competer = new HashMap<>();
/* 257 */     ranking = new HashMap<>();
/* 258 */     top1 = null; top1Name = "#";
/* 259 */     top2 = null; top2Name = "#";
/* 260 */     top3 = null; top3Name = "#";
/* 261 */     inCompete = false;
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Fishing/Contest/FishingCT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */