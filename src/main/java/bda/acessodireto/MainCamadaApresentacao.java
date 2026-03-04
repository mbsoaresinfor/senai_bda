package bda.acessodireto;

import java.util.Scanner;

import bda.acessodireto.camadanegocio.AlunoServico;
import bda.acessodireto.dal.acessodireto.AlunoDAO;
import bda.acessodireto.dal.orm.AlunoRepositorio;

/**
 * Exemplo de uso DAL (Data Access Layer) fazendo uso das estrategias vista em
 * aula. A aplicação está dividida em 3 camadas:
 * 1. Camada de Apresentação 
 * 2. Camada de Regras de negocio.
 * 3. Camada de acesso ao dado (DAL)
 *
 */
public class MainCamadaApresentacao {
	// ** CAMADA DE APRESENTAÇÃO **
	
	public static int OPCAO_ESTRATEGIA_DAL;

	public static void main(String[] args) {


		Scanner teclado = new Scanner(System.in);
		System.out.println("Selecione a estrategia de DAL:\n1. Acesso Direto" + "\n2. ORM  ");
		OPCAO_ESTRATEGIA_DAL = teclado.nextInt();
		if (OPCAO_ESTRATEGIA_DAL == 1) {
			AlunoDAO.criarConexao();
		} else if (OPCAO_ESTRATEGIA_DAL == 2) {
			AlunoRepositorio.criarConexao();
		} else {
			System.out.println("Erro na escolha da estrategia DAL");
			return;
		}

		AlunoServico usuarioServico = new AlunoServico();

		System.out.println("Digite nome do aluno: ");
		String nome = teclado.next();

		System.out.println("Digite idade do aluno: ");
		String idade = teclado.next();

		boolean resultado = usuarioServico.salvarAluno(nome, idade);
		if (resultado) {
			System.out.println("sucesso ao salvar o aluno");
		}

	}

}
