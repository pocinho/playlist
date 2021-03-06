/**
 * @author Paulo Pocinho
 * @since 21-02-2017
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Player {

	private Playlist[] playlists;
	private int capacidade;
	private int totalPlaylists;

	private void compactar() {
		for (int i = 0; i < totalPlaylists; ++i) {
			if (playlists[i] == null) {
				// buraco encontrado;
				// puxar todas as posições uma casa para trás um a um:
				for (int t = i; t < (totalPlaylists - 1); ++t) {
					playlists[t] = playlists[t + 1];
				}
				// necessário para a condição em que a ultima posição não vai
				// receber a seguinte (fim do array).
				playlists[totalPlaylists - 1] = null;
				break;
			}
		}
	}

	public int getTotalPlaylists() {
		return this.totalPlaylists;
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	public Boolean isFull() {
		return totalPlaylists >= capacidade;
	}

	public void criarPlaylist(String nome, int capacidade) throws IllegalArgumentException {
		if (isFull()) {
			throw new IllegalArgumentException("Não é possível criar mais playlists.");
		} else {
			playlists[totalPlaylists] = new Playlist(nome, capacidade);
			totalPlaylists++;
		}
	}

	public void criarMusica(int playlist, Musica musica) throws IllegalArgumentException {
		int pos = playlist - 1;
		if ((pos < 0) || (pos >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + playlist + ".");
		} else {
			playlists[pos].adicionarMusica(musica);
		}
	}

	public String consultarMusica(int playlist, int posicao) throws IllegalArgumentException {
		int pos = playlist - 1;
		String consulta = "";
		if ((pos < 0) || (pos >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + playlist + ".");
		} else {
			consulta = playlists[pos].consultarMusica(posicao);
		}
		return consulta;
	}

	public void removerMusica(int playlist, int posicao) throws IllegalArgumentException {
		int pos = playlist - 1;
		if ((pos < 0) || (pos >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + playlist + ".");
		} else {
			playlists[pos].removerMusica(posicao);
		}
	}

	public void alterarMusica(int playlist, int posicao, Musica musica) throws IllegalArgumentException {
		int pos = playlist - 1;
		if ((pos < 0) || (pos >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + playlist + ".");
		} else {
			playlists[pos].alterarMusica(posicao, musica);
		}
	}

	public void alterarPlaylist(int playlist, String nome) throws IllegalArgumentException {
		int pos = playlist - 1;
		if ((pos < 0) || (pos >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + playlist + ".");
		} else {
			playlists[pos].setNome(nome);
		}
	}

	public String consultarPlaylist(int playlist) throws IllegalArgumentException {
		int pos = playlist - 1;
		String consulta = "";
		if ((pos < 0) || (pos >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + playlist + ".");
		} else {
			consulta = playlists[pos].toString();
		}
		return consulta;
	}

	public void alocarMusica(int origem, int musica, int destino) throws IllegalArgumentException {
		int posOrigem = origem - 1;
		int posDestino = destino - 1;
		if ((posOrigem < 0) || (posOrigem >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + origem + ".");
		} else if ((posDestino < 0) || (posDestino >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + destino + ".");
		} else if (playlists[posDestino].isFull()) {
			throw new IllegalArgumentException("Não é possível adicionar mais musicas à playlist " + destino + ".");
		} else if (!playlists[posOrigem].hasObject(musica)) {
			throw new IllegalArgumentException("Não é possível encontrar a musica " + musica + " na playlist " + origem + ".");
		} else {
			playlists[posDestino].adicionarMusica(playlists[posOrigem].getMusica(musica));
			playlists[posOrigem].removerMusica(musica);
		}
	}

	public void reordenarPlaylist(int playlist, int origem, int destino) throws IllegalArgumentException {
		int pos = playlist - 1;
		if ((pos < 0) || (pos >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + playlist + ".");
		} else {
			playlists[pos].reordenarMusica(origem, destino);
		}
	}

	public void removerPlaylist(int playlist) throws IllegalArgumentException {
		int pos = playlist - 1;
		if ((pos < 0) || (pos >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + playlist + ".");
		} else {
			playlists[pos] = null;
			compactar();
			totalPlaylists--;
		}
	}

	public void tocarMusica(int playlist, int musica) throws IOException, IllegalArgumentException {
		int pos = playlist - 1;
		if ((pos < 0) || (pos >= totalPlaylists)) {
			throw new IllegalArgumentException("Não é possível encontrar a playlist " + playlist + ".");
		} else {
			playlists[pos].tocarMusica(musica);
		}
	}

	public String gravarDados() throws FileNotFoundException {
		StringBuilder resultado = new StringBuilder("Alguma coisa correu mal.");
		String delimitador = ";";
		PrintWriter f = null;
		try {
			f = new PrintWriter("playlist.txt");
			for (int i = 0; i < totalPlaylists; ++i) {
				String[] lista = playlists[i].serializarMusicas(delimitador);
				int total = playlists[i].getTotalMusicas();
				for (int m = 0; m < total; ++m) {
					f.println((i + 1) + delimitador + total + delimitador +
									playlists[i].getNome() + delimitador +
									lista[m]);
				}
			}
			resultado.replace(0, resultado.length(), "Dados gravados com sucesso.");
		} finally {
			if (f != null) {
				f.close();
			}
		}
		return resultado.toString();
	}

	public void reset() {
		for (int i = 0; i < totalPlaylists; ++i) {
			playlists[i] = null;
		}
		totalPlaylists = 0;
	}

	@Override
	public String toString() {
		StringBuilder resultado = new StringBuilder("Playlists disponíveis:\n\n");

		for (int i = 0; i < totalPlaylists; ++i) {
			resultado.append(i + 1);
			resultado.append(". ");
			resultado.append(playlists[i].getNome());
			resultado.append("\n");
		}

		resultado.append("\nTotal Playlists: ");
		resultado.append(totalPlaylists);
		resultado.append("\nCapacidade: ");
		resultado.append(capacidade);
		resultado.append("\n");

		return resultado.toString();
	}

	public Player(int capacidade) {
		this.capacidade = capacidade;
		this.playlists = new Playlist[capacidade];
		this.totalPlaylists = 0;
	}
}
