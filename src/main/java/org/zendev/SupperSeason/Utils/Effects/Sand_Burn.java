/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ import org.zendev.SupperSeason.Files.File_effect;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ public class Sand_Burn
/*    */ {
/*    */   public static void RegEvent(final Season ss) {
/* 17 */     final double chance = File_effect.efConfig.getDouble("sand_burn.chance") / 100.0D;
/* 18 */     final double damage = File_effect.efConfig.getDouble("sand_burn.damage");
/* 19 */     Bukkit.getPluginManager().registerEvents(new Listener()
/*    */         {
/*    */           @EventHandler
/*    */           public void onMove(PlayerMoveEvent e)
/*    */           {
/* 24 */             if (File_time.ss == ss) {
/*    */               
/* 26 */               Player p = e.getPlayer();
/* 27 */               if (p.getLocation().getBlock().getType().toString().contains("SAND") && Utils.roll(chance) && Utils.ActiveWorld().contains(p.getWorld()))
/*    */               {
/* 29 */                 p.damage(damage);
/*    */               }
/*    */             } 
/*    */           }
/* 33 */         },  Utils.plugin);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Sand_Burn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */