package url;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class UrlCall {

    private final String USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/602.4.6 (KHTML, like Gecko) Version/10.0 Mobile/14D27 Safari/602.1";

    public static void main(String[] args) {
        UrlCall call = new UrlCall();
        try {
            call.sendGet("http://xxx/rtb/rtbShop", "uid=" + java.net.URLEncoder.encode(
                    "K_|90927|012000004412^10882|8524^98203|1724^5338|037000000800:024002000942:024002000968:024002000924:024002000714:024002000701:024002000995^10882|8278^_|23a91760d809bdc62836cfdc164b84f81ac369",
                    "UTF-8"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // HTTP GET request
    private void sendGet(String url, String urlParameters) {
        URL obj;
        HttpURLConnection con = null;
        try {
            obj = new URL(url + "?" + urlParameters);
            con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");

            // add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("Sending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);


            // try-with-resources
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))){
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // print result
                System.out.println(response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
