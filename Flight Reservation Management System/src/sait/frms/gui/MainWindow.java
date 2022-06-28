package sait.frms.gui;

/**

@author: Madhu Madhavan

**/


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import sait.frms.exception.InvalidCitizenshipException;
import sait.frms.exception.InvalidFlightCodeException;
import sait.frms.exception.InvalidNameException;
import sait.frms.exception.NoMoreSeatsException;
import sait.frms.exception.NullFlightException;
import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;


/**
 * The main window (JFrame).
 * 
 */
public class MainWindow extends JFrame 
{
	private static final String TAB_FLIGHTS = "flights";
	private static final String TAB_RESERVATIONS = "reservations";
	
	/**
	 * Holds the flight  manager.
	 */
    private FlightManager flightManager;
	
    /**
	 * Holds the reservation  manager.
	 */
    private ReservationManager reservationManager;
    
    /**
	 * Card layout to display tab content.
	 */
	private CardLayout cardLayout;
	
	/**
	 * North panel.
	 */
	private JPanel northPanel;
	
	/**
	 * Center panel.
	 */
	private JPanel centerPanel;
	
	/**
	 * Flights tab button.
	 */
	private JButton flightsButton;
	
	/**
	 * Reservations tab button.
	 */
	private JButton reservationsButton;
	
	/**
	 * Flights tab panel.
	 */
	private TabBase flightsTab;
	
	/**
	 * Reservations tab panel.
	 */
	private ReservationsTab reservationsTab;
	
	/**
	 * Creates the Main Window and any components inside it.
	 * 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 * @throws InvalidNameException 
	 * @throws InvalidCitizenshipException 
	 * @author Madhu
	 */
	public MainWindow() throws NumberFormatException, IOException, InvalidCitizenshipException, InvalidNameException {
		this.flightManager = new FlightManager();
		this.reservationManager = new ReservationManager();
		
		setTitle("Flight Reservation Management System");
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//try this
		this.reservationsTab = new ReservationsTab(this.reservationManager);
		
		
		northPanel = createNorthPanel();
		add(northPanel, BorderLayout.NORTH);
		
		centerPanel = createCenterPanel();
		add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
			
		JPanel tabPanel = createTabPanel();
		panel.add(tabPanel, BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		cardLayout = new CardLayout();
		
		this.flightsTab = new FlightsTab(this.flightManager, this.reservationManager);
		//this.reservationsTab = new ReservationsTab(this.reservationManager);
		
		panel.setLayout(cardLayout);
		
		panel.add(flightsTab.getPanel(), TAB_FLIGHTS);
		panel.add(this.reservationsTab.getPanel(), TAB_RESERVATIONS);
		
		cardLayout.first(panel);

		return panel;
	}
	
	/**
	 * Creates the tab buttons.
	 * @return JPanel containing tab buttons.
	 */
	private JPanel createTabPanel() {
		JPanel tabPanel = new JPanel();
		
		tabPanel.setLayout(new GridLayout(1, 2));
		
		flightsButton = new JButton("Flights");
		reservationsButton = new JButton("Reservations");
		
		//this.reservationsTab = new ReservationsTab(this.reservationManager);           
		//ReservationsTab reservationsTab = (ReservationsTab) this.reservationsTab;
		
		flightsButton.addActionListener(new TabButtonActionListener(this.reservationsTab));
		reservationsButton.addActionListener(new TabButtonActionListener(this.reservationsTab));
		
		tabPanel.add(flightsButton);
		tabPanel.add(reservationsButton);
		
		return tabPanel;
	}
	
	/**
	 * Displays the JFrame window.
	 */
	public void display() 
	{
		pack();
		this.setSize(new Dimension(900,600));
		setVisible(true);
	}
	
	/**
	 * Inner action listener class that listens for a tab button to be clicked.
	 * 
	 * @author Madhu
	 * @version July 6, 2021
	 */
private class TabButtonActionListener implements ActionListener 
	{

	private ReservationsTab reservationsTab;
	public TabButtonActionListener(ReservationsTab reservationsTab) {
		this.reservationsTab = reservationsTab;
	}
	
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource() == flightsButton) 
			{
				cardLayout.show(centerPanel, TAB_FLIGHTS);
			} 
			else if (e.getSource() == reservationsButton) 
			{
			 	 try {
					this.reservationsTab.loadCurrentReservations();   // this loads the current reservations from the bin file
					cardLayout.show(centerPanel, TAB_RESERVATIONS);
			 	} catch (InvalidCitizenshipException | InvalidNameException | IOException e1) {
			 		JOptionPane.showMessageDialog(null, " The name/citizenship is invalid!");
			 		System.out.println(" The name/citizenship is invalid!");
					//e1.printStackTrace();
				}
			}
		}
		
	}
}
