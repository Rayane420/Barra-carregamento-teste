package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import entidades.Usuario;



public class DaoUser {

	private static Connection connection;
	
	public DaoUser() {
		connection = ConnectionDataBase.getConnection();
	}
	
	public List<Usuario> getUsuarios() throws Exception {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) { // enquanto tiver resultados
			
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setImagem(resultSet.getString("imagem"));

			usuarios.add(usuario);
		}
		return usuarios;
	}

	public void gravarImagem(String imagem) throws SQLException{
		
			String tipoDados = imagem.split(",")[0].split(";")[0].split("/")[1];

			String sql = "insert into usuario (imagem, tipofile) values (?, ?);";
			
 			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setString(1, imagem);
			insert.setString(2, tipoDados);
			insert.execute();
	}

	public Usuario buscaImagem(String iduser) {
		try {
		
		String sql = "select * from usuario where id = " + iduser;
		
		PreparedStatement buscaImagem = connection.prepareStatement(sql);
		ResultSet resultSet = buscaImagem.executeQuery();
		
		while (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setImagem(resultSet.getString("imagem"));
			usuario.setTipoFile(resultSet.getString("tipofile"));
			
			return usuario;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
