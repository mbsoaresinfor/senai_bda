package bda.dal.cache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import java.io.IOException;

public class CacheManager {
	private static MemcachedClient client;

	// para rodar memcached via docker
	// sudo docker run --name meu-memcached -p 11211:11211 -d memcached
	
	static {
		try {

			XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("127.0.0.1:11211"));

			builder.setConnectionPoolSize(5);

			client = builder.build();
			System.out.println("Conexão com Memcached inicializada com sucesso!");
		} catch (IOException e) {
			System.err.println("Erro ao conectar no Memcached: " + e.getMessage());
		}
	}

	public static MemcachedClient getClient() {
		return client;
	}

	public static void shutdown() {
		if (client != null) {
			try {
				client.shutdown();
				System.out.println("Conexão com Memcached encerrada.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
