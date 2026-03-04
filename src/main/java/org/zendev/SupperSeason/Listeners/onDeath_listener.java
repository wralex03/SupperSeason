/*    */ package org.zendev.SupperSeason.Listeners;
/*    */ 
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.zendev.SupperSeason.BossBar.TimeBar;
/*    */ import org.zendev.SupperSeason.Files.File_config;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ public class onDeath_listener
/*    */   implements Listener
/*    */ {
/*    */   private SupperSeason plugin;
/*    */   
/*    */   public onDeath_listener(SupperSeason plugin) {
/* 18 */     this.plugin = plugin;
/*    */     
/* 20 */     plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onDeath(PlayerDeathEvent e) {
/* 26 */     if ((!Utils.alwayNight() && !Utils.alwayDay()) || (Utils.alwayNight() && Utils.alwayDay()))
/*    */     
/* 28 */     { if (TimeBar.count > (File_config.getDayParts() / 2) && TimeBar.count <= File_config.getDayParts()) e.setKeepInventory(false); 
/* 29 */       if (TimeBar.count >= 0.0D && TimeBar.count <= (File_config.getDayParts() / 2)) e.setKeepInventory(true);
/*    */        }
/* 31 */     else if (Utils.alwayNight()) { e.setKeepInventory(false); }
/* 32 */     else if (Utils.alwayDay()) { e.setKeepInventory(true); }
/*    */   
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Listeners/onDeath_listener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */