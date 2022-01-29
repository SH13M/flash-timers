package com.sh13m.flashtimers;

public abstract class Time {
    public static String formatTime(int time) {
        int min = time / 60;
        int sec = time % 60;
        return String.format("%01d%c%02d", min, ':', sec);
    }
}
