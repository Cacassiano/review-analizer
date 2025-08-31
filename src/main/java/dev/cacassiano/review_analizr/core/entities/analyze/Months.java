package dev.cacassiano.review_analizr.core.entities.analyze;

public enum Months {
    JAN(1),
    FEV(2),
    MAR(3),
    ABR(4),
    MAI(5),
    JUN(6),
    JUL(7),
    AGO(8),
    SET(9),
    OUT(10),
    NOV(11),
    DEZ(12);

    private final int value;

    Months(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
