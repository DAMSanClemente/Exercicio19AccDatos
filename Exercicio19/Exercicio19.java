import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
public class Exercicio19{
	public static void main(String[] args){
		ArrayList<Libro> libros=UtilidadesLibro.xerarDatLibro();
		UtilidadesLibro.mostrarDatLibro(libros);
		UtilidadesLibro.xerarXmlLibroDom();
		UtilidadesLibro.xerarXmlLibroXStream();

	}
}