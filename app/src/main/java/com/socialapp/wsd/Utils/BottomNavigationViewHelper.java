package com.socialapp.wsd.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.socialapp.wsd.DesktopActivity;
import com.socialapp.wsd.LikesActivity;
import com.socialapp.wsd.ProfileActivity;
import com.socialapp.wsd.R;
import com.socialapp.wsd.SearchActivity;
import com.socialapp.wsd.ShareActivity;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: Setting up bottom navigation view");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(context, DesktopActivity.class); // ACTIVITY_NUMBER = 0
                        context.startActivity(intent1);
                        break;
                    case R.id.navigation_dashboard:
                        Intent intent2 = new Intent(context, SearchActivity.class); // ACTIVITY_NUMBER = 1
                        context.startActivity(intent2);
                        break;
                    case R.id.navigation_publish:
                        Intent intent3 = new Intent(context, ShareActivity.class); // ACTIVITY_NUMBER = 2
                        context.startActivity(intent3);
                        break;
                    case R.id.navigation_notifications:
                        Intent intent4 = new Intent(context, LikesActivity.class); // ACTIVITY_NUMBER = 3
                        context.startActivity(intent4);
                        break;
                    case R.id.navigation_profile:
                        Intent intent5 = new Intent(context, ProfileActivity.class); // ACTIVITY_NUMBER = 4
                        context.startActivity(intent5);
                        break;
                }
                return false;
            }
        });
    }
}
