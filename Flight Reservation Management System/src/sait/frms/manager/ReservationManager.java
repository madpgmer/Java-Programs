package sait.frms.manager;

/**
 
* This program generates reservation codes, checks the number of seats and creates a reservation, It also finds reservation by code, 
 * airline and name and populates the reservations from the binary file into the reservation arraylist.
 * @author Madhu
 * @version July 9th 2021
 * 
 *
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import sait.frms.exception.InvalidCitizenshipException;
import sait.frms.exception.InvalidNameException;
import sait.frms.exception.NoMoreSeatsException;
import sait.frms.exception.NullFlightException;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

public class ReservationManager {
	private ArrayList<Reservation> reservations;

	public ArrayList<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) { // we may not need this - keeping it just in case
		this.reservations = reservations;
	}

	public void addToReservations(Reservation reservation) {
		this.reservations.add(reservation);
	}

	public void removeFromReservations(Reservation reservation) {
		this.reservations.remove(reservation);
	}

	private static final String RESERVATIONS_BINARY = "res/reservations.bin";
	private static final String MODE = "rw";
	private RandomAccessFile raf;
	private static final int RESERVATION_SIZE = 198; // see comment in persist method

	/**
	 * constructor for the reservation manager
	 * 
	 * @throws IOException
	 * @throws InvalidCitizenshipException
	 * @throws InvalidNameException
	 * @author Madhu
	 */
	public ReservationManager() throws IOException, InvalidCitizenshipException, InvalidNameException { 
		// should call the reservations file (if it exists)
		this.raf = new RandomAccessFile(RESERVATIONS_BINARY, MODE);
		populateFromBinary();
	}

	/**
	 * this generates a reservation code, checks the number of seats and creates a
	 * reservation
	 * 
	 * @param flight
	 * @param name
	 * @param citizenship
	 * @return
	 * @throws InvalidCitizenshipException
	 * @throws InvalidNameException
	 * @throws NullFlightException
	 * @throws NoMoreSeatsException
	 * @author Madhu
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship)
			throws InvalidCitizenshipException, InvalidNameException, NullFlightException, NoMoreSeatsException {
		if (flight == null) {
			throw new NullFlightException();
		} else {
			String reservationCode = generateReservationCode(flight);

			Reservation reservation = null;
			// need to first check if the flight has enough seats (more than 0) only then
			// make the reservation
			int currentSeats = flight.getSeats();
			if (currentSeats > 0) {
				reservation = new Reservation(reservationCode, flight, name, citizenship);
				// need to reduce the flight seats
				flight.setSeats(currentSeats--);
			} else {
				throw new NoMoreSeatsException();
			}
			return reservation;
		}
	}

	/**
	 * this finds a reservation by code, airline and name
	 * 
	 * @param code
	 * @param airline
	 * @param name
	 * @author Madhu
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();

		for (Reservation reserve : this.reservations) {
			if (reserve.getName().equalsIgnoreCase(name)) {
				if (reserve.getCode().equalsIgnoreCase(code) || reserve.getAirline().equalsIgnoreCase(airline)) {
					// if the reservation code OR airline matches,then return record
					reservations.add(reserve);
				}
			}
		}
		return reservations;
	}

	/**
	 * this finds a reservation by code
	 * 
	 * @param code
	 * @author Madhu
	 */
	public Reservation findReservationByCode(String code) {
		Reservation foundreservation = null;
		for (Reservation reserve : this.reservations) {
			if (reserve.getCode().equalsIgnoreCase(code)) {
				foundreservation = reserve;
				break;
			}
		}
		return foundreservation;
	}

	/**
	 * gets the available seats on a flight
	 * 
	 * @param flight
	 * @author Madhu
	 */
	public int getAvailableSeats(Flight flight) {
		int availSeats = flight.getSeats();
		return availSeats;
	}

	/**
	 * this generates a reservation code
	 * 
	 * @param flight
	 * @author Madhu
	 */
	public String generateReservationCode(Flight flight) { 
		String reservationCode = "";
		if (flight.isDomestic()) {
			reservationCode += "D";
		} else {
			reservationCode += "I";
		}
		int randomNum = 1000 + (int) (Math.random() * ((9999 - 1000) + 1)); // generate a random number from 1000 to 9999
		reservationCode += randomNum;

		return reservationCode;
	}

	/**
	 * this method saves the reservations data back into the binary file
	 * 
	 * @throws IOException
	 * @author Madhu
	 */
	public void persist() throws IOException {
		// reservation Code = 5 bytes + 2 = 7
		// airport code = 7 bytes + 2 = 9
		// airline = 17 bytes + 2 = 19
		// name = 100 bytes + 2 = 102
		// citizenship = 50 bytes + 2 = 52
		// cost = 1 double = 8 bytes = 8
		// active = 1boolean = 1 byte = 1
		// total = 7 + 9 + 19 + 102 + 52 + 8 + 1 = 198

		// need to set it to a new file otherwise, it is appending the file and it is
		// doubling every time Persist is called
		this.raf = new RandomAccessFile(RESERVATIONS_BINARY, MODE);

		// Write formatted output to the file
		for (Reservation reserve : this.reservations) {
			String reserveCode = String.format("%-5s", reserve.getCode());
			this.raf.writeUTF(reserveCode);

			String airportCode = String.format("%-7s", reserve.getFlightCode());
			this.raf.writeUTF(airportCode);

			String airline = String.format("%-17s", reserve.getAirline());
			this.raf.writeUTF(airline);

			String name = String.format("%-50s", reserve.getName());
			this.raf.writeUTF(name);

			String citizenship = String.format("%-100s", reserve.getCitizenship());
			this.raf.writeUTF(citizenship);

			// write 8 bytes
			this.raf.writeDouble(reserve.getCost());

			// write 1 byte
			this.raf.writeBoolean(reserve.getActive());
		}
	}

	/**
	 * this method reads the reservations individual line and creates a reservation
	 * record
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidCitizenshipException
	 * @throws InvalidNameException
	 * @author Madhu
	 */
	private Reservation readReservation() throws IOException, InvalidCitizenshipException, InvalidNameException {
		String reservationCode = this.raf.readUTF().trim();
		String airportCode = this.raf.readUTF().trim();
		String airline = this.raf.readUTF().trim();
		String name = this.raf.readUTF().trim();
		String citizenship = this.raf.readUTF().trim();
		double cost = this.raf.readDouble();
		boolean active = this.raf.readBoolean();

		Reservation reserve = new Reservation(reservationCode, airportCode, airline, name, citizenship, cost, active);
		return reserve;
	}

	/**
	 * this populates the reservations from the binary file into the reservation arraylist
	 * 
	 * @throws IOException
	 * @throws InvalidCitizenshipException
	 * @throws InvalidNameException
	 * @author Madhu
	 */
	public void populateFromBinary() throws IOException, InvalidCitizenshipException, InvalidNameException {
		this.reservations = new ArrayList<Reservation>(); // reservations should be added to a new list every time it is loaded from the binary
		for (long pos = 0; pos < this.raf.length(); pos += RESERVATION_SIZE) {
			this.raf.seek(pos);
			Reservation r = this.readReservation();
			this.reservations.add(r);
		}
	}
}
