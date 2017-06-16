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
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;


public class IngresoDiagnostico extends JFrame {

	private JPanel contentPane;
	private final JTextArea textAreaDiagnostico;
	private final JComboBox comboBoxMedico,comboBoxPaciente ;
	private final JLabel lblNombreMedico;
	private int codigoMedico ;
	private JDateChooser dateChooserFecha;
	private JLabel lblResult;
	
	private final DBControlador conector = new DBControlador();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresoDiagnostico frame = new IngresoDiagnostico();
					frame.setLocationRelativeTo(null);
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(IngresoDiagnostico.class.getResource("/recursos/iconoBig.png")));
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("SITUACION PACIENTE - CENTRO MEDICO LOS LAURELES");
		setBounds(100, 100, 575, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JLabel lblCdigoDelPaciente = new JLabel("Paciente:");
		lblCdigoDelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCdigoDelPaciente.setBounds(10, 106, 121, 14);
		panel.add(lblCdigoDelPaciente);
		
		JLabel lblCdigoDelMdico = new JLabel("M\u00E9dico:");
		lblCdigoDelMdico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCdigoDelMdico.setBounds(10, 136, 103, 14);
		panel.add(lblCdigoDelMdico);
		
		JLabel lblDatosDelPaciente = new JLabel("Ingrese el diagn\u00F3stico");
		lblDatosDelPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatosDelPaciente.setFont(new Font("Arial", Font.BOLD, 16));
		lblDatosDelPaciente.setBounds(295, 11, 244, 20);
		panel.add(lblDatosDelPaciente);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Diagnóstico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 164, 529, 106);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textAreaDiagnostico = new JTextArea();
		textAreaDiagnostico.setBounds(20, 16, 499, 83);
		panel_1.add(textAreaDiagnostico);
		textAreaDiagnostico.setLineWrap(true);
		textAreaDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
			
		comboBoxMedico = new JComboBox();		
		comboBoxMedico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxMedico.setBounds(141, 133, 398, 20);
		panel.add(comboBoxMedico);
		
		comboBoxPaciente = new JComboBox();
		comboBoxPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxPaciente.setBounds(141, 103, 398, 20);
		panel.add(comboBoxPaciente);
		
		this.llenarPacientes();
		this.llenarMedicos();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ingresos ing = new Ingresos();
				ing.setLocationRelativeTo(null);
				ing.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.setBounds(449, 281, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				
				if(dateChooserFecha.getDate() == null || dateChooserFecha.getDate().equals("")){
					lblResult.setText("El campo fecha no puede estar vacío");
					lblResult.setForeground(Color.RED);
				} else {
					String formatted = format1.format(dateChooserFecha.getDate());
					
					if(!validarCampos(comboBoxMedico.getSelectedItem(), comboBoxPaciente.getSelectedItem(),textAreaDiagnostico.getText())){
						//lblResult.setText("Complete todos los campos");
						//lblResult.setForeground(Color.RED);
					} else {
						if(conector.insertarDiagnostico(comboBoxMedico.getSelectedItem(), comboBoxPaciente.getSelectedItem(), textAreaDiagnostico.getText(),formatted)){
							lblResult.setText("Grabado");
							lblResult.setForeground(Color.GREEN);
							refrescarPacientes();
							refrescarMedicos();
							textAreaDiagnostico.setText("");
							
						} else {
							lblResult.setText("Error al grabar");
							lblResult.setForeground(Color.RED);
						}
					}
				}	
				
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.setBounds(336, 281, 103, 23);
		panel.add(btnGuardar);
		
		lblNombreMedico = new JLabel("");
		lblNombreMedico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombreMedico.setBounds(285, 109, 244, 14);
		panel.add(lblNombreMedico);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFecha.setBounds(10, 72, 121, 14);
		panel.add(lblFecha);
		
		dateChooserFecha = new JDateChooser();
		dateChooserFecha.setBounds(141, 72, 138, 20);
		panel.add(dateChooserFecha);
		
		lblResult = new JLabel("");
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResult.setBounds(10, 285, 253, 14);
		panel.add(lblResult);

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
			//System.out.println("ERROR");
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
	
	private boolean validarCampos(Object m, Object p, String diagnostico){
		ComboBoxItem medico = (ComboBoxItem) p;
		ComboBoxItem paciente = (ComboBoxItem) m;
		
		if(medico.getValue().equals(null) || medico.getValue().equals("") ){
			lblResult.setText("Campo médico no puede estar vacío");
			lblResult.setForeground(Color.RED);
			return false;
		}
		if(paciente.getValue().equals(null) || paciente.getValue().equals("") ){
			lblResult.setText("Campo paciente no puede estar vacío");
			lblResult.setForeground(Color.RED);
			return false;
		}
		if(diagnostico.equals(null)||diagnostico.equals("")){
			lblResult.setText("Campo diagnóstico no puede estar vacío");
			lblResult.setForeground(Color.RED);
			return false;
		}
		
		return true;
	}
}
