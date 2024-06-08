package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Cliente;

public class ClienteController implements ActionListener {

	private JTextField cpf;
	private JTextField nomeF;
	private JTextField logradouroF;
	private JTextField numeroPortaF;
	private JTextField complementoF;
	private JTextField cepF;
	private JTextField celular;
	private JTextArea taClienteFisico;

	public ClienteController(JTextField cpf, JTextField nomeF, JTextField logradouroF, JTextField numeroPortaF,
			JTextField complementoF, JTextField cepF, JTextField celular, JTextArea taClienteFisico) {
		this.cpf = cpf;
		this.nomeF = nomeF;
		this.logradouroF = logradouroF;
		this.numeroPortaF = numeroPortaF;
		this.complementoF = complementoF;
		this.cepF = cepF;
		this.celular = celular;
		this.taClienteFisico = taClienteFisico;
	}

	public void cadastrarClienteFisico() throws Exception {
		Cliente cliente = new Cliente();
		cliente.cpf = cpf.getText();
		cliente.nome = nomeF.getText();
		cliente.logradouro = logradouroF.getText();
		cliente.numeroPorta = numeroPortaF.getText();
		cliente.complemento = complementoF.getText();
		cliente.cep = cepF.getText();
		cliente.celular = celular.getText();

		// codigo para adicionar o cliente ao csv

		String linha = criarLinhaCliente(cliente);

		File arq = new File("C:\\TEMP", "Cliente.csv"); // mudar o local
		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha1 = buffer.readLine(); // recebe a linha
		linha1 = buffer.readLine();// pula a primeira linha
		while (linha1 != null) { // realiza a busca em todo o csv ate o fim do arquivo
			String[] dados = linha1.split(";");
			if (dados[0].equals(cpf.getText().toString())) {
				taClienteFisico.setText("Cliente ja cadastrado!");
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
		taClienteFisico.setText("Cliente Cadastrado!");
	}

	public void consultarClienteFisico() throws Exception {
		// codigo para buscar o cliente no csv
		File arq = new File("C:\\TEMP", "Cliente.csv"); // mudar o local
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine(); // recebe a linha
			linha = buffer.readLine();// pula a primeira linha
			while (linha != null) { // realiza a busca em todo o csv ate o fim do arquivo
				String[] dados = linha.split(";");
				if (dados[0].equals(cpf.getText().toString())) {
					taClienteFisico.setText("CPF: " + dados[0] + "\nNome: " + dados[1] + "\nLogradouro: " + dados[2]
							+ "\nNumero de Porta: " + dados[3] + "\nComplemento: " + dados[4] + "\nCEP: " + dados[5]
							+ "\nCelular: " + linha.split(";")[6]);
					buffer.close();
					leitor.close();
					fluxo.close();
					return; // caso encontre retorna o cliente
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new Exception("Arquivo inexistente");
		}
		taClienteFisico.setText("Cliente Nao Encontrado!"); // caso nao encontre seta o texto para Cliente Nao
															// Encontrado
	}

	public void excluirClienteFisico() throws Exception {

		File arq = new File("C:\\TEMP", "Cliente.csv"); // mudar o local

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
			if (!dados[0].equals(cpf.getText().toString())) {
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
			taClienteFisico.setText("Cliente Removido!");
		} else {
			taClienteFisico.setText("Cliente Nao Encontrado!");
		}
	}

	public String criarLinhaCliente(Cliente cliente) {
		return cliente.cpf + ";" + cliente.nome + ";" + cliente.logradouro + ";" + cliente.numeroPorta + ";"
				+ cliente.complemento + ";" + cliente.cep + ";" + cliente.celular;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Cadastrar Cliente Fisico")) {
			try {
				cadastrarClienteFisico();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (cmd.equals("Consultar por CPF")) {
			try {
				consultarClienteFisico();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (cmd.equals("Excluir por CPF")) {
			try {
				excluirClienteFisico();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}