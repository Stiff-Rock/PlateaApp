package Modelo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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

	public String[] verificarUsuario(String usuario, String contrasena) {
		String[] usuarioEncontrado = new String[2];

		String query = "SELECT NICK, PWD FROM platea.usuario WHERE NICK = ? AND PWD = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, usuario);
			statement.setString(2, contrasena);
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

	private String comprobarConfiguracion(String condicion) {
		String query = "SELECT valor FROM platea.configuracion WHERE clave = ?";
		String codigoAdmin = "";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, condicion);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					codigoAdmin = resultSet.getString("VALOR");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(codigoAdmin);
		return codigoAdmin;
	}

	public DefaultComboBoxModel obtenerComboBox(String campo, String tabla) {
		ArrayList<String> listaResultados = new ArrayList<>();
		listaResultados.add("Elige:");
		String query = "SELECT " + campo + " FROM platea." + tabla;

		try (PreparedStatement statement = conexion.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				String datos = resultSet.getString(campo);
				listaResultados.add(datos);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[] objetoResultado = listaResultados.toArray(new String[listaResultados.size()]);
		DefaultComboBoxModel preguntas = new DefaultComboBoxModel(objetoResultado);
		return preguntas;
	}

//	"SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA"
	public DefaultTableModel obtenerTabla(int pagina) {
		String query = null;

		if (pagina == 3) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA WHERE ESTADO != 'Pendiente'";
		}
		;
		if (pagina == 5) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA WHERE USUARIO_NICK = ?";
		}
		if (pagina == 6) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA WHERE USUARIO_NICK = ? AND USUARIO_NICK IN (SELECT USUARIO_NICK FROM PLATEA.VOTAR WHERE FAVORITO = 'S')";
		}
		if (pagina == 7) {
			query = "SELECT CODIGO, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO FROM PLATEA.DENUNCIA WHERE USUARIO_NICK = ? AND USUARIO_NICK IN (SELECT USUARIO_NICK FROM PLATEA.VOTAR WHERE UPVOTE = 'S')";
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
			if (pagina == 5 || pagina == 6 || pagina == 7) {
				pstmt.setString(1, user.getNickname());
			}

			System.out.println("Executing query: " + pstmt.toString());

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
			if (query.contains("?") && pagina == 5 || query.contains("?") && pagina == 7
					|| query.contains("?") && pagina == 6) {
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
			if (query.contains("?") && pagina == 5 || query.contains("?") && pagina == 7
					|| query.contains("?") && pagina == 6) {
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

	public void updateUsuario() {
		String sql = "UPDATE platea.usuario SET nombre = ?, apellido = ?, cp = ? WHERE nick = ?";
		try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
			pstmt.setString(1, user.getNombre());
			pstmt.setString(2, user.getApellido());
			pstmt.setInt(3, Integer.valueOf(user.getCp()));
			pstmt.setString(4, user.getNickname());
//			pstmt.setString(5, user.getFoto());
			pstmt.executeUpdate();
			System.out.println("Datos cambiados correctamente");
		} catch (SQLException e) {
			// Manejo de excepciones
			e.printStackTrace();
		}
	}

	public String obtenerPreguntaUsuario(String nombreUsuario) {
		String pregunta = "";
		String query = "SELECT pregunta.Codigo FROM platea.USUARIO "
				+ "INNER JOIN platea.PREGUNTA ON platea.USUARIO.PREGUNTA_CODIGO = PREGUNTA.CODIGO "
				+ "WHERE USUARIO.NICK = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, nombreUsuario);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					pregunta = resultSet.getString("Codigo");
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
		} else {
			if (!datosRegistro[12].equals(comprobarConfiguracion("Codigo Admin"))
					&& !(datosRegistro[12].equals(null) || datosRegistro[12].equals(""))) {
				resultado = "Admin";
			}
		}

		if (!datosRegistro[11].equals(((_01_Registrar) vistas[1]).getCaptcha()))

		{
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

			datosRegistro[7] = generarCodigo("PRE", datosRegistro[7]);

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

	public String generarCodigo(String prefijo, String indice) {
		String codigo = "";

		// Generar codigo pregunta a partir del índice dado por parámetro
		for (int i = indice.length(); i < 3; i++) {
			codigo += "0";
		}
		codigo = prefijo + codigo + indice;

		return codigo;
	}

	public String actualizarDatosUsuario(String[] datos) {
		String resultado = "";
		String numeros = "1234567890";

		// TODO AÑADIR FOTO PERFIL??

		for (int i = 0; i < numeros.length(); i++) {
			if (datos[0].contains(String.valueOf(numeros.charAt(i)))) {
				resultado = "Nombre";
			}
		}

		for (int i = 0; i < numeros.length(); i++) {
			if (datos[1].contains(String.valueOf(numeros.charAt(i)))) {
				resultado = "Apellido";
			}
		}

		for (int i = 0; i < datos[2].length(); i++) {
			if (!numeros.contains(String.valueOf(datos[2].charAt(i)))) {
				resultado = "Cp";
			}
		}

		if (resultado == "") {
			resultado = "Correcto";
		}

		return resultado;
	}

	private void crearPublicacion(String[] datosPublicacion) {
		String sqlCount = "SELECT COUNT(*) FROM platea.denuncia";
		String sqlInsert = "INSERT INTO platea.denuncia (CODIGO, IMAGEN, DIRECCION, CP, ESTADO, FECHA, USUARIO_NICK, CATEGORIA_CODIGO, DESCRIPCION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			// Paso 1: Ejecutar la consulta para obtener la cantidad de denuncias
			PreparedStatement countStmt = conexion.prepareStatement(sqlCount);
			ResultSet resultSet = countStmt.executeQuery();
			resultSet.next(); // Mover al primer resultado
			String numeroDenuncias = String.valueOf(resultSet.getInt(1) + 1); // Sumar 1 para crear el nuevo código
			countStmt.close();

			// Paso 2: Generar el nuevo código
			String codigoDenuncia = generarCodigo("DEN", numeroDenuncias);
			String codigoCategoria = generarCodigo("CAT", datosPublicacion[3]);

			// Convertir el String de fecha al formato "yyyy-MM-dd"
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaDate = null;
			try {
				fechaDate = dateFormat.parse(datosPublicacion[1]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// Paso 3: Ejecutar la inserción con el nuevo código
			try (PreparedStatement pstmt = conexion.prepareStatement(sqlInsert)) {
				pstmt.setString(1, codigoDenuncia);
				pstmt.setBytes(2, null); // CAMBIAR URGENTE
				pstmt.setString(3, datosPublicacion[4]);
				pstmt.setInt(4, Integer.valueOf(datosPublicacion[2]));
				pstmt.setString(5, "Nueva");
				pstmt.setDate(6, new java.sql.Date(fechaDate.getTime())); // Convertir la fecha a java.sql.Date
				pstmt.setString(7, user.getNickname());
				pstmt.setString(8, codigoCategoria);
				pstmt.setString(9, datosPublicacion[5]);

				pstmt.executeUpdate();

				System.out.println("Denuncia creada exitosamente.");
			} catch (SQLException e) {
				System.out.println("Error al crear la denuncia: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener la cantidad de denuncias: " + e.getMessage());
		}
	}

	public ImageIcon obtenerImagen(String tabla, String campoFoto, String campoId, int id, int alturaImagen) {
		String query = "SELECT " + campoFoto + " FROM platea." + tabla + " WHERE " + campoId + " = ?";
		ImageIcon imageIcon = null;

		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Blob blob = resultSet.getBlob(campoFoto);
				InputStream inputStream = blob.getBinaryStream();

				// Convertir el BLOB a BufferedImage
				BufferedImage originalImage = ImageIO.read(inputStream);

				// Calcular el nuevo tamaño de la imagen
				int anchuraOriginal = originalImage.getWidth();
				int alturaOriginal = originalImage.getHeight();
				int anchuraEscalada = (alturaImagen * anchuraOriginal) / alturaOriginal;

				// Redimensionar la imagen
				Image scaledImage = originalImage.getScaledInstance(anchuraEscalada, alturaImagen, Image.SCALE_SMOOTH);

				// Convertir la imagen escalada a BufferedImage
				BufferedImage bufferedScaledImage = new BufferedImage(anchuraEscalada, alturaImagen,
						BufferedImage.TYPE_INT_ARGB);
				bufferedScaledImage.getGraphics().drawImage(scaledImage, 0, 0, null);

				// Convertir BufferedImage a byte[]
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ImageIO.write(bufferedScaledImage, "png", outputStream);
				byte[] imageBytes = outputStream.toByteArray();

				// Crear ImageIcon
				imageIcon = new ImageIcon(imageBytes);

				inputStream.close();
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return imageIcon;
	}

	public String comprobarInfoPublicacion(String[] datosPublicacion) {
		String resultado = "";

		if (datosPublicacion[1] != null
				&& !datosPublicacion[1].matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}")) {
			resultado = "Fecha";
		}

		String numeros = "1234567890";
		for (int i = 0; i < datosPublicacion[2].length(); i++) {
			if (!numeros.contains(String.valueOf(datosPublicacion[2].charAt(i)))) {
				resultado = "Cp";
			}
		}

		if (datosPublicacion[3].equals("0") || datosPublicacion[3].equals("-1")) {
			resultado = "Categoria";
		}

		if (datosPublicacion[5].length() < 100) {
			resultado = "DescripcionPoco";
		}

		if (datosPublicacion[5].length() > 300) {
			resultado = "DescripcionMucho";
		}

		for (int i = 0; i < datosPublicacion.length; i++) {
			if (datosPublicacion[i].equals(null) || datosPublicacion[i].equals("")) {
				resultado = "Faltan"; // Todos los campos son obligatorios
			}
		}

		if (resultado.equals("")) {
			resultado = "Correcto";
			crearPublicacion(datosPublicacion);
		}
		return resultado;
	}

	public String deImagenABase64(ImageIcon imagen) {
		String fotoBase64 = "";
		if (imagen != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(imagen);
				fotoBase64 = Base64.getEncoder().encodeToString(baos.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fotoBase64;
	}

	public ImageIcon deBase64AImagen(String fotoBase64) {
		ImageIcon imagen = null;
		if (fotoBase64 != null && !fotoBase64.isEmpty()) {
			byte[] bytes = Base64.getDecoder().decode(fotoBase64);
			try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
					ObjectInputStream ois = new ObjectInputStream(bais)) {
				imagen = (ImageIcon) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return imagen;
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

	public boolean verificarCambio(String nick, String pregunta, String respuesta, String nuevaPwd, String confirmPwd) {
		String selectQuery = "SELECT 1 FROM platea.usuario WHERE NICK = ? AND PREGUNTA_CODIGO = ? AND RESPUESTA = ?";
		String updateQuery = "UPDATE platea.usuario SET PWD = ? WHERE NICK = ?";

		try (PreparedStatement selectStatement = conexion.prepareStatement(selectQuery)) {
			selectStatement.setString(1, nick);
			selectStatement.setString(2, pregunta);
			selectStatement.setString(3, respuesta);
			ResultSet resultSet = selectStatement.executeQuery();

			if (resultSet.next()) {
				if (nuevaPwd.equals(confirmPwd)) {
					try (PreparedStatement updateStatement = conexion.prepareStatement(updateQuery)) {
						updateStatement.setString(1, nuevaPwd);
						updateStatement.setString(2, nick);
						updateStatement.executeUpdate();
						return true;
					}
				} else {
					System.out.println("FALLOS");
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}
