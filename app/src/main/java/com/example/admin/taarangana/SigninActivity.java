package com.example.admin.taarangana;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Admin on 04-06-2017.
 */

public class SigninActivity extends AsyncTask<String,Void,String> {
    private TextView statusField;
    private Context context;
    private int byGetOrPost = 0;

    //flag 0 means get and 1 means post.(By default it is get.)
    public SigninActivity(Context context, TextView statusField, int flag) {
        this.context = context;
        this.statusField = statusField;
        //this.roleField = roleFied;
        byGetOrPost = flag;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... strings) {

        try{
            String username = (String)strings[0];
            String password = (String)strings[1];

            String link="http://192.168.43.225:80/tarara.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" +
                    URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(password, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            return sb.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result){

        this.statusField.setText("Login Successful");
        //this.roleField.setText(result);
    }

}
