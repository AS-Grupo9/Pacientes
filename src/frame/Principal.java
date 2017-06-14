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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("CENTRO MEDICO LOS LAURELES");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Nuevo");
		mnNewMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmPaciente = new JMenuItem("Paciente");
		mntmPaciente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		mnNewMenu.add(mntmPaciente);
		
		JMenuItem mntmMdico = new JMenuItem("M\u00E9dico");
		mntmMdico.setFont(new Font("Tahoma", Font.PLAIN, 11));
		mnNewMenu.add(mntmMdico);
		
		JMenu mnNewMenu_1 = new JMenu("Salir");
		mnNewMenu_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menuBar.add(mnNewMenu_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblElijaSuOpcin = new JLabel("Elija su opci\u00F3n...");
		lblElijaSuOpcin.setBounds(173, 104, 113, 14);
		panel.add(lblElijaSuOpcin);
		
		JButton btnIngresoDatos = new JButton("Ingreso de datos");
		btnIngresoDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IngresoPaciente ingDat = new IngresoPaciente();
				ingDat.setVisible(true);
			}
		});
		btnIngresoDatos.setBounds(10, 129, 404, 23);
		panel.add(btnIngresoDatos);
		
		JButton btnInformes = new JButton("Informes");
		btnInformes.setBounds(10, 163, 404, 23);
		panel.add(btnInformes);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSalir.setBounds(10, 197, 404, 23);
		panel.add(btnSalir);
	}
}
