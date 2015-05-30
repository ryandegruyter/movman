package com.ryandg.movieman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ryandg.android.ViewUtils;
import com.ryandg.movieman.validation.EditTextValidator;
import com.ryandg.movieman.validation.InputValidator;
import com.ryandg.movieman.validation.InputViewErrors;
import com.ryandg.movieman.validation.rules.Alphanumeric;
import com.ryandg.movieman.validation.rules.NotEmpty;
import com.ryandg.movieman.validation.rules.PasswordBelongsToUser;
import com.ryandg.movieman.validation.rules.RuleList;
import com.ryandg.movieman.validation.rules.UserExists;
import com.ryandg.text.NoSpacesInputFilter;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan De Gruyter on 17/05/2015.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, InputValidator.OnInputValidationListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    EditText mInputPassword;
    EditText mInputName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        validate();
    }

    private void validate() {
        EditTextValidator mInputValidator = new EditTextValidator(this);

        mInputValidator.setRules(getInputNameRuleList());
        mInputValidator.validate(mInputName);

        mInputValidator.setRules(getInputPasswordRuleList());
        mInputValidator.validate(mInputPassword);

        if (mInputValidator.isValid()) {

        } else {

        }
    }

    @Override
    public void onInputError(InputViewErrors errors) {
        int viewId = errors.getViewId();
        switch (viewId) {
            case R.id.inputLoginName:
                ViewUtils.setEditTextError(mInputName, errors.getFirstError());
                break;
            case R.id.inputPassword:
                ViewUtils.setEditTextError(mInputPassword, errors.getFirstError());
        }
    }

    @Override
    public void onInputSuccess(int viewId) {
        switch (viewId) {
            case R.id.inputLoginName:
                ViewUtils.setEditTextSuccesfull(mInputPassword);
                break;
            case R.id.inputPassword:
                ViewUtils.setEditTextSuccesfull(mInputName);
                break;
        }
    }

    public RuleList getInputNameRuleList() {
        RuleList rules = new RuleList();
        rules.addRule(new NotEmpty());
        rules.addRule(new Alphanumeric());
        rules.addRule(new UserExists(this));
        return rules;
    }

    public RuleList getInputPasswordRuleList() {
        final RuleList rules = new RuleList();
        rules.addRule(new NotEmpty());

        final String loginName = mInputName.getText().toString();
        rules.addRule(new PasswordBelongsToUser(loginName, this));
        return rules;
    }
}
