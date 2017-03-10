
/**
 * @author Paulo Pocinho
 * @since 21-02-2017
 */

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int opcao = 0;
		Scanner s = new Scanner(System.in);
		s.useLocale(Locale.US); // para detectar double

		int playlistsPossiveis;

		do {
			System.out.println("Introduza o numero de playlists que deseja criar:");
			playlistsPossiveis = s.nextInt();
			s.nextLine();
		} while (playlistsPossiveis <= 0);

		Player player = new Player(playlistsPossiveis);

		do {
			escreveMenu();
			opcao = s.nextInt();
			s.nextLine();

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

	private static void tocarMusica(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o numero da musica:");
		int musica = s.nextInt();
		s.nextLine();
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
		System.out.println("Introduza o numero da playlist:");
		int playlist = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o titulo da musica:");
		String titulo = s.nextLine();
		System.out.println("Introduza o autor da musica " + titulo + ":");
		String autor = s.nextLine();
		System.out.println("Introduza a duração da musica " + titulo + ":");
		double duracao = s.nextDouble();
		s.nextLine();
		System.out.println("Introduza o ano da musica " + titulo + ":");
		int ano = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o genero da musica " + titulo + ":");
		String genero = s.nextLine();
		System.out.println("Introduza o caminho para o ficheiro da musica " + titulo + ":");
		String ficheiro = s.nextLine();
		try {
			p.criarMusica(playlist, new Musica(titulo, autor, duracao, ano, genero, ficheiro));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void criarPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o nome da playlist:");
		String nome = s.nextLine();
		System.out.println("Introduza a capacidade da playlist " + nome + ":");
		int capacidade = s.nextInt();
		s.nextLine();
		try {
			p.criarPlaylist(nome, capacidade);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void alterarMusica(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o numero da musica a alterar:");
		int musica = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o titulo da musica:");
		String titulo = s.nextLine();
		System.out.println("Introduza o autor da musica " + titulo + ":");
		String autor = s.nextLine();
		System.out.println("Introduza a duração da musica " + titulo + ":");
		double duracao = s.nextDouble();
		s.nextLine();
		System.out.println("Introduza o ano da musica " + titulo + ":");
		int ano = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o estilo da musica " + titulo + ":");
		String genero = s.nextLine();
		System.out.println("Introduza o caminho para o ficheiro da musica " + titulo + ":");
		String ficheiro = s.nextLine();
		try {
			p.alterarMusica(playlist, musica, new Musica(titulo, autor, duracao, ano, genero, ficheiro));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void alterarPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = s.nextInt();
		s.nextLine();
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
		int playlist = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o numero da musica:");
		int musica = s.nextInt();
		s.nextLine();
		try {
			System.out.println(p.consultarMusica(playlist, musica));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void consultarPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = s.nextInt();
		s.nextLine();
		try {
			System.out.println(p.consultarPlaylist(playlist));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void alocarMusica(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist origem:");
		int origem = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o numero da musica na playlist " + origem + ":");
		int musica = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o numero da playlist destino:");
		int destino = s.nextInt();
		s.nextLine();
		try {
			p.alocarMusica(origem, musica, destino);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void removerMusica(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o numero da musica:");
		int musica = s.nextInt();
		s.nextLine();
		try {
			p.removerMusica(playlist, musica);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void removerPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = s.nextInt();
		s.nextLine();
		try {
			p.removerPlaylist(playlist);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void reordenarPlaylist(Scanner s, Player p) {
		System.out.println("Introduza o numero da playlist:");
		int playlist = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o numero da musica origem na playlist " + playlist + ":");
		int origem = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o numero da musica destino na playlist " + playlist + ":");
		int destino = s.nextInt();
		s.nextLine();
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
		System.out.println(" (0) Sair");
		System.out.println("Introduza uma opção:");
	}
}
