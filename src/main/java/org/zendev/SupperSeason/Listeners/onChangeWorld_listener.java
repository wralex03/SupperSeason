/*    */ package org.zendev.SupperSeason.Listeners;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerChangedWorldEvent;
/*    */ import org.bukkit.event.player.PlayerRespawnEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.zendev.SupperSeason.BossBar.TimeBar;
/*    */ import org.zendev.SupperSeason.Fishing.Contest.ContestBar;
/*    */ import org.zendev.SupperSeason.Fishing.Contest.FishingCT;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ import org.zendev.SupperSeason.Utils.U_BossBar;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ public class onChangeWorld_listener
/*    */   implements Listener
/*    */ {
/*    */   private SupperSeason plugin;
/*    */   
/*    */   public onChangeWorld_listener(SupperSeason plugin) {
/* 23 */     this.plugin = plugin;
/*    */     
/* 25 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onChangeWorld(PlayerChangedWorldEvent e) {
/* 31 */     Player p = e.getPlayer();
/* 32 */     if (Utils.ActiveWorld().contains(p.getWorld())) {
/*    */       
/* 34 */       if (!TimeBar.timeBar.getPlayers().contains(p)) {
/*    */         
/* 36 */         U_BossBar.addPlayer(TimeBar.timeBar, p);
/* 37 */         if (FishingCT.inCompete) U_BossBar.addPlayer(ContestBar.contestBar, p);
/*    */       
/*    */       } 
/*    */     } else {
/* 41 */       U_BossBar.removePlayer(TimeBar.timeBar, p);
/* 42 */       if (FishingCT.inCompete) U_BossBar.removePlayer(ContestBar.contestBar, p);
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onRespawn(PlayerRespawnEvent e) {
/* 49 */     Player p = e.getPlayer();
/* 50 */     if (Utils.ActiveWorld().contains(p.getWorld())) {
/*    */       
/* 52 */       if (!TimeBar.timeBar.getPlayers().contains(p)) {
/*    */         
/* 54 */         U_BossBar.addPlayer(TimeBar.timeBar, p);
/* 55 */         if (FishingCT.inCompete) U_BossBar.addPlayer(ContestBar.contestBar, p);
/*    */       
/*    */       } 
/*    */     } else {
/* 59 */       U_BossBar.removePlayer(TimeBar.timeBar, p);
/* 60 */       if (FishingCT.inCompete) U_BossBar.removePlayer(ContestBar.contestBar, p); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Listeners/onChangeWorld_listener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */