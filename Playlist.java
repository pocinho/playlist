/**
 * @author Paulo Pocinho
 * @since 21-02-2017
 */

public class Playlist {

	private String nome;
	private Musica[] listaMusica;
	private int capacidade;
	private int totalMusicas;

	private void compactar() {
		for (int i = 0; i < totalMusicas; ++i) {
			if (listaMusica[i] == null) {
				for (int t = i; t < (totalMusicas - 1); ++t) {
					listaMusica[t] = listaMusica[t + 1];
				}
				listaMusica[totalMusicas - 1] = null;
				break;
			}
		}
	}

	public Boolean hasObject(int posicao) {
		Boolean resultado = false;
		int pos = posicao - 1;
		if ((pos >= 0) && (pos < totalMusicas)) {
			resultado = true;
		}
		return resultado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Musica getMusica(int posicao) {
		int pos = posicao - 1;
		if ((pos < 0) || (pos >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + posicao + " na playlist " + nome + ".");
		} else {
			return listaMusica[pos];
		}
	}

	public Boolean isFull() {
		return totalMusicas >= capacidade;
	}

	public void adicionarMusica(Musica musica) {
		if (isFull()) {
			throw new IllegalArgumentException("A playlist " + nome + " está cheia.");
		} else {
			listaMusica[totalMusicas] = musica;
			totalMusicas++;
		}
	}

	public void removerMusica(int posicao) {
		int pos = posicao - 1;
		if ((pos < 0) || (pos >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + posicao + " na playlist " + nome + ".");
		} else {
			listaMusica[pos] = null;
			compactar();
			totalMusicas--;
		}
	}

	public void consultarMusica(int posicao) {
		int pos = posicao - 1;
		if ((pos < 0) || (pos >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + posicao + " na playlist " + nome + ".");
		} else {
			System.out.println("Playlist " + nome + ":\n");
			System.out.println(posicao + ". " + listaMusica[pos].getTitulo() + "\n");
			System.out.println(listaMusica[pos]);
			System.out.println();
		}
	}

	public void alterarMusica(int posicao, Musica musica) {
		int pos = posicao - 1;
		if ((pos < 0) || (pos >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + posicao + " na playlist " + nome + ".");
		} else {
			listaMusica[pos] = musica;
		}
	}

	public void reordenarMusica(int origem, int destino) {
		int posOrigem = origem - 1;
		int posDestino = destino - 1;
		if ((posOrigem < 0) || (posOrigem >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + origem + " na playlist " + nome + ".");
		} else if ((posDestino < 0) || (posDestino >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + destino + " na playlist " + nome + ".");
		} else {
			Musica musica = listaMusica[posOrigem];
			listaMusica[posOrigem] = listaMusica[posDestino];
			listaMusica[posDestino] = musica;
		}
	}

	@Override
	public String toString() {
		String resultado = "Playlist " + nome + ":\n\n";

		for (int i = 0; i < totalMusicas; ++i) {
			resultado = resultado + (i+1) + ". " + listaMusica[i].getTitulo() + "\n";
		}

		resultado = resultado + "\nTotal Musicas: " + totalMusicas + "\nCapacidade: " + capacidade + "\n";

		return resultado;
	}

	public Playlist(String nome, int capacidade) {
		this.nome = nome;
		this.listaMusica = new Musica[capacidade];
		this.capacidade = capacidade;
		this.totalMusicas = 0;
	}
}
