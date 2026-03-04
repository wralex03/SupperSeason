/*    */ package org.zendev.SupperSeason.Utils.Updater;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.function.Consumer;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UpdateChecker
/*    */ {
/*    */   private final JavaPlugin plugin;
/*    */   private final int resourceId;
/*    */   
/*    */   public UpdateChecker(JavaPlugin plugin, int resourceId) {
/* 18 */     this.plugin = plugin;
/* 19 */     this.resourceId = resourceId;
/*    */   }
/*    */   
/*    */   public void getVersion(Consumer<String> consumer) {
/* 23 */     Bukkit.getScheduler().runTaskAsynchronously((Plugin)this.plugin, () -> {
/*    */ 
/*    */           
/*    */           try {
/*    */             Exception exception2, exception1 = null;
/* 28 */           } catch (IOException exception) {
/*    */             this.plugin.getLogger().info("Unable to check for updates: " + exception.getMessage());
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Updater/UpdateChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */