package listener;

import burp.*;
import common.PrintUtil;

public class HttpListener implements IHttpListener {

    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;


    public HttpListener(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.helpers = this.callbacks.getHelpers();
    }

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
        if (messageIsRequest) {
            IHttpService httpService = messageInfo.getHttpService();
            IRequestInfo requestInfo = helpers.analyzeRequest(httpService, messageInfo.getRequest());
            PrintUtil.print(callbacks, "Request URL:" + requestInfo.getUrl().toString());
            for (String header : requestInfo.getHeaders()) {
                if(header.endsWith("HTTP/1.1")
                        ||header.endsWith("HTTP/1.0")
                        ||header.endsWith("HTTP/2.0")){
                    String[] temp = header.split(" ");
                    String method = temp[0].trim();
                    String uri = temp[1].trim();
                    PrintUtil.print(callbacks,"method="+method+",uri="+uri);
                }else{
                    PrintUtil.print(callbacks, header);
                    String[] temp = header.split(":");
                    String key = temp[0].trim();
                    String value = temp[1].trim();
                    PrintUtil.print(callbacks,"key="+key+",value="+value);
                }
            }
        } else {
        }
    }
}
