
package com.example.zozo07.mobile;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.concretepage.android.R;


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
        //Create a new fragment and specify the fragment to show based on nav item clicked.
        Fragment fragment = null;
        Class fragmentclass;
        switch (menuItem.getItemId()){
            case R.id.calendar:
                fragmentclass = CalendarFragment.class;
                break;
            case R.id.graphs:
                fragmentclass = GraphFragment.class;
                break;
            case R.id.sleep:
                fragmentclass = SleepFragment.class;
                break;
            case R.id.settings:     //Might be tricky.
                fragmentclass = SettingsFragment.class;
                break;
        }

        try{
            //Insert a fragment by replacing any existing fragment.
            FragmentManager fragmentManager = getSupportFragmentManager();

            //might be bad as int.
            int ft = fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();


            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);
            //Set action bar title.
            setTitle(menuItem.getTitle());

            //Close the drawer.
            mDrawer.closeDrawers();


        }
        catch (Exception e){
            e.printStackTrace();    //write error to console.
        }

    }
}










