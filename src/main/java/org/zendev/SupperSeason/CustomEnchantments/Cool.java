/*    */ package org.zendev.SupperSeason.CustomEnchantments;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Cool
/*    */ {
/*    */   public static String getName() {
/* 16 */     return "&7Cool";
/*    */   }
/*    */ 
/*    */   
/*    */   public static Boolean hasEnchant(ItemStack item) {
/* 21 */     if (item != null && 
/* 22 */       item.getItemMeta() != null) {
/*    */       
/* 24 */       ItemMeta meta = item.getItemMeta();
/* 25 */       List<String> lore = new ArrayList<>();
/* 26 */       if (meta.getLore() != null) lore = meta.getLore(); 
/* 27 */       for (String s : lore) {
/* 28 */         if (s.startsWith(Utils.chat(getName())))
/* 29 */           return true; 
/*    */       } 
/* 31 */     }  return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ItemStack add(ItemStack item) {
/* 37 */     if (!hasEnchant(item) && 
/* 38 */       item.getItemMeta() != null) {
/*    */       
/* 40 */       ItemMeta meta = item.getItemMeta();
/* 41 */       List<String> lore = new ArrayList<>();
/* 42 */       if (meta.getLore() != null) lore = meta.getLore(); 
/* 43 */       lore.add(0, String.valueOf(Utils.chat(getName())) + " I");
/* 44 */       meta.setLore(lore);
/* 45 */       item.setItemMeta(meta);
/* 46 */       return item;
/*    */     } 
/* 48 */     return item;
/*    */   }
/*    */   
/*    */   public static boolean hasCool(Player p) {
/*    */     ItemStack[] arrayOfItemStack;
/* 53 */     for (int j = (arrayOfItemStack = p.getInventory().getArmorContents()).length, i = 0; i < j; ) { ItemStack item = arrayOfItemStack[i];
/*    */       
/* 55 */       if (item != null && hasEnchant(item))
/*    */       {
/* 57 */         return true; } 
/*    */       i++; }
/*    */     
/* 60 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/CustomEnchantments/Cool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */