package util;

import java.io.*;

public class FileUtil
{

    public static String readAllContent(String path) throws Exception
    {
        BufferedReader reader = null;
        try {
            StringBuilder sb = new StringBuilder();
            // reader = new BufferedReader(new FileReader(path));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            int size;
            int bufSize = 1024;
            char[] buf = new char[bufSize];
            while((size = reader.read(buf, 0, bufSize)) > 0) {
                sb.append(buf, 0, size);
            }
            return sb.toString();
        } finally {
            if(reader != null) reader.close();
        }
    }

    public static void writeAllContent(String path, String s) throws Exception
    {
        BufferedWriter writer = null;
        try {
            // writer = new BufferedWriter(new FileWriter(path));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            writer.write(s);
        } finally {
            if(writer != null) writer.close();
        }
    }
}
