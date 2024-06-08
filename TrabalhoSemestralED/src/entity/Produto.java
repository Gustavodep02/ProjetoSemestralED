package entity;

public class Produto {
	public String id;
	public String nome;
	public String valor;
	public String idTipo;
	public String descricao;
	public String quantidade;
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", idTipo=" + idTipo + ", descricao="
				+ descricao + "]";
	}
	
}
