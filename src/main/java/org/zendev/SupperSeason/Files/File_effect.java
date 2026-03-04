/*    */ package org.zendev.SupperSeason.Files;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class File_effect
/*    */ {
/*    */   public static File efFile;
/*    */   public static FileConfiguration efConfig;
/*    */   
/*    */   public static void save() {
/*    */     try {
/* 17 */       efConfig.save(efFile);
/*    */     }
/* 19 */     catch (IOException e) {
/*    */       
/* 21 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Files/File_effect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */