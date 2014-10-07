package matthbo.plugin.chatcooldown;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatCooldown extends JavaPlugin{
	
	public Config config = new Config();
	
	public static ChatCooldown instance;
	
	public static final Object pluginMSG = ChatColor.DARK_PURPLE + "[ChatCooldown] " + ChatColor.RESET;
	public static final Object pluginUsage = pluginMSG + "" + ChatColor.DARK_RED;
	
	public static Plugin getPlugin(){
		return instance;
	}
	
	public void onEnable(){
		PluginManager pw = getServer().getPluginManager();
		instance = this;
		
		config.InitCfg();
		
		pw.registerEvents(new PluginEventHandler(), this);
		
		getLogger().info("ChatCooldown has been Activated");
	}
	
	public void onDisable(){
		getLogger().info("ChatCooldown has been Disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("ccooldown")){
			if(args.length > 0){
				if(args.length == 1 && args[0].equalsIgnoreCase("disable")){
					if(!config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is already Disabled!"); return true;}
					config.isOn = false;
					config.set(config.isOn_LANG, false);
					sender.sendMessage(pluginMSG + "ChatCooldown is Disabled!");
					
					return true;
				}
				else if(args.length == 1 && args[0].equalsIgnoreCase("enable")){
					if(config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is already Enabled!"); return true;}
					config.isOn = true;
					config.set(config.isOn_LANG, true);
					sender.sendMessage(pluginMSG + "ChatCooldown is Enabled!");
					return true;
				}
				else if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
					config.reload();
					sender.sendMessage(pluginMSG + "Reloaded!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("auto")){
					if(args.length == 2 && args[1].equalsIgnoreCase("on")){
						config.autoOn = true;
						config.set(config.autoOn_LANG, true);
						sender.sendMessage(pluginMSG + "AutoOn is On!");
						return true;
					}
					if(args.length == 2 && args[1].equalsIgnoreCase("off")){
						config.autoOn = false;
						config.set(config.autoOn_LANG, false);
						sender.sendMessage(pluginMSG + "AutoOn is Off!");
						return true;
					}
					else{
						if(config.autoOn) sender.sendMessage(pluginMSG + "AutoOn is On!");
						if(!config.autoOn) sender.sendMessage(pluginMSG + "AutoOn is Off!");
						return true;
					}
				}
			}
			
			else{
				if(config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is Enabled!"); return true;}
				if(!config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is Disabled!"); return true;}
				else sender.sendMessage(pluginUsage + "something went wrong! ;(");
				return true;
			}
		}
		return false;
	}
	
}