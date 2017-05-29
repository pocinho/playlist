/**
 * @author Paulo Pocinho
 * @since 21-02-2017
 */

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
			String caminho = listaMusica[pos].getFicheiro();
			File f = new File(caminho);
			if (f.exists()) {
                Desktop.getDesktop().open(f);
            } else {
                throw new IllegalArgumentException("Não é possivel abrir o ficheiro: " + caminho);
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

	//public Musica[] getLista() {
	//	return Arrays.copyOf(listaMusica, listaMusica.length);
	//}

	public String[] serializarMusicas(String delimitador) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < totalMusicas; ++i) {
			sb.append(listaMusica[i].getTitulo());
			sb.append(delimitador);
			sb.append(listaMusica[i].getAutor());
			sb.append(delimitador);
			sb.append(listaMusica[i].getDuracao());
			sb.append(delimitador);
			sb.append(listaMusica[i].getAno());
			sb.append(delimitador);
			sb.append(listaMusica[i].getEstilo());
			sb.append(delimitador);
			sb.append(listaMusica[i].getFicheiro());
			sb.append("\n");
		}
		String[] resultado = sb.toString().split("\n");
		return resultado;
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
		StringBuilder consulta = new StringBuilder(0);
		if ((pos < 0) || (pos >= totalMusicas)) {
			throw new IllegalArgumentException("Não é possivel encontrar a musica " + posicao + " na playlist " + nome + ".");
		} else {
			consulta.append("Playlist ");
			consulta.append(nome);
			consulta.append(":\n\n");
			consulta.append(posicao);
			consulta.append(". ");
			consulta.append(listaMusica[pos].getTitulo());
			consulta.append("\n\n");
			consulta.append(listaMusica[pos]);
			consulta.append("\n");
		}
		return consulta.toString();
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
		StringBuilder resultado = new StringBuilder("Playlist ");
		resultado.append(nome);
		resultado.append(":\n\n");

		for (int i = 0; i < totalMusicas; ++i) {
			resultado.append(i + 1);
			resultado.append(". ");
			resultado.append(listaMusica[i].getTitulo());
			resultado.append("\n");
		}

		resultado.append("\nTotal Musicas: ");
		resultado.append(totalMusicas);
		resultado.append("\nCapacidade: ");
		resultado.append(capacidade);
		resultado.append("\n");

		return resultado.toString();
	}

	public Playlist(String nome, int capacidade) {
		this.nome = nome;
		this.listaMusica = new Musica[capacidade];
		this.capacidade = capacidade;
		this.totalMusicas = 0;
	}
}
