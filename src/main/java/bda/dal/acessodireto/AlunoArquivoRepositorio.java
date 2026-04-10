package bda.dal.acessodireto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import bda.entidades.Aluno;

public class AlunoArquivoRepositorio {

	public AlunoArquivoRepositorio()  {
		   File meuArquivo = new File("alunos.csv");
           try {
           // Cria o arquivo se ele não existir
           if (meuArquivo.createNewFile()) {
               System.out.println("Arquivo criado: " + meuArquivo.getName());
           } else {
               System.out.println("O arquivo já existe.");
           }
           }catch(Exception e) {
        	   e.printStackTrace();
           }
	}
	
	public void salvar(String nome, String idade) throws IOException {
		String conteudo = nome + ";" +idade;
		Files.writeString(Paths.get("alunos.csv"),
				conteudo);
	}
	
	public Aluno buscarAluno(Long id) {
		return null;
	}
	
	public boolean existeAlunoMenorIdade() {
		return false;
	}
	
	public boolean removerAluno(long codigo) {
		return false;
	}
	
	public List<Aluno> buscarAlunoMaiorIdade() {
		return null;
	}
	
	
}
