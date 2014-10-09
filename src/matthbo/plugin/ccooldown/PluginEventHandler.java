package matthbo.plugin.ccooldown;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PluginEventHandler implements Listener {
	
	private CCManager manager =  new CCManager();
	
	private Config config = CCooldown.instance.config;
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		UUID id = player.getUniqueId();
		
		if(config.isOn){
			if((!bypass(player)) && (manager.hasCooldown(id))){
				event.setCancelled(true);
				player.sendMessage(CCooldown.pluginMSG + "" + ChatColor.DARK_GRAY + config.message);
				player.sendMessage(CCooldown.pluginMSG + "" + ChatColor.DARK_GRAY + "Time between messages: " + ChatColor.GRAY + config.time + " seconds");
			}else{
				this.manager.add(new Cooldown(id, manager.getCooldownTime(),System.currentTimeMillis()));
			}
		}
	}
	
	private boolean bypass(Player player){
		return player.hasPermission(PermissionList.bypass) || player.hasPermission(PermissionList.admin) || player.isOp();
	}

}
