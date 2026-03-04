/*    */ package org.zendev.SupperSeason.Files;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class File_rarity
/*    */ {
/*    */   public static File rFile;
/*    */   public static FileConfiguration rConfig;
/*    */   
/*    */   public static void save() {
/*    */     try {
/* 21 */       rConfig.save(rFile);
/*    */     }
/* 23 */     catch (IOException e) {
/*    */       
/* 25 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static List<String> sortedRarity() {
/* 31 */     List<String> rarities = allRarity();
/* 32 */     rarities.remove(getDefaultRarity());
/* 33 */     List<String> Frarities = new ArrayList<>();
/*    */ 
/*    */     
/* 36 */     double[] price = new double[rarities.size()];
/* 37 */     for (int i = 0; i <= rarities.size() - 1; i++)
/*    */     {
/* 39 */       price[i] = price((String)rarities.get(i));
/*    */     }
/*    */     
/* 42 */     double curMin = 0.0D;
/* 43 */     double Min = 101.0D;
/* 44 */     int sMin = 0;
/* 45 */     for (int j = 0; j <= rarities.size() - 1; j++) {
/*    */       
/* 47 */       for (int k = 0; k <= rarities.size() - 1; k++) {
/*    */         
/* 49 */         if (price[k] < Min && price[k] > curMin) {
/*    */           
/* 51 */           Min = price[k];
/* 52 */           sMin = k;
/*    */         } 
/*    */       } 
/* 55 */       curMin = Min;
/* 56 */       Min = 101.0D;
/* 57 */       if (!File_fish.getFishR(rarities.get(sMin)).isEmpty()) Frarities.add(rarities.get(sMin)); 
/*    */     } 
/* 59 */     return Frarities;
/*    */   }
/*    */ 
/*    */   
/*    */   public static List<String> allRarity() {
/* 64 */     List<String> rarities = new ArrayList<>();
/* 65 */     Set<String> raritiesS = rConfig.getConfigurationSection("Rarity.").getKeys(false);
/* 66 */     raritiesS.remove("default");
/* 67 */     for (String s : raritiesS)
/*    */     {
/* 69 */       rarities.add(s);
/*    */     }
/*    */     
/* 72 */     return rarities;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getColor(String rarity) {
/* 77 */     return rConfig.getString("Rarity." + rarity + ".color");
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getDefaultRarity() {
/* 82 */     return rConfig.getString("Rarity.default");
/*    */   }
/*    */ 
/*    */   
/*    */   public static double price(String rarity) {
/* 87 */     return rConfig.getDouble("Rarity." + rarity + ".price-per-cm");
/*    */   }
/*    */ 
/*    */   
/*    */   public static double chance(String rarity) {
/* 92 */     return rConfig.getDouble("Rarity." + rarity + ".chance");
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Files/File_rarity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */