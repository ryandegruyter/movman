package com.ryandg.movieman;

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
import com.ryandg.movieman.validation.EditTextValidator;
import com.ryandg.movieman.validation.InputValidator;
import com.ryandg.movieman.validation.InputViewErrors;
import com.ryandg.movieman.validation.rules.Alphanumeric;
import com.ryandg.movieman.validation.rules.NameIsUnique;
import com.ryandg.movieman.validation.rules.NotEmpty;
import com.ryandg.movieman.validation.rules.RuleList;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan De Gruyter on 29/05/2015.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, InputValidator.OnInputValidationListener {
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
        inputName = (EditText) findViewById(R.id.inputSignupName);
        inputPassword = (EditText) findViewById(R.id.inputSignupPassword);
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
        validate();
    }

    private void validate() {
        EditTextValidator inputValidator = new EditTextValidator(this);
        inputValidator.setRules(getUserNameRules());
        inputValidator.validate(inputName);

        inputValidator.setRules(getPassWordRules());
        inputValidator.validate(inputPassword);

        if (inputValidator.isValid()) {
//            sign up user
            Toast.makeText(this, "signup user", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInputError(InputViewErrors viewErrors) {
        switch (viewErrors.getViewId()) {
            case R.id.inputSignupName:
                ViewUtils.setEditTextError(inputName, viewErrors.getFirstError());
                break;
            case R.id.inputSignupPassword:
                ViewUtils.setEditTextError(inputPassword, viewErrors.getFirstError());
                break;
        }
    }

    @Override
    public void onInputSuccess(int viewId) {
        switch (viewId) {
            case R.id.inputSignupName:
                ViewUtils.setEditTextSuccesfull(inputName);
                break;
            case R.id.inputSignupPassword:
                ViewUtils.setEditTextSuccesfull(inputPassword);
                break;
        }
    }

    public RuleList getUserNameRules() {
        final RuleList rules = new RuleList();
        rules.addRule(new NotEmpty());
        rules.addRule(new Alphanumeric());
        rules.addRule(new NameIsUnique(this));
        return rules;
    }


    public RuleList getPassWordRules() {
        final RuleList rules = new RuleList();
        rules.addRule(new NotEmpty());
        return rules;
    }
}
