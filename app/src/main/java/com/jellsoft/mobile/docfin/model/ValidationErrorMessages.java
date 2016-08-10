package com.jellsoft.mobile.docfin.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by atulanand on 8/9/16.
 */
public class ValidationErrorMessages {

    private final Map<Integer, String> messages = new HashMap<>();

    public void addErrorMsg(int id, String msg)
    {
        this.messages.put(id, msg);
    }

    public void reset()
    {
        this.messages.clear();
    }

    public boolean hasNoErrors()
    {
        return this.messages.isEmpty();
    }
}
