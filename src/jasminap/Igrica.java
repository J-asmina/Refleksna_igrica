package jasminap;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JProgressBar;

public class Igrica extends JFrame implements ActionListener {

    JPanel contentPane;
    JButton[][] polja;
    JLabel lblRezultat;
    JLabel lblVreme;
    int rez;
    int preostaloVreme;
    int prethodniRed;
    int prethodnaKolona;
    Random rand;
    JPanel gamePanel;
    ImageIcon zelenaMeta;
    ImageIcon meta;
    static boolean tesko;
    static int vreme = 30;
    private JProgressBar pbNapredak;
    int napredak = 0;
    

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Igrica frame = new Igrica(tesko, vreme);
                    frame.setVisible(true);
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
    
    public Igrica(boolean tesko, int vreme) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Igrica.class.getResource("/Slike/Logo.png")));
    	createEvents.setTesko(tesko);
        createEvents.setVreme(vreme);
        initComponents();
    }
    
    public void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0, 0, 800, 700);
        setTitle("Refleksna igrica");
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 220));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        pbNapredak = new JProgressBar();
        pbNapredak.setForeground(new Color(143, 188, 143));
        pbNapredak.setValue(napredak);
        contentPane.add(pbNapredak);
        
        lblRezultat = new JLabel("Rezultat: " + rez);
        lblRezultat.setForeground(Color.BLACK);
        lblRezultat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblRezultat.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblRezultat);
        
        lblVreme = new JLabel("Vreme: " + preostaloVreme);
        lblVreme.setForeground(Color.BLACK);
        lblVreme.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblVreme.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblVreme);
        
        zelenaMeta = new ImageIcon(Igrica.class.getResource("/Slike/ZelenaMeta.png"));
        meta = new ImageIcon(Igrica.class.getResource("/Slike/Meta.png"));
        
        gamePanel = new JPanel();
        gamePanel.setBackground(new Color(245, 245, 220));
        contentPane.add(gamePanel);
        gamePanel.setLayout(new GridLayout(4, 4, 2, 2));

        polja = new JButton[4][4];
        for (int red = 0; red < polja.length; red++) {
            for (int kolona = 0; kolona < polja[red].length; kolona++) {
            	if(tesko == true) {
            		polja[red][kolona] = new JButton(zelenaMeta);
            	}
            	else {
            		polja[red][kolona] = new JButton();
            	}
                polja[red][kolona].putClientProperty("red", red);
                polja[red][kolona].putClientProperty("kolona", kolona);
                polja[red][kolona].addActionListener(this);
                gamePanel.add(polja[red][kolona]);
            }
        }
        
        
        rez = 0;
        preostaloVreme = vreme;
        prethodniRed = -1;
        prethodnaKolona = -1;
        rand = new Random();

        setVisible(true);

        zapocniIgricu();
    }

    private void zapocniIgricu() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (preostaloVreme > 0) {
                    preostaloVreme--;
                    lblVreme.setText("Vreme: " + preostaloVreme);
                    napredak = (int) (((vreme - preostaloVreme) / (double) vreme) * 100);
                    pbNapredak.setValue(napredak);
                    if (preostaloVreme == 0) {
                        krajIgrice();
                        new Kraj(rez).setVisible(true);
                        dispose();
                    } else {
                        azurirajPolje();
                    }
                }
            }
        };
        new javax.swing.Timer(1000, taskPerformer).start();
        azurirajPolje();
    }

    private void azurirajPolje() {
        int red = rand.nextInt(4);
        int kolona = rand.nextInt(4);
        if (prethodniRed != -1 && prethodnaKolona != -1) {
        	if(createEvents.isTesko() == true) {
        		polja[prethodniRed][prethodnaKolona].setIcon(zelenaMeta);
        	}
        	else {
        		polja[prethodniRed][prethodnaKolona].setIcon(null);
        	}
        }
        polja[red][kolona].setIcon(meta);
        prethodniRed = red;
        prethodnaKolona = kolona;
    }

    private void krajIgrice() {
        for (int red = 0; red < polja.length; red++) {
            for (int kolona = 0; kolona < polja[red].length; kolona++) {
                polja[red][kolona].setEnabled(false);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int red = (int) button.getClientProperty("red");
        int kolona = (int) button.getClientProperty("kolona");
        if (red == prethodniRed && kolona == prethodnaKolona) {
        	if(tesko == true) {
        		rez+=5;
        	}
        	else {
        		rez++;
        	}
            lblRezultat.setText("Rezultat: " + rez);
            if(createEvents.isTesko() == true) {
            	polja[red][kolona].setIcon(zelenaMeta);
            }
            else {
            	polja[red][kolona].setIcon(null);
            }
            azurirajPolje();
        }
    }

	public interface createEvents {
		public static boolean isTesko() {
			return tesko;
		}

		public static void setTesko(boolean tesko) {
			Igrica.tesko = tesko;
		}

		public static int getVreme() {
			return vreme;
		}

		public static void setVreme(int vreme) {
			Igrica.vreme = vreme;
		}
    }

} 
