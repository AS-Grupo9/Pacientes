package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.DBControlador;
import db.Log4J;
import net.proteanit.sql.DbUtils;
import utilidades.ComboBoxItem;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Toolkit;

public class InformePacientePorMedico extends JFrame {

	private JPanel contentPane;
	private static final Logger log = LogManager.getLogger(Log4J.class.getName());
	private final DBControlador conector = new DBControlador();
	private String[] cabecera = {"Código","Médico","Paciente"};
	private JTable table;
	private JComboBox comboBoxMedicos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformePacientePorMedico frame = new InformePacientePorMedico();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InformePacientePorMedico() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformePacientePorMedico.class.getResource("/recursos/iconoBig.png")));
		setTitle("CENTRO MEDICO LOS LAURELES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 348);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelInforme = new JLabel("Informe de pacientes por m\u00E9dico");
		labelInforme.setHorizontalAlignment(SwingConstants.RIGHT);
		labelInforme.setFont(new Font("Arial", Font.BOLD, 16));
		labelInforme.setBounds(187, 11, 376, 20);
		contentPane.add(labelInforme);
		
		
		JButton buttonVolver = new JButton("Volver");
		buttonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Informes inf = new Informes();
				inf.setLocationRelativeTo(null);
				inf.setVisible(true);
				dispose();
			}
		});
		buttonVolver.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonVolver.setBounds(474, 275, 89, 23);
		contentPane.add(buttonVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 553, 191);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Ver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla(comboBoxMedicos.getSelectedItem());
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(505, 42, 58, 23);
		contentPane.add(button);
		
		JLabel label = new JLabel("M\u00E9dico:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 48, 121, 14);
		contentPane.add(label);
		
		comboBoxMedicos = new JComboBox();
		comboBoxMedicos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxMedicos.setBounds(80, 45, 415, 20);
		contentPane.add(comboBoxMedicos);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{labelInforme, buttonVolver, scrollPane, table, button, comboBoxMedicos, label}));
		llenarMedicos();
	}
	
	
	private void llenarTabla(Object m){
		ComboBoxItem medico = (ComboBoxItem) m;
		ResultSet linea = conector.getPacientesPorMedico(medico.getValue());
		table.setModel(DbUtils.resultSetToTableModel(linea));
		
	}
	
	private void llenarMedicos(){
		ResultSet medicos = conector.getMedicos();
		try {
			
			while(medicos.next()){
				ComboBoxItem item = new ComboBoxItem(medicos.getString("codigo"), "Dr. "+medicos.getString("nombre"));		
				this.comboBoxMedicos.addItem(item);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}	
	}
}
