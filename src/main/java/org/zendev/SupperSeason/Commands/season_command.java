package org.zendev.SupperSeason.Commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.zendev.SupperSeason.CustomEnchantments.Cool;
import org.zendev.SupperSeason.CustomEnchantments.Warm;
import org.zendev.SupperSeason.Files.File_config;
import org.zendev.SupperSeason.Files.File_time;
import org.zendev.SupperSeason.SupperSeason;
import org.zendev.SupperSeason.Utils.Utils;




public class season_command
   implements CommandExecutor, TabCompleter
{
   private SupperSeason plugin;

   public season_command(SupperSeason plugin) {
     this.plugin = plugin;
     plugin.getCommand("season").setExecutor(this);
     plugin.getCommand("season").setTabCompleter(this);
   }


   public static void sendHelp(CommandSender sender) {
     List<String> toSend = File_config.mHelp();
     if (!(sender instanceof Player)) {
       toSend.remove(14);
       toSend.remove(7);
       toSend.remove(5);
       for (String s : toSend)
       {
         sender.sendMessage(Utils.chat(
               s.replace("<placeholder_version>", SupperSeason.instance.getDescription().getVersion())));
       }

       return;
     }

     if (!sender.hasPermission("season.fishcontest.manage")) {

       toSend.remove(12);
       toSend.remove(11);
     }
     if (!sender.hasPermission("season.fishing.op"))
       toSend.remove(9);
     if (!sender.hasPermission("season.enchant")) {
       toSend.remove(6);
     }
     for (String s : toSend)
     {
       sender.sendMessage(Utils.chat(
             s.replace("<placeholder_version>", SupperSeason.instance.getDescription().getVersion())));
     }
   }


   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
     if (args.length == 0) {
       for (String s : (Iterable<String>)File_config.mSeason()) {
         sender.sendMessage(Utils.chat(s.replace("<season>", File_time.loadDisplay(File_time.ss))
               .replace("<date>", File_time.Date)));
       }
     } else if (args[0].equalsIgnoreCase("help")) {

       sendHelp(sender);
     }
     else if (args[0].equalsIgnoreCase("reload") && args.length == 1) {

       if (!(sender instanceof Player)) {

         SupperSeason.reload();
         Bukkit.getConsoleSender().sendMessage(Utils.chat(File_config.mReload()
               .replace("<version>", SupperSeason.instance.getDescription().getVersion())));
       }
       else {

         Player p = (Player)sender;
         if (p.hasPermission("season.reload"))
         {
           SupperSeason.reload();
           Utils.sendMessage(p, File_config.mReload()
               .replace("<version>", SupperSeason.instance.getDescription().getVersion()));
         }

       }
     } else if (args[0].equalsIgnoreCase("enchant") && args.length == 2) {

       if (!(sender instanceof Player)) { sender.sendMessage(File_config.mOnlyPlayer()); }
       else

       { Player p = (Player)sender;
         if (!p.hasPermission("season.enchant")) { p.sendMessage(File_config.mNoPermission());
            }

         else if (args[1].toLowerCase().equals("warm"))

         { if (p.getInventory().getItemInMainHand() != null)
           {
             if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {

               String message = File_config.mHandSomeThing();
               p.sendMessage(Utils.chat(message));
             } else {

               ItemStack result = Warm.add(p.getInventory().getItemInMainHand());
               p.getInventory().remove(p.getInventory().getItemInMainHand());
               p.getInventory().addItem(new ItemStack[] { result });
               String message = File_config.mAddEnchant().replace("<enc>", args[1]);
               p.sendMessage(Utils.chat(message));
             }

           } }

         else if (args[1].toLowerCase().equals("cool"))

         { if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {

             String message = File_config.mHandSomeThing();
             p.sendMessage(Utils.chat(message));
           } else {

             ItemStack result = Cool.add(p.getInventory().getItemInMainHand());
             p.getInventory().remove(p.getInventory().getItemInMainHand());
             p.getInventory().addItem(new ItemStack[] { result });
             String message = File_config.mAddEnchant().replace("<enc>", args[1]);
             p.sendMessage(Utils.chat(message));
           }  }
          }

     } else {
      sendHelp(sender);
     }  return false;
   }



   public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) {
     if (args.length == 1) {

       List<String> arguments = new ArrayList<>();
       if (sender.hasPermission("season.enchant")) arguments.add("enchant");
       if (sender.hasPermission("season.reload")) arguments.add("reload");
       arguments.add("help");
       arguments.add("");
       return arguments;
     }
     if (args.length == 2) {

       List<String> arguments = new ArrayList<>();
       if (args[0].equalsIgnoreCase("enchant") && sender.hasPermission("season.enchant")) {

         arguments.add("cool");
         arguments.add("warm");
       }
       return arguments;
     }
     return null;
   }
 }