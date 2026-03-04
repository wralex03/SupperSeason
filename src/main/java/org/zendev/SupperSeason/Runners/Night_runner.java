/*    */ package org.zendev.SupperSeason.Runners;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.zendev.SupperSeason.BossBar.TimeBar;
/*    */ import org.zendev.SupperSeason.Files.File_config;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ import org.zendev.SupperSeason.Utils.Utils;
/*    */ 
/*    */ public class Night_runner
/*    */   extends BukkitRunnable
/*    */ {
/*    */   private SupperSeason plugin;
/*    */   
/*    */   public Night_runner(SupperSeason plugin, long delay, long period) {
/* 20 */     this.plugin = plugin;
/* 21 */     runTaskTimer((Plugin)plugin, delay, period);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 27 */     List<String> message = File_config.mNight();
/* 28 */     int size = message.size();
/* 29 */     String[] line = new String[size];
/* 30 */     for (int i = 0; i < size; i++)
/*    */     {
/* 32 */       line[i] = message.get(i);
/*    */     }
/* 34 */     for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*    */       
/* 36 */       if (size == 1) { Utils.sendTitle(p, line[0], ""); continue; }
/* 37 */        if (size == 2) { Utils.sendTitle(p, line[0], line[1]); continue; }
/* 38 */        if (size > 2) {
/*    */         
/* 40 */         Utils.sendTitle(p, line[0], line[1]);
/* 41 */         for (int j = 2; j < size; ) { Utils.sendMessage(p, line[j]); j++; }
/*    */       
/*    */       } 
/*    */     } 
/* 45 */     List<String> Commands = File_config.cmdNight();
/* 46 */     for (String cmd : Commands) {
/*    */       
/* 48 */       if (cmd.contains("<player>") && Bukkit.getOnlinePlayers() != null) {
/*    */         
/* 50 */         for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers())
/* 51 */         { if (Utils.ActiveWorld().contains(p.getWorld())) Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmd.replace("<player>", p.getName()));  }  continue;
/* 52 */       }  Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmd);
/*    */     } 
/*    */     
/* 55 */     if ((!Utils.alwayNight() && !Utils.alwayDay()) || (Utils.alwayNight() && Utils.alwayDay())) { TimeBar.setNight(); }
/* 56 */     else if (Utils.alwayNight()) { TimeBar.setNight(); }
/* 57 */     else if (Utils.alwayDay()) { TimeBar.setNightAsMorning(); }
/*    */   
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Runners/Night_runner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */