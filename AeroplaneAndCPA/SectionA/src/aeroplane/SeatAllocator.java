package aeroplane;

import org.w3c.dom.css.CSSUnknownRule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeatAllocator {

	private Map<Seat, Passenger> allocation;
//	private static final Seat FIRST_SEAT = new Seat()
    private static final Seat FIRST_BUS = new Seat(2, 'A');
    private static final Seat LAST_BUS = new Seat(15, 'F');

	private static final Seat FIRST_ECON = new Seat(16, 'A');
	private static final Seat FINAL_SEAT = new Seat(50, 'F');

	private static final String CREW = "crew";
	private static final String BUSINESS = "business";
	private static final String ECONOMY = "economy";
	
	public SeatAllocator() {
		allocation = new HashMap<Seat, Passenger>();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Seat key : allocation.keySet()) {
			sb.append(key).append("->").append(allocation.get(key)).append("\n");
		}
		return sb.toString();
	}
	
	private void allocateInRange(Passenger passenger,
			Seat first, Seat last) throws AeroplaneFullException {
		Seat current = first;
		while (current.compareTo(last) <= 0) { //same or below
			if (!allocation.containsKey(current)) {
				if (!current.isEmergencyExit() || (current.isEmergencyExit() && passenger.isAdult())) {
					allocation.put(current, passenger);
					break;
				}
			}


			if (current.equals(last)) {//not allocated passenger
				throw new AeroplaneFullException();
			} else {
				current = current.next();
			}
		}
	}

	private static String readStringValue(BufferedReader br) throws MalformedDataException, IOException {

		String result = br.readLine();
		
		if(result == null) {
			throw new MalformedDataException();
		}
		
		return result;
		
	}

	private static int readIntValue(BufferedReader br)
			throws MalformedDataException, IOException {
		try {
			return Integer.parseInt(readStringValue(br));
		} catch(NumberFormatException e) {
			throw new MalformedDataException();
		}
	}

	private static Luxury readLuxuryValue(BufferedReader br)
			throws MalformedDataException, IOException {
		try {
			return Luxury.valueOf(readStringValue(br));
		} catch(IllegalArgumentException e) {
			throw new MalformedDataException();
		}
	}

	
	public void allocate(String filename) throws IOException, AeroplaneFullException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));

		String line;
		while((line = br.readLine()) != null) {
			try {
				if(line.equals(CREW)) {
					allocateCrew(br);
				} else if(line.equals(BUSINESS)) {
					allocateBusiness(br);
				} else if(line.equals(ECONOMY)) {
					allocateEconomy(br);
				} else {
					throw new MalformedDataException();
				}
			} catch(MalformedDataException e) {
				System.out.println("Skipping malformed line of input");
			}
		}
		
	}
	
	private void allocateCrew(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		Crew crewMember = new Crew(firstName, lastName); //row 1 is reserved for crew
		allocateInRange(crewMember, new Seat(1, 'A'), new Seat(1, 'F'));



		// TODO: Section A, Task 4
		//       create a crew member using firstName and lastName
		//       call allocateInRange with appropriate arguments
	}

	private void allocateBusiness(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		int age = readIntValue(br);
		Luxury luxury = readLuxuryValue(br);
		BusinessClass busPass = new BusinessClass(firstName, lastName, age, luxury);
		allocateInRange(busPass,FIRST_BUS ,LAST_BUS);

		// TODO: Section A, Task 4
		//       create a business class passenger using firstName, lastName, age and luxury
		//       call allocateInRange with appropriate arguments
	}

	private void allocateEconomy(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		int age = readIntValue(br);
		EconomyClass ecoPass = new EconomyClass(firstName, lastName, age);
		allocateInRange(ecoPass,FIRST_ECON, FINAL_SEAT);

		// TODO: Section A, Task 4
		//       create an economy class passenger using firstName, lastName and age
		//       call allocateInRange with appropriate arguments
	}


	public static void main(String[] args) {
		Seat seat1 = new Seat(1, 'A');
		Seat seat6 = new Seat(1, 'F');
		System.out.println(seat1.compareTo(seat6));
	}

	public void upgrade() {
		Seat current = FIRST_ECON;
		while (current.compareTo(FINAL_SEAT) <= 0) {
			if (allocation.containsKey(current)) {
				Passenger pass = allocation.get(current);
				allocation.remove(current);
				try {
					allocateInRange(pass, FIRST_BUS, LAST_BUS);
				} catch (AeroplaneFullException e) {
					//cant replace passenger, put them back in original spot
					allocation.put(current, pass);
					break; // no more spaces in business class
				}
			}
			if (current.equals(FINAL_SEAT)) break; // no more passengers to allocate
			current = current.next();
		}
	}
}
