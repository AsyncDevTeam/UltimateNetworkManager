package fr.asynchronous.unm.core.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.asynchronous.unm.core.UNMPlugin;

public class ExceptionUtils {
	
	private ExceptionUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Register the exception
	 * 
	 * @param cast true to send message or false without message
	 * @return
	 */
	public static boolean register(final Throwable throwable, final boolean cast) {
		UNMPlugin plugin = UNMPlugin.getInstance();
		if (cast)
			plugin.getLogger().warning("*** AN ERROR OCCURED : " + throwable.getMessage() + " ***");
		String timeLog = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
		File folder = new File(plugin.getDataFolder(), "reports/");
		if (!folder.exists())
			folder.mkdirs();
		File logFile = new File(plugin.getDataFolder(), "reports/" + timeLog + ".txt");

		StringWriter errors = new StringWriter();
		throwable.printStackTrace(new PrintWriter(errors));

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
			writer.write(errors.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
