/*
 * SWCOM (Servicio Web para la Corrección Ortográfica Multilingüe)
 * Proyecto Fin de Grado de Ingeniería en Informática.
 * (UNIR) Universidad Internacional de la Rioja
 * José María Beltrán Vicente
 */
package swcom;


public class Palabra {
    private String valor;
    private int linea;
    
    public Palabra(){
        
    }
    
    public void SetValor(String v){
        this.valor=v;
    }
    public String getValor(){
        return this.valor;
    }
    
    public void SetLinea(int l){
        this.linea=l;
    }
    public int getLinea(){
        return this.linea;
    }
    
}
