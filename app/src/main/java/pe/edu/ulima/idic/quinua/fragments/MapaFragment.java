package pe.edu.ulima.idic.quinua.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import pe.edu.ulima.idic.quinua.R;
import pe.edu.ulima.idic.quinua.activities.EstacionActivity;
import pe.edu.ulima.idic.quinua.utils.Constants;
import pe.edu.ulima.idic.quinua.utils.Httparty;

/**
 * Created by pepe on 27/06/17.
 */

public class MapaFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private static final String TAG = "MapaFrgment";
    MapView mapView;
    GoogleMap googleMap;

    private WebView contactWebView;

    public MapaFragment(){
    }

    public static MapaFragment newInstance() {
        MapaFragment fragment = new MapaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mapa, container, false);
        mapView = (MapView) v.findViewById(R.id.mapaEstaciones);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this); //this is important

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            String urlEstaciones = Constants.BASE_URL + "estacion/listar";
            Httparty httpartyEstacion = new Httparty(urlEstaciones, "GET");
            httpartyEstacion.action();

            JSONArray estacionesJsonArray = new JSONArray(httpartyEstacion.getRpta());

            for(int i = 0; i < estacionesJsonArray.length(); i++){
                JSONObject estacionJson = estacionesJsonArray.getJSONObject(i);
                double latitud = estacionJson.getDouble("latitud");
                double longitud = estacionJson.getDouble("longitud");
                String descripcion = estacionJson.getString("descripcion");

                LatLng estacionMapa = new LatLng(latitud, longitud);
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                Marker estacion = googleMap.addMarker(new MarkerOptions().position(estacionMapa));
                estacion.setTag(estacionJson.getInt("ide_estacion"));
                googleMap.setOnMarkerClickListener(this);
            }

            LatLng peru = new LatLng(-10.569220973686791, -75.20462410000005);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(peru, 5));
        }catch (Exception e){
            Log.d("TRY1", e.toString());
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            String urlDetalleEstacion = Constants.BASE_URL + "estacion/detalle/" + marker.getTag().toString();
            Httparty httpartyDetalleEstacion = new Httparty(urlDetalleEstacion, "GET");
            httpartyDetalleEstacion .action();
            //Log.d("onMarkerClick", marker.getTag().toString());
            //Log.d("onMarkerClick - MapaFragment", httpartyDetalleEstacion.getRpta());
            Intent myIntent = new Intent(this.getActivity(), EstacionActivity.class);
            myIntent.putExtra("detalleEstacion", httpartyDetalleEstacion.getRpta());
            this.getActivity().startActivity(myIntent);
        }catch (Exception e){
            Log.d("TRY1", e.toString());
        }

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
