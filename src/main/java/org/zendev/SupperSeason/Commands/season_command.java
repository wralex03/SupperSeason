/*     */ package org.zendev.SupperSeason.Commands;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.TabCompleter;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.zendev.SupperSeason.CustomEnchantments.Cool;
/*     */ import org.zendev.SupperSeason.CustomEnchantments.Warm;
/*     */ import org.zendev.SupperSeason.Files.File_config;
/*     */ import org.zendev.SupperSeason.Files.File_time;
/*     */ import org.zendev.SupperSeason.SupperSeason;
/*     */ import org.zendev.SupperSeason.Utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class season_command
/*     */   implements CommandExecutor, TabCompleter
/*     */ {
/*     */   private SupperSeason plugin;
/*     */   
/*     */   public season_command(SupperSeason plugin) {
/*  29 */     this.plugin = plugin;
/*     */     
/*  31 */     plugin.getCommand("season").setExecutor(this);
/*  32 */     plugin.getCommand("season").setTabCompleter(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendHelp(CommandSender sender) {
/*  37 */     List<String> toSend = File_config.mHelp();
/*  38 */     if (!(sender instanceof Player)) {
/*     */       
/*  40 */       toSend.remove(14);
/*  41 */       toSend.remove(7);
/*  42 */       toSend.remove(5);
/*  43 */       for (String s : toSend)
/*     */       {
/*  45 */         sender.sendMessage(Utils.chat(
/*  46 */               s.replace("<placeholder_version>", SupperSeason.instance.getDescription().getVersion())));
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  52 */     if (!sender.hasPermission("season.fishcontest.manage")) {
/*     */       
/*  54 */       toSend.remove(12);
/*  55 */       toSend.remove(11);
/*     */     } 
/*  57 */     if (!sender.hasPermission("season.fishing.op"))
/*  58 */       toSend.remove(9); 
/*  59 */     if (!sender.hasPermission("season.enchant")) {
/*  60 */       toSend.remove(6);
/*     */     }
/*  62 */     for (String s : toSend)
/*     */     {
/*  64 */       sender.sendMessage(Utils.chat(
/*  65 */             s.replace("<placeholder_version>", SupperSeason.instance.getDescription().getVersion())));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  73 */     if (args.length == 0) {
/*     */       
/*  75 */       for (String s : (Iterable<String>)File_config.mSeason()) {
/*  76 */         sender.sendMessage(Utils.chat(s.replace("<season>", File_time.loadDisplay(File_time.ss))
/*  77 */               .replace("<date>", File_time.Date)));
/*     */       }
/*  79 */     } else if (args[0].equalsIgnoreCase("help")) {
/*     */       
/*  81 */       sendHelp(sender);
/*     */     }
/*  83 */     else if (args[0].equalsIgnoreCase("reload") && args.length == 1) {
/*     */       
/*  85 */       if (!(sender instanceof Player)) {
/*     */         
/*  87 */         SupperSeason.reload();
/*  88 */         Bukkit.getConsoleSender().sendMessage(Utils.chat(File_config.mReload()
/*  89 */               .replace("<version>", SupperSeason.instance.getDescription().getVersion())));
/*     */       }
/*     */       else {
/*     */         
/*  93 */         Player p = (Player)sender;
/*  94 */         if (p.hasPermission("season.reload"))
/*     */         {
/*  96 */           SupperSeason.reload();
/*  97 */           Utils.sendMessage(p, File_config.mReload()
/*  98 */               .replace("<version>", SupperSeason.instance.getDescription().getVersion()));
/*     */         }
/*     */       
/*     */       } 
/* 102 */     } else if (args[0].equalsIgnoreCase("enchant") && args.length == 2) {
/*     */       
/* 104 */       if (!(sender instanceof Player)) { sender.sendMessage(File_config.mOnlyPlayer()); }
/*     */       else
/*     */       
/* 107 */       { Player p = (Player)sender;
/* 108 */         if (!p.hasPermission("season.enchant")) { p.sendMessage(File_config.mNoPermission());
/*     */            }
/*     */         
/* 111 */         else if (args[1].toLowerCase().equals("warm"))
/*     */         
/* 113 */         { if (p.getItemInHand() != null)
/*     */           {
/* 115 */             if (p.getItemInHand().getType() == Material.AIR) {
/*     */               
/* 117 */               String message = File_config.mHandSomeThing();
/* 118 */               p.sendMessage(Utils.chat(message));
/*     */             } else {
/*     */               
/* 121 */               ItemStack result = Warm.add(p.getItemInHand());
/* 122 */               p.getInventory().remove(p.getItemInHand());
/* 123 */               p.getInventory().addItem(new ItemStack[] { result });
/* 124 */               String message = File_config.mAddEnchant().replace("<enc>", args[1]);
/* 125 */               p.sendMessage(Utils.chat(message));
/*     */             }
/*     */           
/*     */           } }
/*     */         
/* 130 */         else if (args[1].toLowerCase().equals("cool"))
/*     */         
/* 132 */         { if (p.getItemInHand().getType() == Material.AIR) {
/*     */             
/* 134 */             String message = File_config.mHandSomeThing();
/* 135 */             p.sendMessage(Utils.chat(message));
/*     */           } else {
/*     */             
/* 138 */             ItemStack result = Cool.add(p.getItemInHand());
/* 139 */             p.getInventory().remove(p.getItemInHand());
/* 140 */             p.getInventory().addItem(new ItemStack[] { result });
/* 141 */             String message = File_config.mAddEnchant().replace("<enc>", args[1]);
/* 142 */             p.sendMessage(Utils.chat(message));
/*     */           }  }
/*     */          }
/*     */     
/*     */     } else {
/* 147 */       sendHelp(sender);
/* 148 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) {
/* 154 */     if (args.length == 1) {
/*     */       
/* 156 */       List<String> arguments = new ArrayList<>();
/* 157 */       if (sender.hasPermission("season.enchant")) arguments.add("enchant"); 
/* 158 */       if (sender.hasPermission("season.reload")) arguments.add("reload"); 
/* 159 */       arguments.add("help");
/* 160 */       arguments.add("");
/* 161 */       return arguments;
/*     */     } 
/* 163 */     if (args.length == 2) {
/*     */       
/* 165 */       List<String> arguments = new ArrayList<>();
/* 166 */       if (args[0].equalsIgnoreCase("enchant") && sender.hasPermission("season.enchant")) {
/*     */         
/* 168 */         arguments.add("cool");
/* 169 */         arguments.add("warm");
/*     */       } 
/* 171 */       return arguments;
/*     */     } 
/* 173 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Commands/season_command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */