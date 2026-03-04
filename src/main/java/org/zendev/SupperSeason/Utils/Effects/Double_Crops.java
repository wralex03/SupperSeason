/*    */ package org.zendev.SupperSeason.Utils.Effects;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
         import org.bukkit.block.data.Ageable;
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
/*    */ public class Double_Crops
/*    */ {
/*    */   public static void RegEvent(final Season ss) {
/* 23 */     final double chance = File_effect.efConfig.getDouble("double_crops.chance." + File_time.loadId(ss)) / 100.0D;
/* 24 */     Bukkit.getPluginManager().registerEvents(new Listener()
/*    */         {
/*    */           @EventHandler
/*    */           public void onBreakBlock(BlockBreakEvent e)
/*    */           {
/* 29 */             if (File_time.ss == ss) {
/*    */               
/* 31 */               Player p = e.getPlayer();
/* 32 */               Block bl = e.getBlock();
/* 33 */               String type = bl.getType().toString();
/*    */               
/* 36 */               if (type.equals("MELON") || type.equals("HOPPER") ||
/* 37 */                 type.equals("SUGAR_CANE") || type.equals("PUMPKIN") || type.equals("BAMBOO")) {
/*    */                 
/* 39 */                 boolean isPlaced = bl.hasMetadata("PLACED");
/* 40 */                 if (isPlaced) {
/*    */                   return;
/*    */                 }
/*    */               } else {
/* 44 */                 if (!(bl.getBlockData() instanceof Ageable)) return;
                         Ageable ageable = (Ageable) bl.getBlockData();
                         if (ageable.getAge() < ageable.getMaximumAge()) return;
/*    */               } 
/* 49 */               if (Utils.roll(chance) && Utils.ActiveWorld().contains(p.getWorld()))
/*    */               {
/* 51 */                 for (ItemStack it : (Iterable<ItemStack>)bl.getDrops()) {
/*    */                   
/* 53 */                   p.getInventory().addItem(new ItemStack[] { it });
/* 54 */                   if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
/*    */                     
/* 56 */                     int fortuneLevel = Utils.FortuneLevel(p.getInventory().getItemInMainHand());
/* 57 */                     for (int i = 0; i < fortuneLevel; ) { p.getInventory().addItem(new ItemStack[] { it }); i++; }
/*    */                   
/*    */                   } 
/*    */                 }  } 
/*    */             } 
/*    */           }
/* 63 */         },  Utils.plugin);
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Effects/Double_Crops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */