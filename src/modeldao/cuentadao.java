package modeldao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.database;
import model.cuenta;
import model.natural;

public class cuentadao {
	private static final String SQL_INGRESAR = 
			"call banco.sp_ingresar_cuenta(?, ?, ?);";
	
	private static final String SQL_ACTUALIZAR="";
	
	private static final String SQL_ACTUALIZAR_OPERACION=
			"UPDATE cuenta SET cueSaldo = ? WHERE cueId= ?;";
	
	private static final String SQL_BUSCAR=
			"SELECT cueId,cueSaldo,cueSobregiro, cliente_persona_perRut FROM cuenta WHERE cueId = ?";
	
	private static final String SQL_LISTAR_JURIDICOS=
	        "SELECT * FROM vw_listar_juridico_cuentas";
	private static final String SQL_LISTAR_NATURALES=
	        "SELECT * FROM vw_listar_natural_cuentas";
	private static final database cnn = database.saberEstado();
	
	
	public boolean ingresar(cuenta c){
		CallableStatement cs;
		ResultSet rs;
		int bandera=0;
	        try {
	        	cs = cnn.getCnn().prepareCall(SQL_INGRESAR);
	        	cs.setString(1, c.getCli().getPerRut());
	            cs.setInt(2, c.getCueSaldo());
	            cs.setInt(3, c.getCueSobregiro());
	            rs = cs.executeQuery();
	            while(rs.next()){
	            	bandera = rs.getInt("_resultado");
	            }
	            if(bandera > 0) {
	            	return true;
	            }
	        } catch (SQLException ex) {
	        	 System.out.println("Error al Ingresar Cuenta " + ex.toString());
	        }finally{
	            cnn.cerrarConexion();
	        }
		return false;
	}
	
	 public boolean actualizarSaldo(cuenta c) {
	        PreparedStatement ps;
	        int bandera;
	        try {
	            ps=cnn.getCnn().prepareStatement(SQL_ACTUALIZAR_OPERACION);
	            ps.setInt(1,c.getCueSaldo());
	            ps.setInt(2,c.getCueId());
	            bandera=ps.executeUpdate();
	            if(bandera>0){
	                return true;
	            }
	        } catch (SQLException ex) {
	        	System.out.println("Error al Depositar en Cuenta " + ex.toString());	   
        	}finally{
	                cnn.cerrarConexion();
	          }
	        return false;
	   }
	
	   public cuenta buscar(cuenta c) {
	        PreparedStatement ps;
	        ResultSet rs;
	        try {
	            ps=cnn.getCnn().prepareStatement(SQL_BUSCAR);
	            ps.setInt(1, c.getCueId());
	            rs=ps.executeQuery();
	            while(rs.next()){
	            	c.setCueSaldo(rs.getInt("cueSaldo"));
	            	c.setCueSobregiro(rs.getInt("cueSobregiro"));
	            }
	        } catch (SQLException ex) {
	            	System.out.println("Error al Depositar en Cuenta " + ex.toString());     
            }finally{
                cnn.cerrarConexion();
            }
	        return c;
	   }
}
