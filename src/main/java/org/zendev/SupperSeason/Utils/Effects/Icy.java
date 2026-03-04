/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerBucketEmptyEvent;
/*    */ import org.bukkit.event.player.PlayerBucketFillEvent;
/*    */ import org.zendev.SupperSeason.Files.File_effect;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ public class Icy
/*    */ {
/*    */   public static void RegEvent(final Season ss) {
/* 18 */     final String message = File_effect.efConfig.getString("icy.message");
/* 19 */     Bukkit.getPluginManager().registerEvents(new Listener()
/*    */         {
/*    */           @EventHandler
/*    */           public void useBucket(PlayerBucketFillEvent e)
/*    */           {
/* 24 */             if (File_time.ss == ss) {
/*    */               
/* 26 */               Player p = e.getPlayer();
/* 27 */               if (e.getBlockClicked().getType().toString().contains("WATER") && Utils.ActiveWorld().contains(e.getBlock().getWorld())) {
/*    */                 
/* 29 */                 Utils.sendMessage(p, message);
/* 30 */                 e.setCancelled(true);
/*    */               } 
/*    */             } 
/*    */           }
/*    */           
/*    */           @EventHandler
/*    */           public void useBucket(PlayerBucketEmptyEvent e) {
/* 37 */             if (File_time.ss == ss) {
/*    */               
/* 39 */               Player p = e.getPlayer();
/* 40 */               if (e.getBlockClicked().getType().toString().contains("WATER") && Utils.ActiveWorld().contains(e.getBlock().getWorld())) {
/*    */                 
/* 42 */                 Utils.sendMessage(p, message);
/* 43 */                 e.setCancelled(true);
/*    */               } 
/*    */             } 
/*    */           }
/* 47 */         },  Utils.plugin);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Icy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */