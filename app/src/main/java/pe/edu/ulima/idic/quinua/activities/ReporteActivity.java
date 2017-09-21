package pe.edu.ulima.idic.quinua.activities;

import android.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pe.edu.ulima.idic.quinua.R;
import pe.edu.ulima.idic.quinua.fragments.FechaFragment;

public class ReporteActivity extends AppCompatActivity implements FechaFragment.OnFragmentInteractionListener{

    private int ideSensor;
    private LinearLayout linearFechaInicio;
    private TextView txtInicio;
    private TextView txtFin;
    private String itemReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int idSensor = Integer.parseInt(getIntent().getStringExtra("idSensor"));
        String itemRerpote = getIntent().getStringExtra("itemReporte");
        //Log.d("idSensor", idSensor + "");
        //Log.d("itemReporte", itemRerpote);
        this.ideSensor = idSensor;
        this.itemReporte = itemRerpote;
        setContentView(R.layout.activity_reporte);
        this.txtInicio = (TextView) findViewById(R.id.txtInicio);
        this.txtFin = (TextView) findViewById(R.id.txtFin);
    }

    public int getIdeSensor() {
        return this.ideSensor;
    }

    public void setIdeSensor(int ideSensor) {
        this.ideSensor = ideSensor;
    }

    public void btnSetFechaInicio(View v) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReporteActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.fragment_fecha, null);
        FragmentManager fm = getFragmentManager();
        FechaFragment dialogFragment = new FechaFragment("inicio");
        dialogFragment.show(fm, "Sample Fragment");
        //Log.d("getFechaSeleccionadaString", dialogFragment.getFechaSeleccionadaString());
    }

    public void btnSetFechaFin(View v) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReporteActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.fragment_fecha, null);
        FragmentManager fm = getFragmentManager();
        FechaFragment dialogFragment = new FechaFragment("fin");
        dialogFragment.show(fm, "Sample Fragment");
    }

    @Override
    public void onFinishEditDialog(String rptaFechaFragment) {
        String[] rpta = rptaFechaFragment.split("::");
        if(rpta[1].equalsIgnoreCase("inicio")){
            txtInicio.setText(rpta[0]);
        }else if(rpta[1].equalsIgnoreCase("fin")){
            txtFin.setText(rpta[0]);
        }

    }
}
