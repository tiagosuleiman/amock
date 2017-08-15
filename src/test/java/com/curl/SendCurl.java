package com.curl;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendCurl {

  public static void main(String[] args) {

    try {

    String url = "http://localhost:8080/segur-api/oauth/token?grant_type=password&username=admin&password=admin";

    URL obj = new URL(url);
    HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true);

    conn.setRequestMethod("POST");

    String userpass = "myRestClient:P@ssw0rd";
    String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
    conn.setRequestProperty ("Authorization", basicAuth);

    String data =  "{\"format\":\"json\",\"pattern\":\"#\"}";
    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
    out.write(data);
    out.close();

    new InputStreamReader(conn.getInputStream());   

    } catch (Exception e) {
    e.printStackTrace();
    }

  }

}
