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

import javax.swing.DefaultComboBoxModel;
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

import Controlador.Controlador;

//@Autor Yago Pernas
public class _02_Reestablecer extends JFrame implements Vista {
	private JPanel mainPanel;
	private JTextField txtRespuesta;
	private JLabel lblWarning;

	private Controlador controlador;
	private JComboBox comboBoxPreguntas;
	private JTextField txtNick;
	private JPasswordField txtPwd1;
	private JPasswordField txtPwd2;

	/**
	 * Establece el controlador para esta vista.
	 *
	 * @param controlador El controlador que se asignará a esta vista.
	 */
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * Muestra un mensaje de advertencia en la etiqueta correspondiente indicando
	 * que ha habido un error al cambiar la contraseña.
	 */
	public void mostrarWarningcambio() {
		lblWarning.setText("Campos incorrectos, fallo al cambiar contraseña");
	}

	/**
	 * Carga el modelo de ComboBox de preguntas con el modelo proporcionado.
	 *
	 * @param preguntas El modelo de ComboBox de preguntas que se cargará en el
	 *                  ComboBox correspondiente.
	 */
	public void cargarPreguntas(DefaultComboBoxModel preguntas) {
		comboBoxPreguntas.setModel(preguntas);
	}

	/**
	 * Obtiene el nickname ingresado en el campo de texto correspondiente.
	 *
	 * @return El nickname ingresado.
	 */
	public String getNick() {
		return txtNick.getText();
	}

	/**
	 * Obtiene la contraseña ingresada en el primer campo de contraseña.
	 *
	 * @return La contraseña ingresada como una cadena de caracteres.
	 */
	public String getPwd() {
		return String.valueOf(txtPwd1.getPassword());
	}

	/**
	 * Obtiene la contraseña de verificación ingresada en el segundo campo de
	 * contraseña.
	 *
	 * @return La contraseña de verificación ingresada como una cadena de
	 *         caracteres.
	 */
	public String getPwdVer() {
		return String.valueOf(txtPwd2.getPassword());
	}

	/**
	 * Obtiene la respuesta ingresada en el campo de texto correspondiente.
	 *
	 * @return La respuesta ingresada.
	 */
	public String getRespuesta() {
		return txtRespuesta.getText();
	}

	/**
	 * Obtiene el índice de la pregunta seleccionada en el ComboBox de preguntas.
	 *
	 * @return El índice de la pregunta seleccionada.
	 */
	public String getPregunta() {
		comboBoxPreguntas.getSelectedIndex();
		return String.valueOf(comboBoxPreguntas.getSelectedIndex());
	}

	public _02_Reestablecer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Restablecer contraseña");

		setResizable(false);
		setBounds(480, 150, 1024, 760);
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setForeground(new Color(162, 196, 201));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottomPanel.setBounds(259, 625, 488, 70);
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
		registerPanel.setBounds(259, 193, 488, 408);
		registerPanel.setBackground(new Color(207, 226, 243));
		mainPanel.add(registerPanel);
		registerPanel.setLayout(null);

		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.verificarCambio();
			}
		});
		btnCambiar.setBounds(35, 347, 124, 23);
		registerPanel.add(btnCambiar);

		JLabel lblTitle = new JLabel("Reestablecer contraseña");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(35, 9, 348, 39);
		registerPanel.add(lblTitle);

		txtRespuesta = new JTextField();
		txtRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtRespuesta.setColumns(10);
		txtRespuesta.setBounds(35, 296, 418, 28);
		registerPanel.add(txtRespuesta);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(2, 0);
			}
		});
		btnAtras.setBounds(329, 347, 124, 23);
		registerPanel.add(btnAtras);

		JLabel lblNewPwd1 = new JLabel("Nueva contraseña:");
		lblNewPwd1.setBounds(35, 116, 124, 14);
		registerPanel.add(lblNewPwd1);

		JLabel lblNewPwd2 = new JLabel("Repetir nueva contraseña:");
		lblNewPwd2.setBounds(35, 176, 160, 14);
		registerPanel.add(lblNewPwd2);

		lblWarning = new JLabel("");
		lblWarning.setForeground(Color.RED);
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarning.setBounds(35, 323, 286, 22);
		registerPanel.add(lblWarning);

		comboBoxPreguntas = new JComboBox();
		comboBoxPreguntas.setBounds(35, 236, 418, 28);
		registerPanel.add(comboBoxPreguntas);

		JLabel lblRespuesta = new JLabel("Respuesta:");
		lblRespuesta.setBounds(35, 273, 81, 14);
		registerPanel.add(lblRespuesta);

		txtNick = new JTextField();
		txtNick.setToolTipText("");
		txtNick.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNick.setColumns(10);
		txtNick.setBounds(35, 79, 418, 28);
		registerPanel.add(txtNick);

		JLabel lblNewLabel = new JLabel("Nickname");
		lblNewLabel.setBounds(35, 57, 81, 13);
		registerPanel.add(lblNewLabel);

		txtPwd1 = new JPasswordField();
		txtPwd1.setBounds(35, 139, 418, 28);
		registerPanel.add(txtPwd1);

		txtPwd2 = new JPasswordField();
		txtPwd2.setBounds(35, 199, 418, 28);
		registerPanel.add(txtPwd2);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(343, 40, 321, 113);
		mainPanel.add(lblLogo);

		ImageIcon resizedLogo = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/logo.png")).getImage()
				.getScaledInstance(321, 113, Image.SCALE_SMOOTH));
		lblLogo.setIcon(resizedLogo);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				comboBoxPreguntas.setModel(controlador.getPreguntas());
			}
		});
	}
}
