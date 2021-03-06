package pe.edu.ulima.idic.quinua.activities;

import android.app.FragmentManager;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;

import pe.edu.ulima.idic.quinua.R;
import pe.edu.ulima.idic.quinua.fragments.FechaFragment;
import pe.edu.ulima.idic.quinua.reportes.RangoFechasFragment;
import pe.edu.ulima.idic.quinua.utils.Constants;
import pe.edu.ulima.idic.quinua.utils.Httparty;

public class ReporteActivity extends AppCompatActivity implements FechaFragment.OnFragmentInteractionListener, RangoFechasFragment.OnFragmentInteractionListener {

    private int ideSensor;
    private LinearLayout linearFechaInicio;
    private TextView txtInicio;
    private TextView txtFin;
    private TextView lblTituloReporte;
    private Button btnEnviarRangoFechas;
    private String itemReporte;

    public TextView getTxtInicio() {
        return txtInicio;
    }

    public TextView getTxtFin() {
        return txtFin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int idSensor = Integer.parseInt(getIntent().getStringExtra("idSensor"));
        String itemRerpote = getIntent().getStringExtra("itemReporte");
        this.ideSensor = idSensor;
        this.itemReporte = itemRerpote;
        setContentView(R.layout.activity_reporte);
        this.lblTituloReporte = (TextView) findViewById(R.id.lblTituloReporte);
        this.lblTituloReporte.setText(itemRerpote);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (itemRerpote){
            case "Promedio por día en rango de fechas":
                transaction.replace(R.id.content, new RangoFechasFragment());
                break;
            case "Máximo y minimo en rango de fechas":
                transaction.replace(R.id.content, new RangoFechasFragment());
                break;
            case "Máximo, minimo y promedio en rango de fechas":
                transaction.replace(R.id.content, new RangoFechasFragment());
                break;
            case "Mediciones por rango de tiempo de un día":

                break;
        }

        transaction.addToBackStack(null);
        transaction.commit();
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
            this.txtInicio = (TextView) findViewById(R.id.txtInicio);
            this.getTxtInicio().setText(rpta[0]);
        }else if(rpta[1].equalsIgnoreCase("fin")){
            this.txtFin = (TextView) findViewById(R.id.txtFin);
            this.getTxtFin().setText(rpta[0]);
        }
    }

    public void btnEnviarRangoFechasClick(View v){
        Log.d("TAB", "btnEnviarRangoFechasClick");
        this.setData();
    }

    private void setData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONArray historiosJSON = null;
        try{
            //String urlHistorico = Constants.BASE_URL + "sensor/historico/" + this.getIdeSensor() + "?fecha_inicio=2017-01-01&fecha_fin=2017-08-10";
            String urlHistorico = Constants.BASE_URL + "sensor/historico/" + this.getIdeSensor() + "?fecha_inicio=" + txtInicio.getText() + "&fecha_fin=" + txtFin.getText();
            Httparty httpartyHistorico = new Httparty(urlHistorico, "GET");
            httpartyHistorico.action();

            historiosJSON = new JSONArray(httpartyHistorico.getRpta());
            Log.d("JSON", historiosJSON.toString());
        } catch(Exception e){
            e.printStackTrace();
        }

        Log.d("SETDATE", "XD");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
