import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import com.thoughtworks.xstream.XStream;

public class UtilidadesLibro{

	public static ArrayList<Libro> xerarDatLibro(){
		String[] isbn={"111111","222222","3333333","444444","555555"};
		String[] titulo={"aaaaaa","bbbbbb","vcccccc","eeeeee","ddddd"};
		ArrayList<String[]> autores=new ArrayList<String[]>();
		String[] autor1={"pedro","juan"};
		String[] autor2={"jony","melavo"};
		String[] autor3={"aitor","tilla"};
		String[] autor4={"elena","nito"};
		String[] autor5={"carmen","lomana"};
		autores.add(autor1);
		autores.add(autor2);
		autores.add(autor3);
		autores.add(autor4);
		autores.add(autor5);
		int[] anos={1,2,3,4,5};		
		ArrayList<Libro> arrayL=new ArrayList<Libro>();				
		try{
			FileOutputStream fos=new FileOutputStream("libros.dat");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			for(int x=0;x<5;x++){
				Libro libro=new Libro(isbn[x],titulo[x],autores.get(x),anos[x]);
				arrayL.add(libro);
				oos.writeObject(libro);
			}
			oos.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return arrayL;
	}

	public static ArrayList<Libro> leDatLibro(){
		ArrayList<Libro> libros=new ArrayList<Libro>();
		try{
			FileInputStream fis=new FileInputStream("libros.dat");
			ObjectInputStream ois=new ObjectInputStream(fis);
			while(fis.available()>0){				
				Libro libro = (Libro) ois.readObject();
				libros.add(libro);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return libros;
	} 

	public static void mostrarDatLibro(ArrayList<Libro> arrayL){
		ArrayList<Libro> libros=new ArrayList<Libro>();
		try{
			FileInputStream fis=new FileInputStream("libros.dat");
			ObjectInputStream ois=new ObjectInputStream(fis);
			while(fis.available()>0){
				int x=1;
				Libro libro = (Libro) ois.readObject();
				System.out.println("Libro "+x);
				System.out.println("ISBN: "+libro.getIsbn());
				System.out.println("Titulo: "+libro.getTitulo());
				System.out.println("Autores: "+libro.getAutores());
				System.out.println("Anos: "+libro.getAno());
				x+=1;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void xerarXmlLibroDom(){
		try{	
			ArrayList<Libro> libros=leDatLibro();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation=builder.getDOMImplementation();
			Document documento=implementation.createDocument(null,"libros",null);
			documento.setXmlVersion("1.0");
			for(int x=0;x<libros.size();x++){
				
				Element elLibro=documento.createElement("libro");
				documento.getDocumentElement().appendChild(elLibro);

				Element elIsbn=documento.createElement("isbn");
				elLibro.appendChild(elIsbn);
				Text textoIsbn=documento.createTextNode(""+libros.get(x).getIsbn());
				elIsbn.appendChild(textoIsbn);

				Element elTitulo=documento.createElement("titulo");
				elLibro.appendChild(elTitulo);
				Text textoTitulo=documento.createTextNode(""+libros.get(x).getTitulo());
				elTitulo.appendChild(textoTitulo);

				Element elAutores=documento.createElement("autores");
				elLibro.appendChild(elAutores);
				for(int y=0;y<libros.get(x).getAutoresArray().length;y++){
					Element elAutor=documento.createElement("autor");
					elAutores.appendChild(elAutor);
					Text textoAutor=documento.createTextNode(""+libros.get(x).getAutoresArray()[y].toString());
					elAutor.appendChild(textoAutor);
				}

				Element elAno=documento.createElement("ano");
				elLibro.appendChild(elAno);
				Text textoAno=documento.createTextNode(""+libros.get(x).getAno());
				elAno.appendChild(textoAno);

				Transformer transformador=TransformerFactory.newInstance().newTransformer();
				transformador.setOutputProperty(OutputKeys.INDENT, "yes");
				transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				Source fonte=new DOMSource(documento);
				Result resultado=new StreamResult(new java.io.File("libros.xml"));
				transformador.transform(fonte,resultado);			

			}

		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}

	public static void xerarXmlLibroXStream(){
		ArrayList<Libro> arrayL=leDatLibro();
		try{
			XStream xstream=new XStream();
			xstream.alias("libros",List.class);
			xstream.alias("libro",Libro.class);
			xstream.alias("autor",String.class);
			xstream.toXML(arrayL,new FileOutputStream("libros1.xml"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}






























