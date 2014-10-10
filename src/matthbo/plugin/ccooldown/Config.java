package matthbo.plugin.ccooldown;

import java.io.File;

public class Config {
	
	public CCooldown cc = CCooldown.instance;
	
	public boolean isOn = true;
	public boolean autoOn = false;
	public int time = 20;
	public String message = "You send the message to quickly!";
	public int messages = 10;
	
	private String cfgVersion = "VERSION NOT FOUND!";
	private String cfgNeededVersion = "1.1";
	
	public String isOn_LANG = "general.isOn";
	public String autoOn_LANG = "auto.autoOn";
	public String messages_LANG = "auto.messages";
	public String time_LANG = "slowmode.time";
	public String message_LANG = "slowmode.message";
	
	private String cfgVersion_LANG = "doNotChange.cfgVersion";
	
	public void InitCfg(){
		cc = CCooldown.instance;
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
		
		//slowmode
		time = cc.getConfig().getInt(time_LANG);
		message = cc.getConfig().getString(message_LANG);
		
		//autoOn
		autoOn = cc.getConfig().getBoolean(autoOn_LANG);
		messages = cc.getConfig().getInt(messages_LANG);
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
