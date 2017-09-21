package pe.edu.ulima.idic.quinua.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import pe.edu.ulima.idic.quinua.R;

public class ReporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int idSensor = Integer.parseInt(getIntent().getStringExtra("idSensor"));
        String itemRerpote = getIntent().getStringExtra("itemReporte");

        Log.d("idSensor", idSensor + "");
        Log.d("itemReporte", itemRerpote);

        setContentView(R.layout.activity_reporte);
    }
}
