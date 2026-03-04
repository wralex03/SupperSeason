/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ import org.zendev.SupperSeason.CustomEnchantments.Warm;
/*    */ import org.zendev.SupperSeason.Files.File_effect;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ public class Freezing
/*    */ {
/*    */   public static void RegRunner(final Season ss) {
/* 17 */     final int duration = File_effect.efConfig.getInt("freezing.duration");
/* 18 */     final int tier = File_effect.efConfig.getInt("freezing.tier");
/* 19 */     final String message = File_effect.efConfig.getString("freezing.message");
/* 20 */     Bukkit.getScheduler().runTaskTimer(Utils.plugin, new Runnable()
/*    */         {
/*    */           
/*    */           public void run()
/*    */           {
/* 25 */             if (File_time.ss == ss && Bukkit.getOnlinePlayers() != null)
/* 26 */               for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*    */                 
/* 28 */                 if (!Utils.nearFire(p) && !Utils.wearCloth(p) && !Warm.hasWarm(p) && Utils.ActiveWorld().contains(p.getWorld())) {
/*    */                   
/* 30 */                   Utils.sendTitle(p, message, "");
/* 31 */                   if (!p.hasPotionEffect(PotionEffectType.SLOWNESS))
/* 32 */                     p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, tier));
/*    */                 } 
/*    */               }  
/*    */           }
/* 36 */         },  30L, 30L);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Freezing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */