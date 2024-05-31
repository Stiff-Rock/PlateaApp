package Vistas;

//@Autor: Anton Luo
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class _03_Home extends Menus {
	private JTable table;

	public void cargarTabla(DefaultTableModel tablaInicio) {
		table.setModel(tablaInicio);
	}

	public _03_Home() {
		setTitle("Inicio");
		setContentPane(mainPanel);

		ImageIcon lupa = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/lupa.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		ImageIcon mas = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/mas.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		JPanel tablaPanel = new JPanel();
		tablaPanel.setBounds(45, 50, 695, 595);
		contentPanel.add(tablaPanel);
		tablaPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tablaPanel.setForeground(new Color(162, 196, 201));
		tablaPanel.setBackground(new Color(207, 226, 243));
		tablaPanel.setLayout(null);

		JScrollPane tablaPane = new JScrollPane();
		tablaPane.setBounds(10, 60, 675, 525);
		tablaPanel.add(tablaPane);

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
					String valor = table.getValueAt(fila, 0).toString();
					controlador.prepararPublicacion(valor);
					controlador.cambiarVentana(3, 9);
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Obtener la fila y columna donde se hizo clic
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());
				// Obtener el valor del primer campo de la fila donde se hizo clic
				String valor = table.getValueAt(fila, 0).toString();
				controlador.prepararPublicacion(valor);
				controlador.cambiarVentana(6, 9);
			}
		});

		table.getTableHeader().setReorderingAllowed(false);

		tablaPane.setViewportView(table);

		JPanel filtrosPanel = new JPanel();
		filtrosPanel.setLayout(null);
		filtrosPanel.setForeground(Color.WHITE);
		filtrosPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		filtrosPanel.setBackground(new Color(162, 196, 201));
		filtrosPanel.setBounds(0, 0, 695, 49);
		tablaPanel.add(filtrosPanel);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInicio.setBounds(10, 11, 297, 25);
		filtrosPanel.add(lblInicio);

		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.setBounds(609, 660, 131, 35);
		contentPanel.add(btnPublicar);
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambiarVentana(3, 10);
			}
		});
		btnPublicar.setIcon(mas);
		btnPublicar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		
		addWindowListener(new WindowAdapter() {
		    public void windowOpened(WindowEvent e) {

		    	table.setModel(controlador.getTabla(3));
		    }
		});
	}
}
