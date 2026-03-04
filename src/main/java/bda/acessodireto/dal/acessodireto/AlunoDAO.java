package bda.acessodireto.dal.acessodireto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//** CAMADA DA DAL - camada de acesso ao dado **
// Usando DAO
// Um DAL para a entidade Aluno
// Estrategia acesso direto (native sql)
// 
public class AlunoDAO {

	// URL: jdbc:mysql://servidor:porta/nome_do_banco
	private static final String URL = "jdbc:mysql://localhost:3306/aula";
	private static final String USUARIO = "marcelo";
	private static final String SENHA = "";
	private static Connection conexao;

	public static void criarConexao() {
		try {
			// criar uma conexao direta com o banco de dados.
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("Criado conexao Acesso Direto");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erro ao conectar: " + e.getMessage());
		}
	}

	/**
	 * Operações para salvar no Armazenamento dos Dados
	 */
	public void salvar(String nome, String idade) {

		try {
			// criando o sql native
			String sql = "INSERT INTO aula.aluno (nome, idade) VALUES(?, ?)";
			final PreparedStatement pstmt = conexao.prepareStatement(sql);			
			pstmt.setString(1, nome);
			pstmt.setInt(2, Integer.parseInt(idade));
			pstmt.execute();
			System.out.println("Inserido um aluno no banco de dado pela DAL"
					+ " usando estrategia acesso direto (native sql)");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	// criar outras operações

}
