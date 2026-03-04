/*    */ package org.zendev.SupperSeason.Fishing.Contest;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.TabCompleter;
/*    */ import org.zendev.SupperSeason.Commands.season_command;
/*    */ import org.zendev.SupperSeason.Files.File_config;
/*    */ import org.zendev.SupperSeason.SupperSeason;
/*    */ 
/*    */ 
/*    */ public class FishContest_Command
/*    */   implements CommandExecutor, TabCompleter
/*    */ {
/*    */   public FishContest_Command() {
/* 18 */     SupperSeason.instance.getCommand("fishcontest").setExecutor(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/* 25 */     if (args.length == 0)
/*    */     
/* 27 */     { for (String s : FishingCT.info()) sender.sendMessage(s);
/*    */        }
/*    */     
/* 30 */     else if (args.length == 1)
/*    */     
/* 32 */     { if (args[0].equalsIgnoreCase("start"))
/*    */       
/* 34 */       { if (sender.hasPermission("season.fishcontest.manage"))
/*    */         
/* 36 */         { if (!FishingCT.inCompete) { FishingCT.start(); }
/* 37 */           else { sender.sendMessage(File_config.FCTAlreadyStart()); }
/*    */            }
/* 39 */         else { sender.sendMessage(File_config.mNoPermission()); }
/*    */          }
/* 41 */       else if (args[0].equalsIgnoreCase("end"))
/*    */       
/* 43 */       { if (sender.hasPermission("season.fishcontest.manage"))
/*    */         
/* 45 */         { if (FishingCT.inCompete) { FishingCT.end(); }
/* 46 */           else { sender.sendMessage(File_config.FCTNotStart()); }
/*    */            }
/* 48 */         else { sender.sendMessage(File_config.mNoPermission()); }
/*    */          }
/* 50 */       else if (args[0].equalsIgnoreCase("top"))
/*    */       
/* 52 */       { if (!FishingCT.inCompete) { sender.sendMessage(File_config.FCTNotStart()); }
/* 53 */         else { FishingCT.sendRank(sender); }
/*    */          }
/* 55 */       else { season_command.sendHelp(sender); }
/*    */        }
/* 57 */     else { season_command.sendHelp(sender); }
/*    */     
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) {
/* 65 */     if (args.length == 1) {
/*    */       
/* 67 */       List<String> arguments = new ArrayList<>();
/* 68 */       if (!FishingCT.inCompete && sender.hasPermission("season.fishcontest.manage")) arguments.add("start"); 
/* 69 */       if (FishingCT.inCompete && sender.hasPermission("season.fishcontest.manage")) arguments.add("end"); 
/* 70 */       if (FishingCT.inCompete) arguments.add("top"); 
/* 71 */       arguments.add("");
/* 72 */       return arguments;
/*    */     } 
/* 74 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/alex/Downloads/SupperSeasonv1.1.4+.jar!/org/zendev/SupperSeason/Fishing/Contest/FishContest_Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.2.3
 */