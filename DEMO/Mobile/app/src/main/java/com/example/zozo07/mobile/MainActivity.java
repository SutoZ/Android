package com.example.zozo07.mobile;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.concretepage.android.R;
//import com.concretepage.android.R;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set a toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //Set a Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Setup a drawer view.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()){      //that's the way we refer to an item.
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;    //get out of the switch.
        }

        return super.onOptionsItemSelected(item);
    }

    //Setup a handler to respond to click events on the navigation elements and swap out the fragment.

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(
        new NavigationView.onNavigationItemSelectedListener(){
        @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){
            selectDrawerItem(menuItem);
            return true;
        }
        });
    }

    public void selectDrawerItem(MenuItem menuItem){

    }
}










