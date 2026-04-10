package bda.camadanegocio;

import java.sql.SQLException;
import java.util.List;

import bda.camadaapresentacao.MainCamadaApresentacao;
import bda.dal.acessodireto.AlunoDAO;
import bda.dal.orm.AlunoRepositorio;
import bda.entidades.Aluno;

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

	public Aluno buscarAluno(long codigo) throws SQLException {
		if(MainCamadaApresentacao.OPCAO_ESTRATEGIA_DAL == 1) {
			return alunoDAO.buscarAluno(codigo);
		}else {
			return alunoRepositorio.buscarAluno(codigo);	
		}
		
	}
	
	public boolean existeAlunoMenorIdade() {
		if(MainCamadaApresentacao.OPCAO_ESTRATEGIA_DAL == 1) {
			return alunoDAO.existeAlunoMenorIdade();
		}else {
			return alunoRepositorio.existeAlunoMenorIdade();	
		}
	}

	public boolean deletar(long codigo) {
		if(MainCamadaApresentacao.OPCAO_ESTRATEGIA_DAL == 1) {
			return alunoDAO.removerAluno(codigo);	
		}else {
			return alunoRepositorio.removerAluno(codigo);	
		}
	}
	
	public List<Aluno> buscarAlunoMaiorIdade(){
		if(MainCamadaApresentacao.OPCAO_ESTRATEGIA_DAL == 1) {
			return alunoDAO.buscarAlunoMaiorIdade();	
		}else {
			return alunoRepositorio.buscarAlunoMaiorIdade();	
		}
	}
	
	
	
	
	
}
