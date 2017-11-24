package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import model.cliente;
import model.cuenta;
import model.natural;
import modeldao.cuentadao;

public class archivo {

	public static boolean escribirCuenta(ArrayList<cuenta> cuentas, String ruta){
        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean band=false;
        try {
        	fichero = new FileWriter("./src/report/"+ruta);
           	pw = new PrintWriter(fichero);
           	for (cuenta cu : cuentas) {
				pw.println(cu.getCli().getPerRut()
						+","+cu.getCli().getPerNombre()
						+","+cu.getCli().getPerApePaterno()
						+","+cu.getCli().getPerApeMaterno()
						+","+cu.getCli().getPerNacionalidad()
						+","+cu.getCli().getPerFecNacimiento()
						+","+cu.getCli().getCliCategoria()
						+","+cu.getCli().getEje().getPerRut() 
						+","+cu.getCueId()
						+","+cu.getCueSaldo()
						+","+cu.getCueEstado()
						+","+cu.getCueSobregiro());
				band=true;
			}
           	
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
    			// Nuevamente aprovechamos el finally para 
        		// asegurarnos que se cierra el fichero.
        		if (null != fichero) {
        			fichero.close();
        		}
        	} catch (Exception e2) {
        		e2.printStackTrace();
        		band=false;
        	}
        }
        return band;
    }
 
    public static boolean leerCuenta(String ruta){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        boolean band = false;
        String[] arreglo_datos;
        cuentadao cueDao=new cuentadao();
        cuenta cu;
        cliente c;
        try {
			archivo = new File ("./src/csv/"+ruta);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			// Lectura del fichero
			String linea;
			while((linea=br.readLine())!=null) {
				arreglo_datos = linea.split(",");
				c=new cliente();
				c.setPerRut(arreglo_datos[0]);
				cu=new cuenta(c);
				cu.setCueSaldo(Integer.parseInt(arreglo_datos[1]));
				cu.setCueSobregiro(Integer.parseInt(arreglo_datos[2]));
				band = cueDao.ingresar(cu);
        	}				
        } catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{
              if( null != fr ){
                 fr.close();
              }
           }catch (Exception e2){
              e2.printStackTrace();
           }
        }
        return band;
    }
	
    
	public static boolean escribirCliente(ArrayList<cliente> clientes, String ruta){
        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean band=false;
        try {
        	fichero = new FileWriter("./src/report/"+ruta);
           	pw = new PrintWriter(fichero);
			for (cliente c : clientes) {
				pw.println(c.getPerRut()
						+","+c.getPerNombre()
						+","+c.getPerApePaterno()
						+","+c.getPerApeMaterno()
						+","+c.getPerNacionalidad()
						+","+c.getPerFecNacimiento()
						+","+c.getCliCategoria()
						+","+c.getEje().getPerRut());
				band=true;
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
    			// Nuevamente aprovechamos el finally para 
        		// asegurarnos que se cierra el fichero.
        		if (null != fichero) {
        			fichero.close();
        		}
        	} catch (Exception e2) {
        		e2.printStackTrace();
        		band=false;
        	}
        }
        return band;
    }
 
	/*public static boolean escribirAcciones(String accion){
        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean band=false;
        try {
        	fichero = new FileWriter("./src/report/acciones.txt");
           	pw = new PrintWriter(fichero);
			for (cliente c : clientes) {
				pw.println(c.getPerRut()
						+","+c.getPerNombre()
						+","+c.getPerApePaterno()
						+","+c.getPerApeMaterno()
						+","+c.getPerNacionalidad()
						+","+c.getPerFecNacimiento()
						+","+c.getCliCategoria()
						+","+c.getEje().getPerRut());
				band=true;
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
    			// Nuevamente aprovechamos el finally para 
        		// asegurarnos que se cierra el fichero.
        		if (null != fichero) {
        			fichero.close();
        		}
        	} catch (Exception e2) {
        		e2.printStackTrace();
        		band=false;
        	}
        }
        return band;
    }*/
	
}
