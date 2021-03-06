package modeldao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.database;
import model.cuenta;
import model.ejecutivo;
import model.juridico;
import model.natural;

public class juridicodao {
	private static final String SQL_INGRESAR = 
			"call banco.sp_ingresar_juridico(?, ?, ?, ?, ?, ?);";
	private static final String SQL_BUSCAR=
            "SELECT * FROM vw_listar_cliente_juridicos WHERE RUT LIKE ?";
	
	private static final String SQL_LISTAR=
            "SELECT * FROM vw_listar_cliente_juridicos";
	
	private static final database cnn = database.saberEstado();
	
	public boolean ingresar(juridico j){
		CallableStatement cs;
		ResultSet rs;
		int bandera=0;
	        try {
	        	cs = cnn.getCnn().prepareCall(SQL_INGRESAR);
	        	cs.setString(1, j.getPerRut());
	            cs.setString(2, j.getPerNombre());
	            cs.setString(3, j.getPerApePaterno());
	            cs.setString(4, j.getPerApeMaterno());
	            cs.setString(5, j.getCliCategoria());
	            cs.setString(6, j.getJurRazSocial());
	            rs = cs.executeQuery();
	            while(rs.next()){
	            	bandera = rs.getInt("_resultado");
	            }
	            if(bandera > 0) {
	            	return true;
	            }
	        } catch (SQLException ex) {
	        	 System.out.println("Error al Ingresar Clientes Juridico " + ex.toString());
	        }finally{
	            cnn.cerrarConexion();
	        }
		return false;
	}
	
	
   public juridico buscar(juridico j) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = cnn.getCnn().prepareStatement(SQL_BUSCAR);
            ps.setString(1, j.getPerRut());
            rs = ps.executeQuery();
            while(rs.next()){
            	j.setPerNombre(rs.getString("NOMBRE"));
            	j.setPerApePaterno(rs.getString("PATERNO"));
            	j.setPerApeMaterno(rs.getString("MATERNO"));
            	j.setPerNacionalidad(rs.getString("NACIONALIDAD"));
            	j.setPerFecNacimiento(rs.getString("NACIMIENTO"));
            	j.setCliCategoria(rs.getString("CATEGORIA"));
            	ejecutivo e = new ejecutivo();
   	            e.setPerRut(rs.getString("EJECUTIVO"));
   	            j.setEje(e);
            	j.setJurRazSocial(rs.getString("RAZON_SOCIAL"));
            }
        } catch (SQLException ex) {
            	System.out.println("Error al Depositar en Cuenta " + ex.toString());     
       }finally{
           cnn.cerrarConexion();
       }
       return j;
   }
	
	public ArrayList<juridico> listar() {		    
		PreparedStatement ps;
		ResultSet rs;
        ArrayList<juridico> juridicos= new ArrayList<>(); 
	    try {
	    	ps = cnn.getCnn().prepareStatement(SQL_LISTAR);
	        rs = ps.executeQuery();
	        while(rs.next()){
	            juridico j=new juridico();
	            j.setPerRut(rs.getString("RUT"));
	            j.setPerNombre(rs.getString("NOMBRE"));
	            j.setPerApePaterno(rs.getString("PATERNO"));
	            j.setPerApeMaterno(rs.getString("MATERNO"));
	            j.setPerNacionalidad(rs.getString("NACIONALIDAD"));
	            j.setPerFecNacimiento(rs.getString("NACIMIENTO"));
	            j.setCliCategoria(rs.getString("CATEGORIA"));
	            ejecutivo e = new ejecutivo();
	            e.setPerRut(rs.getString("EJECUTIVO"));
	            j.setEje(e);
	            j.setJurRazSocial(rs.getString("RAZON_SOCIAL"));
	            juridicos.add(j);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Clientes Juridico "+ ex.toString());
        } finally {
			cnn.cerrarConexion();
		}
        return juridicos;
	}
}
