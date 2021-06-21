package srcapp.myshadow.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GateOpenClass {

    public static HttpURLConnection getHttpConnection(String url, String type){
        URL uri = null;
        HttpURLConnection con = null;
        try{
            uri = new URL(url);
            con = (HttpURLConnection) uri.openConnection();
            con.setRequestMethod(type);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("connection", "keep-alive");
            con.setRequestProperty("UID", "443346938");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }

    public static void sendReq(String url, String type, String reqbody){
        HttpURLConnection con = null;
        String result = null;
        try {
            con = getHttpConnection( url , type);
            if( reqbody != null){
                OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                out.write(reqbody);
                out.flush();
                out.close();
            }
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String temp = null;
            StringBuilder sb = new StringBuilder();
            while((temp = in.readLine()) != null){
                sb.append(temp).append(" ");
            }
            result = sb.toString();
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void open() {
        new Thread(new Runnable() {
            public void run() {
                String sBuf =  "{   \"command\" : \"dispauth\",   \"data\" : \"high priority\",   \"imei\" : \"868999035186448\",   \"pass\" : \"2810\"}";

                String sBuf1 = "{   \"command\" : \"gsmst\",   \"data\" : \"\",   \"imei\" : \"868999035186448\",   \"pass\" : \"2810\" }";
                String sBuf2 = "{   \"command\" : \"gchrono\",\"data\" : {\"edate\" : \"18052021\",\"number\" : \"x\",\"operation\" : \"1\",\"response\" : \"1\",\"sdate\" : \"18052021\"},\"imei\" : \"868999035186448\",\"pass\" : \"2810\"}";
                String sBuf3 = "{   \"command\" : \"close\",  \"data\" : \"\",  \"imei\" : \"868999035186448\",  \"pass\" : \"2810\"}";
                String sBuf4 = "{   \"command\" : \"setout\",   \"data\" : \"\",   \"imei\" : \"868999035186448\",   \"pass\" : \"2810\"}";


                // TODO code application logic here
                sendReq("http://92.255.190.104:10951", "PUT", sBuf );
                sendReq("http://92.255.190.104:10951", "PUT", sBuf4 );
                sendReq("http://92.255.190.104:10951", "PUT", sBuf3 );
            }
        }).start();
    }

}
