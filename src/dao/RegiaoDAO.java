package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Regiao;
import util.ConnectionUtil;

public class RegiaoDAO {
	
private static Connection con = ConnectionUtil.getConnection();
	
	public List<Regiao> montarRegioes() {
		ArrayList<Regiao> lista = new ArrayList<Regiao>();
		try {
			
			String sql = "select idregiao, regiao from regiao order by idregiao; ";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				Regiao e = new Regiao();
				
				e.setIdregiao(result.getInt(1));
				e.setNomeRegiao(result.getString(2));
				lista.add(e);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

}
