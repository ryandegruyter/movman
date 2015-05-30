package com.ryandg.movieman.validation;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import com.ryandg.android.ViewUtils;
import com.ryandg.movieman.validation.rules.Alphanumeric;
import com.ryandg.movieman.validation.rules.NameIsUnique;
import com.ryandg.movieman.validation.rules.NotEmpty;
import com.ryandg.movieman.validation.rules.PasswordBelongsToUser;
import com.ryandg.movieman.validation.rules.RuleList;
import com.ryandg.movieman.validation.rules.UserExists;

import ryandg.ryandg.movieman.R;

/**
 * Created by Ryan on 30/05/2015.
 */
public class SignupValidationTask extends LoginValidationTask{

    public SignupValidationTask(Context context, EditText userInput, EditText passWordInput, IsValidListener callBack) {
        super(context, userInput, passWordInput, callBack);
    }

    @Override
    public RuleList getInputNameRuleList() {
        final RuleList rules = new RuleList();
        rules.addRule(new NotEmpty());
        rules.addRule(new Alphanumeric());
        rules.addRule(new NameIsUnique(context));
        return rules;
    }

    @Override
    public RuleList getInputPasswordRuleList() {
        final RuleList rules = new RuleList();
        rules.addRule(new NotEmpty());
        return rules;
    }
}
