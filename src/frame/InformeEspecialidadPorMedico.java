package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.DBControlador;
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

public class InformeEspecialidadPorMedico extends JFrame {

	private JPanel contentPane;
	private final DBControlador conector = new DBControlador();
	private String[] cabecera = {"Código","Médico","Paciente"};
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeEspecialidadPorMedico frame = new InformeEspecialidadPorMedico();
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
	public InformeEspecialidadPorMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelInforme = new JLabel("Informe de especialidades por m\u00E9dico");
		labelInforme.setHorizontalAlignment(SwingConstants.RIGHT);
		labelInforme.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelInforme.setBounds(187, 11, 376, 20);
		contentPane.add(labelInforme);
		
		
		JButton buttonVolver = new JButton("Volver");
		buttonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonVolver.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonVolver.setBounds(474, 275, 89, 23);
		contentPane.add(buttonVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 553, 219);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		llenarTabla();
	}
	
	
	private void llenarTabla(){
		
		ResultSet linea = conector.getEspecialidadesPorMedico();
		table.setModel(DbUtils.resultSetToTableModel(linea));
		
	}
}
