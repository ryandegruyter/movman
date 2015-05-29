package com.ryandg.movieman.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public class InputViewErrors{
    private int viewId;
    private List<String> errors;

    public InputViewErrors(int viewId) {
        this.viewId = viewId;
    }

    public void addError(String error) {
        if (errors == null) {
            errors = new ArrayList();
        }
        errors.add(error);
    }

    public boolean hasErrors() {
        return (errors != null && errors.size() > 0);
    }

    public String getFirstError() {
        if (errors != null && errors.size() > 0) {
            return errors.get(0);
        }
        return null;
    }

    public String getAllErrors() {
        if (errors != null && errors.size() > 0) {
            String eachErrorNewLine = "";
            for (String error : errors) {
                eachErrorNewLine += error + "\n";
            }
        }
        return null;
    }
    public void clear() {
        if (errors != null) {
            errors.clear();
        }
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }
}
