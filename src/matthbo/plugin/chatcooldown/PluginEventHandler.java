package matthbo.plugin.chatcooldown;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PluginEventHandler implements Listener {
	
	private Config config = ChatCooldown.instance.config;
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event){
		if(config.isOn){
			event.setCancelled(true);
			event.getPlayer().sendMessage("<" + event.getPlayer().getName() + "> " + event.getMessage());
		}else{}
	}

}
