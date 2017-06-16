package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.DBControlador;

import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class Splash extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldPass;
	private JLabel lblResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
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
	public Splash() {
		setTitle("CENTRO MEDICO LOS LAURELES");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnIngresoDatos = new JButton("Ingresar");
		btnIngresoDatos.setIcon(null);
		btnIngresoDatos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnIngresoDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validarCampos(textFieldUsuario.getText(),passwordFieldPass.getText())){
					Principal ppal = new Principal();
					ppal.setVisible(true);
					dispose();
				} else {
					
				}
				
				
			}
		});
		btnIngresoDatos.setBounds(0, 293, 414, 23);
		panel.add(btnIngresoDatos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(0, 327, 414, 23);
		panel.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Splash.class.getResource("/recursos/logo.png")));
		lblNewLabel.setBounds(102, 11, 232, 192);
		panel.add(lblNewLabel);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldUsuario.setBounds(205, 214, 129, 20);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsuario.setBounds(102, 217, 79, 14);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContrasea.setBounds(102, 239, 93, 14);
		panel.add(lblContrasea);
		
		passwordFieldPass = new JPasswordField();
		passwordFieldPass.setBounds(205, 236, 129, 20);
		panel.add(passwordFieldPass);
		
		lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(102, 268, 232, 14);
		panel.add(lblResultado);
	}
	
	private boolean validarCampos(String usuario, String password){
		DBControlador conector = new DBControlador();
		if(usuario.equals(null) || usuario.equals("")){
			lblResultado.setText("El campo usuario no puede estar vacío");
			lblResultado.setForeground(Color.RED);
			return false;
		}
		if(password.equals(null) || password.equals("")){
			lblResultado.setText("El campo contraseña no puede estar vacío");
			lblResultado.setForeground(Color.RED);
			return false;
		}
		if(!conector.validarUsuarioYPassword(usuario,password)){
			lblResultado.setText("Usuario y/o contraseña inválidos");
			lblResultado.setForeground(Color.RED);
			return false;
		}
		return true;
	}
}
