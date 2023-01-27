package cn.hackedmc.tender.utils.config;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {
    public static String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String inString;
            while ((inString = in.readLine()) != null) {
                sb.append(inString);
                sb.append("\n");
            }
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
