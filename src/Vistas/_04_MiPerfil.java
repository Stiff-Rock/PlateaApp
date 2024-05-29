package Vistas;

//@Autor: Anton Luo
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class _04_MiPerfil extends Menus {
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCp;
	private JTextField txtTipo;
	private JLabel lblNickname;
	private JLabel lblimage;
	private JLabel lblWarning;

	public _04_MiPerfil() {
		setTitle("Mi perfil");
		setContentPane(mainPanel);

		JPanel perfilPanel = new JPanel();
		perfilPanel.setLayout(null);
		perfilPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		perfilPanel.setBackground(new Color(240, 240, 240));
		perfilPanel.setBounds(0, 0, 786, 695);
		contentPanel.add(perfilPanel);

		JPanel fotoPanel = new JPanel();
		fotoPanel.setLayout(null);
		fotoPanel.setBackground(Color.WHITE);
		fotoPanel.setBounds(36, 56, 145, 145);
		perfilPanel.add(fotoPanel);

		JButton btnUpload = new JButton("Subir Imagen");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpload.setBounds(22, 60, 100, 25);
		fotoPanel.add(btnUpload);

		lblimage = new JLabel("");
		lblimage.setBackground(Color.LIGHT_GRAY);
		lblimage.setBounds(0, 0, 145, 145);
		fotoPanel.add(lblimage);

		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 806, 128);
		perfilPanel.add(headerPanel);
		headerPanel.setLayout(null);
		headerPanel.setBorder(null);
		headerPanel.setBackground(new Color(208, 224, 227));

		lblNickname = new JLabel("Nickname");
		lblNickname.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNickname.setBounds(206, 70, 541, 47);
		headerPanel.add(lblNickname);

		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(0, 127, 806, 594);
		perfilPanel.add(contentPanel);
		contentPanel.setBackground(new Color(240, 240, 240));
		contentPanel.setLayout(null);

		JPanel datosPanel = new JPanel();
		datosPanel.setLayout(null);
		datosPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		datosPanel.setBackground(new Color(207, 226, 243));
		datosPanel.setBounds(156, 124, 493, 345);
		contentPanel.add(datosPanel);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 27, 109, 13);
		datosPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(30, 51, 432, 19);
		datosPanel.add(txtNombre);

		JLabel lblApellido = new JLabel("Apellidos");
		lblApellido.setBounds(30, 97, 84, 13);
		datosPanel.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(30, 121, 432, 19);
		datosPanel.add(txtApellido);

		JLabel lblCp = new JLabel("CP");
		lblCp.setBounds(30, 167, 45, 13);
		datosPanel.add(lblCp);

		txtCp = new JTextField();
		txtCp.setColumns(10);
		txtCp.setBounds(30, 191, 432, 19);
		datosPanel.add(txtCp);

		JLabel lblTipo = new JLabel("Tipo de usuario");
		lblTipo.setBounds(30, 237, 91, 13);
		datosPanel.add(lblTipo);

		txtTipo = new JTextField();
		txtTipo.setEditable(false);
		txtTipo.setColumns(10);
		txtTipo.setBounds(30, 261, 432, 19);
		datosPanel.add(txtTipo);

		JButton btnAplicar = new JButton("Aplicar cambios");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.actualizarDatosUsuario();
			}
		});
		btnAplicar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAplicar.setBounds(322, 315, 140, 19);
		datosPanel.add(btnAplicar);
		
		lblWarning = new JLabel("");
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarning.setBounds(30, 317, 282, 14);
		datosPanel.add(lblWarning);

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				controlador.setDatosUsuario();
			}
		});
	}

	public void mostrarWarning(String mensaje, Color color) {
		lblWarning.setForeground(color);
		lblWarning.setText(mensaje);
	}
	
	public String getNombre() {
		return txtNombre.getText();
	}

	public void setNombre(String nombre) {
		txtNombre.setText(nombre);
	}

	public String getApellido() {
		return txtApellido.getText();
	}

	public void setApellido(String apellido) {
		txtApellido.setText(apellido);
	}

	public String getCp() {
		return txtCp.getText();
	}

	public void setCp(String cp) {
		txtCp.setText(cp);
	}

	public String getNickname() {
		return lblNickname.getText();
	}

	public void setNickname(String nickname) {
		lblNickname.setText(nickname);
	}

	public String getImage() {
		return lblimage.getText();
	}

	public void setImage(String image) {
		lblimage.setText(image);
	}

	public void setTipo(String tipo) {
		txtTipo.setText(tipo);
	}
}
