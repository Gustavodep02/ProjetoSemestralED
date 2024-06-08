package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Cliente_J;

public class ClienteJController implements ActionListener{
	private JTextField cnpj;
	private JTextField nomeJ;
	private JTextField logradouroJ;
	private JTextField numeroPortaJ;
	private JTextField complementoJ;
	private JTextField cepJ;
	private JTextField telefoneJ;
	private JTextField email;
	private JTextArea taClienteJuridico;
	
	public ClienteJController(JTextField cnpj, JTextField nomeJ, JTextField logradouroJ, JTextField numeroPortaJ, JTextField complementoJ, JTextField cepJ,
			JTextField telefoneJ, JTextField email, JTextArea taClienteJuridico) {
		this.cnpj = cnpj;
		this.nomeJ = nomeJ;
		this.logradouroJ = logradouroJ;
		this.numeroPortaJ = numeroPortaJ;
		this.complementoJ = complementoJ;
		this.cepJ = cepJ;
		this.telefoneJ = telefoneJ;
		this.email = email;
		this.taClienteJuridico = taClienteJuridico;
	}
	public void cadastrarClienteJuridico() throws Exception {
		Cliente_J cliente = new Cliente_J();
		cliente.cnpj = cnpj.getText();
		cliente.nome = nomeJ.getText();
		cliente.logradouro = logradouroJ.getText();
		cliente.numeroPorta = numeroPortaJ.getText();
		cliente.complemento = complementoJ.getText();
		cliente.cep = cepJ.getText();
		cliente.telefone = telefoneJ.getText();
		cliente.email = email.getText();

		// codigo para adicionar o cliente ao csv

		String linha = criarLinhaClienteJuridico(cliente);

		File arq = new File("C:\\TEMP", "Cliente_juridico.csv"); // mudar o local
		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha1 = buffer.readLine(); // recebe a linha
		linha1 = buffer.readLine();// pula a primeira linha
		while (linha1 != null) { // realiza a busca em todo o csv ate o fim do arquivo
			String[] dados = linha1.split(";");
			if (dados[0].equals(cnpj.getText().toString())) {
				taClienteJuridico.setText("Cliente ja cadastrado!");
				buffer.close();
				leitor.close();
				fluxo.close();
				return; // caso encontre finaliza a funcao
			}
			linha1 = buffer.readLine();
		}
		FileWriter fw = new FileWriter(arq, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.write("\r\n" + linha);
		pw.flush();
		pw.close();
		fw.close();
		taClienteJuridico.setText("Cliente Cadastrado!");
	}
	
	public void consultarClienteJuridico() throws Exception {
		// codigo para buscar o cliente no csv
				File arq = new File("C:\\TEMP", "Cliente_juridico.csv"); // mudar o local
				if (arq.exists() && arq.isFile()) {
					FileInputStream fluxo = new FileInputStream(arq);
					InputStreamReader leitor = new InputStreamReader(fluxo);
					BufferedReader buffer = new BufferedReader(leitor);
					String linha = buffer.readLine(); // recebe a linha
					linha = buffer.readLine();// pula a primeira linha
					while (linha != null) { // realiza a busca em todo o csv ate o fim do arquivo
						String[] dados = linha.split(";");
						if (dados[0].equals(cnpj.getText().toString())) {
							taClienteJuridico.setText("CNPJ: " + dados[0] + "\nNome Fantasia: " + dados[1] + "\nLogradouro: " + dados[2]
									+ "\nNumero de Porta: " + dados[3] + "\nComplemento: " + dados[4] + "\nCEP: " + dados[5]
									+ "\nTelefone: " + linha.split(";")[6]+ "\nEmail: " + linha.split(";")[7]);
							buffer.close();
							leitor.close();
							fluxo.close();
							return; // caso encontre retorna o cliente
						}
						linha = buffer.readLine();
					}

				} else {
					throw new Exception("Arquivo inexistente");
				}
				taClienteJuridico.setText("Cliente Nao Encontrado!"); // caso nao encontre seta o texto para Cliente Nao
																	// Encontrado
	}
	
	public void excluirClienteJuridico() throws Exception {
		File arq = new File("C:\\TEMP", "Cliente_juridico.csv"); // mudar o local

		if (!arq.exists() || !arq.isFile()) {
			throw new Exception("Arquivo inexistente");
		}

		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		StringBuilder novoConteudo = new StringBuilder();
		String linha = buffer.readLine();

		// Mantem a primeira linha (cabeçalho) no novo conteúdo
		if (linha != null) {
			novoConteudo.append(linha).append("\n");
			linha = buffer.readLine();
		}

		boolean clienteRemovido = false;

		// Le cada linha e adiciona ao novo conteúdo, exceto a linha a ser excluída
		while (linha != null) {
			String[] dados = linha.split(";");
			if (!dados[0].equals(cnpj.getText().toString())) {
				novoConteudo.append(linha).append("\r\n");
			} else {
				clienteRemovido = true;
			}
			linha = buffer.readLine();
		}

		buffer.close();
		leitor.close();
		fluxo.close();

		// Se o cliente foi removido, reescreve o arquivo
		if (clienteRemovido) {
			FileWriter fw = new FileWriter(arq);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(novoConteudo.toString());
			pw.flush();
			pw.close();
			fw.close();
			taClienteJuridico.setText("Cliente Removido!");
		} else {
			taClienteJuridico.setText("Cliente Nao Encontrado!");
		}
	}
	public String criarLinhaClienteJuridico(Cliente_J cliente) {
		return cliente.cnpj + ";" + cliente.nome + ";" + cliente.logradouro + ";"
				+ cliente.numeroPorta + ";" + cliente.complemento + ";" + cliente.cep + ";"
				+ cliente.telefone + ";" + cliente.email;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Cadastrar Cliente Juridico")) {
			try {
				cadastrarClienteJuridico();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Consultar por CNPJ")) {
			try {
				consultarClienteJuridico();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if (cmd.equals("Excluir por CNPJ")) {
			try {
				excluirClienteJuridico();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
		
}
