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
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

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
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/recursos/iconoBig.png")));
		setTitle("CENTRO MEDICO LOS LAURELES");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnIngresoDatos = new JButton("Ingreso de datos");
		btnIngresoDatos.setIcon(null);
		btnIngresoDatos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnIngresoDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ingresos ing = new Ingresos();
				ing.setLocationRelativeTo(null);
				ing.setVisible(true);
				dispose();
			}
		});
		btnIngresoDatos.setBounds(0, 215, 414, 23);
		panel.add(btnIngresoDatos);
		
		JButton btnInformes = new JButton("Informes");
		btnInformes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Informes inf = new Informes();
				inf.setLocationRelativeTo(null);
				inf.setVisible(true);
				dispose();
			}
			
		});
		
		btnInformes.setBounds(0, 249, 414, 23);
		panel.add(btnInformes);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Splash spl = new Splash();
				spl.setLocationRelativeTo(null);
				spl.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(0, 283, 414, 23);
		panel.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/recursos/logo.png")));
		lblNewLabel.setBounds(102, 11, 232, 192);
		panel.add(lblNewLabel);
	}
}
