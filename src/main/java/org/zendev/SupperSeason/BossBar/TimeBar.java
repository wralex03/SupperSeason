/*    */ package org.zendev.SupperSeason.BossBar;
/*    */ 
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.zendev.SupperSeason.Files.File_config;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.U_BossBar;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ public class TimeBar
/*    */ {
/*    */   public static BossBar timeBar;
/*    */   public static String title;
/* 14 */   public static double count = 0.0D;
/*    */ 
/*    */   
/*    */   public static void setMorning() {
/* 18 */     count = 0.0D;
/* 19 */     U_BossBar.setProcess(timeBar, 1.0D);
/* 20 */     File_time.loadTime();
/* 21 */     String color = File_config.mBossbarColorDay();
/* 22 */     U_BossBar.setTitle(timeBar, File_time.barName());
/* 23 */     U_BossBar.changeColor(timeBar, color);
/* 24 */     for (World w : (Iterable<World>)Utils.ActiveWorld()) w.setFullTime(0L);
/*    */   
/*    */   }
/*    */   
/*    */   public static void setMorningAsNight() {
/* 29 */     count = 0.0D;
/* 30 */     U_BossBar.setProcess(timeBar, 1.0D);
/* 31 */     File_time.loadTime();
/* 32 */     String color = File_config.mBossbarColorNight();
/* 33 */     U_BossBar.setTitle(timeBar, File_time.barName());
/* 34 */     U_BossBar.changeColor(timeBar, color);
/* 35 */     for (World w : (Iterable<World>)Utils.ActiveWorld()) w.setFullTime(12000L);
/*    */   
/*    */   }
/*    */   
/*    */   public static void setNight() {
/* 40 */     count = (File_config.getDayParts() / 2);
/* 41 */     U_BossBar.setProcess(timeBar, 0.5D);
/* 42 */     String color = File_config.mBossbarColorNight();
/* 43 */     U_BossBar.setTitle(timeBar, File_time.barName());
/* 44 */     U_BossBar.changeColor(timeBar, color);
/* 45 */     for (World w : (Iterable<World>)Utils.ActiveWorld()) w.setFullTime(12000L);
/*    */   
/*    */   }
/*    */   
/*    */   public static void setNightAsMorning() {
/* 50 */     count = (File_config.getDayParts() / 2);
/* 51 */     U_BossBar.setProcess(timeBar, 0.5D);
/* 52 */     String color = File_config.mBossbarColorDay();
/* 53 */     U_BossBar.setTitle(timeBar, File_time.barName());
/* 54 */     U_BossBar.changeColor(timeBar, color);
/* 55 */     for (World w : (Iterable<World>)Utils.ActiveWorld()) w.setFullTime(0L); 
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/BossBar/TimeBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */