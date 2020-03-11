package view.tables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.Grupo;
import model.GrupoTipo;

public class GrupoTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
	private static final int COL_TIPO = 1;
	private static final int COL_DESCRICAO = 2;
	private static final int COL_DATA_CRIACAO = 3;
	private static final int COL_REGIAO = 4;
	private static final int COL_ESPORTE = 5;

	private List<Grupo> valores;

	//Esse é um construtor, que recebe a nossa lista de clientes
	public GrupoTableModel(List<Grupo> valores) {
		this.valores = new ArrayList<Grupo>(valores);
	}

	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, só 4.
		return 6;
	}

	public String getColumnName(int column) {
		//Qual é o nome das nossas colunas?
		if (column == COL_NOME) return "Nome";
		if (column == COL_TIPO) return "Tipo";
		if (column == COL_DESCRICAO) return "Descrição";
		if (column == COL_DATA_CRIACAO) return "Data de Criação";
		if (column == COL_REGIAO) return "Região";
		if (column == COL_ESPORTE) return "Esporte";
		
		return ""; //Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		//Precisamos retornar o valor da coluna column e da linha row.
		Grupo grupo = valores.get(row);
		if (column == COL_NOME)
			return grupo.getNome();
		else 
			if (column == COL_TIPO) 
				return grupo.getEnumTipo();
			else
				if (column == COL_DESCRICAO)
					return grupo.getDescricao();
				else
					if (column == COL_DATA_CRIACAO)
						return grupo.getDataCriacao();
					else
						if (column == COL_REGIAO)
							return grupo.getReigao().getNomeRegiao();
						else
							if (column == COL_ESPORTE)
								return grupo.getEsporte().getNomeEsporte();
		return ""; //Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Grupo grupo = valores.get(rowIndex);
		//Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no parï¿½metro.
		//Note que vc poderia alterar 2 campos ao invï¿½s de um sï¿½.
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (columnIndex == COL_NOME)
			grupo.setNome(aValue.toString());
		else 
			if (columnIndex == COL_TIPO) 
				grupo.setEnumTipo(GrupoTipo.valueOf(aValue.toString()));
			else
				if (columnIndex == COL_DESCRICAO)
					grupo.setDescricao(aValue.toString());
				else
					if (columnIndex == COL_DATA_CRIACAO)
						try {
							grupo.setDataCriacao(sdf.parse(aValue.toString()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else
						if (columnIndex == COL_REGIAO)
							grupo.getReigao().setNomeRegiao(aValue.toString());
						else
							if (columnIndex == COL_ESPORTE)
								grupo.getEsporte().setNomeEsporte(aValue.toString());
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
	public Grupo get(int row) {
		return valores.get(row);
	}
}

