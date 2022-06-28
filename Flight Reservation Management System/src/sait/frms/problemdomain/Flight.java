package sait.frms.problemdomain;

import sait.frms.exception.InvalidFlightCodeException;

/** Constructs the Flight class with all provided details
 * @author Madhu
 * @version July 9th 2021
 *
 */
public class Flight {	
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	
	/**
	 * constructor for Flight
	 * @param code
	 * @param from
	 * @param to
	 * @param weekday
	 * @param time
	 * @param seats
	 * @param costPerSeat
	 * @throws InvalidFlightCodeException
	 */
	public Flight(String code, String from, String to, String weekday, String time, int seats, double costPerSeat) throws InvalidFlightCodeException {
		this.code = code;
		parseCode(code); // this sets the airlineName
		
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}
	
	/**
	 * gets the code of the flight
	 * @return
	 */
	public String getCode() {
		return code;
	}
		
	/**
	 * gets the Airline name
	 * @return
	 */
	public String getAirlineName() {
		return airlineName;
	}
	
	/**
	 * gets the source of the flight
	 * @return
	 */
	public String getFrom() {
		return from;
	}
		
	/**
	 * gets the destination of the flight
	 * @return
	 */
	public String getTo() {
		return to;
	}
	
	/**
	 * gets the flight's weekday
	 * @return
	 */
	public String getWeekday() {
		return weekday;
	}
	
	/**
	 * gets the time of the flight
	 * @return
	 */
	public String getTime() {
		return time;
	}
		
	/**
	 * gets the number of seats
	 * @return
	 */
	public int getSeats() {
		return seats;
	}
	
	/**
	 * sets the number of seats
	 * @param seats
	 */
	public void setSeats(int seats) { 
		// we need this method so that when a reservation is made, the seats need to be set
		this.seats = seats;
	}
		
	/**
	 * This method returns the cost per seat
	 * @return cost per seat
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}
		
	/**
	 * checks to see if the flights from and to are local or not
	 * @return true if local, false if not
	 */
	public boolean isDomestic() {
		if (this.from.startsWith("Y") && (this.to.startsWith("Y"))) {
			return true;		
		} else {
			return false;
		}
	}
	
	/**
	 * This method parses code to generate the full Airline Name
	 * @param code
	 * @throws InvalidFlightCodeException
	 * @author Madhu
	 */
	public void parseCode(String code) throws InvalidFlightCodeException {
		if (code.startsWith("OA")) {
			this.airlineName = "Otto Airlines";
		} else if (code.startsWith("CA")) {
			this.airlineName = "Conned Air";
		} else if (code.startsWith("TB")) {
			this.airlineName = "Try a Bus Airways";
		} else if (code.startsWith("VA")) {
			this.airlineName = "Vertical Airways";
		} else {
			throw new InvalidFlightCodeException(code);
		}
	}
	
	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Flight [code=" + code + ", airlineName=" + airlineName + ", from=" + from + ", to=" + to + ", weekday="
				+ weekday + ", time=" + time + ", seats=" + seats + ", costPerSeat=" + costPerSeat + "]";
	}

}
