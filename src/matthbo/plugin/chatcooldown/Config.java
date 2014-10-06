package matthbo.plugin.chatcooldown;

public class Config {
	
	public ChatCooldown cc = ChatCooldown.instance;
	
	public boolean isOn = true;
	
	public String isOn_LANG = "general.isOn";
	
	public void loadCfg(){
		cc = ChatCooldown.instance;
		cc.saveDefaultConfig();
		
		//general
		isOn = cc.getConfig().getBoolean(isOn_LANG);
	}
	
	public void set(String path, Object value){
		cc.getConfig().set(path, value);
		cc.saveConfig();
	}
	
}
