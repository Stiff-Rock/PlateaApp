package Vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Controlador.Controlador;
import Modelo.Modelo;

//@Autor Yago Pernas
public class _00_Login extends JFrame implements Vista {
	private JPanel mainPanel;
	private JTextField txtUsr;
	private JPasswordField txtPwd;
	private JLabel lblWarning;

	private Controlador controlador;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public String getUsr() {
		return txtUsr.getText();
	}

	public String getPwd() {
		return String.valueOf(txtPwd.getPassword());
	}

	public void mostrarWarning() {
		lblWarning.setText("Usuario o contraseña incorrectos");
	}

	public _00_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Inicio de sesión");

		// Panel principal que contendrá todos los componentes
		setResizable(false);
		setBounds(480, 150, 1024, 760);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setForeground(new Color(162, 196, 201));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottomPanel.setBounds(259, 588, 488, 70);
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
				controlador.cambiarVentana(0, 1);
			}
		});
		lblLink.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLink.setBounds(257, 28, 105, 14);
		bottomPanel.add(lblLink);

		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		registerPanel.setBounds(259, 239, 488, 286);
		registerPanel.setBackground(new Color(207, 226, 243));
		mainPanel.add(registerPanel);
		registerPanel.setLayout(null);

		txtUsr = new JTextField();
		txtUsr.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtUsr.setColumns(10);
		txtUsr.setBounds(36, 101, 418, 28);
		registerPanel.add(txtUsr);

		JButton btnLogin = new JButton("Acceder");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.login();
			}
		});
		btnLogin.setBounds(36, 243, 124, 23);
		registerPanel.add(btnLogin);

		JLabel lblTitle = new JLabel("Iniciar sesión");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblTitle.setBounds(151, 20, 185, 39);
		registerPanel.add(lblTitle);

		JLabel lblForgotPWD = new JLabel("Olvidé mi contraseña");
		lblForgotPWD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblForgotPWD.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblForgotPWD.setForeground(new Color(0, 0, 255));
		lblForgotPWD.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controlador.cambiarVentana(0, 2);
			}
		});
		lblForgotPWD.setBounds(301, 247, 153, 14);
		registerPanel.add(lblForgotPWD);

		txtPwd = new JPasswordField();
		txtPwd.setToolTipText("");
		txtPwd.setBounds(36, 183, 418, 28);
		registerPanel.add(txtPwd);

		JLabel lblUsr = new JLabel("Nickname:");
		lblUsr.setBounds(37, 79, 79, 14);
		registerPanel.add(lblUsr);

		JLabel lblpwd = new JLabel("Contraseña:");
		lblpwd.setBounds(37, 161, 79, 14);
		registerPanel.add(lblpwd);

		lblWarning = new JLabel("");
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setForeground(new Color(255, 0, 0));
		lblWarning.setBounds(128, 219, 231, 14);
		registerPanel.add(lblWarning);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(343, 63, 321, 113);
		mainPanel.add(lblLogo);

		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/logo.png")).getImage()
				.getScaledInstance(321, 113, Image.SCALE_SMOOTH));
		lblLogo.setIcon(resizedLogo);
	}
}
