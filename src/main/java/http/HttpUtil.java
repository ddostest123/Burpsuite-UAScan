package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtil {

    public static String doRequest(String url, String method, Map<String, String> headers) {
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        BufferedReader br = null;
        String content;
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod(method);
            conn.setReadTimeout(60000);
            conn.setConnectTimeout(60000);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            if (conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                while ((content = br.readLine()) != null) {
                    result.append(content);
                }
            } else {
                result.append(String.valueOf(conn.getResponseCode()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result.toString();
    }

}
