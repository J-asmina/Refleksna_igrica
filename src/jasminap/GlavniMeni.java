package jasminap;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Toolkit;

public class GlavniMeni extends JFrame {

	private JPanel contentPane;
	boolean tezina;
	int vreme;
    ImageIcon slikaPozadine;
    ImageIcon dugmeX;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniMeni frame = new GlavniMeni();
					frame.setVisible(true);
					frame.initComponents();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	
	public GlavniMeni() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GlavniMeni.class.getResource("/Slike/Logo.png")));
		initComponents();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 739);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setTitle("Refleksna igrica");
		setResizable(false);
		
		slikaPozadine = new ImageIcon(Igrica.class.getResource("/Slike/Pozadina.png"));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTimer = new JLabel("Vreme");
		lblTimer.setForeground(Color.BLACK);
		lblTimer.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setBounds(404, 322, 46, 22);
		contentPane.add(lblTimer);
		
		JSlider slider = new JSlider();
		slider.setBackground(Color.WHITE);
		slider.setBorder(null);
		slider.setBounds(168, 314, 200, 45);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				switch(slider.getValue()) {
					case 30 : lblTimer.setText("Brzo");
							  vreme = slider.getValue();
						break;
					case 75 : lblTimer.setText("Srednje");
					  		  vreme = slider.getValue();
						break;
					case 120 : lblTimer.setText("Sporo");
					  		   vreme = slider.getValue();
						break;
				}
			}
		});
		
		slider.setMinimum(30);
		slider.setMajorTickSpacing(45);
		slider.setSnapToTicks(true);
		slider.setMaximum(120);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setValue(120);
		contentPane.add(slider);
		vreme = slider.getValue();
		
		JComboBox comboBoxTezina = new JComboBox();
		comboBoxTezina.setBackground(Color.WHITE);
		comboBoxTezina.setSelectedIndex(-1);
		comboBoxTezina.setBounds(168, 420, 200, 22);
		contentPane.add(comboBoxTezina);
		comboBoxTezina.addItem("Lako");
		comboBoxTezina.addItem("Tesko");
		
		JLabel lblTezina = new JLabel(comboBoxTezina.getSelectedItem().toString());
		lblTezina.setForeground(Color.BLACK);
		lblTezina.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTezina.setHorizontalAlignment(SwingConstants.CENTER);
		lblTezina.setBounds(404, 424, 46, 14);
		contentPane.add(lblTezina);
		
		comboBoxTezina.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(comboBoxTezina.getSelectedItem().toString().equals("Tesko")) {
					tezina = true;
					lblTezina.setText(comboBoxTezina.getSelectedItem().toString());
				}
				else {
					tezina = false;
					lblTezina.setText(comboBoxTezina.getSelectedItem().toString());
				}
			}
		});
		
		JButton btnZapocni = new JButton("Započni");
		btnZapocni.setBounds(553, 489, 79, 22);
		btnZapocni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Igrica(tezina, vreme);
				Igrica.tesko = tezina;
				Igrica.vreme = vreme;
				dispose();
			}
		});
		contentPane.add(btnZapocni);
		
		JButton btnZatvori = new JButton("Zatvori");
		btnZatvori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnZatvori.setBounds(676, 633, 91, 45);
		contentPane.add(btnZatvori);
		
		JLabel lblNaslov = new JLabel("Refleksna Igrica");
		lblNaslov.setForeground(new Color(255, 215, 0));
		lblNaslov.setFont(new Font("Times New Roman", Font.PLAIN, 34));
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaslov.setBounds(179, 42, 447, 45);
		contentPane.add(lblNaslov);
		
		dugmeX = new ImageIcon(Igrica.class.getResource("/Slike/X.png"));
		
		JButton btnSakrivenExit = new JButton(dugmeX);
		btnSakrivenExit.setBounds(612, 228, 23, 19);
		contentPane.add(btnSakrivenExit);
		btnSakrivenExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel pozadina = new JLabel(slikaPozadine);
		pozadina.setBounds(0, 0, 800, 700);
		contentPane.add(pozadina);
		
	}
}
