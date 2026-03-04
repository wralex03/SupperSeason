/*    */ package org.zendev.SupperSeason.Listeners;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.zendev.SupperSeason.BossBar.TimeBar;
/*    */ import org.zendev.SupperSeason.Fishing.Contest.ContestBar;
/*    */ import org.zendev.SupperSeason.Fishing.Contest.FishingCT;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ import org.zendev.SupperSeason.Utils.U_BossBar;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ public class onJoin_listener
/*    */   implements Listener
/*    */ {
/*    */   private SupperSeason plugin;
/*    */   
/*    */   public onJoin_listener(SupperSeason plugin) {
/* 23 */     this.plugin = plugin;
/*    */     
/* 25 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onJoin(PlayerJoinEvent e) {
/* 31 */     Player p = e.getPlayer();
/* 32 */     if (Utils.ActiveWorld().contains(p.getWorld())) {
/*    */       
/* 34 */       U_BossBar.addPlayer(TimeBar.timeBar, p);
/* 35 */       if (FishingCT.inCompete) U_BossBar.addPlayer(ContestBar.contestBar, p);
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onQuit(PlayerQuitEvent e) {
/* 42 */     Player p = e.getPlayer();
/* 43 */     U_BossBar.removePlayer(TimeBar.timeBar, p);
/* 44 */     if (FishingCT.inCompete) U_BossBar.removePlayer(ContestBar.contestBar, p); 
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Listeners/onJoin_listener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */