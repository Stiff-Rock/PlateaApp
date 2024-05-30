package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Vistas.Vista;
import Vistas._01_Registrar;

public class Modelo {
	private Properties datosDB;
	private File miFichero;
	private InputStream entrada;
	private final String FILE = "platea.ini";
	private String login;
	private String pwd;
	private String url;

	private Usuario user;
	private Vista[] vistas;

	private Connection conexion;
	private String resultado;
	private int fallos;

	private DefaultTableModel miTabla;

	public Modelo() {
		datosDB = new Properties();
		try {
			miFichero = new File(FILE);
			if (miFichero.exists()) {
				entrada = new FileInputStream(miFichero);
				datosDB.load(entrada);
				login = datosDB.getProperty("login");
				pwd = datosDB.getProperty("pwd");
				url = datosDB.getProperty("url");
			} else {
				System.err.println("Fichero no encontrado");
				System.exit(1);
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(url, login, pwd);
			System.out.println("-> Conexion con ORACLE establecida");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC No encontrado");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al conectarse a la BD");
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
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

//	"SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA"
//	query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA WHERE USUARIO_NICK IN (SELECT USUARIO_NICK FROM PLATEA.VOTAR WHERE UPVOTE = 'S')"
	public DefaultTableModel obtenerTabla(int pagina) {
		String query = null;

		if (pagina == 3) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA WHERE ESTADO != 'Pendiente'";
		};
		if (pagina == 5) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA WHERE USUARIO_NICK = ?";
		}
		if (pagina == 8) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA WHERE ESTADO = 'Pendiente'";
		}

		int numColumnas = getNumColumnas(query, pagina);
		int numFilas = getNumFilas(query, pagina);

		String[] cabecera = new String[numColumnas];
		Object[][] contenido = new Object[numFilas][numColumnas];

		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			if (pagina == 5) {
				pstmt.setString(1, user.getNickname());
			}
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= numColumnas; i++) {
				cabecera[i - 1] = rsmd.getColumnName(i);
			}
			int filas = 0;
			while (rs.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenido[filas][col - 1] = rs.getString(col);
				}
				filas++;
			}
			rs.close();
			pstmt.close();

			miTabla = new DefaultTableModel(contenido, cabecera);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miTabla;
	}

	private int getNumColumnas(String query, int pagina) {
		int numColumnas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
	        if (query.contains("?") && pagina == 5) {
	            pstmt.setString(1, user.getNickname());
	        }
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			numColumnas = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numColumnas;
	}

	private int getNumFilas(String query, int pagina) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
	        if (query.contains("?") && pagina == 5) {
	            pstmt.setString(1, user.getNickname());
	        }
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public String siguienteSingIn(String[] datosRegistro) {
		String resultado = "";

		if (!datosRegistro[4].equals(datosRegistro[5])) {
			resultado = "Contraseña";
		}

		if (resultado.equals("")) {
			resultado = "Avanza";
		}

		// Comprueba si faltan campos por rellenar
		if ((datosRegistro[0].equals(null) || datosRegistro[0].equals(""))
				|| (datosRegistro[4].equals(null) || datosRegistro[4].equals(""))
				|| (datosRegistro[5].equals(null) || datosRegistro[5].equals(""))) {
			resultado = "Faltan";
		}

		if (!(datosRegistro[0].equals(null) || datosRegistro[0].equals(""))
				&& !comprobarDisponibilidadNick(datosRegistro[0])) {
			resultado = "Nickname";
		}

		return resultado;
	}

	public String singIn(String[] datosRegistro) {
		resultado = "";

		if (datosRegistro[9].equals("N")) {
			resultado = "Politica";
		}
		if (datosRegistro[10].equals("N")) {
			resultado = "Mayor";
		}
		if (!datosRegistro[11].equals(((_01_Registrar) vistas[1]).getCaptcha())) {
			resultado = "Captcha";
		}
		if (datosRegistro[7].equals("0") || datosRegistro[7].equals("-1")) {
			resultado = "Pregunta";
		}
		if (datosRegistro[8].equals("") || datosRegistro[8].equals(null)) {
			resultado = "Respuesta";
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

	public DefaultTableModel getTabla(int pagina) {
		return obtenerTabla(pagina);
	}
}
