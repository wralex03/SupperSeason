/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.zendev.SupperSeason.Files.File_effect;
/*    */ import org.zendev.SupperSeason.Files.File_time;
/*    */ import org.zendev.SupperSeason.Utils.Season;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Double_Ores
/*    */ {
/*    */   public static void RegEvent(final Season ss) {
/* 22 */     final double chance = File_effect.efConfig.getDouble("double_ores.chance") / 100.0D;
/* 23 */     Bukkit.getPluginManager().registerEvents(new Listener()
/*    */         {
/*    */           @EventHandler
/*    */           public void onBreakBlock(BlockBreakEvent e)
/*    */           {
/* 28 */             if (File_time.ss == ss) {
/*    */               
/* 30 */               Player p = e.getPlayer();
/* 31 */               List<String> ores = Utils.loadDoubleOre(ss);
/* 32 */               Block bl = e.getBlock();
/* 33 */               String type = bl.getType().toString();
/*    */               
/* 35 */               boolean isPlaced = bl.hasMetadata("PLACED");
/* 36 */               if (isPlaced)
/*    */                 return; 
/* 38 */               if (!ores.contains(type))
/*    */                 return; 
/* 40 */               if (Utils.roll(chance) && Utils.ActiveWorld().contains(p.getWorld()))
/*    */               {
/* 42 */                 for (ItemStack it : (Iterable<ItemStack>)bl.getDrops()) {
/*    */                   
/* 44 */                   p.getInventory().addItem(new ItemStack[] { it });
/* 45 */                   if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
/*    */                     
/* 47 */                     int fortuneLevel = Utils.FortuneLevel(p.getInventory().getItemInMainHand());
/* 48 */                     for (int i = 0; i < fortuneLevel; ) { p.getInventory().addItem(new ItemStack[] { it }); i++; }
/*    */                   
/*    */                   } 
/*    */                 }  } 
/*    */             } 
/*    */           }
/* 54 */         },  Utils.plugin);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Double_Ores.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */