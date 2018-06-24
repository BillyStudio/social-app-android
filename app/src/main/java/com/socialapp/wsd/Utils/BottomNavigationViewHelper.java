package com.socialapp.wsd.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.socialapp.wsd.Desktop.DesktopActivity;
import com.socialapp.wsd.Likes.LikesActivity;
import com.socialapp.wsd.Profile.ProfileActivity;
import com.socialapp.wsd.R;
import com.socialapp.wsd.Search.SearchActivity;
import com.socialapp.wsd.Share.ShareActivity;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: Setting up bottom navigation view");
        /*
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(true);
        */
    }

    public static void enableNavigation(final Context context, final Activity callingActivity, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent0 = new Intent(context, DesktopActivity.class); // ACTIVITY_NUMBER = 0
                        context.startActivity(intent0);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.navigation_dashboard:
                        Intent intent1 = new Intent(context, SearchActivity.class); // ACTIVITY_NUMBER = 1
                        context.startActivity(intent1);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.navigation_publish:
                        Intent intent2 = new Intent(context, ShareActivity.class); // ACTIVITY_NUMBER = 2
                        context.startActivity(intent2);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.navigation_notifications:
                        Intent intent3 = new Intent(context, LikesActivity.class); // ACTIVITY_NUMBER = 3
                        context.startActivity(intent3);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.navigation_profile:
                        Intent intent4 = new Intent(context, ProfileActivity.class); // ACTIVITY_NUMBER = 4
                        context.startActivity(intent4);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                }
                return false;
            }
        });
    }
}
