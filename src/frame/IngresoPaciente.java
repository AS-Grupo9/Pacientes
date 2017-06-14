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
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class IngresoPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodPaciente;
	private JTextField textFieldNombre;
	private JTextField textFieldCodMed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresoPaciente frame = new IngresoPaciente();
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
		
		textFieldCodPaciente = new JTextField();
		textFieldCodPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldCodPaciente.setBounds(141, 50, 398, 20);
		panel.add(textFieldCodPaciente);
		textFieldCodPaciente.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(10, 78, 46, 14);
		panel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNombre.setBounds(141, 75, 398, 20);
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblCdigoDelMdico = new JLabel("C\u00F3digo del m\u00E9dico:");
		lblCdigoDelMdico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCdigoDelMdico.setBounds(10, 103, 103, 14);
		panel.add(lblCdigoDelMdico);
		
		textFieldCodMed = new JTextField();
		textFieldCodMed.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldCodMed.setBounds(141, 100, 398, 20);
		panel.add(textFieldCodMed);
		textFieldCodMed.setColumns(10);
		
		JLabel lblDatosDelPaciente = new JLabel("Datos del paciente");
		lblDatosDelPaciente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDatosDelPaciente.setBounds(141, 11, 170, 14);
		panel.add(lblDatosDelPaciente);
		
		final JTextArea textAreaDiagnostico = new JTextArea();
		textAreaDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textAreaDiagnostico.setBounds(141, 131, 398, 58);
		panel.add(textAreaDiagnostico);
		
		final JLabel lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultado.setBounds(10, 216, 61, 14);
		panel.add(lblResultado);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DBControlador conector = new DBControlador();
				conector.insertarPaciente(textFieldCodPaciente.getText(), textFieldNombre.getText(), textFieldCodMed.getText(),textAreaDiagnostico.getText());
				lblResultado.setText("Grabado");
				lblResultado.setForeground(Color.GREEN);
				panel.dispose();
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.setBounds(211, 216, 89, 23);
		panel.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.setBounds(450, 216, 89, 23);
		panel.add(btnCancelar);
		
		JButton btnGuardarOtro = new JButton("Guardar y crear otro");
		btnGuardarOtro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardarOtro.setBounds(305, 216, 135, 23);
		panel.add(btnGuardarOtro);
		
		JLabel lblDiagnstico = new JLabel("Diagn\u00F3stico:");
		lblDiagnstico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDiagnstico.setBounds(10, 128, 76, 14);
		panel.add(lblDiagnstico);
		
		
		

	}
}
