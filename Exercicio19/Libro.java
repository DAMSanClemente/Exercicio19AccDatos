import java.io.Serializable;
import java.util.*;
public class Libro implements Serializable{
	final static long serialVersionUID=1L;
	private String isbn;
	private String titulo;
	private String[] autores;
	private int ano;
	public Libro(String isbn, String titulo, String[] autores, int ano){
		this.isbn=isbn;
		this.titulo=titulo;
		this.autores=autores;
		this.ano=ano;
	}
	public void setIsbn(String isbn){
		this.isbn=isbn;
	}
	public void setTitulo(String titulo){
		this.titulo=titulo;
	}
	public void setAutores(String[] autores){
		this.autores=autores;
	}
	public void setAno(int ano){
		this.ano=ano;
	}
	public String getIsbn(){
		return isbn;
	}
	public String getTitulo(){
		return titulo;
	}
	public String getAutores(){
		String enviar_autores="";
		for(int x=0;x<autores.length;x++){
			enviar_autores=enviar_autores+" "+autores[x].toString();
		}
		return enviar_autores;
	}
	public String[] getAutoresArray(){
		return autores;
	}
	public int getAno(){
		return ano;
	}
}
