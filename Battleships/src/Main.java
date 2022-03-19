import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Grid grid = makeInitialGrid();
//        Grid grid = new Grid();
//        grid.placeShip(Util.parseCoordinate("A7"),2, false );
        System.out.println(grid);
        int totalShips = grid.getNumberofShips();
        int attackAttempts = 0;

        while (!grid.areAllSunk()) {
            System.out.println();
            System.out.println(grid.toPlayerString());
            System.out.println("Enter a Coordinate to Attack: ");
            String guess = input.next();
            Coordinate coord = Util.parseCoordinate(guess);
            attackAttempts++;
            if (grid.wouldAttackSucceed(coord)) {
                System.out.println("\nDirect Hit!");
            } else {
                System.out.println("\nMissed!");
            }
            grid.attackCell(coord);
        }

        System.out.println("\n---GAME OVER---\n");
        System.out.println(totalShips + " Attack attempts were required\n");
        System.out.println(grid);
    }

    private static Grid makeInitialGrid() {
        Grid grid = new Grid();
//        System.out.println(grid);
        String[] coords = { "A7", "B1", "B4", "D3", "F7", "H1", "H4" };
        int[] sizes = { 2, 4, 1, 3, 1, 2, 5 };
        boolean[] isDowns = { false, true, true, false, false, true, false };

        for (int i = 0; i < coords.length; i++) {
            Coordinate c = Util.parseCoordinate(coords[i]);
            grid.placeShip(c, sizes[i], isDowns[i]);
        }

        return grid;
    }
}
