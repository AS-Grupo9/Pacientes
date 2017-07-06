package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.DBControlador;
import db.Log4J;

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

public class IngresoUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodUsuario;
	private JTextField textFieldNombre;
	private final JLabel lblResultado;
	private static final Logger log = LogManager.getLogger(Log4J.class.getName());
	private final DBControlador conector = new DBControlador();
	private JTextComponent lblResultadoQuery;
	private JPasswordField passwordFieldA;
	private JPasswordField passwordFieldB;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresoUsuario frame = new IngresoUsuario();
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
	public IngresoUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IngresoUsuario.class.getResource("/recursos/iconoBig.png")));
		setBackground(Color.WHITE);
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("INGRESO USUARIO - CENTRO MEDICO LOS LAURELES");
		setBounds(100, 100, 575, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCdigoDelPaciente = new JLabel("C\u00F3digo del usuario:");
		lblCdigoDelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCdigoDelPaciente.setBounds(10, 53, 121, 14);
		panel.add(lblCdigoDelPaciente);
		
		textFieldCodUsuario = new JTextField();
		textFieldCodUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldCodUsuario.setBounds(141, 50, 398, 20);
		panel.add(textFieldCodUsuario);
		textFieldCodUsuario.setColumns(10);
		
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(10, 78, 46, 14);
		panel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNombre.setBounds(141, 75, 398, 20);
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDatosDelUsuario = new JLabel("Datos del usuario");
		lblDatosDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatosDelUsuario.setFont(new Font("Arial", Font.BOLD, 16));
		lblDatosDelUsuario.setBounds(295, 11, 244, 20);
		panel.add(lblDatosDelUsuario);
		
		lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultado.setBounds(10, 216, 296, 14);
		panel.add(lblResultado);
		
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
		btnCancelar.setBounds(450, 199, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validarCampos(textFieldCodUsuario.getText(),textFieldNombre.getText(),passwordFieldA.getText(),passwordFieldB.getText())){
					
				} else {
					if(conector.insertarUsuario(textFieldCodUsuario.getText(),textFieldNombre.getText(),passwordFieldA.getText())){
						log.info("Se creó el usuario " + textFieldCodUsuario.getText());
						lblResultado.setText("Grabado");
						lblResultado.setForeground(Color.GREEN);
						textFieldCodUsuario.setText("");
						textFieldNombre.setText("");		
						passwordFieldA.setText("");
						passwordFieldB.setText("");
					} else {
						log.error("No se pudo crear el usuario " + textFieldCodUsuario.getText());
						lblResultado.setText("Error al grabar");
						lblResultado.setForeground(Color.RED);
					}
				}


			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.setBounds(345, 199, 95, 23);
		panel.add(btnGuardar);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPassword.setBounds(10, 106, 89, 14);
		panel.add(lblPassword);
		
		JLabel lblRepitaPassword = new JLabel("Repita Password:");
		lblRepitaPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRepitaPassword.setBounds(10, 137, 121, 14);
		panel.add(lblRepitaPassword);
		
		passwordFieldA = new JPasswordField();
		passwordFieldA.setBounds(141, 103, 398, 20);
		panel.add(passwordFieldA);
		
		passwordFieldB = new JPasswordField();
		passwordFieldB.setBounds(141, 134, 398, 20);
		panel.add(passwordFieldB);


	}
	
	private boolean validarCampos(String codigo, String nombre, String passA, String passB){
		if(codigo.equals(null) || codigo.equals("")){
			lblResultado.setText("El campo código no puede estar vacío");
			lblResultado.setForeground(Color.RED);
			return false;
		}
		if(nombre.equals(null) || nombre.equals("")){
			lblResultado.setText("El campo nombre no puede estar vacío");
			lblResultado.setForeground(Color.RED);
			return false;
		}
		if(passA.equals(null) || passA.equals("") || passB.equals(null) || passB.equals("")){
			lblResultado.setText("Debe completar los campos password");
			lblResultado.setForeground(Color.RED);
			return false;
		}
		if(!passA.equals(passB)){
			lblResultado.setText("Los passwords ingresados no coinciden");
			lblResultado.setForeground(Color.RED);
			return false;
		}			
		return true;
	}
	

}
