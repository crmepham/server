package com.server.common.model;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class FieldExclusionStrategy implements ExclusionStrategy
{
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes)
    {
        if (fieldAttributes.getDeclaringClass() == AccountTransaction.class) {
            if (fieldAttributes.getName().equals("account")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass)
    {
        return false;
    }
}
