package com.example.myapplication.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.example.myapplication.Model.ModelCatagory;
import com.example.myapplication.R;
import com.example.myapplication.fragment.Fragment_AboutUs;
import com.example.myapplication.fragment.Fragment_Category;
import com.example.myapplication.fragment.Fragment_Latest;
import com.example.myapplication.fragment.Fragment_MyFavorite;
import com.example.myapplication.fragment.Fragment_home;
import com.example.myapplication.utills.ViewHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        myFindViewById();

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new Fragment_home());
        ft.commit();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_latest);
    }

    private void myFindViewById() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    @Override
    public void onBackPressed() {
        updateTitleToolbarOnBackPress();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;

        switch (item.getItemId()) {
            case R.id.nav_latest:
                fragmentClass = Fragment_Latest.class;
                break;
            case R.id.nav_category:
                fragmentClass = Fragment_Category.class;
                break;
            case R.id.nav_heart:
                fragmentClass = Fragment_MyFavorite.class;
                break;
            case R.id.nav_about_us:
                fragmentClass = Fragment_AboutUs.class;
                break;
            default:
                fragmentClass = Fragment_Latest.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.addToBackStack(fragmentClass.getName());
        ft.replace(R.id.container, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        drawer.closeDrawers();
        return true;
    }

    private void updateTitleToolbarOnBackPress() {

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fr = getSupportFragmentManager().findFragmentById(R.id.container);
                if (fr != null) {
                    if (fr.getClass().getSimpleName().equalsIgnoreCase("Fragment_Latest")) {
                        setTitle(navigationView.getMenu().getItem(0).getTitle());
                    } else if (fr.getClass().getSimpleName().equalsIgnoreCase("Fragment_Category")) {
                        setTitle(navigationView.getMenu().getItem(1).getTitle());
                    } else if (fr.getClass().getSimpleName().equalsIgnoreCase("Fragment_MyFavorite")) {
                        setTitle(navigationView.getMenu().getItem(2).getTitle());
                    } else if (fr.getClass().getSimpleName().equalsIgnoreCase("Fragment_AboutUs")) {
                        setTitle(navigationView.getMenu().getItem(3).getTitle());
                    } else if (fr.getClass().getSimpleName().equalsIgnoreCase("Fragment_home")) {
                        setTitle(navigationView.getMenu().getItem(4).getTitle());
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission accept to read your External storage", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
