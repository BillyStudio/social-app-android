package com.socialapp.wsd.Desktop;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.socialapp.wsd.R;
import com.socialapp.wsd.Utils.BottomNavigationViewHelper;

public class DesktopActivity extends AppCompatActivity {

    private static final String TAG = "DesktopActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = DesktopActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: starting.");
        setupBottomNavigationView();
        setupViewPager();

    }

    /**
     * Responsible for adding three tabs: Home, Camera and Messages
     */
    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment());  // index 0
        adapter.addFragment(new HomeFragment());    // index 1
        adapter.addFragment(new MessagesFragment());// index 2
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);
    }
    /*
     * Bottom navigation view setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: settting up BottomNavigationViewEx");
        BottomNavigationViewEx navigationEx = (BottomNavigationViewEx) findViewById(R.id.navigation);
        BottomNavigationViewHelper.setupBottomNavigationView(navigationEx);
        BottomNavigationViewHelper.enableNavigation(mContext, navigationEx);
        Menu menu = navigationEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
