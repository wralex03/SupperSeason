/*    */ package org.zendev.SupperSeason.Runners;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.zendev.SupperSeason.CustomEnchantments.Cool;
/*    */ import org.zendev.SupperSeason.CustomEnchantments.CustomEnchantments;
/*    */ import org.zendev.SupperSeason.CustomEnchantments.Warm;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ 
/*    */ public class check_Enchant
/*    */   extends BukkitRunnable
/*    */ {
/*    */   private SupperSeason plugin;
/*    */   
/*    */   public check_Enchant(SupperSeason plugin, int delay, int period) {
/* 19 */     this.plugin = plugin;
/* 20 */     runTaskTimer((Plugin)plugin, delay, period);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 26 */     if (Bukkit.getOnlinePlayers() != null)
/* 27 */       for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/* 28 */         if (p.getInventory().getArmorContents() != null) {
/*    */           
/* 30 */           CustomEnchantments.CEC.put(p, new CustomEnchantments());
/* 31 */           CustomEnchantments enc = (CustomEnchantments)CustomEnchantments.CEC.get(p); ItemStack[] arrayOfItemStack;
/* 32 */           for (int j = (arrayOfItemStack = p.getInventory().getArmorContents()).length, i = 0; i < j; ) { ItemStack item = arrayOfItemStack[i];
/*    */             
/* 34 */             if (Warm.hasEnchant(item)) enc.warm = true; 
/* 35 */             if (Cool.hasEnchant(item)) enc.cool = true;  i++; }
/*    */           
/* 37 */           CustomEnchantments.CEC.put(p, enc);
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Runners/check_Enchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */