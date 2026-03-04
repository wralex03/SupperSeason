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
/*    */ public class Darkness
/*    */ {
/*    */   public static void RegRunner(final Season ss) {
/* 16 */     int period = File_effect.efConfig.getInt("darkness.period");
/* 17 */     final int duration = period + 20;
/* 18 */     final double chance = File_effect.efConfig.getDouble("darkness.chance") / 100.0D;
/* 19 */     final String message = File_effect.efConfig.getString("darkness.message");
/* 20 */     Bukkit.getScheduler().runTaskTimer(Utils.plugin, new Runnable()
/*    */         {
/*    */           
/*    */           public void run()
/*    */           {
/* 25 */             if (File_time.ss == ss && Utils.roll(chance) && Bukkit.getOnlinePlayers() != null)
/* 26 */               for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*    */                 
/* 28 */                 Utils.sendTitle(p, message, "");
/* 29 */                 if (!p.hasPotionEffect(PotionEffectType.BLINDNESS) && Utils.ActiveWorld().contains(p.getWorld()))
/* 30 */                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 10)); 
/*    */               }  
/*    */           }
/* 33 */         },  period, period);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Darkness.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */