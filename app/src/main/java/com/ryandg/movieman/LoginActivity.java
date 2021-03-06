package com.ryandg.movieman;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ryandg.PrefUtils;
import com.ryandg.android.ViewUtils;
import com.ryandg.movieman.data.MovieManContract;
import com.ryandg.movieman.pojo.MovieManUser;
import com.ryandg.movieman.ui.MainActivity;
import com.ryandg.movieman.validation.EditTextValidator;
import com.ryandg.movieman.validation.InputValidator;
import com.ryandg.movieman.validation.InputViewErrors;
import com.ryandg.movieman.validation.LoginValidationTask;
import com.ryandg.movieman.validation.rules.Alphanumeric;
import com.ryandg.movieman.validation.rules.NotEmpty;
import com.ryandg.movieman.validation.rules.PasswordBelongsToUser;
import com.ryandg.movieman.validation.rules.RuleList;
import com.ryandg.movieman.validation.rules.UserExists;

import ryandg.ryandg.movieman.R;

import static com.ryandg.movieman.data.MovieManContract.MovieManUser.*;

/**
 * Created by Ryan De Gruyter on 17/05/2015.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginValidationTask.IsValidListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    EditText mInputPassword;
    EditText mInputName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (PrefUtils.isUserLoggedIn(this)) {
            launchMainActivity();
        }

        setContentView(R.layout.login_activity);

        initInputs();
        initLoginButton();
        initSignUpButton();
    }

    private void initSignUpButton() {
        final View btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(this);
    }


    private void initLoginButton() {
        Button mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(this);
    }

    private void initInputs() {
        mInputName = (EditText) findViewById(R.id.inputLoginName);
        mInputPassword = (EditText) findViewById(R.id.inputPassword);

        ViewUtils.setNoSpacesInputFilter(mInputName);
        ViewUtils.setNoSpacesInputFilter(mInputPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                onLoginButtonClick();
                break;
            case R.id.btn_signup:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }

    private void onLoginButtonClick() {
        new LoginValidationTask(this, mInputName, mInputPassword, this).execute();
    }

    @Override
    public void onIsValidated() {
        PrefUtils.markUserLoggedIn(true, this);
        PrefUtils.createSession(this, getUser(mInputName.getText().toString()));
        launchMainActivity();
    }

    private MovieManUser getUser(String user) {
        final Cursor query = getContentResolver().query(CONTENT_URI, null, Columns.COL_USERNAME + " = '" + user + "'", null, null);
        query.moveToFirst();
        int id = query.getInt(query.getColumnIndex(BaseColumns._ID));
        final MovieManUser movieManUser = new MovieManUser(user, id);
        return movieManUser;
    }

    private void launchMainActivity() {
        Intent goToMain = new Intent(this, MainActivity.class);
        goToMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(goToMain);
    }
}
