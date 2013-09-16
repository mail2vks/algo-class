package org.vivek.algo2.wk1;

import java.util.Comparator;

public final class ScheduleComparators {

    public Comparator<Schedule> getRatioBasedComparator() {
        return new Comparator<Schedule>() {
            @Override
            public int compare(final Schedule s1, final Schedule s2) {
                Integer v1 = s1.getWeight() * s2.getLength();
                Integer v2 = s2.getWeight() * s1.getLength();
                if (v1 >= v2)
                    return -1;
                else if (v1 < v2)
                    return 1;

                return 0;
            }
        };
    }

    public Comparator<Schedule> getSubBasedComparator() {
        return new Comparator<Schedule>() {
            @Override
            /**
             * Tree Set stores data in ascending order hence returning reverse
             * for descending order sorting.
             */
            public int compare(final Schedule s1, final Schedule s2) {
                Integer v1 = s1.getWeight() - s1.getLength();
                Integer v2 = s2.getWeight() - s2.getLength();

                if (v1 > v2)
                    return -1;
                else if (v1 < v2)
                    return 1;
                else if (v1 == v2) {
                    if (s1.getWeight() >= s2.getWeight())
                        return -1;
                    else
                        return 1;
                }
                return 0;
            }
        };
    }

}
