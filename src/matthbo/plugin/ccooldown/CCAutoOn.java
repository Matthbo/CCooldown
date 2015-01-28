package matthbo.plugin.ccooldown;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CCAutoOn {
	
	public Config config = CCooldown.instance.config;
	
	public final int checkTime = config.messages * 1000;
	private final List<String> messages = new ArrayList<String>();
	
	public void addMSG(String msg){
		this.messages.add(msg);
	}
	
	public void check(Player player){
		if(this.messages.size() >= config.messages && !config.isOn) {config.isOn = true; config.set(config.isOn_LANG, true); CCooldown.instance.messageToAll(player, ChatColor.GOLD +"SlowMode is on!");}
		if(this.messages.size() < (config.messages / 2) && config.isOn){config.isOn = false; config.set(config.isOn_LANG, false); CCooldown.instance.messageToAll(player, ChatColor.GOLD +"SlowMode is off!");}
		messages.removeAll(messages);
	}

}
