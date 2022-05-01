package aeroplane;

import java.util.List;
import java.util.NoSuchElementException;

public class Seat {
    private final int FIRST_ROW = 1;
    private final int FINAL_ROW = 50;
    private final char FIRST_LETTER = 'A';
    private final char FINAL_LETTER = 'F';
    private final List<Integer> eExits = List.of(1, 10, 30);


	private final int row;
    private final char letter;

    public Seat(int row, char letter) {
        assert row >= FIRST_ROW && row <= FINAL_ROW : "Row within range";
        assert letter >= FIRST_LETTER && letter <= FINAL_LETTER : "Letter within range";
        this.row = row;
        this.letter = letter;
    }

    public int getRow() {
        return row;
    }

    public char getLetter() {
        return letter;
    }

    public boolean isEmergencyExit() {
        return eExits.contains(this.row);
    }

    public boolean hasNext() {
        return !(this.row == FINAL_ROW && this.letter == FINAL_LETTER);
    }

    public Seat next() throws NoSuchElementException{
        if (!hasNext()) throw new NoSuchElementException();
        if (this.letter == FINAL_LETTER) {
            return new Seat(this.row + 1, FIRST_LETTER);
        } else {
            char nextLetter = (char) (this.letter + 1);
            return new Seat(this.row, nextLetter);
        }
    }

    public int compareTo(Seat other) {
        int rowDiff = this.row - other.getRow();
        if (rowDiff == 0) {
            return this.letter - other.getLetter();
        }
        return rowDiff;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Seat otherSeat) {
            return this.row == otherSeat.getRow()
                    && this.letter == otherSeat.getLetter();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (row * letter) % 101;
    }

    @Override
    public String toString() {
        return "" + row + letter;

    }
}
