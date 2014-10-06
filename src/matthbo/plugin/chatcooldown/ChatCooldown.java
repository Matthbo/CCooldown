package matthbo.plugin.chatcooldown;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
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
		instance = this;
		
		config.loadCfg();
		
		getLogger().info("ChatCooldown has been Activated");
	}
	
	public void onDisable(){
		getLogger().info("ChatCooldown has been Disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("ccooldown")){
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("disable")){
					if(!config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is already Disabled!");}
					config.isOn = false;
					config.set(config.isOn_LANG, false);
					sender.sendMessage(pluginMSG + "ChatCooldown is Disabled!");
					
					return true;
				}
				else if(args[0].equalsIgnoreCase("enable")){
					if(config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is already Enabled!"); return true;}
					config.isOn = true;
					config.set(config.isOn_LANG, true);
					sender.sendMessage(pluginMSG + "ChatCooldown is Enabled!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("reload")){
					reloadConfig();
					return true; //edit
				}
			}else{
				if(config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is Enabled!"); return true;}
				if(!config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is Disabled!"); return true;}
				else sender.sendMessage(pluginUsage + "something went wrong! ;(");
				return true;
			}
		}
		return false;
	}
	
}