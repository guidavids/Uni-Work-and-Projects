import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;

import java.awt.*;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.util.Random;


class Pulse extends JFrame {

	private JPanel contentPane; // Create the window
	static int charge; // Create "charge" variable to keep count of electric charge.
	static final int maxCharge = 1000; // Create "maxCharge" variable to set a maximum amount of charge when in base form.
	static final int maxOverCharge = 4000; // Create "maxOverCharge" variable to set maximum amount of charge for Overcharged form.
	static final int maxSuperCharge = 8000; // Create "maxSuperCharge" variable to set maximum amount of charge for Super form.
	static final int maxHyperCharge = 16000; // Create "maxHyperCharge" variable to set maximum amount of charge for Hyper form.
	static JLabel lblName; // Your name lol
	static JLabel lblCharge; // Label created to display the amount of electical charge you have stored
	static JButton btnDischarge; // Button of your first ability
	static JButton btnArc; // Button of your ultimate ability
	static JButton btnUTurn; //Neck twister
	static JButton btnKPulse;
	static JButton btnSyphon;
	static JButton btnPulseHeal;
	static JButton btnMwHammer;
	static JButton btnGwBlades;
	static JButton btnSGrenade;
	static JButton btnFTLRush; // Button for the hidden ability
	static JCheckBox chbxOverCharged; // Checkbox that was created to display correct values for Overcharged form.
	static JCheckBox chbxSuper; // Checkbox that was created to display correct values for Super form.
	static JCheckBox chbxHyper; // Checkbox that was created to display correct values for Hyper form.
	static String charge_s = Integer.toString(charge); // Created a String/Text that in case some operations don't accept numerical input.

	Timer Pulse = new Timer(1, new ActionListener() { // Created this time for the purpose of background tasks and cooldowns

		public void actionPerformed(ActionEvent e) { // Action this timer will perform

			if(charge < 200) { // If current charge is less than 200
				btnDischarge.setEnabled(false); // Disable "Discharge" ability
			}
			else { // otherwise
				btnDischarge.setEnabled(true); // Enable "Discharge" ability
			}

			if(charge < 300) { // If current charge is less than 400
				btnArc.setEnabled(false); // Disable "Arc" ability
			}
			else { // otherwise
				btnArc.setEnabled(true); // Enable "Arc" ability
			}
			
			if(charge < 400) {
				btnUTurn.setEnabled(false);
			}
			else {
				btnUTurn.setEnabled(true);
			}
			
			if(charge < 300) {
				btnKPulse.setEnabled(false);
			}
			else {
				btnKPulse.setEnabled(true);
			}
			
			if(charge < 600) {
				btnPulseHeal.setEnabled(false);
			}
			else {
				btnPulseHeal.setEnabled(true);
			}
			
			if(charge < 500) {
				btnMwHammer.setEnabled(false);
			}
			else {
				btnMwHammer.setEnabled(true);
			}
			
			if(charge < 300) {
				btnGwBlades.setEnabled(false);
			}
			else {
				btnGwBlades.setEnabled(true);
			}
			
			if(charge < 400) {
				btnSGrenade.setEnabled(false);
			}
			else {
				btnSGrenade.setEnabled(true);
			}

			if(charge < 4000) { // If current charge is less than 4000
				btnFTLRush.setEnabled(false); // Disable "Faster Than Light Rush" ability
			}
			else { // otherwise
				btnFTLRush.setEnabled(true); // Enable "Faster Than Light Rush" ability
			}
		}
	});

	Timer reCharge = new Timer(25, new ActionListener() { // Create a timer to take care of charge restoration for base form

		public void actionPerformed(ActionEvent e) { // The group of actions that the timer must do

			charge = charge + 1; // Increase charge by 1
			lblCharge.setText("Charge: " + charge); // Change "charge"'s text so it matches
			//System.out.println(charge);
			if(charge >= maxCharge) {
				charge = maxCharge;
				stopCharge();
				lblCharge.setText("Charge: " + charge);
			}
		}
	});

	Timer overReCharge = new Timer(12, new ActionListener() { // Create a timer to take care of charge restoration for overcharged form

		public void actionPerformed(ActionEvent e) {

			charge = charge + 2;
			lblCharge.setText("Charge:" + charge);

			if(charge >= maxOverCharge) {
				charge = maxOverCharge;
				stopOverCharge();
				lblCharge.setText("Charge: " + charge);
			}
		}

	});

	Timer superReCharge = new Timer(6, new ActionListener() { // Create a timer to take care of charge restoration for super form

		public void actionPerformed(ActionEvent e) {

			charge = charge + 4;
			lblCharge.setText("Charge:" + charge);

			if(charge >= maxSuperCharge) {
				charge = maxSuperCharge;
				stopSuperCharge();
				lblCharge.setText("Charge: " + charge);
			}
		}

	});

