/*    */ package org.zendev.SupperSeason.Listeners;
/*    */ 
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ import org.bukkit.metadata.FixedMetadataValue;
/*    */ import org.bukkit.metadata.MetadataValue;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ public class onPlace_listener
/*    */   implements Listener {
/*    */   public onPlace_listener() {
/* 13 */     Utils.plugin.getServer().getPluginManager().registerEvents(this, Utils.plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onPlaceBlock(BlockPlaceEvent e) {
/* 19 */     e.getBlockPlaced().setMetadata("PLACED", (MetadataValue)new FixedMetadataValue(Utils.plugin, true));
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Listeners/onPlace_listener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */