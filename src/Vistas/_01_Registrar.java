package Vistas;

import java.awt.Checkbox;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Controlador.Controlador;

//@Autor Yago Pernas
public class _01_Registrar extends JFrame implements Vista {
	private JPanel mainPanel;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtNickname;
	private JTextField txtCP;
	private JPasswordField txtPwd1;
	private JPasswordField txtPwd2;
	private Controlador controlador;
	private JTextField txtRespuesta;
	private JTextField txtCaptcha;
	private JTextField txtAdmin;
	private JPanel registerPanel2;
	private JPanel registerPanel;
	private Checkbox checkAdmin;
	private Checkbox checkPolitica;
	private Checkbox checkMayor;
	private JLabel lblLogo;
	private JLabel lblWarning2;
	private JLabel lblWarning1;
	private JLabel lblCaptcha;
	private JComboBox comboBoxPreguntas;
	private JLabel lblTitle1;
	private JLabel lblTitle1_1;

	private String[] datosRegistro = new String[13];
	private int progresoSignIn = 0;

	public _01_Registrar() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registro");

		// Panel principal que contendrá todos los componentes
		setBounds(480, 150, 1024, 760);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setForeground(new Color(162, 196, 201));

		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/logo.png")).getImage()
				.getScaledInstance(321, 113, Image.SCALE_SMOOTH));

		registerPanel2 = new JPanel();
		registerPanel2.setVisible(false);
		registerPanel2.setLayout(null);
		registerPanel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		registerPanel2.setBackground(new Color(207, 226, 243));
		registerPanel2.setBounds(260, 143, 488, 475);
		mainPanel.add(registerPanel2);

		txtRespuesta = new JTextField();
		txtRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtRespuesta.setColumns(10);
		txtRespuesta.setBounds(35, 173, 418, 28);
		registerPanel2.add(txtRespuesta);

		checkAdmin = new Checkbox("¿Eres admin? Si es así, introduce el código de administrador:");
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkAdmin.setBounds(35, 221, 319, 22);
		registerPanel2.add(checkAdmin);

		comboBoxPreguntas = new JComboBox();
		comboBoxPreguntas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxPreguntas.setBounds(35, 101, 418, 28);
		registerPanel2.add(comboBoxPreguntas);

		JButton btnRegister = new JButton("Crear Cuenta");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.singIn();
			}
		});
		btnRegister.setBounds(35, 426, 124, 23);
		registerPanel2.add(btnRegister);

		checkPolitica = new Checkbox("He leído y acepto la política de privacidad.");
		checkPolitica.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkPolitica.setBounds(35, 294, 222, 32);
		registerPanel2.add(checkPolitica);

		checkMayor = new Checkbox("Soy mayor de 14 años");
		checkMayor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkMayor.setBounds(36, 332, 146, 22);
		registerPanel2.add(checkMayor);

		JPanel captchaPanel = new JPanel();
		captchaPanel.setLayout(null);
		captchaPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		captchaPanel.setBackground(new Color(85, 170, 170));
		captchaPanel.setBounds(277, 292, 176, 88);
		registerPanel2.add(captchaPanel);

		JLabel lblCaptchaTitle = new JLabel("Completa el Captcha:");
		lblCaptchaTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaptchaTitle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCaptchaTitle.setBounds(13, 6, 149, 14);
		captchaPanel.add(lblCaptchaTitle);

		txtCaptcha = new JTextField();
		txtCaptcha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCaptcha.setColumns(10);
		txtCaptcha.setBounds(44, 57, 86, 20);
		captchaPanel.add(txtCaptcha);

		lblCaptcha = new JLabel("");
		lblCaptcha.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaptcha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCaptcha.setBounds(23, 32, 129, 14);
		captchaPanel.add(lblCaptcha);

		JLabel lblRespuesta = new JLabel("Respuesta:");
		lblRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRespuesta.setBounds(35, 148, 81, 14);
		registerPanel2.add(lblRespuesta);

		JButton btnAnterior = new JButton("<Anterior 2/2>");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerPanel.setVisible(true);
				registerPanel2.setVisible(false);
			}
		});
		btnAnterior.setBounds(316, 426, 137, 23);
		registerPanel2.add(btnAnterior);

		txtAdmin = new JTextField();
		txtAdmin.setBounds(35, 249, 418, 28);
		registerPanel2.add(txtAdmin);
		txtAdmin.setColumns(10);

		JLabel lblPregunta = new JLabel("Pregunta:");
		lblPregunta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPregunta.setBounds(35, 76, 81, 14);
		registerPanel2.add(lblPregunta);

		lblWarning2 = new JLabel("");
		lblWarning2.setForeground(Color.RED);
		lblWarning2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarning2.setBounds(35, 393, 384, 22);
		registerPanel2.add(lblWarning2);

		lblTitle1_1 = new JLabel("Crear cuenta");
		lblTitle1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle1_1.setBounds(175, 15, 137, 28);
		registerPanel2.add(lblTitle1_1);

		// PANEL 1

		registerPanel = new JPanel();
		registerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		registerPanel.setBounds(260, 143, 488, 475);
		registerPanel.setBackground(new Color(207, 226, 243));
		mainPanel.add(registerPanel);
		registerPanel.setLayout(null);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNombre.setBounds(34, 104, 192, 28);
		registerPanel.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtApellido.setColumns(10);
		txtApellido.setBounds(260, 104, 192, 28);
		registerPanel.add(txtApellido);

		txtNickname = new JTextField();
		txtNickname.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNickname.setColumns(10);
		txtNickname.setBounds(34, 177, 192, 28);
		registerPanel.add(txtNickname);

		txtCP = new JTextField();
		txtCP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCP.setColumns(10);
		txtCP.setBounds(260, 177, 192, 28);
		registerPanel.add(txtCP);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(35, 76, 74, 14);
		registerPanel.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblApellido.setBounds(260, 79, 74, 14);
		registerPanel.add(lblApellido);

		JLabel lblUsr = new JLabel("Nickname:");
		lblUsr.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsr.setBounds(35, 152, 81, 14);
		registerPanel.add(lblUsr);

		JLabel lblCp = new JLabel("CP:");
		lblCp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCp.setBounds(260, 152, 81, 14);
		registerPanel.add(lblCp);

		JLabel lblPwd = new JLabel("Contraseña");
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPwd.setBounds(34, 224, 81, 14);
		registerPanel.add(lblPwd);

		JLabel lblPwd_1 = new JLabel("Repetir contraseña:");
		lblPwd_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPwd_1.setBounds(35, 301, 146, 14);
		registerPanel.add(lblPwd_1);

		txtPwd1 = new JPasswordField();
		txtPwd1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPwd1.setBounds(34, 249, 418, 28);
		registerPanel.add(txtPwd1);

		txtPwd2 = new JPasswordField();
		txtPwd2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPwd2.setBounds(34, 326, 418, 28);
		registerPanel.add(txtPwd2);

		JButton btnSiguiente = new JButton("<Siguiente 1/2>");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.siguienteSignIn();
			}
		});
		btnSiguiente.setBounds(316, 426, 137, 23);
		registerPanel.add(btnSiguiente);

		lblWarning1 = new JLabel("");
		lblWarning1.setForeground(Color.RED);
		lblWarning1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarning1.setBounds(35, 382, 222, 22);
		registerPanel.add(lblWarning1);

		lblTitle1 = new JLabel("Crear cuenta");
		lblTitle1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle1.setBounds(175, 15, 137, 28);
		registerPanel.add(lblTitle1);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottomPanel.setBounds(260, 633, 488, 70);
		bottomPanel.setBackground(new Color(208, 224, 227));
		mainPanel.add(bottomPanel);
		bottomPanel.setLayout(null);

		JLabel lblBottom = new JLabel("¿Ya tienes cuenta? ");
		lblBottom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom.setBounds(72, 28, 158, 14);
		bottomPanel.add(lblBottom);

		JLabel lblBottom_1 = new JLabel("|");
		lblBottom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBottom_1.setBounds(240, 28, 7, 14);
		bottomPanel.add(lblBottom_1);

		JLabel lblLink = new JLabel("Iniciar Sesión");
		lblLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLink.setForeground(new Color(0, 0, 255));
		lblLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controlador.cambiarVentana(1, 0);
			}
		});
		lblLink.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLink.setBounds(267, 28, 137, 14);
		bottomPanel.add(lblLink);

		lblLogo = new JLabel("");
		lblLogo.setBounds(343, 15, 321, 113);
		mainPanel.add(lblLogo);

		lblLogo.setIcon(resizedLogo);

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				comboBoxPreguntas.setModel(controlador.getPreguntas());
				lblCaptcha.setText(controlador.getCaptcha());
			}
		});
	}

	public JPanel getRegisterPanel() {
		return registerPanel;
	}

	public JPanel getRegisterPanel2() {
		return registerPanel2;
	}

	public void setProgresoSignIn(int progresoSignIn) {
		this.progresoSignIn = progresoSignIn;
	}

	public int getProgresoSignIn() {
		return progresoSignIn;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public String getCaptcha() {
		String captcha = txtCaptcha.getText();
		return captcha;
	}

	// PREGUNTAR A PEDRO
	public String[] getDatosRegistro() {
		datosRegistro[0] = txtNickname.getText();
		datosRegistro[1] = txtApellido.getText();
		datosRegistro[2] = txtNombre.getText();
		datosRegistro[3] = txtCP.getText();
		datosRegistro[4] = String.valueOf(txtPwd1.getPassword());
		datosRegistro[5] = String.valueOf(txtPwd2.getPassword());
		datosRegistro[6] = checkAdmin.getState() ? "S" : "N";
		datosRegistro[7] = String.valueOf(comboBoxPreguntas.getSelectedIndex());
		datosRegistro[8] = txtRespuesta.getText();
		datosRegistro[9] = checkPolitica.getState() ? "S" : "N";
		datosRegistro[10] = checkMayor.getState() ? "S" : "N";
		datosRegistro[11] = txtCaptcha.getText();
		datosRegistro[12] = txtAdmin.getText();

		return datosRegistro;
	}

	public void mostrarWarning1(String mensaje) {
		lblWarning1.setText(mensaje);
	}

	public void mostrarWarning2(String mensaje) {
		lblWarning2.setText(mensaje);
	}

}
