package org.joshjoyce.sunbeam;

import java.util.ArrayList;
import java.util.List;

public class IntersectionList {
    public static final IntersectionList EMPTY = new IntersectionList(0);

    public final List<Intersection> intersections;
    private boolean hasNonNegative;

    IntersectionList(int initialSize) {
        intersections = new ArrayList<>(initialSize);
    }

    public void add(Intersection i) {
        intersections.add(i);
        if (i.t >= 0) {
            hasNonNegative = true;
        }
    }

    public int size() {
        return intersections.size();
    }

    public Intersection get(int i) {
        return intersections.get(i);
    }

    public Intersection hit() {
        if (!hasNonNegative) {
            return null;
        }

        Intersection h = null;

        for (Intersection i : intersections) {
            if (i.t >= 0) {
                if (h == null || h.t > i.t) {
                    h = i;
                }
            }
        }

        return h;
    }
}
