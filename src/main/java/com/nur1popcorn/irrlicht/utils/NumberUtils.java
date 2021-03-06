/*
 * Copyright (C) Keanu Poeschko - All Rights Reserved
 * Unauthorized copying of this file is strictly prohibited
 *
 * Created by Keanu Poeschko <nur1popcorn@gmail.com>, April 2017
 * This file is part of {Irrlicht}.
 *
 * Do not copy or distribute files of {Irrlicht} without permission of {Keanu Poeschko}
 *
 * Permission to use, copy, modify, and distribute my software for
 * educational, and research purposes, without a signed licensing agreement
 * and for free, is hereby granted, provided that the above copyright notice
 * and this paragraph appear in all copies, modifications, and distributions.
 *
 *
 *
 *
 */

package com.nur1popcorn.irrlicht.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@link NumberUtils} is an util storing useful methods to do with {@link Number}s.
 *
 * @author nur1popcorn
 * @since 1.0.0-alpha
 */
public class NumberUtils
{
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TYPE_MAP = new HashMap<>(); static {
        PRIMITIVE_TYPE_MAP.put(Byte.TYPE, Byte.class);
        PRIMITIVE_TYPE_MAP.put(Short.TYPE, Short.class);
        PRIMITIVE_TYPE_MAP.put(Integer.TYPE, Integer.class);
        PRIMITIVE_TYPE_MAP.put(Long.TYPE, Long.class);
        PRIMITIVE_TYPE_MAP.put(Double.TYPE, Double.class);
        PRIMITIVE_TYPE_MAP.put(Float.TYPE, Float.class);
    }

    private static final Map<Class<? extends Number>, Method> NUMBER_CONVERSION_MAP = new HashMap<>(); static {
        for (Method method : Number.class.getDeclaredMethods())
            NUMBER_CONVERSION_MAP.put(
                (Class<Number>) PRIMITIVE_TYPE_MAP.get(method.getReturnType()),
                method
            );
    }

    //prevent construction :/
    private NumberUtils()
    {}

    /**
     * This function can be used to convert any {@link Number} to any other {@link Number}.
     *
     * @param number the {@link Number} that should be converted.
     * @param clazz the class it should be converted to.
     *
     * @return a converted version of the number cast to the class provided.
     */
    public static <T extends Number> T convertToTarget(Number number, Class<T> clazz)
    {
        try
        {
            return clazz.cast(NUMBER_CONVERSION_MAP.get(clazz).invoke(number));
        }
        catch (IllegalAccessException | InvocationTargetException e)
        {
            throw new RuntimeException("Could not determine correct conversion method for the provided number type.");
        }
    }
}
