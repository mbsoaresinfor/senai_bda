package bda.acessodireto.camadanegocio;

import bda.acessodireto.MainCamadaApresentacao;
import bda.acessodireto.dal.acessodireto.AlunoDAO;
import bda.acessodireto.dal.orm.AlunoRepositorio;

public class AlunoServico {

	// ** CAMADA DE LOGICA DE NEGOCIO **
	
	private AlunoRepositorio alunoRepositorio = new AlunoRepositorio();
	private AlunoDAO alunoDAO = new AlunoDAO();
	
	public boolean salvarAluno(String nome, String idade) {
		if(nome.equals("")) {
			System.out.println("ERROR: Nome deve ser preenchido");
			return false;
		}
		
		if(idade.equals("")) {
			System.out.println("ERROR: Idade deve ser preenchido");
			return false;
		}
		
		if(MainCamadaApresentacao.OPCAO_ESTRATEGIA_DAL == 1) {
			alunoDAO.salvar(nome, idade);
		}else {
			alunoRepositorio.salvar(nome, idade);	
		}
		
		return true;
	}
	
}
