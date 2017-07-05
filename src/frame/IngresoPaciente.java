package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.DBControlador;

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
import java.awt.Toolkit;

public class IngresoPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodPaciente;
	private JTextField textFieldNombre;
	private final JLabel lblResultado;
	
	private final DBControlador conector = new DBControlador();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresoPaciente frame = new IngresoPaciente();
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
	public IngresoPaciente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IngresoPaciente.class.getResource("/recursos/iconoBig.png")));
		setBackground(Color.WHITE);
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("INGRESO PACIENTE - CENTRO MEDICO LOS LAURELES");
		setBounds(100, 100, 575, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCdigoDelPaciente = new JLabel("C\u00F3digo del paciente:");
		lblCdigoDelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCdigoDelPaciente.setBounds(10, 53, 121, 14);
		panel.add(lblCdigoDelPaciente);
		
		textFieldCodPaciente = new JTextField();
		textFieldCodPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldCodPaciente.setBounds(141, 50, 398, 20);
		panel.add(textFieldCodPaciente);
		textFieldCodPaciente.setColumns(10);
		textFieldCodPaciente.setText(conector.getSiguientePaciente());
		textFieldCodPaciente.disable();
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(10, 78, 46, 14);
		panel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNombre.setBounds(141, 75, 398, 20);
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDatosDelPaciente = new JLabel("Datos del paciente");
		lblDatosDelPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatosDelPaciente.setFont(new Font("Arial", Font.BOLD, 16));
		lblDatosDelPaciente.setBounds(295, 11, 244, 20);
		panel.add(lblDatosDelPaciente);
		
		lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultado.setBounds(10, 216, 161, 14);
		panel.add(lblResultado);
		
		final JLabel lblResultadoQuery = new JLabel("");
		lblResultadoQuery.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultadoQuery.setBounds(10, 138, 325, 14);
		panel.add(lblResultadoQuery);
		
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
		btnCancelar.setBounds(450, 134, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(!validarCampos(textFieldNombre.getText())){
						lblResultadoQuery.setText("El nombre no puede estar vacío");
						lblResultadoQuery.setForeground(Color.RED);
					} else {
						if(conector.insertarPaciente(conector.getSiguientePaciente(), textFieldNombre.getText())){
							lblResultadoQuery.setText("Grabado");
							lblResultadoQuery.setForeground(Color.GREEN);
							textFieldCodPaciente.setText(conector.getSiguientePaciente());
							textFieldNombre.setText("");						
						} else {
							lblResultadoQuery.setText("Error al grabar");
							lblResultadoQuery.setForeground(Color.RED);
						}
					}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.setBounds(345, 134, 95, 23);
		panel.add(btnGuardar);
		

		
		

	}
	
	private boolean validarCampos(String nombre){
		if(nombre.equals(null) || nombre.equals("")){
			return false;
		}
		return true;
	}
	
}
