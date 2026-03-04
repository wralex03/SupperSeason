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
/*    */ public class Cool_Water
/*    */ {
/*    */   public static void RegRunner(final Season ss) {
/* 16 */     final int duration = File_effect.efConfig.getInt("cool_water.duration");
/* 17 */     final int tier = File_effect.efConfig.getInt("cool_water.tier");
/* 18 */     Bukkit.getScheduler().runTaskTimer(Utils.plugin, new Runnable()
/*    */         {
/*    */           
/*    */           public void run()
/*    */           {
/* 23 */             if (File_time.ss == ss && Bukkit.getOnlinePlayers() != null)
/* 24 */               for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*    */                 
/* 26 */                 if (p.getLocation().getBlock().getType().toString().contains("WATER") && 
/* 27 */                   !p.hasPotionEffect(PotionEffectType.SLOW) && Utils.ActiveWorld().contains(p.getWorld()))
/* 28 */                   p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, tier)); 
/*    */               }  
/*    */           }
/* 31 */         },  20L, 0L);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Cool_Water.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */