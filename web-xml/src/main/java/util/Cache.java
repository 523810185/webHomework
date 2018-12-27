package util;

import java.io.File;

public class Cache
{
    final static String CacheDir = "./cache";

    static {
        File dir = new File(CacheDir);
        if(!dir.exists() || !dir.isDirectory()) dir.mkdirs();
    }

    public static String getRelativePath(String path)
    {
        String s = String.format("%s%s%s", CacheDir, path.startsWith("/") ? "" : "/", path);
        return new File(s).getAbsolutePath();
    }
}
