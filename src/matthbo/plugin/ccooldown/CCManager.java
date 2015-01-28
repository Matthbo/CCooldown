package matthbo.plugin.ccooldown;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CCManager {
	
	public Config config = CCooldown.instance.config;
	
	private final int cooldownTime = config.time * 1000;
	private final Map<UUID, Cooldown> cooldowns = new HashMap<UUID, Cooldown>();

	public void add(Cooldown cooldown){
		UUID player = cooldown.getPlayer();
		this.cooldowns.put(player, cooldown);
	}
	
	public boolean hasCooldown(UUID player){
		Cooldown cooldown = this.cooldowns.get(player);
		if(cooldown == null) return false;
		if(cooldown.isExpired()) {
			this.cooldowns.remove(player);
			return false;
		}
		
		return true;
	}
	
	public int getCooldownTime(){
		return this.cooldownTime;
	}
	
}
