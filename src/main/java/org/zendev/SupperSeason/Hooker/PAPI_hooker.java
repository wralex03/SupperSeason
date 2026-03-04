/*    */ package org.zendev.SupperSeason.Hooker;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ public class PAPI_hooker
/*    */   implements Hooker
/*    */ {
/*    */   public boolean isHooked;
/*    */   
/*    */   public void hook() {
/* 13 */     if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
/*    */       
/* 15 */       this.isHooked = true;
/* 16 */       new SupperSeason_PlaceholderExpansion(SupperSeason.instance).register();
/* 17 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&2&l[SupperSeason] &ePlaceholderAPI hooked"));
/*    */     } else {
/*    */       
/* 20 */       this.isHooked = false;
/* 21 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&2&l[SupperSeason] &cCould not find &ePlaceholderAPI! &cThis plugin is important. Without this may cause some issue"));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Hooker/PAPI_hooker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */