package com.socialapp.wsd.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import com.socialapp.wsd.R;


public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mContext = AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started.");
        setupSettingsList();
    }

    private void setupSettingsList() {
        Log.d(TAG, "setupSettingsList: Initializing account settings list.");
        ListView listView = (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String>  options = new ArrayList<>();
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.sign_out));

        ArrayAdapter adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);
    }
}
