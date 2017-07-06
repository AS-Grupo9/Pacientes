package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.Log4J;

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

public class Informes extends JFrame {

	private JPanel contentPane;
	private static final Logger log = LogManager.getLogger(Log4J.class.getName());
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Informes frame = new Informes();
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
	public Informes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Informes.class.getResource("/recursos/iconoBig.png")));
		setTitle("INFORMES - CENTRO MEDICO LOS LAURELES");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnIngresoDatos = new JButton("Pacientes por m\u00E9dico");
		btnIngresoDatos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnIngresoDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InformePacientePorMedico infPacPorMed = new InformePacientePorMedico();
				infPacPorMed.setLocationRelativeTo(null);
				infPacPorMed.setVisible(true);
				dispose();
			}
		});
		btnIngresoDatos.setBounds(10, 249, 412, 23);
		panel.add(btnIngresoDatos);
		
		JButton btnInformes = new JButton("Especialidades por m\u00E9dico");
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeEspecialidadPorMedico infEspPorMed = new InformeEspecialidadPorMedico();
				infEspPorMed.setLocationRelativeTo(null);
				infEspPorMed.setVisible(true);
				dispose();
			}
		});
		btnInformes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInformes.setBounds(10, 283, 412, 23);
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
		btnSalir.setBounds(10, 317, 412, 23);
		panel.add(btnSalir);
		
		JLabel lblInformes = new JLabel("Informes");
		lblInformes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInformes.setFont(new Font("Arial", Font.BOLD, 16));
		lblInformes.setBounds(10, 11, 407, 20);
		panel.add(lblInformes);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/recursos/logo.png")));
		lblNewLabel.setBounds(102, 11, 232, 192);
		panel.add(lblNewLabel);
	}
}
