package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Produto;
import entity.TipoProduto;

public class TipoProdutoController implements ActionListener {
	private JTextField id;
	private JTextField tipo;
	private JTextField descricao;
	private JTextArea taTipoProduto;

	public TipoProdutoController(JTextField id, JTextField tipo, JTextField descricao, JTextArea taTipoProduto) {
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.taTipoProduto = taTipoProduto;

	}

	public void cadastrarTipoProduto() throws Exception {
		TipoProduto tp = new TipoProduto();
		tp.id = id.getText();
		tp.tipo = tipo.getText();
		tp.descricao = descricao.getText();

		// codigo para adicionar o tipo de produto ao csv

		String linha = criarLinhaTipoProduto(tp);

		File arq = new File("C:\\TEMP", "tipoproduto.csv"); // mudar o local
		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha1 = buffer.readLine(); // recebe a linha
		linha1 = buffer.readLine();// pula a primeira linha
		while (linha1 != null) { // realiza a busca em todo o csv ate o fim do arquivo
			String[] dados = linha1.split(";");
			if (dados[0].equals(id.getText().toString())) {
				taTipoProduto.setText("Tipo de Produto ja cadastrado!");
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
		taTipoProduto.setText("Tipo de Produto Cadastrado!");
	}

	public void excluirTipoProduto() throws Exception {
		File arq = new File("C:\\TEMP", "tipoproduto.csv"); // mudar o local

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

		boolean tipoRemovido = false;

		// Le cada linha e adiciona ao novo conteúdo, exceto a linha a ser excluída
		while (linha != null) {
			String[] dados = linha.split(";");
			if (!dados[0].equals(id.getText().toString())) {
				novoConteudo.append(linha).append("\r\n");
			} else {
				tipoRemovido = true;
			}
			linha = buffer.readLine();
		}

		buffer.close();
		leitor.close();
		fluxo.close();

		// Se o tipo de produto foi removido, reescreve o arquivo
		if (tipoRemovido) {
			FileWriter fw = new FileWriter(arq);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(novoConteudo.toString());
			pw.flush();
			pw.close();
			fw.close();
			taTipoProduto.setText("Tipo de Produto Removido!");
		} else {
			taTipoProduto.setText("Tipo de Produto Nao Encontrado!");
		}
	}

	public void consultarTipoProduto() throws Exception {
		// codigo para buscar o tipo de produto no csv
		File arq = new File("C:\\TEMP", "tipoproduto.csv"); // mudar o local
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine(); // recebe a linha
			linha = buffer.readLine();// pula a primeira linha
			while (linha != null) { // realiza a busca em todo o csv ate o fim do arquivo
				String[] dados = linha.split(";");
				if (dados[0].equals(id.getText().toString())) {
					taTipoProduto.setText(
							"Codigo Identificador: " + dados[0] + "\nTipo: " + dados[1] + "\nDescricao: " + dados[2]);
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
		taTipoProduto.setText("Tipo de Produto Nao Encontrado!"); // caso nao encontre seta o texto para Tipo de
																	// Produto Nao Encontrado
	}

	public String criarLinhaTipoProduto(TipoProduto tp) {
		return tp.id + ";" + tp.tipo + ";" + tp.descricao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Cadastrar")) {
			try {
				cadastrarTipoProduto();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (cmd.equals("Consultar por Codigo")) {
			try {
				consultarTipoProduto();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (cmd.equals("Excluir por Codigo")) {
			try {
				excluirTipoProduto();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}
}