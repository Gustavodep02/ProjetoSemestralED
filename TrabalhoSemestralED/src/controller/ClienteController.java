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
		if(cpf.getText().length() != 11 || !cpf.getText().matches("[0-9]+")) {
            taClienteFisico.setText("CPF Invalido!");
            return;
        }else if(celular.getText().length() != 11 || !celular.getText().matches("[0-9]+")) {
            taClienteFisico.setText("Celular Invalido!");
            return;
        }else if(cepF.getText().length() != 8|| !cepF.getText().matches("[0-9]+")) {
            taClienteFisico.setText("CEP Invalido!");
            return;
        }else if(numeroPortaF.getText().length() == 0|| !numeroPortaF.getText().matches("[0-9]+")) {
            taClienteFisico.setText("Numero de Porta Invalido!");
            return;
        }else if(logradouroF.getText().length() == 0) {
            taClienteFisico.setText("Logradouro Invalido!");
            return;
        }else if(nomeF.getText().length() == 0) {
        	taClienteFisico.setText("Nome Invalido!");
            return;
        }else if(complementoF.getText().length() == 0) {
        	taClienteFisico.setText("Complemento Invalido!");
            return;
        }
		
		cliente.cpf = cpf.getText();
		cliente.nome = nomeF.getText();
		cliente.logradouro = logradouroF.getText();
		cliente.numeroPorta = numeroPortaF.getText();
		cliente.complemento = complementoF.getText();
		cliente.cep = cepF.getText();
		cliente.celular = celular.getText();
		
		String linha = criarLinhaCliente(cliente);

		File arq = new File("./Cliente.csv");
		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha1 = buffer.readLine();
		linha1 = buffer.readLine();
		while (linha1 != null) {
			String[] dados = linha1.split(";");
			if (dados[0].equals(cpf.getText().toString())) {
				taClienteFisico.setText("Cliente ja cadastrado!");
				buffer.close();
				leitor.close();
				fluxo.close();
				return;
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
		File arq = new File("./Cliente.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			while (linha != null) {
				String[] dados = linha.split(";");
				if (dados[0].equals(cpf.getText().toString())) {
					taClienteFisico.setText("CPF: " + dados[0] + "\nNome: " + dados[1] + "\nLogradouro: " + dados[2]
							+ "\nNumero de Porta: " + dados[3] + "\nComplemento: " + dados[4] + "\nCEP: " + dados[5]
							+ "\nCelular: " + linha.split(";")[6]);
					buffer.close();
					leitor.close();
					fluxo.close();
					return;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new Exception("Arquivo inexistente");
		}
		taClienteFisico.setText("Cliente Nao Encontrado!");
	}

	public void excluirClienteFisico() throws Exception {

		File arq = new File("./Cliente.csv");

		if (!arq.exists() || !arq.isFile()) {
			throw new Exception("Arquivo inexistente");
		}

		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		StringBuilder novoConteudo = new StringBuilder();
		String linha = buffer.readLine();

		if (linha != null) {
			novoConteudo.append(linha).append("\n");
			linha = buffer.readLine();
		}

		boolean clienteRemovido = false;

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