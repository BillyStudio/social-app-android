package com.socialapp.wsd.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.socialapp.wsd.R;
import com.socialapp.wsd.Utils.FirebaseMethods;
import com.socialapp.wsd.Utils.SectionsStatePagerAdapter;


public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";
    private Context mContext;
    private static final int ACTIVITY_NUM = 4;
    public SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started.");

        setContentView(R.layout.activity_accountsettings);
        mContext = AccountSettingsActivity.this;

        mViewPager = (ViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayout1);
        setupSettingsList();    // 完成多个编辑条目的显示和点击的监听
        setupFragments();
        getIncomingIntent();

        // setup the backarrow for navigating back to 'ProfileActivity'
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });
    }

    private void getIncomingIntent() {
        Intent intent = getIntent();

        // if there is an imageUrl attached as an extra, then it was chosen from the gallery/photo fragment
        if (intent.hasExtra(getString(R.string.selected_image))) {
            Log.d(TAG, "getIncomingIntent: New incoming image URL");
            if (intent.getStringExtra(getString(R.string.return_to_fragment)).equals(getString(R.string.edit_profile_fragment))) {
                Log.d(TAG, "getIncomingIntent: Set new profile photo");
                FirebaseMethods firebaseMethods = new FirebaseMethods(AccountSettingsActivity.this);
                firebaseMethods.uploadNewPhoto(getString(R.string.profile_photo), null, 0,
                        intent.getStringExtra(getString(R.string.selected_image)), null);
            }
        }

        if (intent.hasExtra(getString(R.string.calling_activity))) {
            Log.d(TAG, "getIncomingIntent: Receive incoming intent from " + getString(R.string.profile_activity));
            setViewPager(pagerAdapter.getFragmentNumber(getString(R.string.edit_profile_fragment)));
        }
    }
    private void setupSettingsList() {
        Log.d(TAG, "setupSettingsList: Initializing account settings list.");
        ListView listView = (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String>  options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment));  // fragment 0
        options.add(getString(R.string.sign_out_fragment));  // fragment 1

        ArrayAdapter adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);   // 为当前条目列表配置 ArrayAdapter

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: Navigating to fragment #: " + i);
                setViewPager(i);
            }
        });
    }

    private void setupFragments() {
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment));  // fragment 0
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment));  // fragment 1
    }

    public void setViewPager(int fragmentNumber) {
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setupViewPager: Navigating to fragment #: " + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);    // 为每个具体条目设置 SectionStatePagerAdapter
        mViewPager.setCurrentItem(fragmentNumber);
    }


}
