package com.mutantes.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutantes.conexion.Conexion;
import com.mutantes.model.Dna;
import com.mutantes.model.Resultado;

import java.sql.Statement;
import java.util.Arrays;
import java.sql.ResultSet;
@RestController
public class DnaRest {
	
	@GetMapping("/")
	public String Api() {
		return "<html><title>Mirko</title><body><h1>Api Rest Mutantes</h1></body></html>";
	}
	
	/*RETORNA EL OBJETO DE LOS STATS*/
	@GetMapping("/stats")
	public Resultado getStats() {
		int cantMutante,cantHumano,suma;
		double ratio;
		/*CUENTO LA CANTIDAD DE MUTANTES QUE HAY EN LA BBDD*/
		cantMutante=contarAdn("mutante");
		/*CUENTO LA CANTIDAD DE HUMANOS QUE HAY EN LA BBDD*/
		cantHumano=contarAdn("humano");
		
		/*SI NO HAY HUMANOS Y MUTANTES EL RATIO ES 0.0*/
		if(cantHumano==0&&cantMutante==0) {
			ratio=0.0;
		}else {
			/*SI NO HAY HUMANOS EL RATIO ES 0.0p*/
			if(cantHumano==0) {
				ratio=1.0;
			}else {
				/*SINO HACE EL CALCULO*/
				suma=cantHumano+cantMutante;
				ratio= ((cantMutante*100.0)/suma)/100;
			}
		}
		Resultado resultado=new Resultado(cantMutante, cantHumano, ratio);
		/*RETORNA EL OBJETO*/
		return resultado;
	}
	
	@PostMapping("/mutant")
	public ResponseEntity<String> postDna(@RequestBody Dna dna1) {
		String [] dna;
		String arrayString;
		int bandera=0;
		boolean resultado;
		/* IGUALO EL JSON QUE INGRESO POR POST A UNA VARIABLE TIPO ARRAY STRING*/
		dna=dna1.getDna();
		arrayString = String.join(".",dna1.getDna());
		/* VALIDACION DATOS*/
		for (int i = 0; i < dna.length; i++) {
            if(dna[i].length()!=dna.length){
                bandera=1;
            }
            for (int j = 0; j < dna[i].length(); j++) {
                if(dna[i].charAt(j)!='A' && dna[i].charAt(j)!='C' && dna[i].charAt(j)!='G' &&dna[i].charAt(j)!='T' ){
                   bandera=1;
                }
            }
        }
		
		/*  SI LA VALIDACION ES CORRECTA ENTRA A LA FUNCION BOOL*/
		if(bandera==1){
			return ResponseEntity.status(403).body("Error");
	       }else{
	            resultado=isMutant(dna);
	        
	            if(resultado==true){
	            	/* INSERTO EL STRING DNA EN LA BASE DE DATOS CON ID MUTANTE*/
	            	insertarBBDD(1,arrayString,"mutante");
	            	return ResponseEntity.status(200).body("OK");
	            }else{

	            	/* INSERTO EL STRING DNA EN LA BASE DE DATOS CON ID HUMANO*/
	            	insertarBBDD(2,arrayString,"humano");
	            	return ResponseEntity.status(403).body("Forbidden");
	            } 
	       }
	}
	
	/* SI HAY UNA SECUENCIA DE CARACTERES IGUALES OBLICUA HORIZONATL VERTICAL RETORNA TRUE SINO FALSE*/
    public static boolean isMutant(String [] dna){
        int sum=0, n1, n2; 
        char letra1,letra2,letra3,letra4;
        /*
            RECORRO CADA POSICION DEL ARRAY
        */
        for (int i = 0; i < dna.length; i++) {
            n1=i;
            /* RECORRO CADA CARACTER DEL ARRAY*/
            for (int j = 0; j < dna.length; j++) {
                n2=j;
                sum=0;
                /* SI LA POCICION EN LA QUE ME ENCCUENTRO ME PERMITE 
                   COMPARAR LOS SIGUIENTES CUATRO CARACTERES
                   LOS COMPARO SI SON IGUALES SUMA 1 
                */
                
                /* COMPARA DE FORMA OBLICUA DECRECIENTE*/
                if((dna.length-j)>3 && (dna.length-i)>3){
                    letra1=dna[i].charAt(j);
                    letra2=dna[n1+1].charAt(n2+1);
                    letra3=dna[n1+2].charAt(n2+2);
                    letra4=dna[n1+3].charAt(n2+3);
                    if(letra2==letra1 && letra3==letra1 && letra4==letra1){
                    	sum++;
                    }
                }
                /* COMPARA DE FORMA HORIZONTAL*/

                if((dna.length-j)>3){
                    letra1=dna[i].charAt(j);
                    letra2=dna[i].charAt(n2+1);
                    letra3=dna[i].charAt(n2+2);
                    letra4=dna[i].charAt(n2+3);
                    if(letra2==letra1 && letra3==letra1 && letra4==letra1){
                    	sum++;
                    }
                }
                /* COMPARA DE FORMA VERTICAL*/
                if((dna.length-i)>3){
                    letra1=dna[i].charAt(j);
                    letra2=dna[n1+1].charAt(j);
                    letra3=dna[n1+2].charAt(j);
                    letra4=dna[n1+3].charAt(j);
                    if(letra2==letra1 && letra3==letra1 && letra4==letra1){
                    	sum++;
                    }
                }
                /* COMPARA DE FORMA OBLICUA ASCENDENTE*/
                if(j>=3 && (dna.length-i)>3){
                    letra1=dna[i].charAt(j);
                    letra2=dna[n1+1].charAt(n2-1);
                    letra3=dna[n1+2].charAt(n2-2);
                    letra4=dna[n1+3].charAt(n2-3);
                    if(letra2==letra1 && letra3==letra1 && letra4==letra1){
                    	sum++;
                    }
                }
                if(sum>0) {
                	return true;
                }
            }
        }
        
       /* SI EL SUMADOR ES MAS DE 0 ES QUE HAY UNA SECUENCIA DE CARACTERES IGUALES
            YA SEA OBLICUA HORIZONTAL VERTICAL
        */
       
            return false;
    }
    
    public static void insertarBBDD(int id,String dna,String tipo) {
    	try{
            String query = "insert into dna (id,dna,tipo) values ('" + id +"', '" + dna+  "', '" + tipo + "')";
            Statement stmt=Conexion.getConexion().createStatement();
            stmt.executeUpdate(query);
            Conexion.getConexion().close();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /* CUENTA LA CANTIDAD DE DNA DEL TIPO ("MUTANTE" O "HUMANO") SE LE MANDA POR PARAMETRO*/
    public static int contarAdn(String tipo) {
    	
		try {
			int n=0;
			String sql="SELECT count(*) FROM dna WHERE tipo='"+tipo+"'";
			Statement stmt = Conexion.getConexion().createStatement();
	        ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()) {
			       //Si hay resultados obtengo el valor. 
			        n= rs.getInt(1);
			     }
			Conexion.getConexion().close();
			return n;
		}catch(Exception e) {
			System.out.println(e);
		}
		return 0;
    }
}