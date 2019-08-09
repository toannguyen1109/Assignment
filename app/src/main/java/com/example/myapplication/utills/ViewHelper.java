package com.example.myapplication.utills;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.myapplication.R;

public class ViewHelper {
    // fun này để chuyển fragment
    public static void switchFragment(FragmentActivity context, Fragment fragment) {
        String name = fragment.getClass().getName();
        // lấy ra tên fragment được chuyển tới -> mục đích để cho vào stack. có thể log ra
        Log.d("switchFragment", "fragment name: " + name);

        // Lớp FragmentTransaction dùng để chuyển trang và cung cấp hiệu ứng chuyển trang
        FragmentTransaction ft = context.getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        // Thay thế fragment vào frameLayout của main activity (có id = container)
        ft.replace(R.id.container, fragment);

        // add vào stack của fragment manager
        ft.addToBackStack(name);

        // thực thi chuyển fragment
        ft.commit();
    }
}
