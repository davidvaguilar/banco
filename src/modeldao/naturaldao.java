package modeldao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexion.database;
import model.ejecutivo;
import model.juridico;
import model.natural;

public class naturaldao {
	private static final String SQL_INGRESAR = 
			"call banco.sp_ingresar_natural(?, ?, ?, ?, ?, ?);";
	private static final String SQL_BUSCAR=
            "SELECT * FROM vw_listar_cliente_naturales WHERE RUT LIKE ?";
	
	
	
	private static final String SQL_ACTUALIZAR="";
	
	private static final String SQL_LISTAR=
	        "SELECT * FROM vw_listar_cliente_naturales";
	
	private static final database cnn = database.saberEstado();
	
	public boolean ingresar(natural n){
		CallableStatement cs;
		ResultSet rs;
		int bandera=0;
	        try {
	        	cs = cnn.getCnn().prepareCall(SQL_INGRESAR);
	        	cs.setString(1, n.getPerRut());
	            cs.setString(2, n.getPerNombre());
	            cs.setString(3, n.getPerApePaterno());
	            cs.setString(4, n.getPerApeMaterno());
	            cs.setString(5, n.getCliCategoria());
	            cs.setInt(6, n.getNatPatrimonio());
	            rs = cs.executeQuery();
	            while(rs.next()){
	            	bandera = rs.getInt("_resultado");
	            }
	            if(bandera > 0) {
	            	return true;
	            }
	        } catch (SQLException ex) {
	        	 System.out.println("Error al Ingresar Clientes Naturales " + ex.toString());
	        }finally{
	            cnn.cerrarConexion();
	        }
		return false;
	}
	
	public natural buscar(natural n) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = cnn.getCnn().prepareStatement(SQL_BUSCAR);
            ps.setString(1, n.getPerRut());
            rs = ps.executeQuery();
            while(rs.next()){
            	n.setPerRut(rs.getString("RUT"));
            	n.setPerNombre(rs.getString("NOMBRE"));
            	n.setPerApePaterno(rs.getString("PATERNO"));
            	n.setPerApeMaterno(rs.getString("MATERNO"));
            	n.setPerNacionalidad(rs.getString("NACIONALIDAD"));
            	n.setPerFecNacimiento(rs.getString("NACIMIENTO"));
            	n.setCliCategoria(rs.getString("CATEGORIA"));
            	ejecutivo e = new ejecutivo();
   	            e.setPerRut(rs.getString("EJECUTIVO"));
   	            n.setEje(e);
            	n.setNatPatrimonio(rs.getInt("PATRIMONIO"));
            }
        } catch (SQLException ex) {
            	System.out.println("Error al Depositar en Cuenta " + ex.toString());     
       }finally{
           cnn.cerrarConexion();
       }
       return n;
   }
	
	
	public ArrayList<natural> listar() {		    
		PreparedStatement ps;
		ResultSet rs;
        ArrayList<natural> naturales= new ArrayList<>(); 
	    try {
	    	ps = cnn.getCnn().prepareStatement(SQL_LISTAR);
	        rs = ps.executeQuery();
	        while(rs.next()){
	            natural n = new natural();
	            n.setPerRut(rs.getString("RUT"));
	            n.setPerNombre(rs.getString("NOMBRE"));
	            n.setPerApePaterno(rs.getString("PATERNO"));
	            n.setPerApeMaterno(rs.getString("MATERNO"));
	            n.setPerNacionalidad(rs.getString("NACIONALIDAD"));
	            n.setPerFecNacimiento(rs.getString("NACIMIENTO"));
	            n.setCliCategoria(rs.getString("CATEGORIA"));
	            ejecutivo e = new ejecutivo();
	            e.setPerRut(rs.getString("EJECUTIVO"));
	            n.setEje(e);
	            n.setNatPatrimonio(rs.getInt("PATRIMONIO"));
	            naturales.add(n);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Clientes Naturales "+ ex.toString());
        } finally {
			cnn.cerrarConexion();
		}
        return naturales;
	}
}
