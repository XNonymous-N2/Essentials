package de.xnonymous.essentials.utils;

public class ColorUtils {
	
	public static String getColoredText(String text) {
		return text.replaceAll("&", "§");
	}

	public static String getStrippedText(String text) {
		String[] split = text.split("");
		for(int x = 0; x < split.length; x++)
			if(split[x].equals("§") && !split[x+1].equals(" "))
				text = text.replace(split[x] + split[x+1], "");
		return text;
	}

}