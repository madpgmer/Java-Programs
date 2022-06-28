package sait.frms.problemdomain;

/** Constructs the Reservation class with all provided details

 * @author Madhu
 * @version July 9th 2021
 *
 */

import sait.frms.exception.InvalidCitizenshipException;
import sait.frms.exception.InvalidNameException;

public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
	
	/**
	 * Constructor of the Reservation Class
	 * @param code
	 * @param flightCode
	 * @param airline
	 * @param name
	 * @param citizenship
	 * @param cost
	 * @param active
	 * @throws InvalidCitizenshipException
	 * @throws InvalidNameException
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost, boolean active) throws InvalidCitizenshipException, InvalidNameException {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.setName(name);
		this.setCitizenship(citizenship);
		this.cost = cost;
		this.active = active;
	}
	
	/**
	 * second constructor of Reservation class
	 * @param code
	 * @param flight
	 * @param name
	 * @param citizenship
	 * @throws InvalidCitizenshipException
	 * @throws InvalidNameException
	 */
	public Reservation(String code, Flight flight, String name, String citizenship) throws InvalidCitizenshipException, InvalidNameException {
		super();
		this.code = code;
		this.flightCode = flight.getCode();
		this.airline = flight.getAirlineName();
		this.setName(name);
		this.setCitizenship(citizenship);
		this.cost = flight.getCostPerSeat();
		this.setActive(true);
		}

	/**
	 * gets the code
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * gets the flight code
	 * @return
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * gets the airline
	 * @return
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * gets the name of the reservation
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the reservation
	 * @param name
	 * @throws InvalidNameException
	 * @author Madhu
	 * 
	 */
	public void setName(String name) throws InvalidNameException {
		if (name == null || name.trim().isEmpty()) {
			throw new InvalidNameException();
		} else {
			this.name = name;
		}
	}

	/**
	 * gets the citizenship
	 * @return
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * sets the citizenship
	 * @param citizenship
	 * @throws InvalidCitizenshipException
	 * @author Madhu
	 */
	public void setCitizenship(String citizenship) throws InvalidCitizenshipException {
		if (citizenship == null || citizenship.trim().isEmpty()) {
			throw new InvalidCitizenshipException();
		} else {
			this.citizenship = citizenship;
		}
	}

	/**
	 * gets the cost of the resrvation
	 * @return
	 */
	public double getCost() {
		return cost;
	}

	public boolean getActive() {
		return active;
	}

	/**
	 * this sets the active status
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * toString for Reservation class
	 */
	@Override
	public String toString() {
		return "Reservation [code=" + code + ", flightCode=" + flightCode + ", airline=" + airline + ", name=" + name
				+ ", citizenship=" + citizenship + ", cost=" + cost + ", active=" + active + "]";
	}
	
}
