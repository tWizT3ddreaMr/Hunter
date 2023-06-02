package me.tWizT3d_dreaMr.Hunter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class LangHandler {
private static YamlConfiguration Lang;
private static final Pattern pattern = Pattern.compile("(?<!\\\\)(&#[a-fA-F\\d]{6})");
public static void Init(YamlConfiguration l) {
	Lang=l;
} 

public static void sendMessage(Player p, String path) {
	if(path.equals("Main.Help")) {
		@SuppressWarnings("unchecked")
		List<String> helpmessage= (List<String>) Lang.getList(path);
		for(String message:helpmessage)
			p.sendMessage(Colorfy(message));
		return;
	}
	p.sendMessage(Colorfy(Lang.getString(path)));
		
	return;
}
public static String getMessage(String path) {
	
	return Colorfy(Lang.getString(path));
}

private static String Colorfy(String message) {
    Matcher matcher = pattern.matcher(message);
    while (matcher.find()) {
        String color = message.substring(matcher.start()+1, matcher.end());
            
        message = message.replace("&"+color, "" + ChatColor.of(color));
        matcher = pattern.matcher(message);
  
    }
    return ChatColor.translateAlternateColorCodes('&', message);
}
}
