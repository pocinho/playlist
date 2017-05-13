/**
 * @author Paulo Pocinho
 * @since 21-02-2017
 */

public class Musica {

	private String titulo;
	private String autor;
	private double duracao;
	private int ano;
	private Estilo estilo;
	private String ficheiro;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public String getFicheiro() {
		return ficheiro;
	}

	public void setFicheiro(String ficheiro) {
		this.ficheiro = ficheiro;
	}

	@Override
	public String toString() {
		StringBuilder resultado = new StringBuilder("Titulo: ");
		resultado.append(titulo);
		resultado.append("\nAutor: ");
		resultado.append(autor);
		resultado.append("\nDuração: ");
		resultado.append(duracao);
		resultado.append("\nAno: ");
		resultado.append(ano);
		resultado.append("\nEstilo: ");
		resultado.append(Estilo.capitalizarEstilo(estilo));
		resultado.append("\nFicheiro: ");
		resultado.append(ficheiro);
		return resultado.toString();
	}

	public Musica(String titulo, String autor, double duracao, int ano, Estilo estilo, String ficheiro) {
		this.titulo = titulo;
		this.autor = autor;
		this.duracao = duracao;
		this.ano = ano;
		this.estilo = estilo;
		this.ficheiro = ficheiro;
	}
}
