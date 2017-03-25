import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

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

	public void tocarMusica(int posicao) throws IOException, IllegalArgumentException {
		int pos = posicao - 1;
		if ((pos < 0) || (pos >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + posicao + " na playlist " + nome + ".");
		} else {
			String ficheiro = listaMusica[pos].getFicheiro();
			Desktop.getDesktop().open(new File(ficheiro));
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

	public Musica[] getLista() {
		return this.listaMusica;
	}

	public int getTotalMusicas() {
		return this.totalMusicas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Musica getMusica(int posicao) throws IllegalArgumentException {
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

	public void adicionarMusica(Musica musica) throws IllegalArgumentException {
		if (isFull()) {
			throw new IllegalArgumentException("A playlist " + nome + " está cheia.");
		} else {
			listaMusica[totalMusicas] = musica;
			totalMusicas++;
		}
	}

	public void removerMusica(int posicao) throws IllegalArgumentException {
		int pos = posicao - 1;
		if ((pos < 0) || (pos >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + posicao + " na playlist " + nome + ".");
		} else {
			listaMusica[pos] = null;
			compactar();
			totalMusicas--;
		}
	}

	public String consultarMusica(int posicao) throws IllegalArgumentException {
		int pos = posicao - 1;
		String consulta = "";
		if ((pos < 0) || (pos >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + posicao + " na playlist " + nome + ".");
		} else {
			consulta = "Playlist " + nome + ":\n\n";
			consulta += posicao + ". " + listaMusica[pos].getTitulo() + "\n\n";
			consulta += listaMusica[pos] + "\n";
		}
		return consulta;
	}

	public void alterarMusica(int posicao, Musica musica) throws IllegalArgumentException {
		int pos = posicao - 1;
		if ((pos < 0) || (pos >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + posicao + " na playlist " + nome + ".");
		} else {
			listaMusica[pos] = musica;
		}
	}

	public void reordenarMusica(int origem, int destino) throws IllegalArgumentException {
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
			resultado += (i + 1) + ". " + listaMusica[i].getTitulo() + "\n";
		}

		resultado += "\nTotal Musicas: " + totalMusicas + "\nCapacidade: " + capacidade + "\n";

		return resultado;
	}

	public Playlist(String nome, int capacidade) {
		this.nome = nome;
		this.listaMusica = new Musica[capacidade];
		this.capacidade = capacidade;
		this.totalMusicas = 0;
	}
}
