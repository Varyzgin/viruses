package general;

import java.util.Arrays;

public class Message {
    public Cell cell;
    public CellType[][] movesHistoryTable;
    public boolean[][] availableMovesTable;
    public Action action;

    // CONNECT
    public Message(Action action) {
        this.action = action;
    }

    // MOVE
    public Message(Action action, Cell cell) {
        this.action = action;
        this.cell = cell;
    }

    // HISTORY
    public Message(Action action, CellType[][] movesHistoryTable, boolean[][] availableMovesTable) {
        this.action = action;
        this.movesHistoryTable = movesHistoryTable;
        this.availableMovesTable = availableMovesTable;
    }

    @Override
    public String toString() {
        String str = "Message{" + "action=" + this.action;
        if (this.action == Action.MOVE) str = str +
                ", cell=" + cell;
        else if (this.action == Action.FROM_SERVER) str = str +
                ", movesHistoryTable=" + Arrays.deepToString(movesHistoryTable) +
                ", availableMovesTable=" + Arrays.deepToString(availableMovesTable);
        str += '}';
        return str;
    }
}