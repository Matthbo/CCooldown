package matthbo.plugin.chatcooldown;

public class Config {
	
	public ChatCooldown cc = ChatCooldown.instance;
	
	public boolean isOn = true;
	
	public String isOn_LANG = "general.isOn";
	
	public void preInitCfg(){
		cc = ChatCooldown.instance;
		cc.saveDefaultConfig();
		
		InitCfg();
	}
	
	public void InitCfg(){
		//general
		isOn = cc.getConfig().getBoolean(isOn_LANG);
	}
	
	public void reload(){
		cc.reloadConfig();
		
		InitCfg();
	}
	
	public void set(String path, Object value){
		cc.getConfig().set(path, value);
		cc.saveConfig();
	}
	
}
