public enum color {
    RED(30, true),
    GREEN(1, true),
    BLACK(0, false),
    WHITE(0, true),
    NONE;

    private int value;
    private boolean canMove;

    color(int num, boolean canMove) {
        this.value = num;
        this.canMove = canMove;
    }

    color() {}

    public int getValue() {
        return value;
    }

    public boolean isCanMove() {
        return canMove;
    }
}
