package ua.com.alevel.enumerations;

public enum Month {

    JANUARY(1, "JAN"),
    FEBRUARY(2, "FEB"),
    MARCH(3, "MAR"),
    APRIL(4, "APR"),
    MAY(5, "MAY"),
    JUNE(6, "JUN"),
    JULY(7, "JUL"),
    AUGUST(8, "AUG"),
    SEPTEMBER(9, "SEP"),
    OCTOBER(10, "OCT"),
    NOVEMBER(11, "NOV"),
    DECEMBER(12, "DEC");

    private int monthOrder;
    private String monthName;

    Month(int monthOrder) {
        this.monthOrder = monthOrder;
    }

    Month(int monthOrder, String monthName) {
        this.monthOrder = monthOrder;
        this.monthName = monthName;
    }

    public int getMonthOrder() {
        return this.monthOrder;
    }

    public String getMonthName() {
        return this.monthName;
    }

    public static Month fromString(String text) {
        for (Month b : Month.values()) {
            if (b.monthName.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}