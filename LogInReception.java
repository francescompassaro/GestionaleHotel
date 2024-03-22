package finestre;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LogInReception extends JFrame{
	public LogInReception() {
		setTitle("SEZIONE DEDICATA AI RECEPTIONIST");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JLabel label = new JLabel("SEZIONE DEDICATA AI RECEPTIONIST");


	    // STRUTTURA FINESTRA LOGIN AMMINISTRAZIONE
	    setVisible(true);	
	    JPanel mainPanel = new JPanel();
	    add(mainPanel);
    	mainPanel.setLayout(new FlowLayout());    
    	mainPanel.add(label);    
    	
	    JButton logout = new JButton("Logout");
	    logout.setPreferredSize(new Dimension(90,20));
	    mainPanel.add(logout);
	    
	    // EVENTO PULSANTE LOGOUT
	    logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(() -> new LoginPrincipale());

				setEnabled(false);
				setVisible(false);
			}
		});
	    
	    
	}
}
