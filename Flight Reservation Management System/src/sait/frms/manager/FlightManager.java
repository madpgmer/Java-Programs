package sait.frms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import sait.frms.exception.InvalidFlightCodeException;
import sait.frms.problemdomain.Flight;

/**
 * This program contains all the methods that reads all elements from airports.csv and flights.csv files 
 * 
 * @author Madhu
 * @version July 9th 2021
 *
 */
public class FlightManager {
	public static final String WEEKDAY_ANY = "Any";
	public static final String WEEKDAY_SUNDAY = "Sunday";
	public static final String WEEKDAY_MONDAY = "Monday";
	public static final String WEEKDAY_TUESDAY = "Tuesday";
	public static final String WEEKDAY_WEDNESDAY = "Wednesday";
	public static final String WEEKDAY_THURSDAY = "Thursday";
	public static final String WEEKDAY_FRIDAY = "Friday";
	public static final String WEEKDAY_SATURDAY = "Saturday";
	private static final String AIRPORTS_TEXT = "res/airports.csv";
	private static final String FLIGHTS_TEXT = "res/flights.csv";
	
	private ArrayList<Flight> flights;
	private ArrayList<String> airports;
	Scanner read = null;
	
	/**
	 * this is the FlightManager constructor
	 * @throws NumberFormatException
	 * @throws FileNotFoundException
	 * @author Madhu
	 */
	public FlightManager() throws NumberFormatException, FileNotFoundException {
		populateFlights(); //load the flights and airprots when this object is created
		populateAirports();
	}

	/**
	 * gets all the flights
	 * @return flights
	 */
	public ArrayList<Flight> getFlights() { //returns all the flights
		return flights;
	}

	/**
	 * gets the airports
	 * @return airports
	 */
	public ArrayList<String> getAirports() { //returns all the airport codes
		return airports;
	}

	/**
	 * this finds the airports by code
	 * @param code
	 * @return founfAirport
	 * @author Madhu
	 */
	public String findAirportByCode(String code) { //searches airports by code
		String foundAirport = "null";
		for (String airCode: this.airports) {
			if (code.equalsIgnoreCase(airCode)) {
				foundAirport = airCode;
				break;
			} 
		}
		return foundAirport;
	}
	
	/**
	 * this finds the flights by code
	 * @param code
	 * @return foundFlight
	 * @author Madhu
	 */
	public Flight findFlightsByCode(String code) { //searches all flights by their flight code
		Flight foundFlight = null;
		for (Flight fli: this.flights) {
			if (fli.getCode().equalsIgnoreCase(code)) {
				foundFlight = fli;
				break;
			} 
		}
		return foundFlight;
	}
	
	/**
	 * this finds the flights by from, to and weekday
	 * @param from
	 * @param to
	 * @param weekday
	 * @return flights 
	 * @author Madhu
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) { //need to test this
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for (Flight fli: this.flights) {
			if (fli.getFrom().equalsIgnoreCase(from) && fli.getTo().equalsIgnoreCase(to)) {
				if (weekday.equalsIgnoreCase(WEEKDAY_ANY)){
					flights.add(fli); //if weekday is ANY, then just add the fli flight to the returning arraylist
				} else if(fli.getWeekday().equalsIgnoreCase(weekday)) {
 					flights.add(fli); // otherwise only add the flight whose weekday matches
				} //else dont add anything
			} 
		}	
		return flights;
	}

	/**
	 * reads all the flights from a flights csv
	 * @throws NumberFormatException
	 * @throws FileNotFoundException
	 * @author Madhu
	 */
	public void populateFlights() throws NumberFormatException, FileNotFoundException {
		this.flights = new ArrayList<Flight>();
		File file = new File(FLIGHTS_TEXT);

		read = new Scanner(file);
		String data = "";
		String[] items = null;

		while (read.hasNextLine()) {

			data = read.nextLine();
			items = data.split(",");
			try {
				Flight flight = new Flight(items[0], items[1], items[2], items[3], items[4], Integer.parseInt(items[5]), Double.parseDouble(items[6]));
				this.flights.add(flight);
			}
			catch (InvalidFlightCodeException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * this reads the airports from the airports csv file
	 * @throws FileNotFoundException
	 * @author Madhu
	 */
	public void populateAirports() throws FileNotFoundException {
		this.airports = new ArrayList<String>();
		File file = new File(AIRPORTS_TEXT);

		read = new Scanner(file);
		String data = "";
		String[] items = null;

		while (read.hasNextLine()) {

			data = read.nextLine();
			items = data.split(",");
	
			this.airports.add(items[0]);
			}
	}

	
	
}
