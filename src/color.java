public enum color {
    RED(30, true),
    GREEN(1, true),
    BLACK(0, false),
    WHITE(0, true),
    NONE(0, true);

    private int value;
    private boolean canMove;

    color(int num, boolean canMove) {
        this.value = num;
        this.canMove = canMove;
    }

    public int getValue() {
        return value;
    }

    public boolean isCanMove() {
        return canMove;
    }
}
