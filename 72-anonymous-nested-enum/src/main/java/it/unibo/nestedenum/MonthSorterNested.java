package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    enum Month{
        JANUARY(31,1),
        FEBRUARY(28,2),
        MARCH(31,3),
        APRIL(30,4),
        MAY(31,5),
        JUNE(30,6),
        JULY(31,7),
        AUGUST(31,8),
        SEPTEMBER(30,9),
        OCTOBER(31,10),
        NOVEMBER(30,11),
        DECEMBER(31,12);

        private int days;
        private int order;
        private Month(int days, int or) {
            this.days = days;
            this.order = or;
        }

        public static Month fromString(String name) {
            Month finded = null;
            for (Month elem : values()) {
                if(elem.name().toUpperCase().contains(name.toUpperCase())) {
                    if(finded == null) {
                        finded = elem;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            }
            if(finded == null) {
                throw new IllegalArgumentException();
            }
            return finded;
        }
    }
    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                try {
                    Month m1 = Month.fromString(o1);
                    Month m2 = Month.fromString(o2);
                    return m1.days - m2.days;
                } catch (Exception e) {
                    throw new IllegalArgumentException();
                }
                
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                try {
                    Month m1 = Month.fromString(o1);
                    Month m2 = Month.fromString(o2);
                    return m1.order-m2.order;
                } catch (Exception e) {
                    throw new IllegalArgumentException();
                }
            }
        };
    }
}
