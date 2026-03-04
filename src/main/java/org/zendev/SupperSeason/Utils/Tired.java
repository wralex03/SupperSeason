/*     */ package org.zendev.SupperSeason.Utils;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerBedLeaveEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.zendev.SupperSeason.Files.File_config;
/*     */ import org.zendev.SupperSeason.Files.File_time;
/*     */ import org.zendev.SupperSeason.SupperSeason;
/*     */ 
/*     */ public class Tired extends BukkitRunnable implements Listener {
/*  19 */   private int count = 0;
/*     */   private boolean wokeup = false;
/*  21 */   private int slepttime = 0;
/*  22 */   private int time = 0;
/*     */ 
/*     */   
/*     */   public Tired() {
/*  26 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)SupperSeason.instance);
/*  27 */     runTaskTimer((Plugin)SupperSeason.instance, 0L, 20L);
/*  28 */     this.time = (int)(File_config.getTimeCycle() / 40L);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onWakeUp(PlayerBedLeaveEvent e) {
/*  34 */     if (!this.wokeup && !Utils.isDay() && this.slepttime >= 5) {
/*     */       
/*  36 */       this.wokeup = true;
/*  37 */       File_time.addDay(1);
/*  38 */       File_time.loadTime();
/*  39 */       SupperSeason.instance.reload_Mechanic();
/*  40 */       this.count = 0;
/*  41 */       this.slepttime = 0;
/*     */       
/*  43 */       String message = File_config.mNewDay();
/*  44 */       List<String> Commands = File_config.cmdNewDay();
/*  45 */       for (String cmd : Commands) {
/*     */         
/*  47 */         if (cmd.contains("<player>") && Bukkit.getOnlinePlayers() != null) {
/*  48 */           for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers())
/*  49 */             Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmd.replace("<player>", p.getName()));  continue;
/*     */         } 
/*  51 */         Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmd);
/*     */       } 
/*     */       
/*  54 */       if (Bukkit.getOnlinePlayers() != null)
/*  55 */         for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*  56 */           Utils.sendTitle(p, message, "&6- " + File_time.Date);
/*     */         } 
/*  58 */       Bukkit.getScheduler().runTaskLater(Utils.plugin, new Runnable()
/*     */           {
/*     */             
/*     */             public void run()
/*     */             {
/*  63 */               Tired.this.wokeup = false;
/*     */             }
/*  65 */           },  200L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     boolean allslept = true;
/*  73 */     if (Bukkit.getOnlinePlayers() != null && !Utils.isDay()) {
/*     */       
/*  75 */       for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*     */         
/*  77 */         if (!p.isSleeping()) {
/*     */           
/*  79 */           allslept = false;
/*     */           break;
/*     */         } 
/*     */       } 
/*  83 */       for (Player p : (Iterable<Player>)Bukkit.getOnlinePlayers()) {
/*     */         
/*  85 */         if (allslept) { this.slepttime++; continue; }
/*     */         
/*  87 */         if (this.count < this.time && this.count < this.time / 2) { this.count++; continue; }
/*     */         
/*  89 */         if (this.count >= this.time / 2 && this.count < this.time) {
/*     */           
/*  91 */           this.count++;
/*  92 */           List<String> message = File_config.mTired();
/*  93 */           int size = message.size();
/*  94 */           String[] line = new String[size];
/*  95 */           for (int i = 0; i < size; i++)
/*     */           {
/*  97 */             line[i] = message.get(i);
/*     */           }
/*     */           
/* 100 */           if (size == 1) { Utils.sendTitle(p, line[0], ""); continue; }
/* 101 */            if (size == 2) { Utils.sendTitle(p, line[0], line[1]); continue; }
/* 102 */            if (size > 2) {
/*     */             
/* 104 */             Utils.sendTitle(p, line[0], line[1]);
/* 105 */             for (int j = 2; j < size; ) { Utils.sendMessage(p, line[j]); j++; }
/*     */           
/*     */           }  continue;
/* 108 */         }  if (this.count == this.time && !p.isSleeping()) {
/*     */           
/* 110 */           p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 9));
/* 111 */           p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 2));
/* 112 */           p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 4));
/* 113 */           p.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 100, 4));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Utils/Tired.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */