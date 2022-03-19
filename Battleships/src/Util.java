import java.util.Arrays;

public class Util {

    private static int letterToIndex(char letter) {
        // accepts letters and returns their index:
        assert Character.isAlphabetic(letter);
        letter = Character.toUpperCase(letter);
        return Character.getNumericValue(letter) - Character.getNumericValue('A');
    }

    private static int numberToIndex(char number) {
        assert number <= 9; // number always greater than 0
        return Character.getNumericValue(number);
    }

    public static Coordinate parseCoordinate(String s) {
        assert s.length() == 2; // other assertions are done in the toIndex functions
        return new Coordinate(letterToIndex(s.charAt(0)), numberToIndex(s.charAt(1)));
    }

    public static Piece hideShip(Piece piece) {
        return (piece == Piece.SHIP) ? Piece.WATER : piece;
    }

    public static void hideShips(Piece[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = hideShip(grid[r][c]);
            }
        }

    }

    public static Piece[][] deepClone(Piece[][] input) {
        Piece[][] output = new Piece[input.length][];
        for (int i = 0; i < input.length; i++) {
            output[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(letterToIndex('B'));
        System.out.println(numberToIndex('2'));
        System.out.println(parseCoordinate("D3"));
        System.out.println(parseCoordinate("H1"));
    }
}
