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
/*    */ public class Warm
/*    */ {
/*    */   public static String getName() {
/* 16 */     return "&7Warm";
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
/*    */   public static ItemStack add(ItemStack item) {
/* 36 */     if (!hasEnchant(item) && 
/* 37 */       item.getItemMeta() != null) {
/*    */       
/* 39 */       ItemMeta meta = item.getItemMeta();
/* 40 */       List<String> lore = new ArrayList<>();
/* 41 */       if (meta.getLore() != null) lore = meta.getLore(); 
/* 42 */       lore.add(0, String.valueOf(Utils.chat(getName())) + " I");
/* 43 */       meta.setLore(lore);
/* 44 */       item.setItemMeta(meta);
/* 45 */       return item;
/*    */     } 
/* 47 */     return item;
/*    */   }
/*    */   
/*    */   public static boolean hasWarm(Player p) {
/*    */     ItemStack[] arrayOfItemStack;
/* 52 */     for (int j = (arrayOfItemStack = p.getInventory().getArmorContents()).length, i = 0; i < j; ) { ItemStack item = arrayOfItemStack[i];
/*    */       
/* 54 */       if (item != null && hasEnchant(item))
/*    */       {
/* 56 */         return true; } 
/*    */       i++; }
/*    */     
/* 59 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/CustomEnchantments/Warm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */