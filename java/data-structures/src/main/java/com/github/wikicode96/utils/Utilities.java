package com.github.wikicode96.utils;

import java.util.List;

public class Utilities {

    public static double measureTime(Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    public static List addObjects(List list, int n) {
        for (int i = 0; i < n; i++) {
            list.add(new Object());
        }
        return list;
    }
}
