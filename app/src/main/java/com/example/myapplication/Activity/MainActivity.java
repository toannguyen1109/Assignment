package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.myapplication.R;
import com.example.myapplication.fragment.Fragment_AboutUs;
import com.example.myapplication.fragment.Fragment_Category;
import com.example.myapplication.fragment.Fragment_Latest;
import com.example.myapplication.fragment.Fragment_MyFavorite;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment_AboutUs fragment_aboutUs;
    private Fragment_Category fragment_category;
    private Fragment_Latest fragment_latest;
    private Fragment_MyFavorite fragment_myFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        fragment_latest = new Fragment_Latest();
        fragment_myFavorite = new Fragment_MyFavorite();
        fragment_aboutUs = new Fragment_AboutUs();
        fragment_category = new Fragment_Category();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        switch (item.getItemId()) {
            case R.id.nav_latest:
                showLatest();
                break;
            case R.id.nav_category:
                showCategory();
                break;
            case R.id.nav_heart:
                showMyFavorite();
                break;
            case R.id.nav_about_us:
                showAboutUs();
                break;
            default:
                showAboutUs();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showLatest() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();


        if (fragment_latest.isAdded()) {
            ft.show(fragment_latest);
        } else {
            ft.add(R.id.container, fragment_latest);
        }


        if (fragment_aboutUs.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_aboutUs);
        }
        if (fragment_category.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_category);
        }
        if (fragment_myFavorite.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_myFavorite);
        }

        //Sau khi thay đổi fragment phải xác thực lại
        ft.commit();
    }

    public void showCategory() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();


        if (fragment_category.isAdded()) {
            ft.show(fragment_category);
        } else {
            ft.add(R.id.container, fragment_category);
        }


        if (fragment_aboutUs.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_aboutUs);
        }
        if (fragment_latest.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_latest);
        }
        if (fragment_myFavorite.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_myFavorite);
        }

        //Sau khi thay đổi fragment phải xác thực lại
        ft.commit();
    }

    public void showAboutUs() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();


        if (fragment_aboutUs.isAdded()) {
            ft.show(fragment_aboutUs);
        } else {
            ft.add(R.id.container, fragment_aboutUs);
        }


        if (fragment_latest.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_latest);
        }
        if (fragment_category.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_category);
        }
        if (fragment_myFavorite.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_myFavorite);
        }

        //Sau khi thay đổi fragment phải xác thực lại
        ft.commit();
    }

    public void showMyFavorite() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();


        if (fragment_myFavorite.isAdded()) {
            ft.show(fragment_myFavorite);
        } else {
            ft.add(R.id.container, fragment_myFavorite);
        }


        if (fragment_aboutUs.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_aboutUs);
        }
        if (fragment_category.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_category);
        }
        if (fragment_latest.isAdded()) { //Nếu đang hiển thị thì ẩn đi
            ft.hide(fragment_latest);
        }

        //Sau khi thay đổi fragment phải xác thực lại
        ft.commit();
    }
}
