package common;

import burp.IBurpExtenderCallbacks;

import java.io.IOException;

public class PrintUtil {
    public static void print(IBurpExtenderCallbacks callbacks, String content) {
        try {
            content += "\n";
            callbacks.getStdout().write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
