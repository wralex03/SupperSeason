/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockGrowEvent;
/*    */ import org.bukkit.event.world.StructureGrowEvent;
/*    */ import org.zendev.SupperSeason.Files.File_effect;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ public class Slow_Growing
/*    */ {
/*    */   public static void RegEvent(final Season ss) {
/* 17 */     final double chance = File_effect.efConfig.getDouble("slow_growing.chance." + File_time.loadId(ss)) / 100.0D;
/* 18 */     Bukkit.getPluginManager().registerEvents(new Listener()
/*    */         {
/*    */           @EventHandler
/*    */           public void onGrow(BlockGrowEvent e)
/*    */           {
/* 23 */             if (File_time.ss == ss && Utils.roll(chance) && Utils.ActiveWorld().contains(e.getBlock().getWorld()))
/*    */             {
/* 25 */               e.setCancelled(true);
/*    */             }
/*    */           }
/*    */ 
/*    */           
/*    */           @EventHandler
/*    */           public void onGrow(StructureGrowEvent e) {
/* 32 */             if (File_time.ss == ss && Utils.roll(chance) && Utils.ActiveWorld().contains(e.getLocation().getWorld()))
/*    */             {
/* 34 */               e.setCancelled(true);
/*    */             }
/*    */           }
/* 37 */         },  Utils.plugin);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Slow_Growing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */