package Vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controlador.Controlador;
import Modelo.Modelo;

//@Autor Yago Pernas
public class _02_Reestablecer extends JFrame implements Vista {
	private JPanel mainPanel;
	private JTextField txtPwd1;
	private JTextField txtPwd2;
	private JTextField textField;

	private Controlador controlador;
	private Modelo modelo;

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public _02_Reestablecer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Restablecer contraseña");
		setResizable(false);
		setBounds(100, 100, 1024, 760);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setForeground(new Color(162, 196, 201));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottomPanel.setBounds(259, 610, 488, 70);
		bottomPanel.setBackground(new Color(208, 224, 227));
		mainPanel.add(bottomPanel);
		bottomPanel.setLayout(null);

		JLabel lblBottom = new JLabel("¿No tienes cuenta?");
		lblBottom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom.setBounds(77, 28, 153, 14);
		bottomPanel.add(lblBottom);

		JLabel lblBottom_1 = new JLabel("|");
		lblBottom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom_1.setBounds(240, 28, 7, 14);
		bottomPanel.add(lblBottom_1);

		JLabel lblLink = new JLabel("Crear cuenta");
		lblLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLink.setForeground(new Color(0, 0, 255));
		lblLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controlador.cambiarVentana(2, 1);
			}
		});
		lblLink.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLink.setBounds(267, 28, 105, 14);
		bottomPanel.add(lblLink);

		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		registerPanel.setBounds(259, 193, 488, 377);
		registerPanel.setBackground(new Color(207, 226, 243));
		mainPanel.add(registerPanel);
		registerPanel.setLayout(null);

		txtPwd1 = new JTextField();
		txtPwd1.setToolTipText("");
		txtPwd1.setText("Nueva contraseña");
		txtPwd1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPwd1.setColumns(10);
		txtPwd1.setBounds(36, 97, 418, 28);
		registerPanel.add(txtPwd1);

		txtPwd2 = new JTextField();
		txtPwd2.setText("Repetir nueva contraseña");
		txtPwd2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPwd2.setColumns(10);
		txtPwd2.setBounds(36, 154, 418, 28);
		registerPanel.add(txtPwd2);

		JButton btnLogin = new JButton("Acceder");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(2, 0);
			}
		});
		btnLogin.setBounds(34, 325, 124, 23);
		registerPanel.add(btnLogin);

		JLabel lblTitle = new JLabel("Reestablecer contraseña");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblTitle.setBounds(70, 29, 348, 39);
		registerPanel.add(lblTitle);

		JComboBox cmbPregunta = new JComboBox();
		cmbPregunta.setModel(new DefaultComboBoxModel(new String[] { "Elige una pregunta de seguridad:",
				"¿Cuál es el nombre de tu primera mascota? ", "¿Cuál es el nombre de tu escuela primaria? ",
				"¿En qué ciudad naciste? ", "¿Cuál es el nombre de tu mejor amigo de la infancia? ",
				"¿Cuál es el segundo nombre de tu padre? ", "¿Cuál fue tu primer trabajo?" }));
		cmbPregunta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbPregunta.setBounds(36, 211, 418, 28);
		registerPanel.add(cmbPregunta);

		textField = new JTextField();
		textField.setText("Respuesta");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(36, 268, 418, 28);
		registerPanel.add(textField);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(2, 0);
			}
		});
		btnAtras.setBounds(330, 325, 124, 23);
		registerPanel.add(btnAtras);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(343, 40, 321, 113);
		mainPanel.add(lblLogo);

		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/logo.png")).getImage()
				.getScaledInstance(321, 113, Image.SCALE_SMOOTH));
		lblLogo.setIcon(resizedLogo);
	}
}
