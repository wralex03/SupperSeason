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
/*    */ public class Sweating
/*    */ {
/*    */   public static void RegRunner(final Season ss) {
/* 16 */     final int duration = File_effect.efConfig.getInt("sweating.duration");
/* 17 */     final int tier = File_effect.efConfig.getInt("sweating.tier");
/* 18 */     Bukkit.getScheduler().runTaskTimer(Utils.plugin, new Runnable()
/*    */         {
/*    */           
/*    */           public void run()
/*    */           {
/* 23 */             if (File_time.ss == ss && Bukkit.getOnlinePlayers() != null)
/* 24 */               for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*    */                 
/* 26 */                 if (Utils.wearFullCloth(p) && !p.hasPotionEffect(PotionEffectType.WEAKNESS) && Utils.ActiveWorld().contains(p.getWorld()))
/* 27 */                   p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, duration, tier)); 
/*    */               }  
/*    */           }
/* 30 */         },  20L, 0L);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Sweating.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */