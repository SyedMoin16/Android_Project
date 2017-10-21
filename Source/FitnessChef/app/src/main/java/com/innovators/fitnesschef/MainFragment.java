package com.innovators.fitnesschef;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

String g;
    TextView v;
    TextView f;
    TextView e;
    TextView r;
    int n;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      // g=  getArguments().getString("email");
       View root= inflater.inflate(R.layout.fragment_main, container, false);
        home h=(home)getActivity();
        g=h.get();
       v=(TextView) root.findViewById(R.id.gvalue);
     f=(TextView) root.findViewById(R.id.fvalue);
        e=(TextView) root .findViewById(R.id.evalue);
        r=(TextView) root.findViewById(R.id.result);
        String pattern = "dd-MM-yyyy";
        String dateInString =new SimpleDateFormat(pattern).format(new Date());
        SharedPreferences sp = getActivity().getSharedPreferences(g,0);
        v.setText(Integer.toString(sp.getInt("estcal",0)));
        SharedPreferences shared = getActivity().getSharedPreferences(g+"cal"+dateInString,0);
       f.setText(Integer.toString(shared.getInt("food", 0)));
        e.setText(Integer.toString(shared.getInt("exercise", 0)));
        r.setText(Integer.toString(shared.getInt("result",Integer.parseInt(v.getText().toString()))));
        return root;
    }

}
