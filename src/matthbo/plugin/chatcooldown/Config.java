package matthbo.plugin.chatcooldown;

import java.io.File;

public class Config {
	
	public ChatCooldown cc = ChatCooldown.instance;
	
	public boolean isOn = true;
	public boolean autoOn = false;
	
	private String cfgVersion = "0";
	private String cfgNeededVersion = "0.1";
	
	public String isOn_LANG = "general.isOn";
	public String autoOn_LANG = "general.autoOn";
	
	private String cfgVersion_LANG = "doNotChange.cfgVersion";
	
	public void InitCfg(){
		cc = ChatCooldown.instance;
		cc.saveDefaultConfig();
		
		cfgVersion = cc.getConfig().getString(cfgVersion_LANG);
		
		//checks if config is uptodate
		if(!cfgVersion.equalsIgnoreCase(cfgNeededVersion)){
			try{
			File dataFolder = cc.getDataFolder();
			File cfgFile = new File(dataFolder, "config.yml");
			File oldCfgFile = new File(dataFolder, "config_old.yml");
			if(oldCfgFile.exists()) oldCfgFile.delete();
			cfgFile.renameTo(oldCfgFile);
			cc.saveDefaultConfig();
			cc.getLogger().warning("New Config File Created!");
			cc.getLogger().warning("Old Configs Are Saved In 'config_old.yml'!");
			}catch(Exception e) {e.printStackTrace();}
		}
		
		//cc.getLogger().info("---" + cfgVersion + "---");
		//cc.getLogger().info("---" + cfgNeededVersion + "---");
		
		//general
		isOn = cc.getConfig().getBoolean(isOn_LANG);
		autoOn = cc.getConfig().getBoolean(autoOn_LANG);
		
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
