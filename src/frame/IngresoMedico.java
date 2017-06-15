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

public class IngresoMedico extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodPaciente;
	private JTextField textFieldNombre;
	private final JLabel lblResultado;
	private JComboBox comboBoxEsp1, comboBoxEsp2 ,comboBoxEsp3 ; 
	
	private final DBControlador conector = new DBControlador();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresoMedico frame = new IngresoMedico();
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
	public IngresoMedico() {
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("CENTRO MEDICO LOS LAURELES");
		setBounds(100, 100, 575, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCdigoDelMedico = new JLabel("C\u00F3digo del Medico:");
		lblCdigoDelMedico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCdigoDelMedico.setBounds(10, 53, 121, 14);
		panel.add(lblCdigoDelMedico);
		
		textFieldCodPaciente = new JTextField();
		textFieldCodPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldCodPaciente.setBounds(141, 50, 398, 20);
		panel.add(textFieldCodPaciente);
		textFieldCodPaciente.setColumns(10);
		textFieldCodPaciente.setText(conector.getSiguienteMedico());
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
		
		JLabel lblDatosDelMedico = new JLabel("Datos del m\u00E9dico");
		lblDatosDelMedico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatosDelMedico.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosDelMedico.setBounds(295, 11, 244, 20);
		panel.add(lblDatosDelMedico);
		
		comboBoxEsp1 = new JComboBox();
		comboBoxEsp1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxEsp1.setBounds(141, 100, 177, 20);
		panel.add(comboBoxEsp1);
			
		
		
		comboBoxEsp2 = new JComboBox();
		comboBoxEsp2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxEsp2.setBounds(141, 128, 177, 20);
		panel.add(comboBoxEsp2);
		
		comboBoxEsp3 = new JComboBox();
		comboBoxEsp3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxEsp3.setBounds(141, 155, 177, 20);
		panel.add(comboBoxEsp3);
		
		this.llenarEspecialidades();
		
		lblResultado = new JLabel("");
		lblResultado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultado.setBounds(10, 216, 161, 14);
		panel.add(lblResultado);
		
		final JLabel lblResultadoQuery = new JLabel("");
		lblResultadoQuery.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResultadoQuery.setBounds(10, 234, 161, 14);
		panel.add(lblResultadoQuery);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCancelar.setBounds(451, 225, 89, 23);
		panel.add(btnCancelar);
		
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					if(conector.insertarMedico(conector.getSiguienteMedico(), textFieldNombre.getText(),comboBoxEsp1.getSelectedItem(),comboBoxEsp2.getSelectedItem(),comboBoxEsp3.getSelectedItem())){
						lblResultadoQuery.setText("Grabado");
						lblResultadoQuery.setForeground(Color.GREEN);
						textFieldCodPaciente.setText(conector.getSiguienteMedico());
						textFieldNombre.setText("");
						refrescarEspecialidades();
						
					} else {
						lblResultadoQuery.setText("Error al grabar");
						lblResultado.setForeground(Color.RED);
					}
				
			
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.setBounds(346, 225, 95, 23);
		panel.add(btnGuardar);
		
		JLabel lblEspecialidad1 = new JLabel("Especialidad 1:");
		lblEspecialidad1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEspecialidad1.setBounds(10, 103, 121, 14);
		panel.add(lblEspecialidad1);
		
		
		
		JLabel labelEspecialidad2 = new JLabel("Especialidad 2:");
		labelEspecialidad2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelEspecialidad2.setBounds(10, 131, 121, 14);
		panel.add(labelEspecialidad2);
		

		
		JLabel labelEspeciaidad3 = new JLabel("Especialidad 3:");
		labelEspeciaidad3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelEspeciaidad3.setBounds(10, 158, 121, 14);
		panel.add(labelEspeciaidad3);
		

		
		

	}
	
	private void llenarEspecialidades(){
		ResultSet especialidades = conector.getEspecialidades();
		try {
			this.comboBoxEsp1.addItem("");
			this.comboBoxEsp2.addItem("");
			this.comboBoxEsp3.addItem("");
			while(especialidades.next()){
				ComboBoxItem item = new ComboBoxItem(especialidades.getString("codigo"), especialidades.getString("nombre"));		
				this.comboBoxEsp1.addItem(item);
				this.comboBoxEsp2.addItem(item);
				this.comboBoxEsp3.addItem(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}	
	}
	
	private void refrescarEspecialidades(){
		this.comboBoxEsp1.removeAllItems();	
		this.comboBoxEsp2.removeAllItems();	
		this.comboBoxEsp3.removeAllItems();	
		llenarEspecialidades();	
	}
}
