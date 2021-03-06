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

package com.nur1popcorn.irrlicht.management;

import java.io.File;

/**
 * The {@link GameConfig} class contains useful information about the game which the
 * client was injected into.
 *
 * @author nur1popcorn
 * @since 1.1.0-alpha
 */
public class GameConfig
{
   public final String version;
   public final File gameDir,
                     assetDir;
   public final Class main;

   public GameConfig(String version, String gameDir, String assetDir, String main) throws ClassNotFoundException
   {
      this.version = version;
      this.gameDir = new File(gameDir);
      this.assetDir = new File(assetDir);
      this.main = Class.forName(main);
   }
}
