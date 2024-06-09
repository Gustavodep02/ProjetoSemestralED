package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Produto;

public class ProdutoController implements ActionListener {
	private JTextField id;
	private JTextField nome;
	private JTextField valor;
	private JTextField descricao;
	private JComboBox idTipo;
	private JTextArea taProduto;
	private JTextField quantidade;
	private HashMap<Integer, List<Integer>> produtosPorTipo = new HashMap<>();

	public ProdutoController(JTextField id, JTextField nome, JTextField valor, JTextField descricao, JComboBox idTipo,
			JTextArea taProduto, JTextField quantidade) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		this.idTipo = idTipo;
		this.taProduto = taProduto;
		this.quantidade = quantidade;

		try {
			adicionarTipoProduto();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrarProduto() throws Exception {
		Produto produto = new Produto();
		if (id.getText().length() == 0) {
            taProduto.setText("Codigo Identificador Invalido!");
            return;
        } else if (nome.getText().length() == 0) {
            taProduto.setText("Nome Invalido!");
            return;
        } else if (valor.getText().length() == 0 || !valor.getText().matches("[0-9]+")) {
            taProduto.setText("Valor Invalido!");
            return;
        } else if (descricao.getText().length() == 0) {
            taProduto.setText("Descricao Invalida!");
            return;
        } else if (quantidade.getText().length() == 0 || !quantidade.getText().matches("[0-9]+")) {
            taProduto.setText("Quantidade Invalida!");
            return;
        }
		produto.id = id.getText();
		produto.nome = nome.getText();
		produto.valor = valor.getText();
		produto.descricao = descricao.getText();
		String[] tipo = idTipo.getSelectedItem().toString().split(" ");
		produto.idTipo = ";" + tipo[0];
		produto.quantidade = quantidade.getText();

		String linha = criarLinhaProduto(produto);

		File arq = new File("./Produto.csv");
		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha1 = buffer.readLine();
		linha1 = buffer.readLine();
		while (linha1 != null) {
			String[] dados = linha1.split(";");
			if (dados[0].equals(id.getText().toString())) {
				taProduto.setText("Produto ja cadastrado!");
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
		taProduto.setText("Produto Cadastrado!");
	}

	public void ConsultarProduto() throws Exception {
		File arq = new File("./Produto.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			while (linha != null) {
				String[] dados = linha.split(";");
				if (dados[0].equals(id.getText().toString())) {
					taProduto.setText(
							"ID: " + dados[0] + "\nNome: " + dados[1] + "\nValor: " + dados[2] + "\nDescricao: "
									+ dados[3] + "\nQuantidade: " + dados[4] + "\nTipo de Produto: " + dados[5]);
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
		taProduto.setText("Produto Nao Encontrado!");
	}

	public void ExcluirProduto() throws Exception {
		File arq = new File("./Produto.csv");

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

		boolean produtoRemovido = false;

		while (linha != null) {
			String[] dados = linha.split(";");
			if (!dados[0].equals(id.getText().toString())) {
				novoConteudo.append(linha).append("\r\n");
			} else {
				produtoRemovido = true;
			}
			linha = buffer.readLine();
		}

		buffer.close();
		leitor.close();
		fluxo.close();

		if (produtoRemovido) {
			FileWriter fw = new FileWriter(arq);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(novoConteudo.toString());
			pw.flush();
			pw.close();
			fw.close();
			taProduto.setText("Produto Removido!");
		} else {
			taProduto.setText("Produto Nao Encontrado!");
		}
	}

	public void adicionarTipoProduto() throws IOException {
		File arq = new File("./tipoproduto.csv");
		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		linha = buffer.readLine();
		while (linha != null) {
			String valor = linha.replaceAll(";", " | ");
			idTipo.addItem(valor);
			linha = buffer.readLine();
		}

		buffer.close();
		leitor.close();
		fluxo.close();
	}

	public String criarLinhaProduto(Produto produto) {
		return produto.id + ";" + produto.nome + ";" + produto.valor + ";" + produto.descricao + ";"
				+ produto.quantidade + ";" + produto.idTipo;
	}

	public void listarProduto() throws Exception {
		File arq = new File("./Produto.csv");
		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String[] id = idTipo.getSelectedItem().toString().split(" | ");
		int codigo = Integer.parseInt(id[0]);
		String linha = buffer.readLine();
		linha = buffer.readLine();
		HashMap<Integer, List<Integer>> produtosPorTipo = new HashMap<>();
		while (linha != null) {
			String[] dados = linha.split(";");
			if (codigo == Integer.parseInt(dados[5])) {
				int codigoProduto = Integer.parseInt(dados[0]);
				produtosPorTipo.putIfAbsent(codigo, new ArrayList<>());
				produtosPorTipo.get(codigo).add(codigoProduto);
			}
			linha = buffer.readLine();
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		if (produtosPorTipo.containsKey(codigo)) {
			taProduto.setText(produtosPorTipo.get(codigo).toString());
		} else {
			taProduto.setText("Nenhum produto deste tipo cadastrado!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("Cadastrar".equals(cmd)) {
			try {
				cadastrarProduto();
			} catch (Exception e1) {
				taProduto.setText(e1.getMessage());
			}
		} else if ("Consultar por Codigo".equals(cmd)) {
			try {
				ConsultarProduto();
			} catch (Exception e1) {
				taProduto.setText(e1.getMessage());
			}
		} else if ("Excluir por Codigo".equals(cmd)) {
			try {
				ExcluirProduto();
			} catch (Exception e1) {
				taProduto.setText(e1.getMessage());
			}
		} else if ("Listar Produtos".equals(cmd)) {
			try {
				listarProduto();
			} catch (Exception e1) {
				taProduto.setText(e1.getMessage());
			}
		}

	}
}
