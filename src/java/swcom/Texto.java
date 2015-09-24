/*
 * SWCOM (Servicio Web para la Corrección Ortográfica Multilingüe)
 * Proyecto Fin de Grado de Ingeniería en Informática.
 * (UNIR) Universidad Internacional de la Rioja
 * José María Beltrán Vicente
 */
package swcom;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.net.URLConnection;
import java.net.URL;
public class Texto {
    private String URL;	
    private String juego_caracteres;
    private ArrayList<Palabra> Palabras = new ArrayList<Palabra>(); //Lista de palabras 
    private String error;
    
	public Texto(String u, String jc) {
	    this.URL=u;
            this.juego_caracteres=jc;
            this.error="";
             try{
		URL url = new URL(this.URL);
                URLConnection uc = url.openConnection();
                uc.connect();
		BufferedReader bf = new BufferedReader(new InputStreamReader(uc.getInputStream(),this.juego_caracteres));
		String linea;
		int nlinea=1;
                String delimitadores= "0123456789!¡¿?-+*,;. \\\"\'[]{}()<>@#$%&=";
                while ((linea = bf.readLine()) != null){
                    StringTokenizer palabrasSeparadas = new StringTokenizer(linea,delimitadores);
                    while (palabrasSeparadas.hasMoreElements()){
                        Palabra p = new Palabra();
                        p.SetValor(palabrasSeparadas.nextToken().toLowerCase());
                        p.SetLinea(nlinea);
                        this.Palabras.add(p);
                    }
                    nlinea++;
		}
		bf.close();
		}catch(Exception e){
	this.error=e.getMessage();		
        }
    }
	public int CountPalabras(){
            return this.Palabras.size();
        }
        public Palabra GetPalabra(int i){
           return this.Palabras.get(i);
        }
        public String GetError(){
            return this.error;
        }
}
