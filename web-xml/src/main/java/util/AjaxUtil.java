package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AjaxUtil
{
    public static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
    }

    public static String getJson(Object data)
    {
        return getJson(0, null, data);
    }

    public static String getJson(int errorCode, String message, Object data)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errorCode", errorCode);
        map.put("message", message);
        map.put("data", data);

        return JSON.toJSONString(map, config);
    }
}