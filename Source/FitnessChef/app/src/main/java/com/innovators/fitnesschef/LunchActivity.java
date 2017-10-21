package com.innovators.fitnesschef;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LunchActivity extends AppCompatActivity {
    int PICK_IMAGE=1;
    String s;
    Bitmap bitmap;
    String n;
    String str;
    static final int TAKE_PHOTO_CODE = 0;
    private ArrayAdapter mAdapter;
    public ListView l;
    ArrayList<String> myStringArray1;
    final Handler handler = new Handler();
    String q;
    String z;
    String x;
    String g;
    int v;
    String res;
   static int k=0;
    AutoCompleteTextView t;
    String datecontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        Intent intent = getIntent();
        g = intent.getStringExtra("email");
        datecontent=intent.getStringExtra("date");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        l = (ListView) findViewById(R.id.list);
        myStringArray1 = new ArrayList<String>();
        SharedPreferences settings = getSharedPreferences(g+"lunch"+datecontent, 0);
        //     SharedPreferences.Editor editor = settings.edit();
        for(int j=1;j<=settings.getInt("listsize",0);j++)
        {
            String r=  settings.getString("lunchlist"+j,"");
            myStringArray1.add(r);
        }
        k=settings.getInt("listsize",0);
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, myStringArray1);
        l.setAdapter(mAdapter);
        t=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        t.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                res=t.getText().toString();
                if(res!=null & res.length()>3) {
                    auto(res);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
/*str=a.getText().toString();
                if(str!=null) {
                    auto(str);
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
 /*str=a.getText().toString();
                if(str!=null) {
                    auto(str);
                }*/
            }
        });
    }
    public void auto(String x) {
        String getURL = "https://api.nutritionix.com/v2/autocomplete?q="+x+"%20&appId=1d65c19f&appKey=7679fb7207c9b4a2bf091490eb233443";
        String response = null;
        BufferedReader bfr = null;
        try {
            URL url = new URL(getURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bfr.readLine()) != null) {
                // Append server response in string
                sb.append(line + " ");
            }
            response = sb.toString();
            JSONArray j=new JSONArray(response);
            String []s=new String[j.length()];
            for(int i=0;i<j.length();i++)
            {
                JSONObject o=j.getJSONObject(i);
                s[i]=o.getString("text");
            }
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,s);
            t.setAdapter(adapter);
            t.setThreshold(5);

        } catch (Exception e)
        {

        }
    }
    public void cp(View v) {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(LunchActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    cameraIntent();

                }
                else if (items[item].equals("Choose from Library")) {
                    galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }
    private void cameraIntent()
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);

    }
    private  void galleryIntent()
    {
        Intent gallery=new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"),PICK_IMAGE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            s=getPath(uri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        z=imagescan();  //Do something after 100ms
                    }
                }, 1000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imagecal(z);  //Do something after 100ms
                    }
                }, 2000);

            }catch (Exception e)
            {

            }
        }
        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            try {
                bitmap = (Bitmap) data.getExtras().get("data");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        z=imagescan();  //Do something after 100ms
                    }
                }, 1000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imagecal(z);  //Do something after 100ms
                    }
                }, 2000);

            }
            catch (Exception e)
            {

            }
        }
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = LunchActivity.this.getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndex(projection[0]);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }

    private String imagescan()
    {
        String getURL = "https://gateway-a.watsonplatform.net/visual-recognition/api/v3/classify?api_key=2e5b6c2dd38ace34e36a04813437049743814752&version=2016-05-19";

        String response = null;
        BufferedReader bfr = null;
        try {
            URL url = new URL(getURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "image/jpeg");
            conn.setRequestMethod("POST");
            conn.connect();
            OutputStream out = conn.getOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            conn.getURL();
            out.close();
            bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = bfr.readLine()) != null) {
                // Append server response in string
                sb.append(line + " ");
            }
            response = sb.toString();
        } catch (Exception ex) {
            String Error = ex.getMessage();
        } finally {
            try {
                bfr.close();
            } catch (Exception ex) {

            }
        }
        try{
            JSONObject j=new JSONObject(response);
            JSONArray a=j.getJSONArray("images");
            JSONObject b=a.getJSONObject(0);
            JSONArray c=b.getJSONArray("classifiers");
            JSONObject d=c.getJSONObject(0);
            JSONArray e=d.getJSONArray("classes");
            JSONObject f=e.getJSONObject(0);
            n=f.getString("class");


        }catch (Exception e)
        {
            e.getMessage();
        }
