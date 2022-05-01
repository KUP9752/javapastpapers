package aeroplane;

public abstract class Passenger {
    protected String name;
    protected String surname;

    public Passenger(String firstName, String surname) {
        this.name = firstName;
        this.surname = surname;
    }

     public boolean isAdult() {
        return false;
     }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
