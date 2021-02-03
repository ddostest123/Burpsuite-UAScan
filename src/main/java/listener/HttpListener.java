package listener;

import burp.*;
import common.Config;
import common.SimilarityUtils;
import http.HttpUtil;
import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;
import ui.UAScan;

import java.util.HashMap;
import java.util.Map;

public class HttpListener implements IHttpListener {

    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;


    public HttpListener(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.helpers = this.callbacks.getHelpers();
    }

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
        if (UAScan.isEnable()) {
            if (messageIsRequest) {
                String method = "GET";
                String url;
                Map<String, String> headers = new HashMap<>();
                IHttpService httpService = messageInfo.getHttpService();
                IRequestInfo requestInfo = helpers.analyzeRequest(httpService, messageInfo.getRequest());
                url = requestInfo.getUrl().toString();
                for (String header : requestInfo.getHeaders()) {
                    if (header.endsWith("HTTP/1.1")
                            || header.endsWith("HTTP/1.0")
                            || header.endsWith("HTTP/2.0")) {
                        String[] temp = header.split(" ");
                        method = temp[0].trim();
                    } else {
                        String[] temp = header.split(":");
                        String key = temp[0].trim();
                        String value = temp[1].trim();
                        headers.put(key, value);
                    }
                }
                doAnalyze(url, method, headers);
            }
        }
    }

    private void doAnalyze(String url, String method, Map<String, String> headers) {
        if(Config.filters.size()>0){
            for(String item:Config.filters){
                if(url.contains(item)){
                    return;
                }
            }
        }
        String[] suffix = new String[]{
                ".js", ".jsx", ".coffee", ".ts",
                ".css", ".less", ".scss", ".sass",
                ".ico", ".jpg", ".png", ".gif", ".bmp", ".svg",
                ".ttf", ".eot", ".woff", ".woff2",
                ".ejs", ".jade", ".vue"
        };
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        for (String item : suffix) {
            if (url.toLowerCase().endsWith(item)) {
                return;
            }
            if (url.toLowerCase().contains(item + "?")) {
                return;
            }
        }
        String oldResult = HttpUtil.doRequest(url, method, headers);
        headers.put("Cookie", "**********************");
        String newResult = HttpUtil.doRequest(url, method, headers);
        SimilarityStrategy strategy = new JaroWinklerStrategy();
        StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
        double score = service.score(oldResult,newResult);
        if (score>0.8){
            UAScan.setText(url);
        }
    }

}
