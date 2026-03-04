/*     */ package org.zendev.SupperSeason.Fishing;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.inventory.InventoryEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.zendev.SupperSeason.Commands.season_command;
/*     */ import org.zendev.SupperSeason.Files.File_config;
/*     */ import org.zendev.SupperSeason.Files.File_fish;
/*     */ import org.zendev.SupperSeason.Hooker.Vault_hooker;
/*     */ import org.zendev.SupperSeason.SupperSeason;
/*     */ import org.zendev.SupperSeason.Utils.Utils;
/*     */ 
/*     */ public class FishShop
/*     */   implements CommandExecutor, Listener {
/*     */   public FishShop() {
/*  30 */     intialize();
/*  31 */     ((SupperSeason)Utils.plugin).getCommand("fishshop").setExecutor(this);
/*  32 */     Bukkit.getServer().getPluginManager().registerEvents(this, Utils.plugin);
/*     */   }
/*     */   
/*     */   public static Inventory inv;
/*     */   public static String inv_name;
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  39 */     if (args.length == 0 && sender instanceof Player)
/*     */     
/*  41 */     { Player p = (Player)sender;
/*  42 */       p.openInventory(open(p)); }
/*     */     
/*  44 */     else if (args.length == 1 && args[0].equalsIgnoreCase("op"))
/*     */     
/*  46 */     { if (sender.hasPermission("season.fishing.op"))
/*     */       
/*  48 */       { if (sender instanceof Player) { Fishing.FishingSimulator((Player)sender); }
/*  49 */         else { Fishing.FishingSimulator(); }
/*     */          }
/*  51 */       else { sender.sendMessage(File_config.mNoPermission()); }
/*     */        }
/*  53 */     else { season_command.sendHelp(sender); }
/*  54 */      return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public static int inv_rows = 3;
/*  61 */   public static int inv_size = inv_rows * 9;
/*     */ 
/*     */   
/*     */   public static void intialize() {
/*  65 */     String name = File_config.FishShopName();
/*  66 */     inv_name = Utils.chat(name);
/*     */     
/*  68 */     inv = Bukkit.createInventory(null, inv_size);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Inventory open(Player p) {
/*  73 */     Inventory toReturn = Bukkit.createInventory(null, inv_size, inv_name);
/*     */ 
/*     */     
/*  76 */     String sellID = File_config.FishShopSellID();
/*  77 */     String sellName = File_config.FishShopSellName()
/*  78 */       .replace("<amount>", "0");
/*     */     
/*  80 */     ItemStack sellIcon = Utils.createItem(sellID, 1, 0);
/*  81 */     ItemMeta meta = sellIcon.getItemMeta();
/*  82 */     meta.setDisplayName(Utils.chat(sellName));
/*  83 */     sellIcon.setItemMeta(meta);
/*     */ 
/*     */     
/*  86 */     String cloID = File_config.FishShopCloseID();
/*  87 */     String cloName = File_config.FishShopCloseName();
/*     */     
/*  89 */     ItemStack cloIcon = Utils.createItem(cloID, 1, 0);
/*  90 */     ItemMeta metaC = cloIcon.getItemMeta();
/*  91 */     metaC.setDisplayName(Utils.chat(cloName));
/*  92 */     cloIcon.setItemMeta(metaC);
/*     */     
/*  94 */     toReturn.setItem(21, cloIcon);
/*  95 */     toReturn.setItem(23, sellIcon);
/*     */     
/*  97 */     return toReturn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onClose(InventoryCloseEvent e) {
/* 104 */     String title = Utils.GetTitle((InventoryEvent)e);
/* 105 */     Player p = (Player)e.getPlayer();
/* 106 */     Inventory inv = e.getInventory();
/*     */     
/* 108 */     if (title.startsWith(Utils.chat(inv_name)))
/*     */     {
/* 110 */       for (int i = 0; i <= inv_size - 1; i++) {
/*     */         
/* 112 */         if (i != 21 && i != 23)
/*     */         {
/* 114 */           if (inv.getItem(i) != null) {
/* 115 */             p.getInventory().addItem(new ItemStack[] { inv.getItem(i) });
/*     */           }
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onClick(InventoryClickEvent e) {
/* 124 */     String title = Utils.GetTitle((InventoryEvent)e);
/*     */     
/* 126 */     if (title.startsWith(Utils.chat(inv_name))) {
/*     */       
/* 128 */       if (e.getSlotType() == InventoryType.SlotType.OUTSIDE)
/* 129 */         return;  Player p = (Player)e.getWhoClicked();
/* 130 */       ItemStack cursor = p.getItemOnCursor();
/* 131 */       Inventory inv = e.getClickedInventory();
/* 132 */       InventoryView invV = p.getOpenInventory();
/* 133 */       ItemStack Clicked = e.getCurrentItem();
/* 134 */       if (e.getRawSlot() <= 26) {
/*     */         
/* 136 */         if (e.getRawSlot() == 21) {
/*     */           
/* 138 */           p.closeInventory();
/*     */         }
/* 140 */         else if (e.getRawSlot() == 23) {
/*     */           
/* 142 */           String message = File_config.FishShopSold().replace("<amount>", new StringBuilder(String.valueOf(calculateMoney(inv))).toString());
/* 143 */           p.sendMessage(Utils.chat(message));
/* 144 */           p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0F, 10.0F);
/* 145 */           Vault_hooker.giveMoney(p, calculateMoney(inv));
/*     */           
/* 147 */           for (int i = 0; i <= inv_size - 1; i++) {
/*     */             
/* 149 */             if (i != 21 && i != 23)
/*     */             {
/* 151 */               if (inv.getItem(i) != null && 
/* 152 */                 File_fish.getPrice(inv.getItem(i)) == 0.0D) p.getInventory().addItem(new ItemStack[] { inv.getItem(i) });
/*     */             
/*     */             }
/*     */           } 
/* 156 */           for (int j = 0; j <= inv_size - 1; j++) {
/*     */             
/* 158 */             if (j != 21 && j != 23)
/*     */             {
/* 160 */               invV.setItem(j, Utils.createItem(Material.AIR, 1, 0));
/*     */             }
/*     */           } 
/* 163 */           onPlaceFish(p);
/*     */         
/*     */         }
/* 166 */         else if (cursor.getType() == Material.AIR) {
/*     */           
/* 168 */           if (Clicked != null && Clicked.getType() != Material.AIR)
/*     */           {
/* 170 */             e.setCancelled(true);
/* 171 */             p.setItemOnCursor(Clicked);
/* 172 */             invV.setItem(e.getRawSlot(), cursor);
/* 173 */             onPlaceFish(p);
/*     */           }
/*     */         
/*     */         }
/* 177 */         else if (Clicked != null && Clicked.getType() != Material.AIR) {
/*     */           
/* 179 */           e.setCancelled(true);
/* 180 */           p.setItemOnCursor(Clicked);
/* 181 */           invV.setItem(e.getRawSlot(), cursor);
/* 182 */           onPlaceFish(p);
/*     */         }
/*     */         else {
/*     */           
/* 186 */           e.setCancelled(true);
/* 187 */           p.setItemOnCursor(Utils.createItem(Material.AIR, 1, 0));
/* 188 */           invV.setItem(e.getRawSlot(), cursor);
/* 189 */           onPlaceFish(p);
/*     */         } 
/*     */ 
/*     */         
/* 193 */         e.setCancelled(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double calculateMoney(InventoryView inv) {
/* 200 */     double sum = 0.0D;
/* 201 */     for (int i = 0; i <= inv_size - 1; i++) {
/*     */       
/* 203 */       if (i != 21 && i != 23) {
/*     */         
/* 205 */         ItemStack item = inv.getItem(i);
/* 206 */         sum += File_fish.getPrice(item);
/*     */       } 
/*     */     } 
/* 209 */     return sum;
/*     */   }
/*     */ 
/*     */   
/*     */   public double calculateMoney(Inventory inv) {
/* 214 */     double sum = 0.0D;
/* 215 */     for (int i = 0; i <= inv_size - 1; i++) {
/*     */       
/* 217 */       if (i != 21 && i != 23) {
/*     */         
/* 219 */         ItemStack item = inv.getItem(i);
/* 220 */         sum += File_fish.getPrice(item);
/*     */       } 
/*     */     } 
/* 223 */     return sum;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlaceFish(Player p) {
/* 228 */     InventoryView toReturn = p.getOpenInventory();
/*     */     
/* 230 */     String sellID = File_config.FishShopSellID();
/* 231 */     String sellName = File_config.FishShopSellName()
/* 232 */       .replace("<amount>", new StringBuilder(String.valueOf(calculateMoney(toReturn))).toString());
/*     */     
/* 234 */     ItemStack sellIcon = Utils.createItem(sellID, 1, 0);
/* 235 */     ItemMeta meta = sellIcon.getItemMeta();
/* 236 */     meta.setDisplayName(Utils.chat(sellName));
/* 237 */     sellIcon.setItemMeta(meta);
/*     */     
/* 239 */     toReturn.setItem(23, sellIcon);
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Fishing/FishShop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */