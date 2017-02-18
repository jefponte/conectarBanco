package conectarBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author Jefferson Uchôa Ponte
 *
 * Pra funcionar você precisa primeiro adicionar o drive do Mysql. Se usa o Maven adicione-o no Pom.xml.  
 *
 */
public class Main {

	public static void main(String[] args) {
		
		Connection conexao = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/nomeDoBanco", "root", "senhaDoBanco");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Feita a conexão vamos fazer algo com o banco. 
		
		PreparedStatement ps2;
		try {
			ps2 = conexao.prepareStatement("INSERT into usuario(nome, login, senha) VALUES(?, ?, ?)");
			ps2.setString(1, "Jefferson");
			ps2.setString(2, "jefponte");
			ps2.setString(3, "aaa@12");
			if(ps2.executeUpdate() > 0){
				System.out.println("Adicionado com sucesso!");
			}else{
				System.out.println("Nao inserido.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Listando os nomes. 
		
		PreparedStatement psSelect;
		try {
			psSelect = conexao.prepareStatement("SELECT * FROM usuario");
			ResultSet resultSet = psSelect.executeQuery();
			while(resultSet.next()){
				System.out.println(resultSet.getString("nome"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//A classe aqui do lado, DAO pode ser muito util. 
		
	}

}
