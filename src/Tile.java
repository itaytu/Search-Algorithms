import java.util.Objects;

public class Tile {
    /**
     * This class represents a Tile in the Tile Puzzle.
     * It has different overridden methods needed in order to print and check equality between objects.
     */
    private color color;
    private int index;


    public Tile(color color, int index) {
        this.color = color;
        this.index = index;
    }

    public Tile(Tile tile) {
        this.color = tile.color;
        this.index = tile.index;
    }

    public color getColor() {
        return color;
    }

    public void setColor(color color) {
        this.color = color;
    }


    public int getIndex() { return  index; }

    public void setIndex(int index) { this.index = index; }

    @Override
    public String toString() {
        return "" + index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return index == tile.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}




