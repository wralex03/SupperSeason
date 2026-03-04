/*    */ package org.zendev.SupperSeason.Hooker;
/*    */ 
/*    */ import me.clip.placeholderapi.expansion.PlaceholderExpansion;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ 
/*    */ 
/*    */ public class SupperSeason_PlaceholderExpansion
/*    */   extends PlaceholderExpansion
/*    */ {
/*    */   private final SupperSeason plugin;
/*    */   
/*    */   public SupperSeason_PlaceholderExpansion(SupperSeason plugin) {
/* 15 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAuthor() {
/* 20 */     return "zendev";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getIdentifier() {
/* 25 */     return "supperseason";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getVersion() {
/* 30 */     return "1.0.0";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean persist() {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String onRequest(OfflinePlayer player, String params) {
/* 40 */     if (params.equalsIgnoreCase("season")) {
/* 41 */       return File_time.loadDisplay(File_time.ss);
/*    */     }
/*    */     
/* 44 */     if (params.equalsIgnoreCase("day")) {
/* 45 */       return String.valueOf(File_time.Day);
/*    */     }
/*    */     
/* 48 */     if (params.equalsIgnoreCase("month")) {
/* 49 */       return String.valueOf(File_time.Month);
/*    */     }
/*    */     
/* 52 */     if (params.equalsIgnoreCase("year")) {
/* 53 */       return String.valueOf(File_time.Year);
/*    */     }
/*    */     
/* 56 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Hooker/SupperSeason_PlaceholderExpansion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */