package com.ryandg.movieman.validation.rules;

import java.util.ArrayList;

/**
 * Created by Ryan De Gruyter on 28/05/2015.
 */
public class RuleList extends ArrayList<Rule> {

    public void addRule(Rule newRule) {
        if (!ruleExists(newRule)) {
            super.add(newRule);
        }
    }

    private boolean ruleExists(Rule newRule) {
        for (Rule rule : this) {
            if (newRule.getName().equals(rule.getName())) {
                return true;
            }
        }
        return false;
    }
}
