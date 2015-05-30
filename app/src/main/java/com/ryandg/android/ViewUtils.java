package com.ryandg.android;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ryandg.text.NoSpacesInputFilter;

/**
 * Created by Ryan De Gruyter on 29/05/2015.
 */
public class ViewUtils {

    public static void setEditTextSuccesfull(EditText editText) {
        editText.setError(null);
        setColorFilter(editText, Color.GREEN);
    }

    private static void setColorFilter(View view, int color) {
        view.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    public static void setNoSpacesInputFilter(EditText view) {
        final InputFilter[] inputFilters = {
                new NoSpacesInputFilter()
        };

        view.setFilters(inputFilters);
    }

    public static void setEditTextError(EditText editText, String error) {
        editText.setError(error);
        setColorFilter(editText, Color.RED);
    }

}
