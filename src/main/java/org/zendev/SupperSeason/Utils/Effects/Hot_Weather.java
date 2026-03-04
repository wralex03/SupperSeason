/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.zendev.SupperSeason.CustomEnchantments.Cool;
/*    */ import org.zendev.SupperSeason.Files.File_effect;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ public class Hot_Weather
/*    */ {
/*    */   public static void RegRunner(final Season ss) {
/* 15 */     final int duration = File_effect.efConfig.getInt("hot_weather.duration");
/* 16 */     final String message = File_effect.efConfig.getString("hot_weather.message");
/* 17 */     Bukkit.getScheduler().runTaskTimer(Utils.plugin, new Runnable()
/*    */         {
/*    */           
/*    */           public void run()
/*    */           {
/* 22 */             if (File_time.ss == ss && Bukkit.getOnlinePlayers() != null)
/* 23 */               for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*    */                 
/* 25 */                 if (Utils.nearFire(p) && !Utils.wearCloth(p) && !Cool.hasCool(p) && Utils.ActiveWorld().contains(p.getWorld())) {
/*    */                   
/* 27 */                   Utils.sendTitle(p, message, "");
/* 28 */                   p.setFireTicks(duration);
/*    */                 } 
/*    */               }  
/*    */           }
/* 32 */         },  30L, 30L);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Hot_Weather.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */