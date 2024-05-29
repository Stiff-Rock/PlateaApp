package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Vistas.Vista;
import Vistas._01_Registrar;

public class Modelo {
	private String login = "SYSTEM";
	private String pwd = "0205";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";

	private Usuario user;
	private Vista[] vistas;

	private Connection conexion;
	private String resultado;
	private int fallos;

	public Modelo() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(url, login, pwd);
			System.out.println("-> Conexion con ORACLE establecida");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC No encontrado");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al conectarse a la BD");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error general de Conexion");
			e.printStackTrace();
		}
	}

	public String[] verificarUsuario(String usuario, String contraseña) {
		String[] usuarioEncontrado = new String[2];

		String query = "SELECT NICK, PWD FROM platea.usuario WHERE NICK = ? AND PWD = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, usuario);
			statement.setString(2, contraseña);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				usuarioEncontrado[0] = resultSet.getString("NICK");
				usuarioEncontrado[1] = resultSet.getString("PWD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarioEncontrado;
	}

	public boolean comprobarDisponibilidadNick(String nickname) {
		boolean isNickAvailable = true;

		String query = "SELECT COUNT(*) AS total FROM platea.usuario WHERE NICK = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, nickname);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int totalUsuarios = resultSet.getInt("total");
				// Si el total de usuarios con ese nickname es mayor que 0 (1), ya está en uso.
				if (totalUsuarios > 0) {
					isNickAvailable = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isNickAvailable;
	}

	public DefaultComboBoxModel obtenerPreguntasSeguridad() {
		ArrayList<String> preguntasList = new ArrayList<>();
		preguntasList.add("Elige una pregunta de seguridad");
		String query = "SELECT CUESTION FROM platea.pregunta";

		try (PreparedStatement statement = conexion.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String cuestion = resultSet.getString("CUESTION");
				preguntasList.add(cuestion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[] preguntasObject = preguntasList.toArray(new String[preguntasList.size()]);
		DefaultComboBoxModel preguntas = new DefaultComboBoxModel(preguntasObject);
		return preguntas;
	}

	public DefaultTableModel obtenerTabla(String condicion) {
		String query = "SELECT * FROM platea.DENUNCIA WHERE ?";

		int numColumnas = getNumColumnas(query, condicion);
		int numFilas = getNumFilas(query, condicion);

		String[] cabecera = new String[numColumnas];
		Object[][] contenido = new Object[numFilas][numColumnas];

		ArrayList<String[]> resultList = new ArrayList<>();

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, condicion);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String[] row = new String[8];
					row[0] = rs.getString("CODIGO");
					row[1] = rs.getString("DIRECCION");
					row[2] = rs.getString("CP");
					row[3] = rs.getString("ESTADO");
					row[4] = rs.getString("FECHA");
					row[5] = rs.getString("USUARIO_NICK");
					row[6] = rs.getString("CATEGORIA_CODIGO");
					row[7] = rs.getString("DESCRIPCION");
					resultList.add(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DefaultTableModel tabla = new DefaultTableModel(contenido, cabecera);
		return tabla;
	}

	private int getNumFilas(String query, String condicion) {
		int numColumnas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, condicion);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			numColumnas = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(numColumnas);
		return numColumnas;
	}

	private int getNumColumnas(String query, String condicion) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, condicion);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(numFilas);
		return numFilas;
	}

	public void crearUsuario(String[] datosRegistro) {
		String query = "INSERT INTO platea.usuario (NICK, APELLIDO, NOMBRE, CP, PWD, ADMIN, PREGUNTA_CODIGO, RESPUESTA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, datosRegistro[0]);
			statement.setString(2, datosRegistro[1]);
			statement.setString(3, datosRegistro[2]);
			statement.setString(4, datosRegistro[3]);
			statement.setString(5, datosRegistro[4]);
			statement.setString(6, datosRegistro[6]);
			statement.setString(7, datosRegistro[7]);
			statement.setString(8, datosRegistro[8]);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("El usuario ha sido creado correctamente.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String[] cargarUsuario(String nick) {
		String[] datos = new String[9];

		String sql = "SELECT NICK, APELLIDO, NOMBRE, CP, PWD, ADMIN, FOTO, PREGUNTA_CODIGO, RESPUESTA FROM platea.usuario WHERE NICK = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			pstmt.setString(1, nick);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					datos[0] = rs.getString("NICK");
					datos[1] = rs.getString("APELLIDO");
					datos[2] = rs.getString("NOMBRE");
					datos[3] = rs.getString("CP");
					datos[4] = rs.getString("PWD");
					datos[5] = rs.getString("ADMIN");
					datos[6] = rs.getBytes("FOTO") != null ? new String(rs.getBytes("FOTO")) : "";
					datos[7] = rs.getString("PREGUNTA_CODIGO");
					datos[8] = rs.getString("RESPUESTA");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return datos;
	}

	public String obtenerPreguntaUsuario(String nombreUsuario) {
		String pregunta = "";
		String query = "SELECT pregunta.CUESTION " + "FROM USUARIO "
				+ "INNER JOIN PREGUNTA ON USUARIO.PREGUNTA_CODIGO = PREGUNTA.CODIGO " + "WHERE USUARIO.NICK = ?";

		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, nombreUsuario);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					pregunta = resultSet.getString("CUESTION");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pregunta;
	}

	public String generateCaptcha() {
		String letras = "abcdefghijklmnopqrstuvwxyz";
		String numeros = "1234567890";
		StringBuilder captcha = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int option = random.nextInt(4);
			if (option == 0) {
				captcha.append(numeros.charAt(random.nextInt(numeros.length())));
			} else {
				int mayus = random.nextInt(2);
				if (mayus == 0) {
					captcha.append(letras.charAt(random.nextInt(letras.length())));
				} else {
					captcha.append(String.valueOf(letras.charAt(random.nextInt(letras.length()))).toUpperCase());
				}
			}
		}
		return String.valueOf(captcha);
	}

	public String singIn(String[] datosRegistro) {
		resultado = "";

		if (!datosRegistro[4].equals(datosRegistro[5])) {
			resultado = "Contraseña";
		}

		if (datosRegistro[9].equals("N")) {
			resultado = "Politica";
		}

		if (datosRegistro[10].equals("N")) {
			resultado = "Mayor";
		}

		if (!datosRegistro[11].equals(((_01_Registrar) vistas[1]).getCaptcha())) {
			resultado = "Captcha";
		}

		for (int i = 0; i < datosRegistro.length; i++) {
			if ((datosRegistro[i].equals(null) || datosRegistro[i].equals("")) && (i != 7 && i != 8)) {
				resultado = "Faltan";
			}
		}

		if (datosRegistro[7].equals("0") || datosRegistro[7].equals("-1")) {
			resultado = "Pregunta";
		}

		if (datosRegistro[8].equals("") || datosRegistro[8].equals(null)) {
			resultado = "Respuesta";
		}

		if (!comprobarDisponibilidadNick(datosRegistro[0])) {
			resultado = "Nickname";
		}
		
		if (resultado.equals("")) {
			resultado = "Correcto";

			// Generar codigo pregunta a partir del índice de comboBox
			for (int i = datosRegistro[7].length(); i < 3; i++) {
				datosRegistro[7] += "0" + datosRegistro[7];
			}
			datosRegistro[7] = "PRE" + datosRegistro[7];

			crearUsuario(datosRegistro);
		}
		return resultado;
	}

	public String login(String usr, String pwd) {
		resultado = "";
		String userData[] = verificarUsuario(usr, pwd);
		if ((userData[0] != null && userData[1] != null) && (userData[0].equals(usr) && userData[1].equals(pwd))) {
			user.cargarDatos(cargarUsuario(usr));
			resultado = "Correcto";
			fallos = 0;
		} else {
			fallos++;
			if (fallos == 3) {
				resultado = "Cerrar";
			} else {
				resultado = "Incorrecto";
			}
		}
		return resultado;
	}

	public void terminar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Métodos getter y setter del modelo
	public void setVistas(Vista[] vistas) {
		this.vistas = vistas;
	}

	public void setUsuario(Usuario user) {
		this.user = user;
	}

	public String getResultado() {
		return resultado;
	}

	public Usuario getUser() {
		return user;
	}

	public DefaultTableModel getTabla(String condicion) {
		return obtenerTabla(condicion);
	}
}
