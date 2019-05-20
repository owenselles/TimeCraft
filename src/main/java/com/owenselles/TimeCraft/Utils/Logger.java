package com.owenselles.TimeCraft.Utils;

import com.owenselles.TimeCraft.Main;

import java.util.logging.Level;

public class Logger {
    public static void log(Level lvl, String info)
    {
        Main.getPlugin(Main.class).getLogger().log(lvl, info);
    }
}
