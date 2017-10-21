package com.innovators.fitnesschef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView=null;
    Toolbar toolbar=null;
    String g;
    int cal;

// code added by revanth
    FloatingActionButton fab,fab1,fab2,fab3,fab4,fab5;
    Animation FabOpen,FabClose,FabRClockwise,FabRanticlockwise;

    boolean isOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle extras = getIntent().getExtras();
        g=extras.getString("email");
        MainFragment fragment=new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // code added by revanth


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1= (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 =  (FloatingActionButton) findViewById(R.id.fab4);
        fab5 =  (FloatingActionButton) findViewById(R.id.fab5);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fab.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View view)
            {
                if(isOpen)
                {
                    fab5.startAnimation(FabClose);
                    fab4.startAnimation(FabClose);
                    fab3.startAnimation(FabClose);
                    fab2.startAnimation(FabClose);
                    fab1.startAnimation(FabClose);
                    fab.startAnimation(FabRanticlockwise);
                    fab5.setClickable(true);
                    fab4.setClickable(true);
                    fab3.setClickable(true);
                    fab2.setClickable(true);
                    fab1.setClickable(true);

                    isOpen=false;


                }
                else
                {
                    fab5.startAnimation(FabOpen);
                    fab4.startAnimation(FabOpen);
                    fab3.startAnimation(FabOpen);
                    fab2.startAnimation(FabOpen);
                    fab1.startAnimation(FabOpen);
                    fab.startAnimation(FabRClockwise);
                    fab5.setClickable(true);
                    fab4.setClickable(true);
                    fab3.setClickable(true);
                    fab2.setClickable(true);
                    fab1.setClickable(true);
                    isOpen=true;
                }


            }

        });



// code commented by revanth

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView drawerUsername = (TextView) headerView.findViewById(R.id.user);
        TextView drawerAccount = (TextView) headerView.findViewById(R.id.textView);
        SharedPreferences sp=getSharedPreferences(g,0);
        drawerUsername.setText(sp.getString("fullname",""));
        drawerAccount.setText(g);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            MainFragment fragment=new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
            // Handle the camera action
        } else if (id == R.id.nav_diary) {

            diaryFragment fragment=new diaryFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_progess) {
            progress fragment= new progress();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_nutrition) {
DashboardFragment fragment= new DashboardFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_profile) {
            Intent intent= new Intent(home.this,ProfileActivity.class);
            intent.putExtra("email",g);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            Intent intent= new Intent(home.this,LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public String get()
    {
        return g;
    }

    // code added by revanth

    public void cardio(View v)
    {
        Intent redirect=new Intent(home.this,helpex.class);
        startActivity(redirect);
    }

    public void strength(View v)
    {
        Intent redirect=new Intent(home.this,strength.class);
        startActivity(redirect);
    }
    public void dash(View v)
    {
        DashboardFragment fragment= new DashboardFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
    public void diaryclick(View v)
    {
        diaryFragment fragment=new diaryFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
    public void prog(View v)
    {
        progress fragment= new progress();
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}
