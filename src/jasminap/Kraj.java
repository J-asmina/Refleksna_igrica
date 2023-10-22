package jasminap;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Kraj extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static int rezultat;
	ImageIcon levaSlika;
	ImageIcon desnaSlika;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Kraj dialog = new Kraj(getRezultat());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @return 
	 */
	public Kraj(int rezultat) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Kraj.class.getResource("/Slike/Logo.png")));
		setRezultat(rezultat);
		initComponents();
	}
	
	public void initComponents() {
		setBounds(100, 100, 520, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 239, 213));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Refleksna igrica");
		setResizable(false);
		setLocationRelativeTo(null);
		
		levaSlika = new ImageIcon(Igrica.class.getResource("/Slike/Meta.png"));
        desnaSlika = new ImageIcon(Igrica.class.getResource("/Slike/ZelenaMeta.png"));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblRezultat = new JLabel("Osvojili ste " + rezultat + " poena!");
		lblRezultat.setForeground(Color.BLACK);
		lblRezultat.setHorizontalAlignment(SwingConstants.CENTER);
		lblRezultat.setBounds(84, 63, 339, 37);
		lblRezultat.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		contentPanel.add(lblRezultat);

		JLabel lblPonovo = new JLabel("Da li zelite da pokusate ponovo?");
		lblPonovo.setForeground(Color.BLACK);
		lblPonovo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPonovo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPonovo.setBounds(107, 132, 296, 43);
		contentPanel.add(lblPonovo);
		
		JLabel lblLevaSlika = new JLabel("");
		lblLevaSlika.setBounds(10, 11, 64, 64);
		lblLevaSlika.setIcon(levaSlika);
		contentPanel.add(lblLevaSlika);
		
		JLabel lblDesnaSlika = new JLabel("");
		lblDesnaSlika.setBounds(430, 11, 64, 64);
		lblDesnaSlika.setIcon(desnaSlika);
		contentPanel.add(lblDesnaSlika);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setSize(new Dimension(0, 50));
			buttonPane.setBackground(new Color(255, 239, 213));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton yesButton = new JButton("Da");
				yesButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						new GlavniMeni().setVisible(true);
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				yesButton.setActionCommand("Yes");
				buttonPane.add(yesButton);
				getRootPane().setDefaultButton(yesButton);
			}
			{
				JButton noButton = new JButton("Ne");
				noButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				JSeparator separator = new JSeparator();
				separator.setForeground(new Color(0, 0, 0, 0));
				separator.setPreferredSize(new Dimension(50, 0));
				buttonPane.add(separator);
				noButton.setActionCommand("No");
				buttonPane.add(noButton);
			}
		}
	}

	public static int getRezultat() {
		return rezultat;
	}

	public static void setRezultat(int rezultat) {
		Kraj.rezultat = rezultat;
	}
}
