/*    */ package org.zendev.SupperSeason.Runners;
/*    */ 
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.zendev.SupperSeason.BossBar.TimeBar;
/*    */ import org.zendev.SupperSeason.Files.File_config;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ public class BossBarProcess_runner
/*    */   extends BukkitRunnable
/*    */ {
/*    */   private SupperSeason plugin;
/*    */   
/*    */   public BossBarProcess_runner(SupperSeason plugin, long delay, long period) {
/* 17 */     this.plugin = plugin;
/* 18 */     runTaskTimer((Plugin)plugin, delay, period);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 24 */     if (TimeBar.timeBar.getProgress() <= 1.0D && TimeBar.timeBar.getProgress() > 0.0D) {
/*    */       
/* 26 */       double percent = 1.0D / File_config.getDayParts();
/* 27 */       double time = (24000 / File_config.getDayParts());
/* 28 */       if (TimeBar.count > File_config.getDayParts()) TimeBar.count = 0.0D;
/*    */       
/* 30 */       TimeBar.timeBar.setProgress((File_config.getDayParts() - TimeBar.count) * percent);
/* 31 */       for (World w : (Iterable<World>)Utils.ActiveWorld()) {
/* 32 */         if ((!Utils.alwayNight() && !Utils.alwayDay()) || (Utils.alwayNight() && Utils.alwayDay())) { w.setFullTime((int)(TimeBar.count * time)); continue; }
/* 33 */          if (Utils.alwayNight()) { w.setFullTime(18000L); continue; }
/* 34 */          if (Utils.alwayDay()) w.setFullTime(6000L); 
/*    */       } 
/* 36 */       TimeBar.count++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Runners/BossBarProcess_runner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */