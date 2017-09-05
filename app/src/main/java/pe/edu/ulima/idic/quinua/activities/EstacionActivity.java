package pe.edu.ulima.idic.quinua.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.ulima.idic.quinua.R;
import pe.edu.ulima.idic.quinua.adapters.EstacionesAdapter;

public class EstacionActivity extends AppCompatActivity {
    private TextView txtNombre;
    private TextView txtDescipcion;
    private ListView listSensores;
    private EstacionesAdapter estacionesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_estacion);

        this.txtNombre = (TextView) findViewById(R.id.txtNombre);
        this.txtDescipcion = (TextView) findViewById(R.id.txtDescipcion);

        String detalleEstacionString = getIntent().getStringExtra("detalleEstacion");
        Log.d("onMarkerClick - EstacionActivity", detalleEstacionString);

        JSONObject jobject = null;
        try {
            jobject = new JSONObject(detalleEstacionString);
            this.txtNombre.setText(jobject.getString("nombre_estacion"));
            this.txtDescipcion.setText(jobject.getString("descripcion"));

            //JSONArray sensoresJsonArray = new JSONArray(httpartyEstacion.getRpta());

            JSONArray sensoresJsonArray = jobject.getJSONArray("sensores");

            ListView listSensores = (ListView) findViewById(R.id.listSensores);
            this.estacionesAdapter = new EstacionesAdapter(EstacionActivity.this, sensoresJsonArray, getResources(), getPackageName());
            listSensores.setAdapter(this.estacionesAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
