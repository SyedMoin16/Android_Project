package com.innovators.fitnesschef;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    WebView webView;
String g;
    int num1;
    int num2;
    int num3;
    int num4;
    String datecontent;
    Button b;
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_dashboard, container, false);
        home h=(home)getActivity();
        g=h.get();
        String pattern = "dd-MM-yyyy";
        String dateInString =new SimpleDateFormat(pattern).format(new Date());
         b=(Button) v.findViewById(R.id.button14);
        b.setText(dateInString);
        datecontent=b.getText().toString();
        SharedPreferences br = getActivity().getSharedPreferences(g+"breakfast"+datecontent,0);
num1=br.getInt("value",0);
        SharedPreferences lu = getActivity().getSharedPreferences(g+"lunch"+datecontent,0);
        num2=lu.getInt("value",0);
        SharedPreferences di = getActivity().getSharedPreferences(g+"dinner"+datecontent,0);
        num3=di.getInt("value",0);
        SharedPreferences sn = getActivity().getSharedPreferences(g+"snacks"+datecontent,0);
        num4=sn.getInt("value",0);
        webView = (WebView) v.findViewById(R.id.web);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/chart.html");

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());

                View promptView = layoutInflater.inflate(R.layout.dialog_date, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setView(promptView);
                final Calendar c = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if(monthOfYear<9) {
                                    b.setText(dayOfMonth + "-0"
                                            + (monthOfYear + 1) + "-" + year);

                                }
                                else
                                {
                                    b.setText(dayOfMonth + "-"
                                            + (monthOfYear + 1) + "-" + year);
                                }
                                datecontent = b.getText().toString();
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
        return v;
    }
    public class WebAppInterface {

        @JavascriptInterface
        public int getNum1() {
            return num1;
        }

        @JavascriptInterface
        public int getNum2() {
            return num2;
        }

        @JavascriptInterface
        public int getNum3() {
            return num3;
        }

        @JavascriptInterface
        public int getNum4() {
            return num4;
        }

    }
public void reload()
{
    SharedPreferences br = getActivity().getSharedPreferences(g+"breakfast"+datecontent,0);
    num1=br.getInt("value",0);
    SharedPreferences lu = getActivity().getSharedPreferences(g+"lunch"+datecontent,0);
    num2=lu.getInt("value",0);
    SharedPreferences di = getActivity().getSharedPreferences(g+"dinner"+datecontent,0);
    num3=di.getInt("value",0);
    SharedPreferences sn = getActivity().getSharedPreferences(g+"snacks"+datecontent,0);
    num4=sn.getInt("value",0);
    webView.addJavascriptInterface(new WebAppInterface(), "Android");
    webView.getSettings().setJavaScriptEnabled(true);
    webView.loadUrl("file:///android_asset/chart.html");
}
}
