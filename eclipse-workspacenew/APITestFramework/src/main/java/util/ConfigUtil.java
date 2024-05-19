package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigUtil {
	private static final Map<String, String> properties = new HashMap<>();

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 2) {
                    properties.put(tokens[0], tokens[1]);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.get(key);
    }
}
