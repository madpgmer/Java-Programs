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
 * Holds the components for the reservation tab.
 * 
 */
public class ReservationsTab extends TabBase 
{
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;
	
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	/**
	 * List of reservation.
	 */
	private JList<Reservation> reservationList;
	
	private DefaultListModel<Reservation> reservationModel;
	
	private Reservation clickedReservation;
	
	/**
	 * Creates the components for reservation tab.
	 * 
	 * 
	 * @param reservationManager Instance of ReservationManager
	 * @author Madhu
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager; //this should load the reservations from the binary file
		
		this.set_reserveLabel("Reserve");
		this.set_label1("Code:");
		this.set_tabTitle("Reservations");
		this.set_label2("Flight:");
		this.set_label3("Airline:");
		this.set_label4("Cost:");
		this.set_label5("Name:");
		this.set_label6("Citzenship:");
		this.set_label7("Status:");
		this.set_text1("");
		this.set_text2("");
		this.set_text3("");
		this.set_text4("");
		this.set_text5("");
		this.set_text6("");
		String[] status = {"Active","Inactive"};
		this.set_text7(new JComboBox(status));
		this.set_button1("Update");
		this.set_blabel1("Search"); 
		this.set_button2("Find Reservations");
		this.set_blabel2("Code:");
		this.set_blabel3("Airline:");
		this.set_blabel4("Name:");
		this.set_btext1(new JTextField(""));
		this.set_btext2(new JTextField(""));;
		this.set_btext3(new JTextField("")); 
		
		
//		reservationModel = new DefaultListModel<>();
		reservationList = new JList(this.reservationManager.getReservations().toArray());
		
		// User can only select one item at a time.
		reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationList);
		
		reservationList.addListSelectionListener(new MyListSelectionListener());
		this.textbox= scrollPane;		
		
		
		JPanel mainPanel = new JPanel();  
		
		mainPanel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		this.button1.addActionListener(new MyButtonActionListenerReserve(this.reservationManager)); 
		this.button2.addActionListener(new MyButtonActionListenerFindR(this.reservationManager)); 
	}
	
	/**
	 * Creates the north panel.
	 * @return panel JPanel that goes in north.
	 * @author Madhu
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return panel JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		

		panel.add(basePanel());
		
		return panel;
	}
	
	/**
	 * this method updates the reservations and updates the list on the tab
	 * @throws IOException
	 * @throws InvalidCitizenshipException
	 * @throws InvalidNameException
	 * @author Madhu
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadCurrentReservations() throws IOException, InvalidCitizenshipException, InvalidNameException {
		this.reservationManager.populateFromBinary(); // need to do this to get the latest reservation list otherwise latest added will be missing		
		
		reservationList = new JList(this.reservationManager.getReservations().toArray());
		
		reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reservationList.addListSelectionListener(new MyListSelectionListener());
		
		textbox.setViewportView(reservationList);	
	}
	
	/***
	 * 
	 * @author madhu
	 * List Select listener to select the list and populate the textboxes on the right
	 */
	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void valueChanged(ListSelectionEvent e) {
			JList l = (JList) e.getSource();
			clickedReservation = (Reservation) l.getSelectedValue();
			
			set_text1(clickedReservation.getCode());
			set_text2(clickedReservation.getFlightCode());
			set_text3(clickedReservation.getAirline());
			set_text4(clickedReservation.getCost() + "");
			set_text5(clickedReservation.getName());
			set_text6(clickedReservation.getCitizenship());
			
			if (clickedReservation.getActive()) {
				JComboBox text7JCB = (JComboBox)text7;
				text7JCB.setSelectedItem("Active");
			} else {
				JComboBox text7JCB = (JComboBox)text7;
				text7JCB.setSelectedItem("Inactive");
			}
		}	
	}
	
	/**
	 * 
	 * @author madhu
	 * Action Listener to update the reservation (name, citizenship and status)
	 */
	private class MyButtonActionListenerReserve implements ActionListener 
	{
		private ReservationManager reservationManager;
		
		public MyButtonActionListenerReserve(ReservationManager reservationManager) {
			this.reservationManager = reservationManager;
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void actionPerformed(ActionEvent e) {			
			String name = text5.getText();
			String citizenship = text6.getText();          //((JTextField) text6).getText();			
			String active = ((JComboBox) text7).getSelectedItem().toString();
			
			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, " The name field is blank, you cannot Reserve without a Name");
			} else if(citizenship.isEmpty()) {
				JOptionPane.showMessageDialog(null, " The citizenship field is blank, you cannot Reserve without a citizenship");
			} else {
				try {
					Reservation reservation = this.reservationManager.findReservationByCode(clickedReservation.getCode()); //this searches and gets the reservation
					
					this.reservationManager.removeFromReservations(reservation);  //remove it from list, update it and then add it back
					
					reservation.setName(name);
					reservation.setCitizenship(citizenship);
					if (active.equalsIgnoreCase("Active")) {
						reservation.setActive(true);
					} else {
						reservation.setActive(false);
					}
						
					this.reservationManager.addToReservations(reservation); //this adds it to the reservation arraylist
					
					this.reservationManager.persist(); //this persists the list in the binary file
					
					loadCurrentReservations();

					JOptionPane.showMessageDialog(null, " The reservation with code: " + clickedReservation.getCode() + " is updated!");
					
				} catch (InvalidCitizenshipException | InvalidNameException | IOException e1) {
					JOptionPane.showMessageDialog(null, " The name/citizenship is invalid!");
					//e1.printStackTrace();
				}
				
			}
		}
	}
	
	/**
	 * 
	 * @author Madhu
	 * Action Listener to find the reservationsand update the reservation list
	 */
	private class MyButtonActionListenerFindR implements ActionListener 
	{
		private ReservationManager reservationManager;
		
		public MyButtonActionListenerFindR(ReservationManager reservationManager) {
			this.reservationManager = reservationManager;
		}
		
		/**
		 * Called when user selects the button.
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void actionPerformed(ActionEvent e) {
			String code = ((JTextField) btext1).getText();
			String airline = ((JTextField) btext2).getText();
			String name = ((JTextField) btext3).getText();

			ArrayList<Reservation> reservations = this.reservationManager.findReservations(code, airline, name);
			reservationList = new JList(reservations.toArray());
			
			// User can only select one item at a time.
			reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			reservationList.addListSelectionListener(new MyListSelectionListener());
			
			textbox.setViewportView(reservationList);		
		}
	}
}