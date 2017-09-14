package pe.edu.ulima.idic.quinua.adapters;

import android.content.Context;
import android.content.DialogInterface;
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

public class MenuEstacionAdapter extends BaseAdapter implements ListAdapter {
    private Context context;
    private List<String> items;

    public MenuEstacionAdapter(Context context) {
        List<String> items = new ArrayList<>();
        items.add("Promedio por día en rango de fechas");
        items.add("Rango de tiempo por día");
        items.add("Máximos y minimos por día");
        this.context = context;
        this.items = items;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.adapter_menu_sensor, parent, false);
        }

        // Set data into the view.
        TextView menuSensorItem = (TextView) rowView.findViewById(R.id.txtItemMenu);

        String item = this.items.get(position);

        if(null != item){
            menuSensorItem.setText(item);
            /*
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    try{
                        AlertDialog.Builder alertbox = new AlertDialog.Builder(view.getRootView().getContext());
                        alertbox.setTitle("XD");
                        //alertbox.setMessage("No Internet Connection");
                        alertbox.setNeutralButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface arg0, int arg1) {
                                    }
                                });
                        alertbox.show();
                    }catch (Exception e){
                        Log.d("TRY1", e.toString());
                    }
                }
            });
            */
        }

        return rowView;
    }
}
