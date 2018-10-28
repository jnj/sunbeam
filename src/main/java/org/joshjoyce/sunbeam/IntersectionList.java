package org.joshjoyce.sunbeam;

import java.util.ArrayList;
import java.util.List;

public class IntersectionList {
    public static final IntersectionList EMPTY = new IntersectionList(0);

    public final List<Intersection> intersections;

    IntersectionList(int initialSize) {
        intersections = new ArrayList<>(initialSize);
    }

    public void add(Intersection i) {
        intersections.add(i);
    }

    public int size() {
        return intersections.size();
    }

    public Intersection get(int i) {
        return intersections.get(i);
    }
}
