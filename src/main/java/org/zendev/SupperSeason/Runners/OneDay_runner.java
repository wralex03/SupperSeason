/*    */ package org.zendev.SupperSeason.Runners;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.zendev.SupperSeason.BossBar.TimeBar;
/*    */ import org.zendev.SupperSeason.Files.File_config;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Fishing.Contest.FishingCT;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ public class OneDay_runner
/*    */   extends BukkitRunnable {
/*    */   private SupperSeason plugin;
/*    */   
/*    */   public OneDay_runner(SupperSeason plugin, long delay, long period) {
/* 23 */     this.plugin = plugin;
/* 24 */     runTaskTimer((Plugin)plugin, delay, period);
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 29 */     File_time.addDay(1);
/* 30 */     File_time.loadTime();
/* 31 */     if ((!Utils.alwayNight() && !Utils.alwayDay()) || (Utils.alwayNight() && Utils.alwayDay())) { TimeBar.setMorning(); }
/* 32 */     else if (Utils.alwayNight()) { TimeBar.setMorningAsNight(); }
/* 33 */     else if (Utils.alwayDay()) { TimeBar.setMorning(); }
/*    */ 
/*    */     
/* 36 */     Season curSS = null;
/* 37 */     if (File_time.Day == 1 && File_time.Month == 1) { curSS = Season.SPRING; }
/* 38 */     else if (File_time.Day == 1 && File_time.Month == 4) { curSS = Season.SUMMER; }
/* 39 */     else if (File_time.Day == 1 && File_time.Month == 7) { curSS = Season.AUTUMN; }
/* 40 */     else if (File_time.Day == 1 && File_time.Month == 10) { curSS = Season.WINTER; }
/*    */ 
/*    */     
/* 43 */     for (Iterator<Integer> iterator = FishingCT.sortedHoldOn().iterator(); iterator.hasNext(); ) { int i = (Integer)iterator.next();
/*    */       
/* 45 */       if (File_time.Day == i && File_config.canFishing(File_time.ss)) {
/*    */         
/* 47 */         String str = File_config.FCTnotPlayer().replace("<amount>", new StringBuilder(String.valueOf(File_config.getFCTPlayer())).toString());
/* 48 */         if (Bukkit.getOnlinePlayers() != null) {
/*    */           
/* 50 */           if (Bukkit.getOnlinePlayers().size() >= File_config.getFCTPlayer()) { FishingCT.start(); continue; }
/* 51 */            Bukkit.broadcastMessage(Utils.chat(str));
/*    */         } 
/*    */       }  }
/*    */ 
/*    */     
/* 56 */     String message = File_config.mNewDay();
/*    */     
/* 58 */     List<String> Commands = File_config.cmdNewDay();
/* 59 */     for (String cmd : Commands) {
/*    */       
/* 61 */       if (cmd.contains("<player>") && Bukkit.getOnlinePlayers() != null) {
/*    */         
/* 63 */         for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers())
/* 64 */         { if (Utils.ActiveWorld().contains(p.getWorld())) Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmd.replace("<player>", p.getName()));  }  continue;
/* 65 */       }  Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmd);
/*    */     } 
/*    */     
/* 68 */     if (curSS != null) {
/*    */       
/* 70 */       List<String> CommandsSS = File_config.cmdSeason(curSS);
/* 71 */       for (String cmd : CommandsSS) {
/*    */         
/* 73 */         if (cmd.contains("<player>") && Bukkit.getOnlinePlayers() != null) {
/*    */           
/* 75 */           for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers())
/* 76 */           { if (Utils.ActiveWorld().contains(p.getWorld())) Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmd.replace("<player>", p.getName()));  }  continue;
/* 77 */         }  Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmd);
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     if (Bukkit.getOnlinePlayers() != null)
/* 82 */       for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/* 83 */         if (Utils.ActiveWorld().contains(p.getWorld())) Utils.sendTitle(p, message, "&6- " + File_time.Date); 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Runners/OneDay_runner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */