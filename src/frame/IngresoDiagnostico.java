package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.DBControlador;
import utilidades.ComboBoxItem;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class IngresoDiagnostico extends JFrame {

	private JPanel contentPane;
	private final JTextArea textAreaDiagnostico;
	private final JComboBox comboBoxMedico,comboBoxPaciente ;
	private final JLabel lblNombreMedico;
	private int codigoMedico ;
	
	private final DBControlador conector = new DBControlador();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresoDiagnostico frame = new IngresoDiagnostico();
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
	public IngresoDiagnostico() {
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("CENTRO MEDICO LOS LAURELES");
		setBounds(100, 100, 575, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCdigoDelPaciente = new JLabel("C\u00F3digo del paciente:");
		lblCdigoDelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCdigoDelPaciente.setBounds(10, 53, 121, 14);
		panel.add(lblCdigoDelPaciente);
		
		JLabel lblCdigoDelMdico = new JLabel("C\u00F3digo del m\u00E9dico:");
		lblCdigoDelMdico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCdigoDelMdico.setBounds(10, 78, 103, 14);
		panel.add(lblCdigoDelMdico);
		
		JLabel lblDatosDelPaciente = new JLabel("Ingrese el diagn\u00F3stico");
		lblDatosDelPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatosDelPaciente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosDelPaciente.setBounds(295, 11, 244, 20);
		panel.add(lblDatosDelPaciente);
		
		textAreaDiagnostico = new JTextArea();
		textAreaDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textAreaDiagnostico.setBounds(141, 106, 398, 83);
		panel.add(textAreaDiagnostico);
		
		final JLabel lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultado.setBounds(10, 216, 161, 14);
		panel.add(lblResultado);
		
		comboBoxMedico = new JComboBox();
		
		comboBoxMedico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxMedico.setBounds(141, 75, 398, 20);
		panel.add(comboBoxMedico);
		
		comboBoxPaciente = new JComboBox();
		comboBoxPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxPaciente.setBounds(141, 50, 398, 20);
		panel.add(comboBoxPaciente);
		
		this.llenarPacientes();
		this.llenarMedicos();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.setBounds(450, 216, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(conector.insertarDiagnostico(comboBoxMedico.getSelectedItem(), comboBoxPaciente.getSelectedItem(), textAreaDiagnostico.getText())){
					lblResultado.setText("Grabado");
					lblResultado.setForeground(Color.GREEN);
					refrescarPacientes();
					refrescarMedicos();
					textAreaDiagnostico.setText("");
					
				} else {
					lblResultado.setText("Error al grabar");
					lblResultado.setForeground(Color.RED);
				}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.setBounds(337, 216, 103, 23);
		panel.add(btnGuardar);
		
		JLabel lblDiagnstico = new JLabel("Diagn\u00F3stico:");
		lblDiagnstico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDiagnstico.setBounds(10, 108, 76, 14);
		panel.add(lblDiagnstico);
		
		lblNombreMedico = new JLabel("");
		lblNombreMedico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombreMedico.setBounds(285, 109, 244, 14);
		panel.add(lblNombreMedico);

	}
	
	private void llenarMedicos(){
		ResultSet medicos = conector.getMedicos();
		try {
			
			while(medicos.next()){
				ComboBoxItem item = new ComboBoxItem(medicos.getString("codigo"), "Dr. "+medicos.getString("nombre"));		
				this.comboBoxMedico.addItem(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}	
	}
	
	private void llenarPacientes(){
		ResultSet pacientes = conector.getPacientes();
		try {
			
			while(pacientes.next()){
				ComboBoxItem item = new ComboBoxItem(pacientes.getString("codigo"), pacientes.getString("nombre"));		
				this.comboBoxPaciente.addItem(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}	
	}
	
	private void refrescarPacientes(){
		this.comboBoxPaciente.removeAllItems();	
		llenarPacientes();	
	}
	
	private void refrescarMedicos(){
		this.comboBoxMedico.removeAllItems();	
		llenarMedicos();	
	}
}
