package bda.camadaapresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import bda.camadanegocio.AlunoServico;
import bda.dal.acessodireto.AlunoArquivoRepositorio;
import bda.dal.acessodireto.AlunoDAO;
import bda.dal.orm.AlunoRepositorio;
import bda.entidades.Aluno;

/**
 * Exemplo de uso DAL (Data Access Layer) fazendo uso das estrategias vista em
 * aula. A aplicação está dividida em 3 camadas: 1. Camada de Apresentação 2.
 * Camada de Regras de negocio. 3. Camada de acesso ao dado (DAL)
 *
 */
public class MainCamadaApresentacao {
	// ** CAMADA DE APRESENTAÇÃO **

	public static int OPCAO_ESTRATEGIA_DAL;

	public static void main(String[] args) throws SQLException, IOException {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Selecione a estrategia de DAL:"
				+ "\n1. Acesso Direto" + "\n2. ORM \n3. Arquivo\n4.NoSql ");
		OPCAO_ESTRATEGIA_DAL = teclado.nextInt();
		if (OPCAO_ESTRATEGIA_DAL == 1) {
			AlunoDAO.criarConexao();
		} else if (OPCAO_ESTRATEGIA_DAL == 2) {
			AlunoRepositorio.criarConexao();
		}else if (OPCAO_ESTRATEGIA_DAL == 3) {
			new AlunoArquivoRepositorio();
		}else if (OPCAO_ESTRATEGIA_DAL == 4) {
			 bda.dal.nosql
			.AlunoRepositorio.conectar();
			 bda.dal.nosql
				.AlunoRepositorio r = new  bda.dal.nosql
				.AlunoRepositorio();
			 
			 Aluno aluno = new Aluno();
			 aluno.setNome("aluno_sql10");
			 aluno.setIdade(80);
			 r.salvar(aluno);
			 
			 String jsonAluno ="{ nome: \"marcelo_50\", "
			 		+ "idade: 3000 }";
			 r.salvar(jsonAluno);
			 
			 for(Aluno a :r.listar2()) {
				 System.out.println(a.getNome());
				 System.out.println(a.getIdade());
			 }
			 
			 
		}
		else {
			System.out.println("Erro na escolha da estrategia DAL");
			return;
		}

		AlunoServico usuarioServico = new AlunoServico();

		System.out.println("1. inserirAluno ");
		System.out.println("2. buscarAluno ");
		System.out.println("3. deletarAluno ");
		System.out.println("4. existeAlunoMenorIdade ");
		System.out.println("5. buscarAlunoMaiorIdade ");
		int op = teclado.nextInt();
		if (op == 1) {
			System.out.println("Digite nome do aluno: ");
			String nome = teclado.next();

			System.out.println("Digite idade do aluno: ");
			String idade = teclado.next();

			boolean resultado = usuarioServico.salvarAluno(nome, idade);
			if (resultado) {
				System.out.println("sucesso ao salvar o aluno");
			}
		} else if (op == 2) {
			System.out.println("Digite o codigo do aluno? ");
			long codigo = teclado.nextLong();
			Aluno aluno = usuarioServico.buscarAluno(codigo);
			if (aluno == null) {
				System.out.println("aluno não existe");
			} else {
				System.out.println("NOme: " + aluno.getNome());
			}
		}

		else if (op == 3) {
			System.out.println("Digite o codigo do aluno? ");
			long codigo = teclado.nextLong();
			boolean resultado = usuarioServico.deletar(codigo);
			if (resultado == true) {
				System.out.println("aluno removido com sucesso");
			} else {
				System.out.println("aluno NÃO removido");
			}
		}

		else if (op == 4) {

			boolean resultado = usuarioServico.existeAlunoMenorIdade();
			if (resultado == true) {
				System.out.println("Existe aluno maior de idade");
			} else {
				System.out.println("NAO Existe aluno maior de idade");
			}
		}
		else if (op == 5) {
			List<Aluno> alunos =usuarioServico.buscarAlunoMaiorIdade();
			for(Aluno aluno : alunos) {
				System.out.println("nome: " + aluno.getNome());
			}
		}

	}

}
