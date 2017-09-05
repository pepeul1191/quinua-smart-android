package pe.edu.ulima.idic.quinua.adapters;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import pe.edu.ulima.idic.quinua.R;
import pe.edu.ulima.idic.quinua.activities.HistorialActivity;


public class EstacionesAdapter extends BaseAdapter implements ListAdapter{
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
        this.iconos.put("Presion (mmHg)","ic_presion");
        this.iconos.put("Humedad relativa del aire (%)","ic_humedad");
        this.resources = resources;
        this.packageName = packageName;
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
                            Intent myIntent = new Intent(activity, HistorialActivity.class);
                            myIntent.putExtra("ide_sensor", view.getTag() + "");
                            activity.startActivity(myIntent);
                        }catch (Exception e){
                            Log.d("TRY1", e.toString());
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return convertView;
    }

}
