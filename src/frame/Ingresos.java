package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;

public class Ingresos extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingresos frame = new Ingresos();
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
	public Ingresos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ingresos.class.getResource("/recursos/iconoBig.png")));
		setTitle("INGRESO - CENTRO MEDICO LOS LAURELES");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnIngresoDatos = new JButton("Ingreso de paciente");
		btnIngresoDatos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnIngresoDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IngresoPaciente ingPac = new IngresoPaciente();
				ingPac.setLocationRelativeTo(null);
				ingPac.setVisible(true);
				dispose();
			}
		});
		btnIngresoDatos.setBounds(10, 214,414, 23);
		panel.add(btnIngresoDatos);
		
		JButton btnInformes = new JButton("Ingreso de m\u00E9dico");
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresoMedico ingMed = new IngresoMedico();
				ingMed.setLocationRelativeTo(null);
				ingMed.setVisible(true);
				dispose();
			}
		});
		btnInformes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInformes.setBounds(10, 283, 414, 23);
		panel.add(btnInformes);
		
		JButton btnSalir = new JButton("Volver");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal ppal = new Principal();
				ppal.setLocationRelativeTo(null);
				ppal.setVisible(true);
				dispose();				
			}
		});
		btnSalir.setBounds(10, 351, 414, 23);
		panel.add(btnSalir);
		
		JLabel lblIngresos = new JLabel("Ingresos");
		lblIngresos.setForeground(new Color(0, 0, 51));
		lblIngresos.setBackground(Color.WHITE);
		lblIngresos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngresos.setFont(new Font("Arial", Font.BOLD, 16));
		lblIngresos.setBounds(10, 11, 414, 20);
		panel.add(lblIngresos);
		
		JButton btnIngresoDeSituacin = new JButton("Ingreso de situaci\u00F3n de paciente");
		btnIngresoDeSituacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresoDiagnostico ingDiag = new IngresoDiagnostico();
				ingDiag.setLocationRelativeTo(null);
				ingDiag.setVisible(true);
				dispose();
			}
		});
		btnIngresoDeSituacin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnIngresoDeSituacin.setBounds(10, 249, 414, 23);
		panel.add(btnIngresoDeSituacin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/recursos/logo.png")));
		lblNewLabel.setBounds(102, 11, 232, 192);
		panel.add(lblNewLabel);
		
		JButton button = new JButton("Ingreso de usuario");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IngresoUsuario ingUser = new IngresoUsuario();
				ingUser.setLocationRelativeTo(null);
				ingUser.setVisible(true);
				dispose();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(10, 317, 414, 23);
		panel.add(button);
	}
}
