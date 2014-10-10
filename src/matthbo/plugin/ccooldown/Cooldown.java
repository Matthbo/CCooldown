package matthbo.plugin.ccooldown;

import java.util.UUID;

public class Cooldown {
	
	private final UUID player;
	private final long length;
	private final long time;

	public Cooldown(UUID player, long length, long time){
		this.player = player;
		this.length = length;
		this.time = time;
	}

	public UUID getPlayer() {
		return this.player;
	}

	public boolean isExpired() {
		return System.currentTimeMillis() >= this.time + this.length;
	}

	//not used, please tell me if needed!
	/*public long getTimeRemaining() {
		return this.time + this.length - System.currentTimeMillis();
	}*/

}
