package com.ryandg.movieman.validation;

import android.view.View;
import android.widget.EditText;

import com.ryandg.movieman.validation.rules.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public class EditTextValidator extends InputValidator {

    List<Integer> viewsWithErrorsIds;

    @Override
    public boolean isValid() {
        return viewsWithErrorsIds.size() == 0;
    }

    public EditTextValidator(OnInputValidationListener listener) {
        super(listener);
    }

    @Override
    public void validate(View view) {
        if (viewsWithErrorsIds == null) {
            viewsWithErrorsIds = new ArrayList<>();
        }

        final String input;
        final int viewId;

        try {
            final EditText editText = (EditText) view;
            input = editText.getText().toString();
            viewId = editText.getId();
        } catch (ClassCastException e) {
            throw new ClassCastException("Moet een edittext meegeven");
        }

        InputViewErrors inputViewErrors = new InputViewErrors(viewId);

        for (Rule rule : rules) {
            try {
                rule.validate(input);
            } catch (ValidationException e) {
                inputViewErrors.addError(e.getMessage());
            }
        }

        if (inputViewErrors.hasErrors()) {
            if (!viewsWithErrorsIds.contains(viewId)) {
                viewsWithErrorsIds.add(viewId);
            }
            getValidationListener().onInputError(inputViewErrors);
        } else {
            if (viewsWithErrorsIds.contains(viewId)) {
                viewsWithErrorsIds.remove(viewId);
            }
            getValidationListener().onInputSuccess(viewId);
        }
    }
}