	static Random rand = new Random(); // Created a new instance of "Random" to create a random number between 100-600
	int phc = pHealCharge();
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JLabel label_15;
	private JLabel label_16;
	private JLabel label_17;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pulse frame = new Pulse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pulse() {

		Pulse.start();
		charge = maxCharge;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		lblName = new JLabel("<html><u>PULSE</u></html>");
		lblName.setForeground(new Color(153, 51, 255));
		lblName.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 60));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);

		btnDischarge = new JButton("Discharge");
		btnDischarge.setFont(new Font("Arial", Font.PLAIN, 30));
		btnDischarge.setForeground(new Color(153, 51, 255));

		btnFTLRush = new JButton("FTL Rush");
		btnFTLRush.setForeground(new Color(153, 51, 255));
		btnFTLRush.setFont(new Font("Arial", Font.PLAIN, 30));
		btnFTLRush.setVisible(false);


		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent btne) {

				Object o = btne.getSource();

				if(o == btnDischarge) {
					charge = charge - 200;
					lblCharge.setText("Charge: " + charge);
				}
				
				if(o == btnArc) {
					charge = charge - 300;
					lblCharge.setText("Charge: " + charge);
				}
				if(o == btnUTurn) {
					charge = charge - 400;
					lblCharge.setText("Charge: " + charge);
				}
				if(o == btnSyphon) {
					charge = charge + 200;
					lblCharge.setText("Charge: " + charge);
				}
				if(o == btnKPulse) {
					charge = charge - 300;
					lblCharge.setText("Charge: " + charge);
				}
				if(o == btnPulseHeal) {
					charge = charge - phc;
					lblCharge.setText("Charge: " + charge);
				}
				if(o == btnMwHammer) {
					charge = charge - 500;
					lblCharge.setText("Charge: " + charge);
				}
				if(o == btnGwBlades) {
					charge = charge - 300;
					lblCharge.setText("Charge: " + charge);
				}
				if(o == btnSGrenade) {
					charge = charge - 400;
				}
				if(o == btnFTLRush) {
					charge = charge - 4000;
					lblCharge.setText("Charge: " + charge);
				}
				//======================================================================
				if(o == chbxOverCharged) {
					charge = maxOverCharge;
				}
				if(o == chbxSuper) {
					charge = maxSuperCharge;
				}
				if(o == chbxHyper) {
					charge = maxHyperCharge;
				}

				if(chbxOverCharged.isSelected() == false && chbxSuper.isSelected() == false && chbxHyper.isSelected() == false) {

					stopOverCharge();
					stopSuperCharge();
					startCharge();

					chbxOverCharged.setEnabled(true);
					chbxSuper.setEnabled(true);
					chbxHyper.setEnabled(true);
					
					btnFTLRush.setVisible(false);

				}

				if(chbxOverCharged.isSelected() == true && chbxSuper.isSelected() == false && chbxHyper.isSelected() == false) {

					stopCharge();
					stopSuperCharge();
					startOverCharge();

					chbxSuper.setEnabled(false);
					chbxHyper.setEnabled(false);

				}

				if(chbxSuper.isSelected() == true && chbxOverCharged.isSelected() == false && chbxHyper.isSelected() == false) {

					stopCharge();
					stopOverCharge();
					startSuperCharge();

					chbxOverCharged.setEnabled(false);
					chbxHyper.setEnabled(false);
				}

				if(chbxHyper.isSelected() == true && chbxSuper.isSelected() == false && chbxOverCharged.isSelected() == false) {

					stopCharge();
					stopSuperCharge();
					stopOverCharge();

					chbxSuper.setEnabled(false);
					chbxOverCharged.setEnabled(false);
					btnFTLRush.setVisible(true);

					charge = Integer.MAX_VALUE;

					lblCharge.setText("Charge: Infinite");
				}
				else {

				}
			}
		};

		btnDischarge.addActionListener(buttonListener);
		btnFTLRush.addActionListener(buttonListener);

		ItemListener il = new ItemListener() {

			@Override
			public void itemStateChanged (ItemEvent ie) {

				Object o = ie.getStateChange() == 1;

				if(o == chbxOverCharged) {

					if(charge >= maxOverCharge) {
						stopOverCharge();
						charge = maxOverCharge;
					}
				}
			}
		};
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		label = new JLabel("");
		contentPane.add(label);
		contentPane.add(lblName);
		
		label_1 = new JLabel("");
		contentPane.add(label_1);
		contentPane.add(btnDischarge);
		
				btnArc = new JButton("Arc");
				btnArc.setForeground(new Color(153, 51, 255));
				btnArc.setFont(new Font("Arial", Font.PLAIN, 30));
				btnArc.addActionListener(buttonListener);
						
						label_2 = new JLabel("");
						contentPane.add(label_2);
				
						lblCharge = new JLabel("Charge: " + charge);
						lblCharge.setHorizontalAlignment(SwingConstants.CENTER);
						lblCharge.setFont(new Font("Arial", Font.BOLD, 40));
						contentPane.add(lblCharge);
				contentPane.add(btnArc);
		
		label_3 = new JLabel("");
		contentPane.add(label_3);
		
		label_4 = new JLabel("");
		contentPane.add(label_4);
		
		btnUTurn = new JButton("U-Turn");
		btnUTurn.setForeground(new Color(153, 51, 255));
		btnUTurn.setFont(new Font("Arial", Font.PLAIN, 30));
		btnUTurn.addActionListener(buttonListener);
		contentPane.add(btnUTurn);
		
		label_5 = new JLabel("");
		contentPane.add(label_5);
		
		label_6 = new JLabel("");
		contentPane.add(label_6);
		
		btnKPulse = new JButton("Kinetic Pulse");
		btnKPulse.setForeground(new Color(153, 51, 255));
		btnKPulse.setFont(new Font("Arial", Font.PLAIN, 26));
		btnKPulse.addActionListener(buttonListener);
		contentPane.add(btnKPulse);
				
				label_7 = new JLabel("");
				contentPane.add(label_7);
				
				btnPulseHeal = new JButton("Pulse Heal");
				btnPulseHeal.setForeground(new Color(153, 51, 255));
				btnPulseHeal.setFont(new Font("Arial", Font.PLAIN, 26));
				btnPulseHeal.addActionListener(buttonListener);
				
						chbxOverCharged = new JCheckBox("Overcharge?");
						chbxOverCharged.setFont(new Font("Arial", Font.PLAIN, 16));
						chbxOverCharged.setHorizontalAlignment(SwingConstants.CENTER);
						
								chbxOverCharged.addActionListener(buttonListener);
								
										chbxOverCharged.addItemListener(il);
										contentPane.add(chbxOverCharged);
				contentPane.add(btnPulseHeal);
				
				label_8 = new JLabel("");
				contentPane.add(label_8);
				
				btnSGrenade = new JButton("Shock Grenade");
				btnSGrenade.setForeground(new Color(153, 51, 255));
				btnSGrenade.setFont(new Font("Arial", Font.PLAIN, 22));
				btnSGrenade.addActionListener(buttonListener);
				
				btnGwBlades = new JButton("Gigawatt Blades");
				btnGwBlades.setForeground(new Color(153, 51, 255));
				btnGwBlades.setFont(new Font("Arial", Font.PLAIN, 20));
				btnGwBlades.addActionListener(buttonListener);
				
						chbxHyper = new JCheckBox("Hyper?");
						chbxHyper.setHorizontalAlignment(SwingConstants.CENTER);
						chbxHyper.setFont(new Font("Arial", Font.PLAIN, 16));
						chbxHyper.addActionListener(buttonListener);
						
						btnMwHammer = new JButton("Megawatt Hammer");
						btnMwHammer.setForeground(new Color(153, 51, 255));
						btnMwHammer.setFont(new Font("Arial", Font.PLAIN, 18));
						btnMwHammer.addActionListener(buttonListener);
						
						btnSyphon = new JButton("Syphon");
						btnSyphon.setForeground(new Color(153, 51, 255));
						btnSyphon.setFont(new Font("Arial", Font.PLAIN, 30));
						btnSyphon.addActionListener(buttonListener);
						
								chbxSuper = new JCheckBox("Super?");
								chbxSuper.setHorizontalAlignment(SwingConstants.CENTER);
								chbxSuper.setFont(new Font("Arial", Font.PLAIN, 16));
								chbxSuper.addActionListener(buttonListener);
								contentPane.add(chbxSuper);
						contentPane.add(btnSyphon);
						contentPane.add(btnMwHammer);
						contentPane.add(chbxHyper);
				contentPane.add(btnGwBlades);
				contentPane.add(btnSGrenade);
				
				label_9 = new JLabel("");
				contentPane.add(label_9);
				
				label_10 = new JLabel("");
				contentPane.add(label_10);
				
				label_11 = new JLabel("");
				contentPane.add(label_11);
		
		label_12 = new JLabel("");
		contentPane.add(label_12);
		
		label_13 = new JLabel("");
		contentPane.add(label_13);
		
		label_14 = new JLabel("");
		contentPane.add(label_14);
		
		label_15 = new JLabel("");
		contentPane.add(label_15);
		contentPane.add(btnFTLRush);
		
		label_16 = new JLabel("");
		contentPane.add(label_16);
		
		label_17 = new JLabel("");
		contentPane.add(label_17);
		//==================================================================================================================================
	}

	void stopCharge() {

		reCharge.stop();
		lblCharge.setText("Charge: " + charge);
	}

	void startCharge() {
		reCharge.start();
		lblCharge.setText("Charge: " + charge);
	}

	void stopOverCharge() {

		overReCharge.stop();
		lblCharge.setText("Charge: " + charge);
	}

	void startOverCharge() {
		overReCharge.start();
		lblCharge.setText("Charge: " + charge);
	}

	void stopSuperCharge() {

		superReCharge.stop();
		lblCharge.setText("Charge: " + charge);
	}

	void startSuperCharge() {
		superReCharge.start();
		lblCharge.setText("Charge: " + charge);
	}
	
	public static int pHealCharge() {
		int rCharge = rand.nextInt(600);
		rCharge = rCharge + 100;
		
		return rCharge;
	}
}
/*
 *Wtf is even going on
 */