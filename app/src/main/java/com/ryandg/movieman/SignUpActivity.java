package com.ryandg.movieman;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ryandg.android.ViewUtils;
import com.ryandg.movieman.db.MovieManContract;
import com.ryandg.movieman.db.MovieManDbHelper;
import com.ryandg.movieman.ui.MainActivity;
import com.ryandg.movieman.validation.EditTextValidator;
import com.ryandg.movieman.validation.InputValidator;
import com.ryandg.movieman.validation.InputViewErrors;
import com.ryandg.movieman.validation.LoginValidationTask;
import com.ryandg.movieman.validation.SignupValidationTask;
import com.ryandg.movieman.validation.rules.Alphanumeric;
import com.ryandg.movieman.validation.rules.NameIsUnique;
import com.ryandg.movieman.validation.rules.NotEmpty;
import com.ryandg.movieman.validation.rules.RuleList;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan De Gruyter on 29/05/2015.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, LoginValidationTask.IsValidListener {
    EditText inputName;
    EditText inputPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        initActionBar();
        initInputFields();
        initSignUpBtn();
    }

    private void initActionBar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        setTitle(R.string.signup);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initSignUpBtn() {
        btnSignUp = (Button) findViewById(R.id.btnSignup);
        btnSignUp.setOnClickListener(this);
    }

    private void initInputFields() {
        inputName = (EditText) findViewById(R.id.inputLoginName);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignup:
                onBtnSignUpClick();
                break;
        }
    }

    private void onBtnSignUpClick() {
        new SignupValidationTask(
                this,
                inputName,
                inputPassword,
                this
        ).execute();
    }

    @Override
    public void onIsValidated() {
        final ContentValues user = MovieManDbHelper.createUser(inputName.getText().toString(), inputPassword.getText().toString());
        getContentResolver().insert(MovieManContract.MovieManUser.CONTENT_URI, user);
        NavUtils.navigateUpFromSameTask(this);
    }
}
