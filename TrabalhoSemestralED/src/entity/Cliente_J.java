package entity;

public class Cliente_J {
	public String cnpj;
	public String nome;
	public String logradouro;
	public String numeroPorta;
	public String complemento;
	public String cep;
	public String telefone;
	public String email;

	@Override
	public String toString() {
		return "Cliente_J [nome=" + nome + ", logradouro=" + logradouro + ", numeroPorta=" + numeroPorta
				+ ", complemento=" + complemento + ", cep=" + cep + ", telefone=" + telefone + ", email=" + email
				+ ", cnpj=" + cnpj + "]";
	}

}