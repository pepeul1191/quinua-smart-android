package pe.edu.ulima.idic.quinua.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import pe.edu.ulima.idic.quinua.R;
import pe.edu.ulima.idic.quinua.dialogs.MenuSensorFragment;


public class EstacionesAdapter extends BaseAdapter implements ListAdapter, MenuSensorFragment.OnFragmentInteractionListener{
    private final Activity activity;
    private final JSONArray jsonArray;
    private final Resources resources;
    private HashMap<String, String> iconos = new HashMap<>();
    private final String packageName;

    public EstacionesAdapter(Activity activity, JSONArray jsonArray, Resources resources, String packageName) {
        this.activity = activity;
        this.jsonArray = jsonArray;
        this.iconos.put("Velocidad (m/s)","ic_viento");
        this.iconos.put("Grados centígrados (°C)","ic_temperatura");
        this.iconos.put("milibares","ic_lluvia");
        this.iconos.put("mmHg","ic_presion");
        this.iconos.put("Humedad relativa del aire (%)","ic_humedad");
        this.resources = resources;
        this.packageName = packageName;
    }

    public Resources getResources() {
        return resources;
    }

    @Override
    public int getCount() {
        if(null==this.jsonArray){
            return 0;
        }else{
            return this.jsonArray.length();
        }
    }

    @Override
    public JSONObject getItem(int position) {
        if(null == this.jsonArray){
            return null;
        }else{
            try {
                return (JSONObject) jsonArray.get(position);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public long getItemId(int position) {
        JSONObject jsonObject = (JSONObject) getItem(position);
        return jsonObject.optLong("id");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.adapter_estaciones, null);
        }

        TextView txtNombreSensor = (TextView)convertView.findViewById(R.id.txtNombreSensor);
        TextView txtDescripcionInstrumento = (TextView)convertView.findViewById(R.id.txtDescripcionInstrumento);
        TextView txtDescripcionTipo = (TextView)convertView.findViewById(R.id.txtDescripcionTipo);
        ImageView imgIcono = (ImageView)convertView.findViewById(R.id.imgIcono);

        JSONObject sensorJson = getItem(position);
        if(null != sensorJson){
            try {
                String mDrawableName = this.iconos.get(sensorJson.getString("des_tipo"));
                int resID = this.resources.getIdentifier(mDrawableName , "drawable", this.packageName);
                Drawable drawable = this.resources.getDrawable(resID );
                imgIcono.setImageDrawable(drawable);
                txtNombreSensor.setText(sensorJson.getString("nombre_sensor"));
                txtDescripcionInstrumento.setText(sensorJson.getString("desc_instrumento"));
                txtDescripcionTipo.setText(sensorJson.getString("des_tipo"));

                convertView.setTag(sensorJson.getInt("ide_sensor"));
                convertView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                    try{
                        //Intent myIntent = new Intent(activity, HistorialActivity.class);
                        //myIntent.putExtra("ide_sensor", view.getTag() + "");
                        //activity.startActivity(myIntent);

                        /*
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);
                        View mView = getLayoutInflater().inflate(R.layout.fragment_menu_sensor, null);
                        FragmentManager fm = getFragmentManager();
                        MenuSensorFragment dialogFragment = new MenuSensorFragment();
                        dialogFragment.show(fm, "Sample Fragment");


                        ListView listSensores = (ListView) findViewById(R.id.listSensores);
                        this.estacionesAdapter = new EstacionesAdapter(EstacionActivity.this, sensoresJsonArray, getResources(), getPackageName());
                        listSensores.setAdapter(this.estacionesAdapter);
                        */
                        View parent = (View)view.getParent();
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        view = activity.getLayoutInflater().inflate(R.layout.fragment_menu_sensor, (ViewGroup) parent, false);

                        ListView listSensores = (ListView) view.findViewById(R.id.menu_list);
                        listSensores.setAdapter(new MenuEstacionAdapter(parent.getContext()));

                        builder.setView(view);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }catch (Exception e){
                        Log.d("TRYXD", e.toString());
                    }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return convertView;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
