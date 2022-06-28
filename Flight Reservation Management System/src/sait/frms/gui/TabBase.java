package sait.frms.gui;
/**

@author: Madhu Madhavan

**/

import java.awt.*;

import javax.swing.*;

/**
 * Abstract class for a tab in the JFrame.
 * 
 */
public abstract class TabBase extends JFrame
{
	/**
	 * Tab panel attribute.
	 * @author Jenny
	 */
	protected JPanel panel;
	protected JLabel tabTitle;
	protected JScrollPane textbox;
	protected JLabel reserveLabel; 
	protected JLabel label1 ;
	protected JLabel label2 ;
	protected JLabel label3 ;
	protected JLabel label4 ;
	protected JLabel label5 ;
	protected JLabel label6 ;
	protected JLabel label7 ;
	protected JTextField text1;
	protected JTextField text2;
	protected JTextField text3;
	protected JTextField text4;
	protected JTextField text5;
	protected JTextField text6;
	protected JComponent text7;
	protected JButton button1;
	protected JLabel blabel1;
	protected JLabel blabel2 ;
	protected JLabel blabel3 ;
	protected JLabel blabel4 ;	
	protected JComponent btext1;
	protected JComponent btext2;
	protected JComponent btext3;
	protected JButton button2;

	
	/**
	 * Default constructor.
	 */
	protected TabBase() {
		// Flights or Reservations (placeholder)
		this.tabTitle = new JLabel("", SwingConstants.CENTER);
		this.tabTitle.setFont(new Font("serif", Font.PLAIN, 30));
		
		// Reserve headings
		this.reserveLabel = new JLabel("Reserve");
		this.reserveLabel = new JLabel("", SwingConstants.CENTER);
		this.reserveLabel.setFont(new Font("serif", Font.PLAIN, 25));
		
		// Large white box for text display
		this.textbox = new JScrollPane(20,30);
		this.textbox.setSize(100,200);
		
		// seven labels under reserve heading (placeholders)
		this.label1 = new JLabel("", SwingConstants.RIGHT);
		this.label2 = new JLabel("", SwingConstants.RIGHT);
		this.label3 = new JLabel("", SwingConstants.RIGHT);
		this.label4 = new JLabel("", SwingConstants.RIGHT);
		this.label5 = new JLabel("", SwingConstants.RIGHT);
		this.label6 = new JLabel("", SwingConstants.RIGHT);
		this.label7 = new JLabel("", SwingConstants.RIGHT);
		
		// seven corresponding text fields 
		this.text1 = new JTextField("");
		this.text2 = new JTextField("");
		this.text3 = new JTextField("");
		this.text4 = new JTextField("");
		this.text5 = new JTextField("");
		this.text6 = new JTextField("");
		this.text7 = new JTextField("");
		// these fields are never editable so the background is clear
		this.text1.setBackground(null); 
		this.text2.setBackground(null);
		this.text3.setBackground(null);
		this.text4.setBackground(null);
		// these fields are never editable so we set the flags
		this.text1.setEditable(false); 
		this.text2.setEditable(false);
		this.text3.setEditable(false);
		this.text4.setEditable(false);

		// Reserve or Update button (placeholder)
		this.button1 = new JButton("");	 
		this.button1.setSize(new Dimension(10, 10));
		
		// Search of Flight Finder heading (placeholder
		this.blabel1 = new JLabel("");
		this.blabel1 = new JLabel("", SwingConstants.CENTER);
		this.blabel1.setFont(new Font("serif", Font.PLAIN, 25));
		
		// three labels for lower section (placeholders)
		this.blabel2 = new JLabel("", SwingConstants.LEFT);
		this.blabel3 = new JLabel("", SwingConstants.LEFT);
		this.blabel4 = new JLabel("", SwingConstants.LEFT);
		
		
		// Fing Flights or Find Reservations button (placeholder)
		this.button2 = new JButton("");
		this.button2.setSize(new Dimension(10, 10));
			

	
	}
	/**
	 * Base panel creation
	 * @author Madhu
	 */
	public JPanel basePanel() {

		// holds lower section labels to the left
		JPanel panel7 = new JPanel();
		panel7.setLayout(new GridLayout(3,1));
		panel7.add(blabel2);
		panel7.add(blabel3);
		panel7.add(blabel4);
		//holds lower section text fields to the right
		JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayout(3,1));
		panel6.add(btext1);
		panel6.add(btext2);
		panel6.add(btext3);
		// contains lower section (heading, content, button)
		JPanel panel5 = new JPanel();
		panel5.setLayout(new BorderLayout());
		panel5.add(blabel1,BorderLayout.NORTH);
		panel5.add(panel7, BorderLayout.WEST);
		panel5.add(panel6,BorderLayout.CENTER);
		panel5.add(button2, BorderLayout.SOUTH);
		// contains mid-section labels & text fields
		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(7,2,2,2));
		panel4.add(label1);
		panel4.add(text1);
		panel4.add(label2);
		panel4.add(text2);
		panel4.add(label3);
		panel4.add(text3);
		panel4.add(label4);
		panel4.add(text4);
		panel4.add(label5);
		panel4.add(text5);
		panel4.add(label6);
		panel4.add(text6);
		panel4.add(label7);
		panel4.add(text7);
		// contains reserve heading, fields+labels & button)
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout(50,20));
		panel3.add(reserveLabel, BorderLayout.NORTH);
		panel3.add(panel4, BorderLayout.CENTER);
		panel3.add(button1, BorderLayout.SOUTH);
		// Contains entire mid section
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(30,0));
		panel2.add(textbox,BorderLayout.CENTER);
		panel2.add(panel3, BorderLayout.EAST);
		// main panel: add everything in
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0,30));
		panel.add(tabTitle, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.CENTER);
		panel.add(panel5, BorderLayout.SOUTH);
		// update attribute
		this.panel = panel;
		
		return panel;
	}
	
	// collection of set methods for defining label and heading values
	public void set_tabTitle(String general) {
		this.tabTitle.setText(general);
	}
	public void set_reserveLabel(String general) {
		this.reserveLabel.setText(general);
	}
	public void set_textbox(JScrollPane s) {
		this.textbox = s;
	}
	public void set_label1(String general) {
		this.label1.setText(general);
	}
	public void set_label2(String general) {
		this.label2.setText(general);
	}
	public void set_label3(String general) {
		this.label3.setText(general);
	} 
	public void set_label4(String general) {
		this.label4.setText(general);
	} 
	public void set_label5(String general) {
		this.label5.setText(general);
	} 
	public void set_label6(String general) {
		this.label6.setText(general);
	} 
	public void set_label7(String general) {
		this.label7.setText(general);
	}
	public void set_text1(String general) {
		this.text1.setText(general);
	}
	public void set_text2(String general) {
		this.text2.setText(general);
	}
	public void set_text3(String general) {
		this.text3.setText(general);
	}
	public void set_text4(String general) {
		this.text4.setText(general);
	}
	public void set_text5(String general) {
		this.text5.setText(general);
	}
	public void set_text6(String general) {
		this.text6.setText(general);
	}
	public void set_text7(JComponent general) {
		this.text7 = general;
	}
	public void set_button1(String general) {
		this.button1.setText(general);
	}
	public void set_blabel1(String general) {
		this.blabel1.setText(general);
	}
	public void set_blabel2(String general) {
		this.blabel2.setText(general);
	} 
	public void set_blabel3(String general) {
		this.blabel3.setText(general);
	}
	public void set_blabel4(String general) {
		this.blabel4.setText(general);
	}
	public void set_btext1(JComponent general) {
		this.btext1 = general;
	}
	public void set_btext2(JComponent general) {
		this.btext2 = general;
	}
	public void set_btext3(JComponent general) {
		this.btext3 = general;
	}
	public void set_button2(String general) {
		this.button2.setText(general);
	} 

	
	/**
	 * Gets the panel containing the tab components.
	 * @return JPanel with components.
	 */
	public JPanel getPanel() {
		return this.panel;
	}
}
