package pt.flawless.fapi.enums;

public enum FInventorySize {
    SIZE_9(9),
    SIZE_18(18),
    SIZE_27(27),
    SIZE_36(36),
    SIZE_45(45),
    SIZE_54(54);

    private final int size;

    FInventorySize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
