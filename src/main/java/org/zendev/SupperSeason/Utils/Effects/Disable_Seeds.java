/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ import org.zendev.SupperSeason.Files.File_effect;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Disable_Seeds
/*    */ {
/*    */   public static void RegEvent(final Season ss) {
/* 20 */     final String message = File_effect.efConfig.getString("disable_seeds.message").replace("<season>", ss.name());
/* 21 */     Bukkit.getPluginManager().registerEvents(new Listener()
/*    */         {
/*    */           @EventHandler
/*    */           public void onBreakBlock(BlockPlaceEvent e)
/*    */           {
/* 26 */             if (File_time.ss == ss) {
/*    */               
/* 28 */               Player p = e.getPlayer();
/* 29 */               List<String> seeds = Utils.loadSeeds(ss);
/* 30 */               Block bl = e.getBlockPlaced();
/* 31 */               if (seeds.contains(bl.getType().toString()) && Utils.ActiveWorld().contains(p.getWorld())) {
/*    */                 
/* 33 */                 Utils.sendMessage(p, message);
/* 34 */                 e.setCancelled(true);
/*    */               } 
/*    */             } 
/*    */           }
/* 38 */         },  Utils.plugin);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Disable_Seeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */