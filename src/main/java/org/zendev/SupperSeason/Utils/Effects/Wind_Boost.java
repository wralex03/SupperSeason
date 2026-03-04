/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ import org.zendev.SupperSeason.Files.File_effect;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ public class Wind_Boost
/*    */ {
/*    */   public static void RegRunner(final Season ss) {
/* 16 */     int period = File_effect.efConfig.getInt("wind_boost.period");
/* 17 */     final int duration = period + 20;
/* 18 */     final double chance = File_effect.efConfig.getDouble("wind_boost.chance") / 100.0D;
/* 19 */     final int tier = File_effect.efConfig.getInt("wind_boost.tier");
/* 20 */     Bukkit.getScheduler().runTaskTimer(Utils.plugin, new Runnable()
/*    */         {
/*    */           
/*    */           public void run()
/*    */           {
/* 25 */             if (File_time.ss == ss && Utils.roll(chance) && Bukkit.getOnlinePlayers() != null)
/* 26 */               for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*    */                 
/* 28 */                 if (!p.hasPotionEffect(PotionEffectType.SPEED) && Utils.isDay() && Utils.ActiveWorld().contains(p.getWorld()))
/* 29 */                   p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, tier)); 
/*    */               }  
/*    */           }
/* 32 */         },  period, period);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Wind_Boost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */