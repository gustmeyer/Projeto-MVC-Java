package view.tables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.Grupo;
import model.GrupoTipo;
import model.Usuario;

public class UsuariosDoGrupoTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_IDUSER = 0;
	private static final int COL_NOME = 1;
	private static final int COL_TIPO = 2;

	private List<Usuario> valores;

	//Esse é um construtor, que recebe a nossa lista de clientes
	public UsuariosDoGrupoTableModel(List<Usuario> valores) {
		this.valores = new ArrayList<Usuario>(valores);
	}

	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, só 4.
		return 3;
	}

	public String getColumnName(int column) {
		//Qual é o nome das nossas colunas?
		if (column == COL_NOME) return "Nome";
		if (column == COL_IDUSER) return "ID User";
		if (column == COL_TIPO) return "Tipo Usuario";
		
		return ""; //Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		//Precisamos retornar o valor da coluna column e da linha row.
		Usuario user = valores.get(row);
		if (column == COL_NOME)
			return user.getNome();
					else
						if (column == COL_IDUSER)
							return user.getIdusuario();
						else
							if (column == COL_TIPO)
								return user.getTipoUsuarioGrupo();
		return ""; //Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Usuario user = valores.get(rowIndex);
		//Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no parï¿½metro.
		//Note que vc poderia alterar 2 campos ao invï¿½s de um sï¿½.
		
		if (columnIndex == COL_NOME)
			user.setNome(aValue.toString());
					else
						if (columnIndex == COL_IDUSER)
							user.setIdusuario(Integer.parseInt(aValue.toString()));
						else
							if (columnIndex == COL_TIPO)
								user.setTipoUsuarioGrupo(aValue.toString());
	}

	public Class<?> getColumnClass(int columnIndex) {
		//Qual a classe das nossas colunas? Como estamos exibindo texto, ï¿½ string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//Indicamos se a célula da rowIndex e da columnIndex é editável. Nossa tabela toda ï¿½.
		return true;
	}
	//Já que esse tableModel é de clientes, vamos fazer um get que retorne um objeto cliente inteiro.
	//Isso elimina a necessidade de chamar o getValueAt() nas telas. 
	public Usuario get(int row) {
		return valores.get(row);
	}
}

