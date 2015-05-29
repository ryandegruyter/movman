package com.ryandg.text;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by Ryan De Gruyter on 25/05/2015.
 */
public class NoSpacesInputFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        for (int i = start; i < end; i++) {
            if (isSpace(source.charAt(i))) {
                return "";
            }
        }
        return null;
    }

    private boolean isSpace(char character) {
        String toCheck = String.valueOf(character);
        return toCheck.matches("\\s");
    }
}
