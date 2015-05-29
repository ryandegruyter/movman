package com.ryandg.movieman.validation;

import android.view.View;

import com.ryandg.movieman.validation.rules.Rule;
import com.ryandg.movieman.validation.rules.RuleList;

import java.util.ArrayList;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public abstract class InputValidator {

    public abstract boolean isValid();

    public interface OnInputValidationListener {
        void onInputError(InputViewErrors viewId);

        void onInputSuccess(int viewId);
    }

    private OnInputValidationListener validationListener;

    ArrayList<Rule> rules;

    public OnInputValidationListener getValidationListener() {
        return validationListener;
    }

    public void setRules(RuleList rules) {
        this.rules = rules;
    }

    public InputValidator(OnInputValidationListener listener) {
        this.validationListener = listener;
    }

    public abstract void validate(View view);
}
