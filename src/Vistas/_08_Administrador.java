package Vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

//@Autor:Anton Luo
public class _08_Administrador extends Menus {
	private int pagina = 8;
	private String codigoDenuncia;
	private JTable table;
	private JButton btnAprobar, btnDenegar;
	private JTextField textField;

	public _08_Administrador() {
		setTitle("Gestionar");
		setContentPane(mainPanel);

		ImageIcon lupa = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/lupa.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon tick = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/crux.png")).getImage()
				.getScaledInstance(17, 17, Image.SCALE_SMOOTH));
		ImageIcon cruz = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/cerrar.png")).getImage()
				.getScaledInstance(17, 17, Image.SCALE_SMOOTH));
		ImageIcon lapiz = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/lapiz.png")).getImage()
				.getScaledInstance(17, 17, Image.SCALE_SMOOTH));
		ImageIcon ojo = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/ojo.png")).getImage()
				.getScaledInstance(17, 17, Image.SCALE_SMOOTH));

		JPanel tablaPanel = new JPanel();
		tablaPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tablaPanel.setForeground(new Color(162, 196, 201));
		tablaPanel.setBackground(new Color(207, 226, 243));
		tablaPanel.setBounds(45, 30, 695, 634);
		contentPanel.add(tablaPanel);
		tablaPanel.setLayout(null);

		JPanel filtrosPanel = new JPanel();
		filtrosPanel.setBounds(0, 0, 695, 49);
		tablaPanel.add(filtrosPanel);
		filtrosPanel.setLayout(null);
		filtrosPanel.setForeground(Color.WHITE);
		filtrosPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		filtrosPanel.setBackground(new Color(162, 196, 201));

		JLabel lblLupa = new JLabel("");
		lblLupa.setBounds(8, 17, 14, 14);
		lblLupa.setIcon(lupa);
		filtrosPanel.add(lblLupa);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(27, 11, 271, 26);
		filtrosPanel.add(textField);

		JLabel lblFiltrosTxt = new JLabel("Filtros:");
		lblFiltrosTxt.setBounds(308, 17, 46, 14);
		filtrosPanel.add(lblFiltrosTxt);

		JComboBox comboBoxFecha = new JComboBox();
		comboBoxFecha.setModel(new DefaultComboBoxModel(new String[] { "Fecha" }));
		comboBoxFecha.setBounds(355, 13, 101, 22);
		filtrosPanel.add(comboBoxFecha);

		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Estado" }));
		comboBoxEstado.setBounds(466, 13, 101, 22);
		filtrosPanel.add(comboBoxEstado);

		JComboBox comboBoxProximidad = new JComboBox();
		comboBoxProximidad.setModel(new DefaultComboBoxModel(new String[] { "Proximidad" }));
		comboBoxProximidad.setBounds(577, 13, 101, 22);
		filtrosPanel.add(comboBoxProximidad);

		JScrollPane tablePane = new JScrollPane();
		tablePane.setBounds(10, 60, 675, 453);
		tablaPanel.add(tablePane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Obtener la fila y columna donde se hizo clic
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());
				// Obtener el valor del primer campo de la fila donde se hizo clic
				codigoDenuncia = table.getValueAt(fila, 0).toString();
				// controlador.prepararPublicacion(valor);
//                controlador.cambiarVentana(6, 9);
			}
		});
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		table.getTableHeader().setReorderingAllowed(false);
		tablePane.setViewportView(table);

		JPanel adminPanel = new JPanel();
		adminPanel.setBounds(0, 547, 695, 87);
		tablaPanel.add(adminPanel);
		adminPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		adminPanel.setBackground(new Color(162, 196, 201));
		adminPanel.setLayout(null);

		JButton btnModificar = new JButton("Modificar\r\n");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(451, 10, 109, 29);
		btnModificar.setIcon(lapiz);
		adminPanel.add(btnModificar);

		JLabel lblCambio = new JLabel("Introduce el cambio:");
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCambio.setBounds(10, 10, 133, 14);
		adminPanel.add(lblCambio);

		JButton btnVer = new JButton("Ver");
		btnVer.setIcon(ojo);
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVer.setBounds(570, 10, 109, 29);
		adminPanel.add(btnVer);

		btnAprobar = new JButton("Aprobar");
		btnAprobar.setBounds(451, 50, 109, 28);
		adminPanel.add(btnAprobar);
		btnAprobar.setIcon(tick);

		btnDenegar = new JButton("Denegar");
		btnDenegar.setBounds(570, 50, 109, 28);
		adminPanel.add(btnDenegar);
		btnDenegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tipo = 2;

				controlador.AprobarDenegar(codigoDenuncia, tipo);
				table.setModel(controlador.getTabla(pagina));
			}
		});
		btnDenegar.setIcon(cruz);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(10, 30, 428, 48);
		adminPanel.add(textArea);
		btnAprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tipo = 1;
				System.out.println(codigoDenuncia);
				controlador.AprobarDenegar(codigoDenuncia, tipo);
				table.setModel(controlador.getTabla(pagina));
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				table.setModel(controlador.getTabla(pagina));
			}
		});
	}
}
