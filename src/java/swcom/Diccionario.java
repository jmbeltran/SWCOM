/*
 * SWCOM (Servicio Web para la Corrección Ortográfica Multilingüe)
 * Proyecto Fin de Grado de Ingeniería en Informática.
 * (UNIR) Universidad Internacional de la Rioja
 * José María Beltrán Vicente
 */
package swcom;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;


public class Diccionario {
    private String URL;	
    private String juego_caracteres;
    private String error;
    private HashMap<String, Integer> Palabras = new HashMap<String, Integer>(); //Lista de palabras 
	
	public Diccionario(String u, String jc) {
	    this.URL=u;
            this.juego_caracteres=jc;
            this.error="";
            try{
		URL url = new URL(this.URL);
                URLConnection uc = url.openConnection();
                uc.connect();
                BufferedReader bf = new BufferedReader(new InputStreamReader(uc.getInputStream(),this.juego_caracteres));
		String linea;
		int i=1;
		while ((linea = bf.readLine()) != null) {
                    this.Palabras.put(linea.toLowerCase(),i++);
                }
		bf.close();
		
				
	}catch(Exception e){
		this.error=e.getMessage();
    }
}
	public Boolean Buscar(String s) {
		if(this.Palabras.containsKey(s)) return true;
		return false;
        } 
        public int CountPalabras(){
            return this.Palabras.size();
        }
        public String getError() {
            return this.error;
        }
}