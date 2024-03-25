package finestre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InterfacciaReception extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

	/**
	 * Launch the application.
	 */

	  private static final String DB_URL = "jdbc:mysql://localhost:3306/utentihotel";
	  private static final String DB_USER = "root";
	  private static final String DB_PASSWORD = "Francesco9000";
	  
	  
	  
	private Vector<String> columnNames = new Vector<>(); // qui ci mettiamo i nomi delle colonne
	private Vector<Vector<Object>> data = new Vector<>(); // qui ci mettiamo i dati

	public InterfacciaReception() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		
		initUI();
	   
	}
	
	private void loadData() {
		Stanza [] stanze= GestioneStanze.getStanza();
		// CARICO I LIBRI DAL DB
		for (Stanza stanza : stanze) {
			Vector<Object> row = new Vector<>();
			//CREAIAMO UN VETTORE DI OGGETTI PER RAPPRESENTARE UNA RIGA
			row.addElement(stanza.getNumeroStanza());
			row.addElement(stanza.getNomeStanza());
			row.addElement(stanza.getDescrizione());
			row.addElement(stanza.getPrezzo());
			row.addElement(stanza.isDisponibile());
			this.data.addElement(row); // AGGIUNGIAMO LA RIGA AL VETTORE DI RIGHE
			
			
		}
	}
	
	private void initUI() {
		// Aggiungo i nomi delle colonne
	    this.columnNames.addElement("Numero stanza"); 					// La colonna ID è un intero
	    this.columnNames.addElement("Nome stanza");		    	// La colonna Titolo è una stringa
	    this.columnNames.addElement("Descrizione");				// La colonna Autore è una stringa
	    this.columnNames.addElement("Prezzo");	// La colonna Anno Pubblicazione è un intero
	    this.columnNames.addElement("Disponibile");			// La colonna Disponibile è un booleano
	    this.loadData();// Carico i dati dal database, dentro this.data
	    // ora this.data contiene i dati dei libri
	    this.table = new JTable(this.data, columnNames);			// Creo la tabella con i dati e i nomi delle colonne
	    JScrollPane scrollPane = new JScrollPane(table);// Creo uno scroll pane per la tabella
		
		
	    
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		scrollPane.setPreferredSize(new Dimension(490, 100));
		contentPane.add(panel, gbc_panel);
		panel.add(scrollPane, BorderLayout.CENTER);

		

		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Numero Stanza");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(5);
		
		JLabel lblNewLabel_1 = new JLabel("Nome stanza");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Descrizione");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		panel_1.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Prezzo");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 4;
		panel_1.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Disponibilità");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 5;
		panel_1.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Aggiorna");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String numeroStanzaNew = textField.getText();
		    	String nomeStanzaNew = textField_1.getText();
		    	String DescrizioneStanzaNew = textField_2.getText();
		    	String PrezzoStanzaNew = textField_3.getText();
		    	double newprezzo = Double.parseDouble(PrezzoStanzaNew);
		    	String disponibileStanzaNew = textField_4.getText();
		    	System.out.println(numeroStanzaNew);
		    	System.out.println(nomeStanzaNew);
		    	System.out.println(DescrizioneStanzaNew);
		    	System.out.println(PrezzoStanzaNew);
		    	System.out.println(disponibileStanzaNew);
		    	updateStanze(numeroStanzaNew,nomeStanzaNew,DescrizioneStanzaNew,newprezzo,disponibileStanzaNew,nomeStanzaNew);
		    }
		});
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		

		
//		JLabel lblNewLabel_5 = new JLabel("");
//		lblNewLabel_5.setIcon(new ImageIcon(win3.class.getResource("")));
//		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
//		gbc_lblNewLabel_5.gridx = 1;
//		gbc_lblNewLabel_5.gridy = 1;
//		panel_2.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
	    table.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
//				int selectedRowIndex = table.getSelectedRow();

				int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
	            int selectedColumn1 = table.convertColumnIndexToModel(0);
	            int selectedColumn2 = table.convertColumnIndexToModel(1);
	            int selectedColumn3 = table.convertColumnIndexToModel(2);
	            int selectedColumn4 = table.convertColumnIndexToModel(3);
	            int selectedColumn5 = table.convertColumnIndexToModel(4);
	            String selectedId1 = (String)model.getValueAt(selectedRow, selectedColumn1);
	            String selectedId2 = (String)model.getValueAt(selectedRow, selectedColumn2);
	            String selectedId3 = (String)model.getValueAt(selectedRow, selectedColumn3);
	            double selectedId4 = (double)model.getValueAt(selectedRow, selectedColumn4);
	            boolean selectedId5 = (boolean)model.getValueAt(selectedRow, selectedColumn5);
	            
				
//	            System.out.println(selectedId1);
//	            System.out.println(selectedId2.getClass().getSimpleName());
//	            System.out.println(selectedId3);
	            //System.out.println(selectedId5);
//	            
	            
	            
	            
	            textField.setText(selectedId1);
	            textField_1.setText(selectedId2);
	            textField_2.setText(selectedId3);
	            String str = selectedId4+"";
	            textField_3.setText(str);
	            String str2 = selectedId2+"";
	            if (selectedId5) {
	            	textField_4.setText("SI");
	            }else {
	            	textField_4.setText("NO");
	            }
	            
	            
	            if(str2.equals("Stanza singola")) {
	            	System.out.println("singola");
	            	
	        		//JLabel etichettaProva = new JLabel("Prova etichetta");
	            	try {
						BufferedImage image = ImageIO.read(new File("./singola.jpg"));
						JLabel label = new JLabel(new ImageIcon(image));
						panel.add(label);
		        		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		        		gbc_lblNewLabel_5.gridx = 1;
		        		gbc_lblNewLabel_5.gridy = 1;
		        		panel_2.add(label, gbc_lblNewLabel_5);
		        		add(panel_2);
		        		setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

//	        		ImageIcon imageIcon = new ImageIcon("C:\\Users\\Francis\\Desktop\\doppia.jpg");
//	                // Crea un'istanza di JLabel
//	                JLabel label = new JLabel(imageIcon);

	            }
	            if(str2.equals("Camera doppia")) {
	            	try {
						BufferedImage image = ImageIO.read(new File("doppia.jpg"));
						JLabel label = new JLabel(new ImageIcon(image));
						panel.add(label);
		        		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		        		gbc_lblNewLabel_5.gridx = 1;
		        		gbc_lblNewLabel_5.gridy = 1;
		        		panel_2.add(label, gbc_lblNewLabel_5);
		        		add(panel_2);
		        		setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	    	    

			}// fine addMouseListener
					
					
	    });



}
	
	private static final String UPDATE_QUERY_STANZE = "UPDATE stanze SET numeroStanza =?,nomeStanza = ?,descrizione = ?,prezzo = ?, disponibile =? WHERE id =?";
	private void updateStanze(String numeroStanzaNew,String nomeStanzaNew, String DescrizioneStanzaNew, double newprezzo,String disponibileStanzaNew, String numeroStanza) {
		try {
			
			Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY_STANZE);
			pstmt.setString(1,numeroStanzaNew);
			pstmt.setString(2,nomeStanzaNew);
			pstmt.setString(3, DescrizioneStanzaNew); 
			pstmt.setDouble(4, newprezzo);
			pstmt.setString(5, disponibileStanzaNew);
			pstmt.setString(6, numeroStanza);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaReception frame = new InterfacciaReception();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}


