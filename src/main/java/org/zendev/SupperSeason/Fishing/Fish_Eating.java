/*    */ package org.zendev.SupperSeason.Fishing;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.zendev.SupperSeason.Files.File_fish;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ public class Fish_Eating
/*    */   implements Listener
/*    */ {
/*    */   public Fish_Eating() {
/* 20 */     Utils.plugin.getServer().getPluginManager().registerEvents(this, Utils.plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onEatFish(PlayerItemConsumeEvent e) {
/* 26 */     ItemStack item = e.getItem();
/* 27 */     Player p = e.getPlayer();
/* 28 */     if (item.getItemMeta() == null)
/* 29 */       return;  ItemMeta meta = item.getItemMeta();
/* 30 */     if (!meta.hasDisplayName())
/* 31 */       return;  String name = meta.getDisplayName();
/* 32 */     for (String fish : (Iterable<String>)File_fish.allFish()) {
/*    */       
/* 34 */       String display = File_fish.getIconDisplay(fish);
/* 35 */       if (name.equalsIgnoreCase(display)) {
/*    */         
/* 37 */         List<String> cmds = File_fish.getEatTrigger(fish);
/* 38 */         if (cmds.isEmpty())
/* 39 */           return;  for (String cmd : cmds)
/*    */         {
/* 41 */           Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), File_fish.replaceTrigger(p, cmd));
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Fishing/Fish_Eating.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */