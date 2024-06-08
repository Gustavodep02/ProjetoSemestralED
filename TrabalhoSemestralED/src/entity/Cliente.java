package entity;

public class Cliente {
	public String cpf;
	public String nome;
	public String logradouro;
	public String numeroPorta;
	public String complemento;
	public String cep;
	public String celular;
	

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", logradouro=" + logradouro + ", numeroPorta=" + numeroPorta
				+ ", complemento=" + complemento + ", cep=" + cep + ", telefone=" + celular + ", cpf=" + cpf + "]";
	}
	

	
}