return  n;
    }

    public void getcallories(String n) {
        String getURL;
        if(n.contains(" ")) {
            String[] l = n.split(" ");
            getURL = "https://api.nutritionix.com/v1_1/search/"+l[0]+"%20"+l[1]+"?fields=item_name%2Cnf_calories&appId=1d65c19f&appKey=7679fb7207c9b4a2bf091490eb233443";
        }
        else {
            getURL = "https://api.nutritionix.com/v1_1/search/" + n + "%20" + "?fields=item_name%2Cnf_calories&appId=1d65c19f&appKey=7679fb7207c9b4a2bf091490eb233443";
        }
        String response = null;
        BufferedReader bfr = null;
        try {
            URL url = new URL(getURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bfr.readLine()) != null) {
                // Append server response in string
                sb.append(line + " ");
            }
            response = sb.toString();

            JSONObject o=new JSONObject(response);
            JSONArray j=o.getJSONArray("hits");
            for(int k=0;k<j.length();k++) {
                JSONObject a = j.getJSONObject(k);
                JSONObject r = a.getJSONObject("fields");
                str = r.getString("nf_calories");
                if(str!="0")
                {
                    break;
                }
            }

        }catch (Exception e) {
        }
    }
    public void additem(View v)
    {
        x=t.getText().toString();
        getcallories(x);
        quantity();

        ;
        // new task1().execute(x);
    }
    private void dis(String x)
    {
        k=k+1;
        float i=(Integer.parseInt(q))*(Float.parseFloat(str));
        myStringArray1.add(x.toUpperCase()+",  "+"QUANTITY: "+q+",  "+"CALORIES: "+(int)i);

        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, myStringArray1);
        l.setAdapter(mAdapter);
        SharedPreferences settings = getSharedPreferences(g+"lunch"+datecontent, 0);
        v=settings.getInt("value",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("lunchlist"+k,x.toUpperCase()+",  "+"QUANTITY: "+q+",  "+"CALORIES: "+(int)i);
        editor.putInt("listsize",k);
        editor.putInt("value",(int)i+v);
        editor.commit();
    }

    private void add(String x)
    {
        k=k+1;
        myStringArray1.add(x.toUpperCase()+"          Calories       "+(int)Float.parseFloat(str));
        //   myStringArray1.add("Quantity : 1");
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, myStringArray1);
        l.setAdapter(mAdapter);
        SharedPreferences settings = getSharedPreferences(g+"lunch"+datecontent,0);
        v=settings.getInt("value",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("lunchlist"+k,x.toUpperCase()+"          Calories       "+(int)Float.parseFloat(str));
        editor.putInt("listsize",k);
        editor.putInt("value",(int)Float.parseFloat(str)+v);
        editor.commit();
    }

    public void quantity() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LunchActivity.this);
        builder.setTitle("Quantity!");
        TextView tv=new TextView(LunchActivity.this);
        tv.setText("Quantity:");
        final EditText input = new EditText(LunchActivity.this);
        input.setText("1");
        builder.setView(tv);
        builder.setView(input);
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        q=input.getText().toString();
                        if(q.isEmpty())
                        {
                            q="0";
                        }
                        dialog.cancel();
                        dis(x);
                    }

                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
                dis(x);
            }
        });
        builder.show();

    }
    public  void imagecal(String n)
    {
        String getURL;
        if(n.contains(" ")) {
            String[] l = n.split(" ");
            getURL = "https://api.nutritionix.com/v1_1/search/"+l[0]+"%20"+l[1]+"?fields=item_name%2Cnf_calories&appId=1d65c19f&appKey=7679fb7207c9b4a2bf091490eb233443";
        }
        else {
            getURL = "https://api.nutritionix.com/v1_1/search/" + n + "%20" + "?fields=item_name%2Cnf_calories&appId=1d65c19f&appKey=7679fb7207c9b4a2bf091490eb233443";
        }
        String response = null;
        BufferedReader bfr = null;
        try {
            URL url = new URL(getURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            if (bfr != null) {
                while ((line = bfr.readLine()) != null) {
                    // Append server response in string
                    sb.append(line + " ");
                }
                response = sb.toString();

                JSONObject o = new JSONObject(response);
                JSONArray j = o.getJSONArray("hits");
                for (int k = 0; k < j.length(); k++) {
                    JSONObject a = j.getJSONObject(k);
                    JSONObject r = a.getJSONObject("fields");
                    str = r.getString("nf_calories");
                    if (str != "0") {
                        break;
                    }
                }

            }
            add(z);
        }catch (Exception e) {
            e.getMessage();
        }

    }
}
