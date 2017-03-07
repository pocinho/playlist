/**
 * @author Paulo Pocinho
 * @since 21-02-2017
 */

public class Musica {

	private String titulo;
	private String autor;
	private double duracao;
	private int ano;
	private String genero;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFicheiro() {
		return ficheiro;
	}

	public void setFicheiro(String ficheiro) {
		this.ficheiro = ficheiro;
	}

	@Override
	public String toString() {
		return "Titulo: " + titulo + "\nAutor: " + autor + "\nDuração: " + duracao + "\nAno: " + ano + "\nGenero: " + genero + "\nFicheiro: " + ficheiro;
	}

	public Musica(String titulo, String autor, double duracao, int ano, String genero, String ficheiro) {
		this.titulo = titulo;
		this.autor = autor;
		this.duracao = duracao;
		this.ano = ano;
		this.genero = genero;
		this.ficheiro = ficheiro;
	}
}
