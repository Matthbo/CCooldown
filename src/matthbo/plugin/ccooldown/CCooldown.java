package matthbo.plugin.ccooldown;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CCooldown extends JavaPlugin{
	
	public Config config = new Config();
	
	public static CCooldown instance;
	
	public static final Object pluginMSG = ChatColor.DARK_PURPLE + "[CCooldown] " + ChatColor.RESET;
	public static final Object pluginUsage = pluginMSG + "" + ChatColor.DARK_RED;
	
	
	/**
	 * used for addons
	 */
	public static Plugin getPlugin(){
		return instance;
	}
	
	public void onEnable(){
		PluginManager pw = getServer().getPluginManager();
		instance = this;
		
		config.InitCfg();
		
		pw.registerEvents(new PluginEventHandler(), this);
		
		getLogger().info("CCooldown has been Activated");
	}
	
	public void onDisable(){
		getLogger().info("CCooldown has been Disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("ccooldown") && sender.hasPermission(PermissionList.admin)){
			if(args.length > 0){
				if(args.length == 1 && args[0].equalsIgnoreCase("disable")){
					if(!config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is already Disabled!"); return true;}
					config.isOn = false;
					config.set(config.isOn_LANG, false);
					sender.sendMessage(pluginMSG + "ChatCooldown is Disabled!");
					messageToAll(sender, ChatColor.GOLD +"SlowMode is off!");
					return true;
				}
				else if(args.length == 1 && args[0].equalsIgnoreCase("enable")){
					if(config.isOn){sender.sendMessage(pluginMSG + "ChatCooldown is already Enabled!"); return true;}
					config.isOn = true;
					config.set(config.isOn_LANG, true);
					sender.sendMessage(pluginMSG + "ChatCooldown is Enabled!");
					messageToAll(sender, ChatColor.GOLD +"SlowMode is on!");
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
	
	public void messageToAll(CommandSender sender, String msg){
		Player[] allPlayers = sender.getServer().getOnlinePlayers();
		
		for(int j = 0; j < allPlayers.length; j++){
        	Player target = allPlayers[j];
        	target.sendMessage(pluginMSG + msg);
        	this.getLogger().info(msg);
        }
		
	}
	
}