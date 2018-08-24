package fr.asynchronous.unm.core.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Utils {
	
	// Path
	public static String configPath;
	
	private Utils() {
		throw new IllegalStateException("Utility class");
	}

	public static Location toLocation(final String string) {
		final String[] splitted = string.split("_");
		World world = Bukkit.getWorld(splitted[0]);
		if (world == null || splitted.length < 6) {
			world = Bukkit.getWorlds().get(0);
		}
		return new Location(world, Double.parseDouble(splitted[1]), Double.parseDouble(splitted[2]), Double.parseDouble(splitted[3]), Float.parseFloat(splitted[4]), Float.parseFloat(splitted[5]));
	}

	public static String toString(final Location location) {
		final World world = location.getWorld();
		return String.valueOf(world.getName()) + "_" + location.getX() + "_" + location.getY() + "_" + location.getZ() + "_" + location.getYaw() + "_" + location.getPitch();
	}
	
	/**
	 * C'est une Integer ?
	 * @param s
	 * @return
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	/**
	 * C'est une Double ?
	 * @param s
	 * @return
	 */
	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException | NullPointerException e) {
			ExceptionUtils.register(e, true);
			return false;
		}
		return true;
	}
	
	/**
	 * Get the path of your configuration 
	 * @return
	 */
	public static String getConfigPath() {
		return configPath;
	}
	

	public interface Test<T> {

		public void test(T t);

	}
	


}
