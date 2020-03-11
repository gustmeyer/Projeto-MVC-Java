package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Esporte;

import util.ConnectionUtil;

public class EsporteDAO {
	
	private static Connection con = ConnectionUtil.getConnection();
	
	public List<Esporte> montarEsportes() {
		ArrayList<Esporte> lista = new ArrayList<Esporte>();
		try {
			
			String sql = "select idesporte, esporte from esporte order by idesporte; ";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				Esporte e = new Esporte();
				
				e.setIdesporte(result.getInt(1));
				e.setNomeEsporte(result.getString(2));
				lista.add(e);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	

}
