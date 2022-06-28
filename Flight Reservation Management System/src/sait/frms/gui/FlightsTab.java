package sait.frms.gui;
/**

@author: Madhu Madhavan

**/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.exception.InvalidCitizenshipException;
import sait.frms.exception.InvalidNameException;
import sait.frms.exception.NoMoreSeatsException;
import sait.frms.exception.NullFlightException;
import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 * 
 */
public class FlightsTab extends TabBase 
{
	
	/**
	 * List of flights.
	 */
	private JList flightsList;
	
	private DefaultListModel<Flight> flightsModel;

	private FlightManager flightManager;
	private ReservationManager reservationManager;
	private Flight foundFlight;
	
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 * @author Jenny
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;
		
		this.set_reserveLabel("Reserve");
		this.set_tabTitle("Flights");
		this.set_label1("Flight:");
		this.set_label2("Airline:");
		this.set_label3("Day:");
		this.set_label4("Time:");
		this.set_label5("Cost:");
		this.set_label6("Name:");
		this.set_label7("Citzenship:");
		this.set_text1("");
		this.set_text2("");
		this.set_text3("");
		this.set_text4("");
		this.set_text5("");
		this.set_text6("");
		this.set_button1("Reserve");
		this.set_blabel1("Flight Finder");
		this.set_button2("Find Flights");
		this.set_blabel2("From:");
		this.set_blabel3("To:");
		this.set_blabel4("Day:");
		
		this.text5.setEditable(false);
		
		//@Madhu 
		//We dont need the below - can take it from flightmanager
		//String[] from = {"YYC","YEG","YUL","YOW","YYZ","YVR","YWG","ATL","PEK","DXB","HKG","LHR","HND","ORD","OVG","PVG","CDG","AMS","DEL","FRA","DFW"};
		
		String[] day = {FlightManager.WEEKDAY_ANY, FlightManager.WEEKDAY_MONDAY, FlightManager.WEEKDAY_TUESDAY, FlightManager.WEEKDAY_WEDNESDAY, FlightManager.WEEKDAY_THURSDAY, FlightManager.WEEKDAY_FRIDAY, FlightManager.WEEKDAY_SATURDAY, FlightManager.WEEKDAY_SUNDAY};
		//String[] status = {"Active","Inactive"};
		
		this.set_btext1(new JComboBox(this.flightManager.getAirports().toArray()));
		this.set_btext2(new JComboBox(this.flightManager.getAirports().toArray()));
		this.set_btext3(new JComboBox(day)); 
		
		this.set_text7(new JTextField());
		
//		flightsModel = new DefaultListModel<>();
		flightsList = new JList();
		
		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);
		
		flightsList.addListSelectionListener(new MyListSelectionListener());
		this.textbox= scrollPane;		
		
		
		
		JPanel mainPanel = new JPanel();  
		
		mainPanel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		//testing this
		this.button2.addActionListener(new MyButtonActionListenerFF(this.flightManager)); 
		this.button1.addActionListener(new MyButtonActionListenerReserve(this.reservationManager)); 
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 * @author Jenny
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(basePanel());
		return panel;
	}
	
	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@SuppressWarnings("rawtypes")
		@Override
		public void valueChanged(ListSelectionEvent e) {
			JList l = (JList) e.getSource();
			foundFlight = (Flight) l.getSelectedValue();
			
			set_text1(foundFlight.getCode());
			set_text2(foundFlight.getAirlineName());
			set_text3(foundFlight.getWeekday());
			set_text4(foundFlight.getTime());
			set_text5(foundFlight.getCostPerSeat() + "");
		}	
	}
	
	/**
	 * 
	 * @author madhu
	 * searches for the flights and populates options in the tab
	 */
	private class MyButtonActionListenerFF implements ActionListener 
	{
		private FlightManager flightManager;
		
		public MyButtonActionListenerFF(FlightManager flightManager) {
			this.flightManager = flightManager;
		}
		
		/**
		 * Called when user selects the button.
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void actionPerformed(ActionEvent e) {
			String toFlight = ((JComboBox) btext1).getSelectedItem().toString();
			String fromFlight = ((JComboBox) btext2).getSelectedItem().toString();
			String weekday = ((JComboBox) btext3).getSelectedItem().toString();

			ArrayList<Flight> flight = this.flightManager.findFlights(toFlight, fromFlight, weekday);
			flightsList = new JList(flight.toArray());
			
			// User can only select one item at a time.
			flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			flightsList.addListSelectionListener(new MyListSelectionListener());
			
			textbox.setViewportView(flightsList);		
		}
	}
	
	/**
	 * 
	 * @author madhu
	 * Action Listener that creates a reservation
	 */
	private class MyButtonActionListenerReserve implements ActionListener 
	{
		private ReservationManager reservationManager;
		
		public MyButtonActionListenerReserve(ReservationManager reservationManager) {
			this.reservationManager = reservationManager;
		}
		
		/**
		 * Called when user selects the button.
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void actionPerformed(ActionEvent e) {
//			String code = text1.getText();
//			String airlineName = text2.getText();
//			String day = text3.getText();
//			String time = text4.getText();
//			String cost = text5.getText();
			
			String name = text6.getText();
			String citizenship = ((JTextField) text7).getText();			
			
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, " The name field is blank, you cannot Reserve without a Name");
			} else if(citizenship.isEmpty()) {
				JOptionPane.showMessageDialog(null, " The citizenship field is blank, you cannot Reserve without a citizenship");
			} else {
				try {
					Reservation reservation = this.reservationManager.makeReservation(foundFlight, name, citizenship); //this creates the reservation
					
					this.reservationManager.addToReservations(reservation); //this adds it to the reservation arraylist
					this.reservationManager.persist(); //this persists the list in the binary file
					JOptionPane.showMessageDialog(null, "Reservation created. Your code is " + reservation.getCode() + " see Reservations Tab for updates");
					
				} catch (InvalidCitizenshipException | InvalidNameException | NullFlightException| NoMoreSeatsException | IOException e1) {
					JOptionPane.showMessageDialog(null, " the name/citizenship is invalid, or there is no Flight to book, or there are no more seats");
					System.out.println(" The name/citizenship is invalid, or there is no Flight to book, or there are no more seats");
//					e1.printStackTrace();
				}
				
			}
		}
		
	}
}