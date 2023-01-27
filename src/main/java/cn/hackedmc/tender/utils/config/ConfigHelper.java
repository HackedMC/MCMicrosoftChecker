package cn.hackedmc.tender.utils.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigHelper {
    private final Map<String, String> configMap = new HashMap<>();

    public ConfigHelper(String text) {
        for (String str : text.split("\n")) {
            if (str.split("=").length >= 2) configMap.put(str.split("=")[0], str.split("=")[1]);
        }
    }

    public String getString(String name, String normal) {
        return configMap.get(name) != null ? configMap.get(name) : normal;
    }

    public Boolean getBool(String name, Boolean normal) {
        return configMap.get(name) != null ? Boolean.parseBoolean(configMap.get(name)) : normal;
    }

    public int getInt(String name, int normal) {
        return configMap.get(name) != null ? Integer.parseInt(configMap.get(name)) : normal;
    }
}
