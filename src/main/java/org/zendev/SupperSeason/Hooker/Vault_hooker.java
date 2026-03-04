/*     */ package org.zendev.SupperSeason.Hooker;
/*     */ 
/*     */ import net.milkbowl.vault.economy.Economy;
/*     */ import net.milkbowl.vault.permission.Permission;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.zendev.SupperSeason.SupperSeason;
/*     */ import org.zendev.SupperSeason.Utils.Utils;
/*     */ 
/*     */ public class Vault_hooker
/*     */   implements Hooker {
/*     */   public boolean isHooked;
/*  15 */   private static Economy econ = null;
/*  16 */   private static Permission perms = null;
/*     */ 
/*     */   
/*     */   public void hook() {
/*  20 */     if (!setupEconomy()) {
/*     */       
/*  22 */       Bukkit.getConsoleSender().sendMessage(Utils.chat("&2&l[SupperSeason] &cCould not find &eVault! &cThis plugin is important. Without this may cause some issue"));
/*     */       
/*  24 */       this.isHooked = false;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  29 */     this.isHooked = true;
/*  30 */     Bukkit.getConsoleSender().sendMessage(Utils.chat("&2&l[SupperSeason] &eVault hooked"));
/*     */     
/*  32 */     setupPermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean setupEconomy() {
/*  37 */     if (Bukkit.getPluginManager().getPlugin("Vault") == null)
/*     */     {
/*  39 */       return false;
/*     */     }
/*     */     
/*  42 */     RegisteredServiceProvider<Economy> rsp = SupperSeason.instance.getServer().getServicesManager().getRegistration(Economy.class);
/*  43 */     if (rsp == null)
/*     */     {
/*  45 */       return false;
/*     */     }
/*  47 */     econ = (Economy)rsp.getProvider();
/*  48 */     return (econ != null);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean setupPermissions() {
/*  53 */     RegisteredServiceProvider<Permission> rsp = SupperSeason.instance.getServer().getServicesManager().getRegistration(Permission.class);
/*  54 */     if (rsp == null)
/*     */     {
/*  56 */       return false;
/*     */     }
/*  58 */     perms = (Permission)rsp.getProvider();
/*  59 */     return (perms != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setMoney(Player p, double amount) {
/*  65 */     econ.withdrawPlayer((OfflinePlayer)p, getMoney(p));
/*  66 */     econ.depositPlayer((OfflinePlayer)p, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void giveMoney(Player p, double amount) {
/*  71 */     econ.depositPlayer((OfflinePlayer)p, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void subtractMoney(Player p, double amount) {
/*  76 */     econ.withdrawPlayer((OfflinePlayer)p, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Boolean hasMoney(Player p, double amount) {
/*  81 */     return (econ.getBalance((OfflinePlayer)p) >= amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getMoney(Player p) {
/*  86 */     return econ.getBalance((OfflinePlayer)p);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addPermission(Player p, String str) {
/*  92 */     if (!perms.playerHas(p, str)) perms.playerAdd(p, str);
/*     */   
/*     */   }
/*     */   
/*     */   public static void removePermission(Player p, String str) {
/*  97 */     if (perms.playerHas(p, str)) perms.playerRemove(p, str);
/*     */   
/*     */   }
/*     */   
/*     */   public static Boolean hasPermission(Player p, String str) {
/* 102 */     return perms.playerHas(p, str);
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Hooker/Vault_hooker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */