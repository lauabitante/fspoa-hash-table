/**
 * Imlementa uma tabela hash com controle de colisão de
 * chaves baseado em hash fechada (closed hashing, ou,
 * open addressing) e "linear probing".
 *
 * @param <K> Tipo de dado da chave.
 * @param <V> Tipo de dado do valor armazenado.
 */
public class Hashtable<K,V> {
	/**
	 * Classe interna (inner class) utilizada para
	 * armazenar os dados na tabela hash.
	 *
	 * @param <K> Tipo de dado da chave.
	 * @param <V> Tipo de dado do valor armazenado.
	 */
	private static class Pair<K,V> {
		public final K key;
		public final V value;
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	// aramzena os dados da tabela hash.
	private Object[] dados = new Object[10];

	/**
	 * Insere um dado na tabela, baseado na sua chave.
	 * Em caso de colisão de dados, implementa o linear
	 * probing.
	 * @param key A chave utilizada para encontrar o dado.
	 * @param value O dado a ser armazenado.
	 */
	private void put(K key, V value) {
		int hash = key.hashCode();
		int indice = Math.abs(hash % dados.length);
		for (int i = 0; i < dados.length; i++) {
			if (dados[indice] == null) {
				dados[indice] = new Pair<>(key,value);
				return;
			} else {
				indice = (indice + 1) % dados.length;
			}
		}
	}

	/**
	 * Recupera um dado armazenado na tabela, baseado na
	 * sua chave.
	 * @param key A chave utilizada para encontrar o dado.
	 */
	@SuppressWarnings("unchecked")
	private V get(K key) {
		int hash = key.hashCode();
		int indice = Math.abs(hash % dados.length);
		for (int i = 0; i < dados.length; i++) {
			if (dados[indice] == null)
				throw new RuntimeException("Chave inexistente");
			Pair<K,V> kv = (Pair<K,V>)dados[indice];
			if (kv.key.equals(key))
				return kv.value;
			indice = (indice + 1) % dados.length;
		}
		throw new RuntimeException("Chave inexistente");
	}

	/**
	 * Exemplo de uso da tabela hash.
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Cria tabela
		Hashtable<String, Integer> hash = new Hashtable<>();
		
		// Insere dados na tabela.
		hash.put("Laura", 6);
		hash.put("Rafael", 1);
		hash.put("Ivonei", 2);
		hash.put("Lucia", 3);
		hash.put("Guilherme", 4);
		hash.put("Aline", 50);
		hash.put("Conterato", 30);

		// Mostra a estrutura da tabela.
		for (Object o: hash.dados) {
			if (o == null) {
				System.out.println("(null)");
				continue;
			}
			Pair<String,Integer> p=(Pair<String,Integer>)o;
			System.out.println(p.key + ": " + p.value);
		}

		// Recupera dados através das chaves.
		System.out.println("---------------");
		System.out.println(hash.get("Rafael"));
		System.out.println(hash.get("Ivonei"));
		System.out.println(hash.get("Laura"));
	}

}
