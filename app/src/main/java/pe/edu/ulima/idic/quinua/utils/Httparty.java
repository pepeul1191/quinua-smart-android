package pe.edu.ulima.idic.quinua.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Httparty {
    private String url;
    private String metodo;
    private int respondeCode;
    private String rpta;

    public Httparty(String url, String metodo){
        this.url = url;
        Log.d("1","++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Log.d("URL", url);
        Log.d("2","++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        this.metodo = metodo;
        this.rpta = "";
    }

    public void action(){
        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(this.metodo);
            conn.setRequestProperty("Accept", "application/json");
            this.respondeCode = conn.getResponseCode();

            if (this.respondeCode != 200) {
                this.rpta = "Failed : HTTP error code : " + conn.getResponseCode();
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                this.rpta = this.rpta + output;
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            this.rpta = e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            this.rpta = e.toString();
        }
    }

    public int getRespondeCode() {
        return respondeCode;
    }

    public void setRespondeCode(int respondeCode) {
        this.respondeCode = respondeCode;
    }

    public String getRpta() {
        return rpta;
    }
}