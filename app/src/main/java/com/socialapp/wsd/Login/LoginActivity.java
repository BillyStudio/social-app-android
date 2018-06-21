package com.socialapp.wsd.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.socialapp.wsd.Desktop.DesktopActivity;
import com.socialapp.wsd.R;
import com.socialapp.wsd.Utils.UsefulRegex;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private Context mContext;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar mProgressBar;
    private EditText mUserId, mPassword;
    private TextView mPleaseWait;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: started.");
        mProgressBar = (ProgressBar) findViewById(R.id.loginRequestLoadingProgressBar);
        mProgressBar.setVisibility(View.GONE);
        mPleaseWait = (TextView) findViewById(R.id.pleaseWait);
        mPleaseWait.setVisibility(View.GONE);
        mUserId = (EditText) findViewById(R.id.input_user_id);
        mPassword = (EditText) findViewById(R.id.input_password);
        setupFirebaseAuth();

        init();
    }

    private boolean isStringNull(String string) {
        Log.d(TAG, "isStringNull: check if the string is null");
        return string.equals("");
    }
    /*
    --------------------------- Firebase --------------------------------------
     */
    private void init() {
        // initialize for button login
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to login");

                String userId = mUserId.getText().toString();
                String password = mPassword.getText().toString();
                // omit null String
                if (isStringNull(userId) || isStringNull(password)) {
                    Toast.makeText(mContext, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPleaseWait.setVisibility(View.VISIBLE);

                    if (UsefulRegex.isEmail(userId)) {
                        mAuth.signInWithEmailAndPassword(userId, password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Log.d(TAG, "SignInWithEmail:onComplete: " + task.isSuccessful());
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if (task.isSuccessful()) {

                                            Log.d(TAG, "onComplete: success.");
                                            mProgressBar.setVisibility(View.GONE);
                                            mPleaseWait.setVisibility(View.GONE);
                                            Intent intent = new Intent(LoginActivity.this, DesktopActivity.class);
                                            startActivity(intent);

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();

                                            mProgressBar.setVisibility(View.GONE);
                                            mPleaseWait.setVisibility(View.GONE);

                                        }
                                    }
                                });
                    } else {
                        Log.d(TAG, "onClick: Invalid Email Address!");
                    }


                }
            }
        });

        TextView linkSignup = (TextView) findViewById(R.id.link_signup);
        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Navigating to register screen");
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        /*
        If the user is logged in then navigate to Desktop activity and finish this loginActivity content
         */
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, DesktopActivity.class);
            startActivity(intent);
            finish();
        }
    }


    /**
     * Setup the Firebase auth object
     */
    private void setupFirebaseAuth() {
        Log.d(TAG, "setupBeegoAuth: setting up Firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User has signed in
                    Log.d(TAG, "onAuthStateChanged: sign_in" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged: signed out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
