import java.util.Arrays;

public class Grid {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private final Piece[][] grid = new Piece[HEIGHT][WIDTH];
    private int totalShipPieces = 0;

    public Grid() {
        Arrays.stream(grid).forEach(row -> Arrays.fill(row, Piece.WATER));
    }

    public int getNumberofShips() {
        return totalShipPieces;
    }

    public boolean canPlace(Coordinate c, int size, boolean isDown) {
        boolean withinBounds;
        boolean placeable = false;
        if (isDown) {
            withinBounds = c.getRow() + size <= HEIGHT;
            for (int i = 0; i < size; i++) {
                placeable = grid[c.getRow() + i][c.getColumn()] == Piece.WATER;
            }
        } else {
            withinBounds = c.getColumn() + size <= WIDTH;
            for (int i = 0; i < size; i++) {
                placeable = grid[c.getRow()][c.getColumn() + i] == Piece.WATER;
            }
        }
        return withinBounds && placeable;
    }

    public void placeShip(Coordinate c, int size, boolean isDown) {
        if (canPlace(c, size, isDown)) {
            if (isDown) {
                for (int i = 0; i < size; i++) {
                    grid[c.getRow() + i][c.getColumn()] = Piece.SHIP;
                    totalShipPieces++;
                }
            } else {
                for (int i = 0; i < size; i++) {
                    grid[c.getRow()][c.getColumn() + i] = Piece.SHIP;
                    totalShipPieces++;
                }
            }
        }
    }

    public boolean wouldAttackSucceed(Coordinate c) {
        return grid[c.getRow()][c.getColumn()] == Piece.SHIP;
    }

    public void attackCell(Coordinate c) {
        Piece piece = grid[c.getRow()][c.getColumn()];
        switch (piece) {
            case SHIP -> {
                piece = Piece.DAMAGED_SHIP;
                totalShipPieces--;
            }
            case WATER -> piece = Piece.MISS;
            default -> System.out.println("-->Already attacked this Coordinate.\n");
        };
        grid[c.getRow()][c.getColumn()] = piece;

//        if (grid[c.getRow()][c.getColumn()] == Piece.SHIP) {
//            grid[c.getRow()][c.getColumn()] = Piece.DAMAGED_SHIP;
//            totalShipPieces--;
//        }
//        if (grid[c.getRow()][c.getColumn()] == Piece.WATER) {
//            grid[c.getRow()][c.getColumn()] = Piece.MISS;
//        }
//

    }

    public boolean areAllSunk() {
        return totalShipPieces == 0;
//        boolean anyShip = true;
//        for (Piece[] row : grid) {
//            for (Piece p : row) {
//                anyShip = p == Piece.SHIP;
//            }
//        }
//        return !(anyShip);
    }

    public String toPlayerString() {
        Piece[][] playerGrid = Util.deepClone(grid);
        Util.hideShips(playerGrid);
        return renderGrid(playerGrid);
    }

    @Override
    public String toString() {
        return renderGrid(grid);
    }

    private static String renderGrid(Piece[][] grid) {
        StringBuilder sb = new StringBuilder();
        sb.append(" 0123456789\n");
        for (int i = 0; i < grid.length; i++) {
            sb.append((char) ('A' + i));
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    return "!";
                }
                switch (grid[i][j]) {
                case SHIP:
                    sb.append('#');
                    break;
                case DAMAGED_SHIP:
                    sb.append('*');
                    break;
                case MISS:
                    sb.append('o');
                    break;
                case WATER:
                    sb.append('.');
                    break;
                }

            }
            sb.append('\n');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        System.out.println(grid);
        grid.placeShip(Util.parseCoordinate("A7"), 2, false);
        System.out.println(grid);
    }
}
