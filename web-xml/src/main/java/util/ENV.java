package util;

import java.util.HashMap;
import java.util.Map;

public class ENV
{
    private static Map<String, String> map = new HashMap<String, String>();

    static {
        map.put("APP_NAME", "管理系统");
    }

    public static String G(String key)
    {
        if(!map.containsKey(key)) return null;
        return map.get(key);
    }
}