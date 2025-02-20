package com.nontage.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportManager {
    private static final Map<UUID, UUID> tpaRequests = new HashMap<>();
    private static final Map<UUID, UUID> tpahereRequests = new HashMap<>();
    private static final Map<UUID, Integer> expires = new HashMap<>();
    public static void addTpaRequest(UUID from, UUID to) {
        tpaRequests.put(from, to);
        expires.put(from, 60);
    }
    public static void removeTpaRequest(UUID from) {
        tpaRequests.remove(from);
        expires.remove(from);
    }
    public static boolean hasTpaRequest(UUID from) {
        return tpaRequests.containsKey(from);
    }
    public static boolean hasTpaRequest(UUID from, UUID to) {
        return tpaRequests.containsKey(from) && tpaRequests.get(from).equals(to);
    }
    public static int getExpires(UUID from) {
        return expires.get(from);
    }
    public static void addTpahereRequest(UUID from, UUID to) {
        tpahereRequests.put(from, to);
        expires.put(from, 60);
    }
    public static void removeTpahereRequest(UUID from) {
        tpahereRequests.remove(from);
        expires.remove(from);
    }
    public static boolean hasTpahereRequest(UUID from) {
        return tpahereRequests.containsKey(from);
    }
    public static boolean hasTpahereRequest(UUID from, UUID to) {
        return tpahereRequests.containsKey(from) && tpahereRequests.get(from).equals(to);
    }
    public static void decreaseExpires() {
        expires.replaceAll((k, v) -> v - 1);
        expires.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }
}
