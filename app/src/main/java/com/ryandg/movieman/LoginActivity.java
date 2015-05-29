package com.ryandg.movieman;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ryandg.movieman.validation.EditTextValidator;
import com.ryandg.movieman.validation.InputValidator;
import com.ryandg.movieman.validation.InputViewErrors;
import com.ryandg.movieman.validation.rules.PasswordBelongsToUser;
import com.ryandg.movieman.validation.rules.NotEmpty;
import com.ryandg.movieman.validation.rules.RuleList;
import com.ryandg.movieman.validation.rules.Alphanumeric;
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

    private InputValidator mInputValidator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initInputs();
        initLoginButton();
    }


    private void initLoginButton() {
        Button mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(this);
    }

    private void initInputs() {
        mInputName = (EditText) findViewById(R.id.inputLoginName);
        mInputPassword = (EditText) findViewById(R.id.inputPassword);

        setInputFilters();
    }

    private void setInputFilters() {
        InputFilter[] inputFilters = {
                new NoSpacesInputFilter()
        };

        mInputName.setFilters(inputFilters);
        mInputName.setFilters(inputFilters);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                onLoginButtonClick();
                break;
        }
    }

    private void onLoginButtonClick() {
        validate();
    }

    private void validate() {
        mInputValidator = new EditTextValidator(this);

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
                onErrorInputLoginName(errors);
                break;
            case R.id.inputPassword:
                onErrorInputPassWord(errors);
        }
    }

    private void onErrorInputPassWord(InputViewErrors errors) {
        setEditTextError(mInputPassword, errors.getFirstError());
    }

    private void onErrorInputLoginName(InputViewErrors errors) {
        setEditTextError(mInputName, errors.getFirstError());
    }

    @Override
    public void onInputSuccess(int viewId) {
        switch (viewId) {
            case R.id.inputLoginName:
                onSuccessInputLoginName();
                break;
            case R.id.inputPassword:
                onSuccessInputPassword();
                break;
        }
    }

    private void onSuccessInputPassword() {
        setEditTextSuccesfull(mInputPassword);
    }

    private void onSuccessInputLoginName() {
        setEditTextSuccesfull(mInputName);
    }

    private void setEditTextSuccesfull(EditText editText) {
        editText.setError(null);
        setColorFilter(editText, Color.GREEN);
    }

    private void setEditTextError(EditText editText, String error) {
        editText.setError(error);
        setColorFilter(editText, Color.RED);
    }

    private void setColorFilter(View view, int color) {
        view.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
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
