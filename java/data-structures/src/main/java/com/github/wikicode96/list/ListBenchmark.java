package com.github.wikicode96.list;

import java.util.Iterator;
import java.util.List;

import static com.github.wikicode96.utils.Utilities.addObjects;
import static com.github.wikicode96.utils.Utilities.measureTime;

public class ListBenchmark {

    public static double add(List list, int n) {
        return measureTime(() -> addObjects(list, n));
    }

    public static double deleteByIndex(List list, int n) {
        var finalList = addObjects(list, n);
        int middleIndex = n / 2;
        return measureTime(() -> finalList.remove(middleIndex));
    }

    public static double deleteByReference(List list, int n) {
        var finalList = addObjects(list, n);
        Object middleObject = list.get(n / 2);
        return measureTime(() -> finalList.remove(middleObject));
    }

    public static double deleteConsecutiveByIterator(List list, int n, int deleteCount) {
        var finalList = addObjects(list, n);

        return measureTime(() -> {
            Iterator<Object> it = finalList.iterator();
            int deleted = 0;
            while (it.hasNext() && deleted < deleteCount) {
                it.next();
                it.remove();
                deleted++;
            }
        });
    }
}
