/*
 * SWCOM (Servicio Web para la Corrección Ortográfica Multilingüe)
 * Proyecto Fin de Grado de Ingeniería en Informática.
 * (UNIR) Universidad Internacional de la Rioja
 * José María Beltrán Vicente
 */
package swcom;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Stateless
@Path ("/ortografia")
@Produces("text/xml")
public class SWCOM { 
    @GET
    public String GenerarInforme(@QueryParam("texto") String tp,@QueryParam("diccionario") String dp, @QueryParam("juego_caracteres") String jcp){
        long ti= System.currentTimeMillis();
        ArrayList<String> sugerencias = new ArrayList<String> ();
        int errores=0;
        StringBuilder br = new StringBuilder();
        Texto t = new Texto(tp,jcp);
        Diccionario d = new Diccionario(dp,jcp);
        br.append("<?xml version='1.0' encoding='UTF-8'?>").append("\n");
        br.append("<swcom>").append("\n");
        if (t.GetError()!="" || d.getError()!="")
        {
        br.append("<excepciones>");
        br.append(t.GetError()).append("\n");
        br.append(d.getError()).append("\n");
        br.append("</excepciones>").append("\n");
        }else {
        br.append("<texto>").append("\n");
        br.append("<url>").append(tp).append("</url>").append("\n");
        br.append("<palabras>").append(t.CountPalabras()).append("</palabras>").append("\n");
        br.append("</texto>").append("\n");
        br.append("<diccionario>").append("\n");
        br.append("<url>").append(dp).append("</url>").append("\n");
        br.append("<palabras>").append(d.CountPalabras()).append("</palabras>").append("\n");
        br.append("</diccionario>").append("\n");
        br.append("<ortografia>").append("\n");
        Corrector c = new Corrector(d);
        for (int i=0;i<t.CountPalabras();++i){
            if (!d.Buscar(t.GetPalabra(i).getValor())){
                    errores++;
                    br.append("<error>").append("\n");
                    br.append("<palabra>").append(t.GetPalabra(i).getValor()).append("</palabra>").append("\n");
                    br.append("<linea>").append(t.GetPalabra(i).getLinea()).append("</linea>").append("\n");
                    br.append("<sugerencias>").append("\n");
                    sugerencias=c.Ortografia(t.GetPalabra(i).getValor());
                    for (int j=0;j<sugerencias.size();++j) br.append("<sugerencia>").append(sugerencias.get(j)).append("</sugerencia>").append("\n");
                    br.append("</sugerencias>").append("\n");
                    br.append("</error>").append("\n");
            }        
        }
        br.append("<errores>").append(Integer.toString(errores)).append("</errores>").append("\n");
        br.append("</ortografia>").append("\n");
    }
    long tf= System.currentTimeMillis();    
    br.append("<tiempo>").append(Long.toString(tf-ti)).append("</tiempo>").append("\n");    
    br.append("</swcom>").append("\n");    
    return br.toString();
    }        

    }
   




