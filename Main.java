/**
 * @author Paulo Pocinho
 * @since 21-02-2017
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int opcao = 0;
		Scanner s = new Scanner(System.in);
		s.useLocale(Locale.US); // para detectar double com ponto

		final int playlistsPossiveis = 100;

		Player player = new Player(playlistsPossiveis);

		inicializar(player);

		do {
			escreveMenu();
			opcao = validarInt(s);

			switch (opcao) {
			case 1:
				criarPlaylist(s, player);
				break;
			case 2:
				criarMusica(s, player);
				break;
			case 3:
				removerPlaylist(s, player);
				break;
			case 4:
				removerMusica(s, player);
				break;
			case 5:
				listarPlaylists(player);
				break;
			case 6:
				consultarPlaylist(s, player);
				break;
			case 7:
				consultarMusica(s, player);
				break;
			case 8:
				alterarPlaylist(s, player);
				break;
			case 9:
				alterarMusica(s, player);
				break;
			case 10:
				reordenarPlaylist(s, player);
				break;
			case 11:
				alocarMusica(s, player);
				break;
			case 12:
				tocarMusica(s, player);
				break;
			case 13:
				gravarDados(player);
				break;
			case 14:
				carregarDados(player);
				break;
			case 0:
				System.out.println("Adeus, volte sempre!");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}

		} while (opcao != 0);

		s.close();
	}

	public static void tocarMusica(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = validarInt(s);
		System.out.println("Introduza o numero da musica:");
		int musica = validarInt(s);
		try {
			p.tocarMusica(playlist, musica);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void listarPlaylists(Player p) {
		System.out.println(p);
		pausa();
	}

	public static void criarMusica(Scanner s, Player p) {
		if (p.getTotalPlaylists() > 0) {
			System.out.println(p);
			System.out.println("Introduza o numero da playlist:");
			int playlist = validarInt(s);
			System.out.println("Introduza o titulo da musica:");
			String titulo = s.nextLine();
			System.out.println("Introduza o autor da musica " + titulo + ":");
			String autor = s.nextLine();
			System.out.println("Introduza a duração da musica " + titulo + ":");
			double duracao = validarDouble(s);
			System.out.println("Introduza o ano da musica " + titulo + ":");
			int ano = validarInt(s);
			System.out.println("Introduza o estilo da musica " + titulo + ":");
			System.out.print("Estilos disponíveis: ");
			for (Estilo e : Estilo.values()) {
			    System.out.print(Estilo.capitalizarEstilo(e) + " ");
            }
            System.out.println();
			String estiloFrase = s.nextLine();
            Estilo estilo = Estilo.parseEstilo(estiloFrase);
			System.out.println("Introduza o caminho para o ficheiro da musica " + titulo + ":");
			String ficheiro = s.nextLine();
			try {
				p.criarMusica(playlist, new Musica(titulo, autor, duracao, ano, estilo, ficheiro));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Ainda não tem playlists criadas.");
		}
		pausa();
	}

	public static void criarPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o nome da playlist:");
		String nome = s.nextLine();
		System.out.println("Introduza a capacidade da playlist " + nome + ":");
		int capacidade = validarInt(s);
		try {
			p.criarPlaylist(nome, capacidade);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void alterarMusica(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = validarInt(s);
		System.out.println("Introduza o numero da musica a alterar:");
		int musica = validarInt(s);
		System.out.println("Introduza o titulo da musica:");
		String titulo = s.nextLine();
		System.out.println("Introduza o autor da musica " + titulo + ":");
		String autor = s.nextLine();
		System.out.println("Introduza a duração da musica " + titulo + ":");
		double duracao = validarDouble(s);
		System.out.println("Introduza o ano da musica " + titulo + ":");
		int ano = validarInt(s);
		System.out.println("Introduza o estilo da musica " + titulo + ":");
        for (Estilo e : Estilo.values()) {
            System.out.print(Estilo.capitalizarEstilo(e) + " ");
        }
        System.out.println();
        String estiloFrase = s.nextLine();
        Estilo estilo = Estilo.parseEstilo(estiloFrase);
		System.out.println("Introduza o caminho para o ficheiro da musica " + titulo + ":");
		String ficheiro = s.nextLine();
		try {
			p.alterarMusica(playlist, musica, new Musica(titulo, autor, duracao, ano, estilo, ficheiro));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void alterarPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = validarInt(s);
		System.out.println("Introduza o nome da playlist:");
		String nome = s.nextLine();
		try {
			p.alterarPlaylist(playlist, nome);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void consultarMusica(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = validarInt(s);
		System.out.println("Introduza o numero da musica:");
		int musica = validarInt(s);
		try {
			System.out.println(p.consultarMusica(playlist, musica));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void consultarPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = validarInt(s);
		try {
			System.out.println(p.consultarPlaylist(playlist));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void alocarMusica(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist origem:");
		int origem = validarInt(s);
		System.out.println("Introduza o numero da musica na playlist " + origem + ":");
		int musica = validarInt(s);
		System.out.println("Introduza o numero da playlist destino:");
		int destino = validarInt(s);
		try {
			p.alocarMusica(origem, musica, destino);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void removerMusica(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = validarInt(s);
		System.out.println("Introduza o numero da musica:");
		int musica = validarInt(s);
		try {
			p.removerMusica(playlist, musica);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void removerPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = validarInt(s);
		try {
			p.removerPlaylist(playlist);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void reordenarPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = validarInt(s);
		System.out.println("Introduza o numero da musica origem na playlist " + playlist + ":");
		int origem = validarInt(s);
		System.out.println("Introduza o numero da musica destino na playlist " + playlist + ":");
		int destino = validarInt(s);
		try {
			p.reordenarPlaylist(playlist, origem, destino);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void pausa() {
		System.out.println("Prima ENTER para continuar.");
		try {
			System.in.read();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void escreveMenu() {
		System.out.println("Opções disponíveis:");
		System.out.println(" (1) Criar playlist");
		System.out.println(" (2) Criar musica");
		System.out.println(" (3) Remover playlist");
		System.out.println(" (4) Remover musica de uma playlist");
		System.out.println(" (5) Ver todas as playlists");
		System.out.println(" (6) Consultar detalhes de playlist");
		System.out.println(" (7) Consultar detalhes de musica");
		System.out.println(" (8) Alterar uma playlist");
		System.out.println(" (9) Alterar uma musica");
		System.out.println("(10) Reordenar playlist");
		System.out.println("(11) Alocar musica a outra playlist");
		System.out.println("(12) Tocar musica");
		System.out.println("(13) Guardar dados");
		System.out.println("(14) Carregar dados");
		System.out.println(" (0) Sair");
		System.out.println("Introduza uma opção:");
	}

	public static void gravarDados(Player player) {
		try {
			String resultado = player.gravarDados();
			System.out.println(resultado);
		} catch (Exception e) {
			System.out.println("Excepção: " + e.getMessage());
			e.printStackTrace();
		}
		pausa();
	}

	public static void carregarDados(Player player) {
		Scanner f = null;
		try {
			File dados = new File("playlist.txt");
			// Não fazer reset ao player se não existirem dados.
			if (dados.exists()) {
				f = new Scanner(dados);
				player.reset();
				String playlistActual = "";
				while (true) {
					if (f.hasNextLine()) {
						String[] token = f.nextLine().split(";");
					/*
					 * Formato de ficheiro:
					 * token[0] = numero de playlist
					 * token[1] = capacidade musicas
					 * token[2] = nome playlist
					 * token[3] = musica.getTitulo()
					 * token[4] = musica.getAutor()
					 * token[5] = musica.getDuracao()
					 * token[6] = musica.getAno()
					 * token[7] = musica.getEstilo()
					 * token[8] = musica.getFicheiro());
					 */
						if (!playlistActual.equals(token[0])) {
							playlistActual = token[0];
							player.criarPlaylist(token[2], Integer.parseInt(token[1]));
						}
						player.criarMusica(Integer.parseInt(token[0]), new Musica(token[3], token[4], Double.parseDouble(token[5]), Integer.parseInt(token[6]), Estilo.parseEstilo(token[7]), token[8]));
					} else {
						System.out.println("Dados carregados com sucesso.");
						break;
					}
				}
			} else {
				System.out.println("Ainda não existem dados guardados.");
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if (f != null) {
				f.close();
			}
		}
		pausa();
	}

	public static void inicializar(Player player) {
		File f = null;
		try {
			f = new File("playlist.txt");
			if (f.exists()) {
				carregarDados(player);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static double validarDouble(Scanner s) {
		double resultado = 0.0;
		for(;;) {
			if (!s.hasNextDouble()) {
				System.out.println("Numero invalido.");
				s.nextLine();
			} else {
				resultado = Double.parseDouble(s.nextLine());
				break;
			}
		}
		return resultado;
	}

	static int validarInt(Scanner s) {
		int resultado = 0;
		for (;;) {
			if (!s.hasNextInt()) {
				System.out.println("Numero invalido.");
				s.nextLine();
			} else {
				resultado = Integer.parseInt(s.nextLine());
				break;
			}
		}
		return resultado;
	}
}
