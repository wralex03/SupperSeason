/*     */ package org.zendev.SupperSeason.Fishing;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerFishEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.zendev.SupperSeason.Files.File_config;
/*     */ import org.zendev.SupperSeason.Files.File_fish;
/*     */ import org.zendev.SupperSeason.Files.File_rarity;
/*     */ import org.zendev.SupperSeason.Files.File_time;
/*     */ import org.zendev.SupperSeason.Fishing.Contest.FishingCT;
/*     */ import org.zendev.SupperSeason.Utils.Season;
/*     */ import org.zendev.SupperSeason.Utils.Utils;
/*     */ 
/*     */ public class Fishing
/*     */   implements Listener
/*     */ {
/*  23 */   public static List<Player> onCooldown = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public static void addCooldown(Player p) {
/*  27 */     onCooldown.add(p);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void removeCooldown(final Player p, int after) {
/*  32 */     Bukkit.getScheduler().runTaskLater(Utils.plugin, new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*  36 */             if (Fishing.onCooldown.contains(p)) Fishing.onCooldown.remove(p); 
/*     */           }
/*  38 */         },  (after * 20));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void cooldown(Player p, int after) {
/*  43 */     addCooldown(p);
/*  44 */     removeCooldown(p, after);
/*     */   }
/*     */ 
/*     */   
/*     */   public Fishing() {
/*  49 */     Utils.plugin.getServer().getPluginManager().registerEvents(this, Utils.plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Boolean canFishing(Season ss) {
/*  54 */     return File_config.canFishing(ss);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onCaughtFish(PlayerFishEvent e) {
/*  60 */     Player p = e.getPlayer();
/*  61 */     if (canFishing(File_time.ss)) {
/*     */       
/*  63 */       if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
/*     */         
/*  65 */         e.setCancelled(true);
/*  66 */         String season = File_time.loadId(File_time.ss);
/*  67 */         int dem = 0;
/*  68 */         int max = File_rarity.sortedRarity().size();
/*     */         
/*  70 */         if (File_rarity.sortedRarity().isEmpty())
/*     */           return; 
/*  72 */         for (String rarity : (Iterable<String>)File_rarity.sortedRarity()) {
/*     */           
/*  74 */           if (Utils.roll(File_rarity.chance(rarity) / 100.0D) && !onCooldown.contains(p)) {
/*     */             
/*  76 */             List<String> fishes = File_fish.getFishSR(season, rarity);
/*  77 */             if (fishes.size() == 0) {
/*     */               break;
/*     */             }
/*  80 */             pickUpFish(p, fishes);
/*     */             
/*     */             return;
/*     */           } 
/*  84 */           dem++;
/*     */         } 
/*  86 */         if (dem == max) {
/*     */           
/*  88 */           List<String> fishes = File_fish.getFishSR(season, File_rarity.getDefaultRarity());
/*  89 */           if (fishes.size() == 0) {
/*     */             return;
/*     */           }
/*  92 */           pickUpFish(p, fishes);
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 100 */       p.sendMessage(File_config.mCannotFishing());
/* 101 */       e.setCancelled(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void FishingSimulator(Player p) {
/* 107 */     String season = File_time.loadId(File_time.ss);
/* 108 */     int dem = 0;
/* 109 */     int max = File_rarity.sortedRarity().size();
/*     */     
/* 111 */     if (File_rarity.sortedRarity().isEmpty())
/*     */       return; 
/* 113 */     for (String rarity : (Iterable<String>)File_rarity.sortedRarity()) {
/*     */       
/* 115 */       if (Utils.roll(File_rarity.chance(rarity) / 100.0D)) {
/*     */         
/* 117 */         List<String> fishes = File_fish.getFishSR(season, rarity);
/* 118 */         if (fishes.size() == 0) {
/*     */           break;
/*     */         }
/* 121 */         pickUpFish(p, fishes);
/*     */         
/*     */         return;
/*     */       } 
/* 125 */       dem++;
/*     */     } 
/* 127 */     if (dem == max) {
/*     */       
/* 129 */       List<String> fishes = File_fish.getFishSR(season, File_rarity.getDefaultRarity());
/* 130 */       if (fishes.size() == 0) {
/*     */         return;
/*     */       }
/* 133 */       pickUpFish(p, fishes);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void FishingSimulator() {
/* 142 */     String season = File_time.loadId(File_time.ss);
/* 143 */     int dem = 0;
/* 144 */     int max = File_rarity.sortedRarity().size();
/*     */     
/* 146 */     if (File_rarity.sortedRarity().isEmpty())
/*     */       return; 
/* 148 */     for (String rarity : (Iterable<String>)File_rarity.sortedRarity()) {
/*     */       
/* 150 */       System.out.println(String.valueOf(rarity) + " " + File_rarity.chance(rarity));
/* 151 */       if (Utils.roll(File_rarity.chance(rarity) / 100.0D)) {
/*     */         
/* 153 */         List<String> fishes = File_fish.getFishSR(season, rarity);
/* 154 */         if (fishes.size() == 0) {
/*     */           break;
/*     */         }
/* 157 */         String fishID = fishes.get(Utils.random(0, fishes.size() - 1));
/* 158 */         System.out.println(Utils.chat(File_fish.getIconDisplay(fishID)));
/*     */         
/*     */         return;
/*     */       } 
/* 162 */       dem++;
/* 163 */       if (dem == max) {
/*     */         
/* 165 */         List<String> fishes = File_fish.getFishSR(season, File_rarity.getDefaultRarity());
/* 166 */         if (fishes.size() == 0) {
/*     */           return;
/*     */         }
/* 169 */         String fishID = fishes.get(Utils.random(0, fishes.size() - 1));
/* 170 */         System.out.println(Utils.chat(File_fish.getIconDisplay(fishID)));
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void pickUpFish(Player p, List<String> fishes) {
/* 179 */     double length = 0.0D;
/* 180 */     String toreplaceLength = "";
/* 181 */     String fishID = fishes.get(Utils.random(0, fishes.size() - 1));
/* 182 */     String display = File_fish.getIconDisplay(fishID);
/* 183 */     String rarity = File_fish.getRarity(fishID);
/* 184 */     ItemStack item = File_fish.getFishIcon(p, fishID);
/* 185 */     if (File_fish.fConfig.getBoolean("Fish." + fishID + ".length.enable")) {
/*     */       
/* 187 */       length = File_fish.getLength(item);
/* 188 */       toreplaceLength = String.valueOf(length) + "cm";
/*     */     } 
/* 190 */     cooldown(p, 2);
/* 191 */     if (File_config.isCaughtBroadcast()) {
/*     */       
/* 193 */       String message = File_config.mFishCaught()
/* 194 */         .replace("<player>", p.getName())
/* 195 */         .replace("<fish>", display)
/* 196 */         .replace("<length>", toreplaceLength);
/* 197 */       Bukkit.broadcastMessage(Utils.chat(message));
/*     */     } 
/* 199 */     p.getInventory().addItem(new ItemStack[] { item });
/* 200 */     Utils.decreaseDurability(p);
/* 201 */     p.updateInventory();
/* 202 */     List<String> cmds = File_fish.getCaughtTrigger(fishID);
/* 203 */     if (!cmds.isEmpty()) {
/* 204 */       for (String cmd : cmds)
/*     */       {
/* 206 */         Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), File_fish.replaceTrigger(p, cmd));
/*     */       }
/*     */     }
/* 209 */     if (FishingCT.inCompete && FishingCT.joinableRarity().contains(rarity)) FishingCT.joinContest(p, length); 
/*     */   }
/*     */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Fishing/Fishing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */