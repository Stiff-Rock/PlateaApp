package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

//@Autor: Hugo Osma
public class _09_Publicacion extends Menus {
	private JTextField txtFecha;
	private JTextField txtDireccion;
	private JTextField txtCp;
	private JTextField txtCategoria;
	private JTextArea txtDescripcion;
	private JLabel lblFoto;
	private JLabel lblWarning;

	/**
	 * Obtiene el código de la publicación actualmente mostrada en la vista.
	 *
	 * @return El código de la publicación actual.
	 */
	public String getCod() {
		String[] datos = controlador.getDatosPublicacion();
		String cod = datos[6];
		return cod;
	}

	/**
	 * Establece el texto de fecha en el campo correspondiente de la vista.
	 *
	 * @param txtFecha El texto de fecha que se establecerá en el campo de fecha.
	 */
	public void setTxtFecha(String txtFecha) {
		this.txtFecha.setText(txtFecha);
	}

	/**
	 * Establece el texto de la dirección en el campo correspondiente de la vista.
	 *
	 * @param txtDireccion El texto de la dirección que se establecerá en el campo
	 *                     de dirección.
	 */
	public void setTxtDireccion(String txtDireccion) {
		this.txtDireccion.setText(txtDireccion);
	}

	/**
	 * Establece el texto del código postal en el campo correspondiente de la vista.
	 *
	 * @param txtCp El texto del código postal que se establecerá en el campo de
	 *              código postal.
	 */
	public void setTxtCp(String txtCp) {
		this.txtCp.setText(txtCp);
	}

	/**
	 * Establece el texto de la categoría en el campo correspondiente de la vista.
	 *
	 * @param txtCategoria El texto de la categoría que se establecerá en el campo
	 *                     de categoría.
	 */
	public void setTxtCategoria(String txtCategoria) {
		this.txtCategoria.setText(txtCategoria);
	}

	/**
	 * Establece el texto de la descripción en el campo correspondiente de la vista.
	 *
	 * @param txtDescripcion El texto de la descripción que se establecerá en el
	 *                       campo de descripción.
	 */
	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion.setText(txtDescripcion);
	}

	/**
	 * Establece la imagen en el componente JLabel de la vista.
	 *
	 * @param imagen El ImageIcon que se establecerá en el componente JLabel para
	 *               mostrar la imagen.
	 */
	public void setLblFoto(ImageIcon imagen) {
		lblFoto.setIcon(imagen);
	}

	public void setWarning(String mensaje) {
		lblWarning.setText(mensaje);
	}

	public _09_Publicacion() {
		setTitle("Publicación");
		setContentPane(mainPanel);

		JPanel publicarPanel = new JPanel();
		publicarPanel.setLayout(null);
		publicarPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		publicarPanel.setBackground(new Color(207, 226, 243));
		publicarPanel.setBounds(61, 27, 664, 640);
		contentPanel.add(publicarPanel);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(60, 311, 141, 28);
		publicarPanel.add(txtFecha);

		JPanel fotoPanel = new JPanel();
		fotoPanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		fotoPanel.setLayout(null);
		fotoPanel.setBackground(Color.WHITE);
		fotoPanel.setBounds(167, 79, 331, 187);
		publicarPanel.add(fotoPanel);

		lblFoto = new JLabel("");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(0, 0, 329, 185);
		fotoPanel.add(lblFoto);

		JLabel lblImage = new JLabel("");
		lblImage.setBackground(Color.GRAY);
		lblImage.setBounds(72, 128, 512, 117);
		publicarPanel.add(lblImage);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(60, 371, 543, 28);
		publicarPanel.add(txtDireccion);

		JLabel lblFecha = new JLabel("Fecha (DD/MM/AAAA):");
		lblFecha.setBounds(60, 294, 141, 13);
		publicarPanel.add(lblFecha);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(60, 354, 151, 13);
		publicarPanel.add(lblDireccion);

		txtCp = new JTextField();
		txtCp.setEditable(false);
		txtCp.setColumns(10);
		txtCp.setBounds(261, 311, 141, 28);
		publicarPanel.add(txtCp);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(462, 294, 141, 13);
		publicarPanel.add(lblCategoria);

		JLabel lblCp = new JLabel("Codigo Postal:");
		lblCp.setBounds(261, 294, 141, 13);
		publicarPanel.add(lblCp);

		JLabel lblDescripcion = new JLabel("Descripción del problema:");
		lblDescripcion.setBounds(60, 417, 320, 13);
		publicarPanel.add(lblDescripcion);

		JPanel filtrosPanel = new JPanel();
		filtrosPanel.setLayout(null);
		filtrosPanel.setForeground(Color.WHITE);
		filtrosPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		filtrosPanel.setBackground(new Color(162, 196, 201));
		filtrosPanel.setBounds(0, 0, 664, 49);
		publicarPanel.add(filtrosPanel);

		JLabel lblLupa = new JLabel("");
		lblLupa.setBounds(8, 17, 14, 14);
		filtrosPanel.add(lblLupa);

		JLabel lblTitle = new JLabel("Publicacion");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(170, 8, 324, 32);
		filtrosPanel.add(lblTitle);

		txtCategoria = new JTextField();
		txtCategoria.setEditable(false);
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(462, 311, 141, 28);
		publicarPanel.add(txtCategoria);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(60, 441, 543, 114);
		publicarPanel.add(panel);

		txtDescripcion = new JTextArea();
		txtDescripcion.setEditable(false);
		txtDescripcion.setRows(5);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDescripcion.setBounds(10, 11, 523, 92);
		panel.add(txtDescripcion);

		JButton btnNewButton = new JButton("Favoritos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.meterFavoritos();

			}

		});
		btnNewButton.setBounds(391, 595, 96, 21);
		publicarPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Votados");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.meterVoatar();

			}
		});
		btnNewButton_1.setBounds(507, 595, 96, 21);
		publicarPanel.add(btnNewButton_1);

		lblWarning = new JLabel("");
		lblWarning.setBounds(60, 598, 282, 14);
		lblWarning.setForeground(new Color(0, 128, 0));
		publicarPanel.add(lblWarning);

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				controlador.cargarPublicacion();
			}
		});
	}
}
