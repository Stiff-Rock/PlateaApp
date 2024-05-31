package Vistas;

import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

//@Autor: Anton Luo
public class _05_MisPublicaciones extends Menus {
	private JPanel filtrosPanel;
	private JTable table;
	private JLabel lblTitle;
	private String codigoDenuncia;

	public _05_MisPublicaciones() {
		setTitle("Mis publicaciones");
		setContentPane(mainPanel);

		ImageIcon mas = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/mas.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon borrar = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/Quitar.png")).getImage()
				.getScaledInstance(16, 16, Image.SCALE_SMOOTH));

		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tablePanel.setForeground(new Color(162, 196, 201));
		tablePanel.setBackground(new Color(207, 226, 243));
		tablePanel.setBounds(45, 50, 695, 595);
		contentPanel.add(tablePanel);
		tablePanel.setLayout(null);

		filtrosPanel = new JPanel();
		filtrosPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		filtrosPanel.setBounds(0, 0, 695, 49);
		tablePanel.add(filtrosPanel);
		filtrosPanel.setBackground(new Color(162, 196, 201));
		filtrosPanel.setForeground(new Color(255, 255, 255));
		filtrosPanel.setLayout(null);

		lblTitle = new JLabel("Mis publicaciones");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(10, 12, 273, 25);
		filtrosPanel.add(lblTitle);

		JScrollPane tablaPane = new JScrollPane();
		tablaPane.setBounds(10, 60, 675, 525);
		tablePanel.add(tablaPane);

		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Obtener la fila y columna donde se hizo clic
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());
				// Obtener el valor del primer campo de la fila donde se hizo clic
				codigoDenuncia = table.getValueAt(fila, 0).toString();
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.getTableHeader().setReorderingAllowed(false);

		tablaPane.setViewportView(table);

		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.setIcon(mas);
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(5, 10);
			}
		});
		btnPublicar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPublicar.setBounds(609, 660, 131, 35);
		contentPanel.add(btnPublicar);

		ImageIcon ojo = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/ojo.png")).getImage()
				.getScaledInstance(17, 17, Image.SCALE_SMOOTH));
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.prepararPublicacion(codigoDenuncia);
				controlador.cambiarVentana(5, 9);
			}
		});
		btnVer.setBounds(468, 662, 131, 35);
		btnVer.setIcon(ojo);
		contentPanel.add(btnVer);

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				table.setModel(controlador.getTabla(5));
			}
		});
	}
}
