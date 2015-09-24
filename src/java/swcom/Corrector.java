/*
 * SWCOM (Servicio Web para la Corrección Ortográfica Multilingüe)
 * Proyecto Fin de Grado de Ingeniería en Informática.
 * (UNIR) Universidad Internacional de la Rioja
 * José María Beltrán Vicente
 */
package swcom;
import java.util.ArrayList;
import java.util.HashMap;
public class Corrector {
    private Diccionario Diccionario;
       
	public Corrector(Diccionario d){
		this.Diccionario=d;
   }
private final ArrayList<String> DamerauInverso(String palabra){	
    char caracteres_validos[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','ñ','á','é','í','ó','ú','ü'};
    ArrayList<String> ediciones = new ArrayList<String>();
    //Eliminaciones
    for(int i=0; i < palabra.length(); ++i) ediciones.add(palabra.substring(0, i) + palabra.substring(i+1));
    //Trasposiciones
    for(int i=0; i < palabra.length()-1; ++i) ediciones.add(palabra.substring(0, i) + palabra.substring(i+1, i+2) + palabra.substring(i, i+1) + palabra.substring(i+2));
    //Sustituciones
    for(int i=0; i < palabra.length(); ++i) for(char c : caracteres_validos) ediciones.add(palabra.substring(0, i) + String.valueOf(c) + palabra.substring(i+1));
    //Inserciones
    for(int i=0; i <= palabra.length(); ++i) for(char c : caracteres_validos) ediciones.add(palabra.substring(0, i) + String.valueOf(c) + palabra.substring(i));
        return ediciones;
    }
	
public ArrayList<String> Ortografia(String palabra) {
    ArrayList<String> candidatas = new ArrayList<String>();
    ArrayList<String> ediciones = DamerauInverso(palabra);
    for(String s : ediciones) if(this.Diccionario.Buscar(s)) if (!candidatas.contains(s)) candidatas.add(s);
    if(candidatas.size() > 0) return candidatas;
    for(String s : ediciones) for(String w : DamerauInverso(s)) if(this.Diccionario.Buscar(w)) if (!candidatas.contains(w))candidatas.add(w);
    return candidatas.size() > 0 ? candidatas : candidatas;
    }
}
		

