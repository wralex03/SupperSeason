/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.event.block.BlockGrowEvent;
/*    */ import org.bukkit.event.world.StructureGrowEvent;
/*    */ import org.zendev.SupperSeason.Files.File_effect;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Dead_Plant
/*    */ {
/*    */   public static void RegEvent(final Season ss) {
/* 22 */     final double chance = File_effect.efConfig.getDouble("dead_plant.chance." + File_time.loadId(ss)) / 100.0D;
/* 23 */     final List<String> list = Utils.loadDeadCrop(ss);
/* 24 */     Bukkit.getPluginManager().registerEvents(new Listener()
/*    */         {
/*    */           @EventHandler
/*    */           public void onGrow(BlockGrowEvent e)
/*    */           {
/* 29 */             if (File_time.ss == ss && Utils.roll(chance) && list.contains(e.getBlock().getType().toString()) && Utils.ActiveWorld().contains(e.getBlock().getWorld())) {
/*    */               
/* 31 */               e.getBlock().setType(Material.AIR);
/* 32 */               e.setCancelled(true);
/*    */             } 
/*    */           }
/*    */ 
/*    */           
/*    */           @EventHandler
/*    */           public void onGrow(StructureGrowEvent e) {
/* 39 */             if (File_time.ss == ss && Utils.roll(chance) && list.contains("TREE") && Utils.ActiveWorld().contains(e.getLocation().getWorld())) {
/*    */               
/* 41 */               for (BlockState bl : (Iterable<BlockState>)e.getBlocks())
/*    */               {
/* 43 */                 bl.setType(Material.AIR);
/*    */               }
/* 45 */               e.setCancelled(true);
/*    */             } 
/*    */           }
/*    */ 
/*    */           
/*    */           @EventHandler
/*    */           public void onBreakBlock(BlockBreakEvent e) {
/* 52 */             if (File_time.ss == ss)
/*    */             {
/* 54 */               if (Utils.roll(chance) && list.contains(e.getBlock().getType().toString()))
/*    */               {
/* 56 */                 e.setDropItems(false);
/*    */               }
/*    */             }
/*    */           }
/* 60 */         },  Utils.plugin);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Dead_Plant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */