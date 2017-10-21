package com.innovators.fitnesschef;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class diaryFragment extends Fragment {

String g;
    TextView v;
    TextView f;
    TextView e;
    TextView r;
    TextView b;
    TextView l;
    TextView d;
    TextView s;
    TextView es;
    Button btn;
    String datecontent;
    public diaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_diary, container, false);
        v=(TextView) view.findViewById(R.id.gvalue);
        f=(TextView) view.findViewById(R.id.fvalue);
        e=(TextView) view.findViewById(R.id.evalue);
        r=(TextView) view.findViewById(R.id.result);
        b=(TextView) view.findViewById(R.id.breakfastvalue);
        l=(TextView) view.findViewById(R.id.lunchvalue);
        d=(TextView) view.findViewById(R.id.dinnervalue);
        s=(TextView) view.findViewById(R.id.snacksvalue);
        es=(TextView) view.findViewById(R.id.exersievalue);
        home h=(home)getActivity();
        g=h.get();
         btn=(Button) view.findViewById(R.id.button7);
        String pattern = "dd-MM-yyyy";
        String dateInString =new SimpleDateFormat(pattern).format(new Date());
        btn.setText(dateInString);
        datecontent=btn.getText().toString();
        SharedPreferences sp = getActivity().getSharedPreferences(g,0);
        v.setText(Integer.toString(sp.getInt("estcal",0)));

        SharedPreferences br = getActivity().getSharedPreferences(g+"breakfast"+datecontent,0);
        b.setText(Integer.toString(br.getInt("value",0)));
        SharedPreferences lu = getActivity().getSharedPreferences(g+"lunch"+datecontent,0);
        l.setText(Integer.toString(lu.getInt("value",0)));
        SharedPreferences di = getActivity().getSharedPreferences(g+"dinner"+datecontent,0);
        d.setText(Integer.toString(di.getInt("value",0)));
        SharedPreferences sn = getActivity().getSharedPreferences(g+"snacks"+datecontent,0);
        s.setText(Integer.toString(sn.getInt("value",0)));
        SharedPreferences er = getActivity().getSharedPreferences(g+"exercise"+datecontent,0);
        es.setText(Integer.toString(er.getInt("value",0)));
        int x=br.getInt("value",0)+lu.getInt("value",0)+di.getInt("value",0)+sn.getInt("value",0);
        int z=Integer.parseInt(v.getText().toString())-x+Integer.parseInt(es.getText().toString());
        SharedPreferences shared = getActivity().getSharedPreferences(g+"cal"+datecontent,0);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("food",x);
        editor.putInt("exercise",er.getInt("value",0));
        editor.putInt("result",z);
        editor.commit();
        f.setText(Integer.toString(shared.getInt("food", 0)));
        e.setText(Integer.toString(shared.getInt("exercise", 0)));
        r.setText(Integer.toString(shared.getInt("result",0)));

        Button button = (Button) view.findViewById(R.id.br_food);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(getActivity(),BreakfastActivity.class);
              in.putExtra("email",g);
                in.putExtra("date",datecontent);
                getActivity().startActivity(in);
            }
        });
        Button button1 = (Button) view.findViewById(R.id.lunch_food);

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(getActivity(),LunchActivity.class);
             in.putExtra("email",g);
                in.putExtra("date",datecontent);
                getActivity().startActivity(in);
            }
        });

        Button button2 = (Button) view.findViewById(R.id.dinner_food);

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(getActivity(),DinnerActivity.class);
            in.putExtra("email",g);
                in.putExtra("date",datecontent);
                getActivity().startActivity(in);
            }
        });
        Button button3 = (Button) view.findViewById(R.id.snacks_food);

        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(getActivity(),SnacksActivity.class);
          in.putExtra("email",g);
                in.putExtra("date",datecontent);
                getActivity().startActivity(in);
            }
        });
        Button button4 = (Button) view.findViewById(R.id.excerise_reg);

        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(getActivity(),ExerciseActivity.class);
           in.putExtra("email",g);
                in.putExtra("date",datecontent);
                getActivity().startActivity(in);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());

                View promptView = layoutInflater.inflate(R.layout.dialog_date, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                alertDialogBuilder.setView(promptView);
                final Calendar c = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if(monthOfYear<9) {
                                    btn.setText(dayOfMonth + "-0"
                                            + (monthOfYear + 1) + "-" + year);

                                }
                                else
                                {
                                    btn.setText(dayOfMonth + "-"
                                            + (monthOfYear + 1) + "-" + year);
                                }
                                datecontent = btn.getText().toString();
                               reload();

                            }
                        }, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE));
                dpd.show();
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Set",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick (DialogInterface dialog, int id){

                                    }
                                }

                        )
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick (DialogInterface dialog,int id){
                                        dialog.cancel();
                                    }
                                });
            }
        });
        return view;
    }

public void reload()
    {SharedPreferences sp = getActivity().getSharedPreferences(g,0);
        v.setText(Integer.toString(sp.getInt("estcal",0)));
        SharedPreferences br = getActivity().getSharedPreferences(g+"breakfast"+datecontent,0);
        b.setText(Integer.toString(br.getInt("value",0)));
        SharedPreferences lu = getActivity().getSharedPreferences(g+"lunch"+datecontent,0);
        l.setText(Integer.toString(lu.getInt("value",0)));
        SharedPreferences di = getActivity().getSharedPreferences(g+"dinner"+datecontent,0);
        d.setText(Integer.toString(di.getInt("value",0)));
        SharedPreferences sn = getActivity().getSharedPreferences(g+"snacks"+datecontent,0);
        s.setText(Integer.toString(sn.getInt("value",0)));
        SharedPreferences er = getActivity().getSharedPreferences(g+"exercise"+datecontent,0);
        es.setText(Integer.toString(er.getInt("value",0)));
        int x=br.getInt("value",0)+lu.getInt("value",0)+di.getInt("value",0)+sn.getInt("value",0);
        int z=Integer.parseInt(v.getText().toString())-x+Integer.parseInt(es.getText().toString());
        SharedPreferences shared = getActivity().getSharedPreferences(g+"cal",0);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("food",x);
        editor.putInt("exercise",er.getInt("value",0));
        editor.putInt("result",z);
        editor.commit();
        f.setText(Integer.toString(shared.getInt("food", 0)));
        e.setText(Integer.toString(shared.getInt("exercise", 0)));
        r.setText(Integer.toString(shared.getInt("result",0)));
    }
    }

