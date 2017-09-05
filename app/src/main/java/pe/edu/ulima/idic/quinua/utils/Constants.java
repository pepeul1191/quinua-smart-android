package pe.edu.ulima.idic.quinua.utils;

import java.util.HashMap;

public class Constants {
    private static HashMap<String, String> mapa = new HashMap<String, String>();

    public static final String BASE_URL = "https://powerful-journey-11909.herokuapp.com/";//"https://quinua-smart.000webhostapp.com/";//"http://172.16.28.50:3000/"; //http://45.55.64.102:3000/";//"";

    public static void set(String llave, String valor){
        mapa.put(llave, valor);
    }

    public static String get(String llave){
        if(mapa.containsKey(llave)){
            return mapa.get(llave);
        }else{
            return "null";
        }
    }
}
