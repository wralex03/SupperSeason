/*    */ package org.zendev.SupperSeason.Utils;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.boss.BarColor;
/*    */ import org.bukkit.boss.BarFlag;
/*    */ import org.bukkit.boss.BarStyle;
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class U_BossBar
/*    */ {
/*    */   public static BossBar createBar(String title, String color, String style) {
/* 14 */     BossBar toReturn = Bukkit.createBossBar(Utils.chat(title), BarColor.valueOf(color), BarStyle.valueOf(style), new BarFlag[0]);
/* 15 */     toReturn.setVisible(true);
/* 16 */     return toReturn;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setTitle(BossBar bar, String str) {
/* 21 */     bar.setTitle(Utils.chat(str));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void changeStyle(BossBar bar, String style) {
/* 26 */     bar.setStyle(BarStyle.valueOf(style));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void changeColor(BossBar bar, String color) {
/* 31 */     bar.setColor(BarColor.valueOf(color));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void addFlag(BossBar bar, String flag) {
/* 36 */     bar.addFlag(BarFlag.valueOf(flag));
/*    */   }
/*    */ 
/*    */   
/*    */   public static void addPlayer(BossBar bar, Player p) {
/* 41 */     bar.addPlayer(p);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void removePlayer(BossBar bar, Player p) {
/* 46 */     bar.removePlayer(p);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setProcess(BossBar bar, double process) {
/* 51 */     bar.setProgress(process);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/U_BossBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */