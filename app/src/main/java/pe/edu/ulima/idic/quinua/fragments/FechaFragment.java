package pe.edu.ulima.idic.quinua.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import pe.edu.ulima.idic.quinua.R;
import pe.edu.ulima.idic.quinua.activities.HistorialActivity;

public class FechaFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnSelcionarFecha;
    private DatePicker datePicker;
    private String fechaSeleccionadaString;
    private String idOrigen;
    Activity activity;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FechaFragment() {
        // Required empty public constructor
    }

    public String getFechaSeleccionadaString() {
        return fechaSeleccionadaString;
    }

    public static FechaFragment newInstance(String param1, String param2) {
        FechaFragment fragment = new FechaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fecha, container, false);
        //getDialog().setTitle("Simple Dialog");
        btnSelcionarFecha = (Button) view.findViewById(R.id.btnSelcionarFecha);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        btnSelcionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity = getActivity();
                int dia = datePicker.getDayOfMonth();
                int mes = datePicker.getMonth() + 1;
                int anio = datePicker.getYear();
                Toast.makeText(v.getContext(), "Fecha selccionada : " + dia + "/" + mes + "/" + anio, Toast.LENGTH_SHORT).show();
                //Log.d("FRAGMENT CLICK", txtCorreo.getText().toString());
                fechaSeleccionadaString = anio + "-" + mes + "-" + dia;
                HistorialActivity activity = (HistorialActivity) getActivity();
                activity.onFinishEditDialog(fechaSeleccionadaString + "::" + idOrigen);
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetach", fechaSeleccionadaString);
        mListener = null;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    public String getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(String idOrigen) {
        this.idOrigen = idOrigen;
    }

    public interface OnFragmentInteractionListener {
        void onFinishEditDialog(String inputText);
    }
}
