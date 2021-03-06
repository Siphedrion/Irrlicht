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

package com.nur1popcorn.irrlicht.modules.impl.misc;

import com.nur1popcorn.irrlicht.Irrlicht;
import com.nur1popcorn.irrlicht.engine.events.EventTarget;
import com.nur1popcorn.irrlicht.engine.hooker.impl.UpdateEvent;
import com.nur1popcorn.irrlicht.engine.mapper.WrapperDelegationHandler;
import com.nur1popcorn.irrlicht.engine.wrappers.client.minecraft.Timer;
import com.nur1popcorn.irrlicht.management.values.SliderValue;
import com.nur1popcorn.irrlicht.management.values.ValueTarget;
import com.nur1popcorn.irrlicht.modules.Category;
import com.nur1popcorn.irrlicht.modules.Module;
import com.nur1popcorn.irrlicht.modules.ModuleInfo;

/**
 * The {@link Delorean} is a cheat that accelerates the game's number of updates effectively
 * incrementing the number of packets being sent and the number of movements being made.
 *
 * @see Module
 * @see UpdateEvent
 *
 * @author nur1popcorn
 * @since 1.0.0-alpha
 */
@ModuleInfo(name = "Delorean",
            category = Category.MISC)
public class Delorean extends Module
{
    @ValueTarget
    private SliderValue<Float> timerSpeed = new SliderValue<>(this, "Speed", 1f, 0.1f, 10f, 0.1f);

    private final Timer timer = WrapperDelegationHandler.createWrapperProxy(Timer.class, null);

    @Override
    public void onDisable()
    {
        super.onDisable();

        Irrlicht.getMinecraft()
                .setTimer(timer.construct(20.0f));
    }

    @EventTarget
    public void onUpdate(UpdateEvent event)
    {
        Irrlicht.getMinecraft()
                .setTimer(timer.construct(20.0f * timerSpeed.value));
    }
}
