package com.teoware.refapp.batch.util;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public final class BatchUtil {

    private BatchUtil() {
    }

    public static SortedSet<Integer> createSortedSet(Set<Integer> set) {
        return new TreeSet<Integer>(set);
    }
}
