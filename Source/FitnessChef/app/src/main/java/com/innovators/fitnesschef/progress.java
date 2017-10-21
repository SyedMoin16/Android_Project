package com.innovators.fitnesschef;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class progress extends Fragment {

    WebView webView;
    String g;
    int num1;
    int num2;
    int num3;
    int num4;
    int num5;
    int num6;
    int num7;
    String date1;
    String date2;
    String date3;
    String date4;
    String date5;
    String date6;
    String date7;
    String pattern = "dd-MM-yyyy";
    public progress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_progress, container, false);
        home h=(home)getActivity();
        g=h.get();

        webView = (WebView) v.findViewById(R.id.progweb);
        webView.addJavascriptInterface(new WebAppInt(), "Android");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/line.html");
        return v;
    }
    public class WebAppInt {

        @JavascriptInterface

        public int getNum1() {
            String dt =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -7);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            SharedPreferences shared = getActivity().getSharedPreferences(g+"cal"+output,0);
            num1=shared.getInt("result",0);
            return num1;
        }
        @JavascriptInterface

        public int getNum2() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -6);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            SharedPreferences shared = getActivity().getSharedPreferences(g+"cal"+output,0);
            num2=shared.getInt("result",0);
            return num2;
        }
        @JavascriptInterface

        public int getNum3() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -5);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            SharedPreferences shared = getActivity().getSharedPreferences(g+"cal"+output,0);
            num3=shared.getInt("result",0);
            return num3;
        }
        @JavascriptInterface

        public int getNum4() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -4);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            SharedPreferences shared = getActivity().getSharedPreferences(g+"cal"+output,0);
            num4=shared.getInt("result",0);
            return num4;
        }
        @JavascriptInterface

        public int getNum5() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -2);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            SharedPreferences shared = getActivity().getSharedPreferences(g+"cal"+output,0);
            num5=shared.getInt("result",0);
            return num5;
        }
        @JavascriptInterface

        public int getNum6() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            SharedPreferences shared = getActivity().getSharedPreferences(g+"cal"+output,0);
            num6=shared.getInt("result",0);
            return num6;
        }
        @JavascriptInterface

        public int getNum7() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, 0);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            SharedPreferences shared = getActivity().getSharedPreferences(g+"cal"+output,0);
            num7=shared.getInt("result",0);
            return num7;
        }
        @JavascriptInterface

        public String getdate1() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -7);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            return output;
        }
        @JavascriptInterface

        public String getdate2() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -6);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            return output;
        }
        @JavascriptInterface

        public String getdate3() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -5);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            return output;
        }
        @JavascriptInterface

        public String getdate4() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -4);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            return output;
        }
        @JavascriptInterface

        public String getdate5() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -2);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            return output;
        }
        @JavascriptInterface

        public String getdate6() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -1);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            return output;
        }
        @JavascriptInterface

        public String getdate7() {
            String dt  =new SimpleDateFormat(pattern).format(new Date());  // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, 0);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
            String output = sdf1.format(c.getTime());
            return output;
        }
    }
}
