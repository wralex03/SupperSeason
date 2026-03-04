/*    */ package org.zendev.SupperSeason.Fishing.Contest;
/*    */ 
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.zendev.SupperSeason.Files.File_config;
/*    */ import org.zendev.SupperSeason.Utils.U_BossBar;
/*    */ 
/*    */ public class ContestBar
/*    */ {
/*    */   public static BossBar contestBar;
/*    */   public static String title;
/* 11 */   public static double count = 0.0D;
/*    */ 
/*    */   
/*    */   public static void genarate() {
/* 15 */     contestBar = U_BossBar.createBar(File_config.fishingContestName(), File_config.fishingContestColor(), 
/* 16 */         File_config.fishingContestStyle());
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Fishing/Contest/ContestBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */