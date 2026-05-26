package bda.camadaapresentacao;

import java.util.Random;
import java.util.Scanner;

import bda.dal.cache.CacheManager;
import net.rubyeye.xmemcached.MemcachedClient;

public class MainCacheLeitura {


	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		MemcachedClient cache = CacheManager.getClient();

		while (true) {
			try {

				System.out.println("Digite o id do usuário: [digite 'sair' para encerrar] ");
				String chaveUsuario = teclado.next();
				
				if(chaveUsuario.equals("sair")) {
					break;
				}

				String usuario = cache.get(chaveUsuario);

				if (usuario != null) {
					System.out.println("[CACHE HIT] Dado recuperado instantaneamente: " + usuario);
				} else {
					System.out.println("[CACHE MISS] Dado não encontrado no cache.");

					usuario = buscarNoBancoDeDadosPrincipal(chaveUsuario);

					int tempoExpiracao = 600;
					cache.set(chaveUsuario, tempoExpiracao, usuario);
					System.out.println("Dado armazenado no cache para as próximas consultas");
				}

			} catch (Exception e) {
				System.err.println("Falha na operação de cache: " + e.getMessage());
			} 
		}

	}

	private static String buscarNoBancoDeDadosPrincipal(String id) {
		try {
			// simula lentidao do banco
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return "teste_" + new Random().nextDouble();
	}

}
