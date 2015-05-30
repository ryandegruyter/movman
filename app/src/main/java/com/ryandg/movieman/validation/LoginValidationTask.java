package com.ryandg.movieman.validation;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import com.ryandg.android.ViewUtils;
import com.ryandg.movieman.validation.rules.Alphanumeric;
import com.ryandg.movieman.validation.rules.NotEmpty;
import com.ryandg.movieman.validation.rules.PasswordBelongsToUser;
import com.ryandg.movieman.validation.rules.RuleList;
import com.ryandg.movieman.validation.rules.UserExists;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan on 30/05/2015.
 */
public class LoginValidationTask extends AsyncTask<Void, InputViewErrors, Boolean> implements InputValidator.OnInputValidationListener {

    private final IsValidListener callback;

    public interface IsValidListener {
        void onIsValidated();
    }

    final EditText userField;
    final EditText passField;
    final Context context;
    EditTextValidator mInputValidator;


    public LoginValidationTask(Context context, EditText userInput, EditText passWordInput, IsValidListener callBack) {
        super();
        this.context = context;
        this.userField = userInput;
        this.passField = passWordInput;
        this.callback = callBack;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        mInputValidator = new EditTextValidator(this);

        mInputValidator.setRules(getInputNameRuleList());
        mInputValidator.validate(userField);

        mInputValidator.setRules(getInputPasswordRuleList());
        mInputValidator.validate(passField);

        return mInputValidator.isValid();
    }

    @Override
    protected void onPostExecute(Boolean isValid) {
        if (isValid) {
            callback.onIsValidated();
        }
    }

    @Override
    protected void onProgressUpdate(InputViewErrors... values) {
        final InputViewErrors inputViewErrors = values[0];
        if (inputViewErrors.hasErrors()) {
            setErrors(inputViewErrors);
        } else {
            setSuccess(inputViewErrors.getViewId());
        }

    }

    private void setSuccess(int viewID) {
        switch (viewID) {
            case R.id.inputLoginName:
                ViewUtils.setEditTextSuccesfull(userField);
                break;
            case R.id.inputPassword:
                ViewUtils.setEditTextSuccesfull(passField);
                break;
        }
    }

    private void setErrors(InputViewErrors inputViewErrors) {
        switch (inputViewErrors.getViewId()) {
            case R.id.inputLoginName:
                ViewUtils.setEditTextError(userField, inputViewErrors.getFirstError());
                break;
            case R.id.inputPassword:
                ViewUtils.setEditTextError(passField, inputViewErrors.getFirstError());
                break;
        }
    }

    public RuleList getInputNameRuleList() {
        RuleList rules = new RuleList();
        rules.addRule(new NotEmpty());
        rules.addRule(new Alphanumeric());
        rules.addRule(new UserExists(context));
        return rules;
    }

    public RuleList getInputPasswordRuleList() {
        final RuleList rules = new RuleList();
        rules.addRule(new NotEmpty());

        final String loginName = userField.getText().toString();
        rules.addRule(new PasswordBelongsToUser(loginName, context));
        return rules;
    }

    @Override
    public void onInputError(InputViewErrors errors) {
        publishProgress(errors);
    }

    @Override
    public void onInputSuccess(int viewId) {
        final InputViewErrors inputViewErrors = new InputViewErrors(viewId);
        publishProgress(inputViewErrors);
    }
}
