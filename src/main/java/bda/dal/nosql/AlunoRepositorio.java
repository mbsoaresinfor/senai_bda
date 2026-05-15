package bda.dal.nosql;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import bda.entidades.Aluno;

public class AlunoRepositorio {

	private static MongoDatabase database;

	public static void conectar() {

		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		database = mongoClient.getDatabase("aula");
		System.out.println("Conectado ao MongoDB!");

	}

	public void salvar(Aluno aluno) {
		MongoCollection<Document> colecao = database.getCollection("aluno");
		Document novoUsuario = new Document()
				.append("nome", aluno.getNome()).append("idade", aluno.getIdade());
		colecao.insertOne(novoUsuario);
		System.out.println("Documento criado com sucesso!");

	}

	public void salvar(String aluno) {

		MongoCollection<Document> colecao = database.getCollection("aluno");
		Document documento = Document.parse(aluno);
		colecao.insertOne(documento);
		System.out.println("Documento criado com sucesso! no formato json "+ aluno);
		;
	}
	
	public void deletar(String nome) {
		MongoCollection<Document> colecao = database.getCollection("aluno");
        DeleteResult resultadoUnico = colecao
        		.deleteOne(Filters.eq("nome", nome));
		
        System.out.println("Documentos deletados: " + resultadoUnico.getDeletedCount());

	}
	
	public List<Aluno> listar(){
		List<Aluno> lista = new ArrayList<Aluno>();
		MongoCollection<Document> colecao = database.getCollection("aluno");
        for(Document d : colecao.find()) {
        	Aluno aluno = new Aluno();
        	aluno.setNome(d.getString("nome"));
        	aluno.setIdade(d.getInteger("idade"));
        	lista.add(aluno);
        }
		return lista;
	}
	
	public List<Aluno> listar2(){
		List<Aluno> lista = new ArrayList<Aluno>();
		MongoCollection<Document> colecao = database.getCollection("aluno");
        Gson gson = new Gson();
       
		for(Document d : colecao.find()) {
        	Aluno aluno = gson
        			.fromJson(d.toJson(), Aluno.class);
        	lista.add(aluno);
        }
		return lista;
	}

}
