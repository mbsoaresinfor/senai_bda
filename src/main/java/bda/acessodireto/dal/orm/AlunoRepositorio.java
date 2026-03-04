package bda.acessodireto.dal.orm;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import bda.acessodireto.entidades.Aluno;

//** CAMADA DA DAL - camada de acesso ao dado **
// Usando Repository
// Um DAL para a entidade Aluno
// Estrategia ORM, utilizando o framework Hibernate.
// 
public class AlunoRepositorio {

	private static SessionFactory conexao;

    public static SessionFactory criarConexao() {
        try {
        	// cria uma conexao com o orm(hibernate).
            conexao =  new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Aluno.class) // Registra sua entidade
                    .buildSessionFactory();
            System.out.println("Criado conexao ORM");
        } catch (Throwable ex) {
            System.err.println("Error na criação da conexao: " + ex.getMessage());            
        }
		return null;
    }
	
	
	
	/**
	 * Operações para salvar no Armazenamento dos Dados
	 */
	public void salvar(String nome, String idade) {

		try {
			// orm vai criar o insert automaticamente.
			Session session = conexao.openSession();
			Transaction transaction = session.beginTransaction();
			
			Aluno aluno = new Aluno();
			aluno.setNome(nome);
			aluno.setIdade(Integer.parseInt(idade));
			
			session.persist(aluno);
			transaction.commit();
			
			System.out.println("Inserido um aluno no banco de dado pela DAL"
					+ " usando estrategia ORM");
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// criar outras operações

}
