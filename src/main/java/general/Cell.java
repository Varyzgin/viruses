package general;

import java.util.Objects;

public class Cell {
    public int row;
    public int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Cell point = (Cell) obj;
        return row == point.row && column == point.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
