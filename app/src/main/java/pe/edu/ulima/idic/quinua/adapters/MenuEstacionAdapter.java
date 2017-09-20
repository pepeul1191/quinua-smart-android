package pe.edu.ulima.idic.quinua.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.ulima.idic.quinua.R;
import pe.edu.ulima.idic.quinua.activities.ReporteActivity;

public class MenuEstacionAdapter extends BaseAdapter implements ListAdapter {
    private Context context;
    private int idSensor;
    private List<String> items;

    public MenuEstacionAdapter(Context context, int idSendor) {
        List<String> items = new ArrayList<>();
        items.add("Promedio por día en rango de fechas");
        items.add("Rango de tiempo por día");
        items.add("Máximos y minimos por día");
        this.context = context;
        this.items = items;
        this.idSensor = idSendor;
    }

    public Context getContext() {
        return context;
    }

    public int getIdSensor() {
        return this.idSensor;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public String getItem(int i) {
        if(null == this.items.get(i)){
            return null;
        }else{
            return this.items.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.adapter_menu_sensor, parent, false);
        }

        // Set data into the view.
        final TextView menuSensorItem = (TextView) rowView.findViewById(R.id.txtItemMenu);
        String item = this.items.get(position);
        //Log.d("ide_sensor", this.getIdSensor() + "");
        if(null != item){
            menuSensorItem.setText(item);
            menuSensorItem.setTag(this.getIdSensor());
            menuSensorItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                try{
                    Log.d("idSensor",(Integer)view.getTag() + "");
                    String x = (String) menuSensorItem.getText();
                    Log.d("TEXTO", x);

                    Intent intent = new Intent(getContext(), ReporteActivity.class);
                    getContext().startActivity(intent);
                }catch (Exception e){
                    Log.d("TRY1", e.toString());
                }
                }
            });
        }

        return rowView;
    }
}
