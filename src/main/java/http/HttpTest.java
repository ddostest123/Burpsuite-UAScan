package http;

public class HttpTest {
    static String uri = "http://59.67.225.28/js/bootstrap/bootstrap.min.js";

    public static void getTest(){
        String result = HttpUtil.doGet(uri);
        System.out.println(result);
    }

    public static void main(String[] args) {
        getTest();
    }

}
