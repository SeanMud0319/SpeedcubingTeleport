package com.nontage.utils;

public class ServerUtils {
    public static boolean isFolia;
    public static void init() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            isFolia = true;
        } catch (Exception ignored) {
            isFolia = false;
        }
    }

}
