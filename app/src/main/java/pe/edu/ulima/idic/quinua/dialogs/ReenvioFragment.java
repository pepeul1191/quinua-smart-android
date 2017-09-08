package pe.edu.ulima.idic.quinua.dialogs;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.ulima.idic.quinua.R;


public class ReenvioFragment extends DialogFragment {
    private OnFragmentInteractionListener mListener;
    private Button btnReenviar;
    private EditText txtCorreo;
    Activity activity;

    public ReenvioFragment() {
        // Required empty public constructor
    }

    public static ReenvioFragment newInstance(String param1, String param2) {
        ReenvioFragment fragment = new ReenvioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reenvio, container, false);
        getDialog().setTitle("Simple Dialog");

        Log.d("FRAGMENT", "onCreateView");

        btnReenviar = (Button) view.findViewById(R.id.btnReenviar);
        txtCorreo = (EditText) view.findViewById(R.id.txtCorreo);

        btnReenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity = getActivity();
                Toast.makeText(v.getContext(), "correo ingresado : " + txtCorreo.getText().toString(), Toast.LENGTH_SHORT).show();
                Log.d("FRAGMENT CLICK", txtCorreo.getText().toString());
            }
        });

        //return super.onCreateView(inflater, container, savedInstanceState);
        /*return view;*/

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
