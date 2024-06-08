package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CarrinhoController;
import controller.ClienteController;
import controller.ClienteJController;
import controller.ProdutoController;
import controller.TipoProdutoController;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumeroDePortaClienteFisico;
	private JTextField txtCPFClienteFisico;
	private JTextField txtNomeClienteFisico;
	private JTextField txtLogradouroClienteFisico;
	private JTextField txtComplementoClienteFisico;
	private JTextField txtCEPClienteFisico;
	private JTextField txtCelularClienteFisico;
	private JTextField txtTelefoneClienteJuridico;
	private JTextField txtCEPClienteJuridico;
	private JTextField txtComplementoClienteJuridico;
	private JTextField txtNumeroDePortaClienteJuridico;
	private JTextField txtLogradouroClienteJuridico;
	private JTextField txtNomeClienteJuridico;
	private JTextField txtCNPJ;
	private JTextField txtEmailJuridico;
	private JTextField txtTipoProduto;
	private JTextField txtCdgTipoProduto;
	private JTextField txtCdgProduto;
	private JTextField txtProduto;
	private JTextField txtValorProduto;
	private JTextField txtDescricaoProduto;
	private JTextField txtEstoqueProduto;
	private JTextField txtDescricaoTipoProduto;
	private JTextField txtQuantidadeCarrinho;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setTitle("Sistema Backoffice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1264, 681);
		contentPane.add(tabbedPane);

		JPanel tabClientesFisicos = new JPanel();
		tabbedPane.addTab("Clientes Fisicos", null, tabClientesFisicos, "Manter Clientes Fisicos");
		tabClientesFisicos.setLayout(null);

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(43, 60, 100, 23);
		tabClientesFisicos.add(lblCPF);

		JLabel lblNomeClienteFisico = new JLabel("Nome:");
		lblNomeClienteFisico.setBounds(43, 120, 100, 23);
		tabClientesFisicos.add(lblNomeClienteFisico);

		JLabel lblLogradouroClienteFisico = new JLabel("Logradouro:");
		lblLogradouroClienteFisico.setBounds(43, 180, 100, 23);
		tabClientesFisicos.add(lblLogradouroClienteFisico);

		JLabel lblCepClienteFisico = new JLabel("CEP:");
		lblCepClienteFisico.setBounds(43, 360, 100, 23);
		tabClientesFisicos.add(lblCepClienteFisico);

		JLabel lblComplementoClienteFisico = new JLabel("Complemento:");
		lblComplementoClienteFisico.setBounds(43, 300, 100, 23);
		tabClientesFisicos.add(lblComplementoClienteFisico);

		JLabel lblNumeroDePortaClienteFisico = new JLabel("Numero de Porta:");
		lblNumeroDePortaClienteFisico.setBounds(43, 240, 100, 23);
		tabClientesFisicos.add(lblNumeroDePortaClienteFisico);

		JLabel lblCelularFisico = new JLabel("Celular:");
		lblCelularFisico.setBounds(43, 420, 100, 23);
		tabClientesFisicos.add(lblCelularFisico);

		txtNumeroDePortaClienteFisico = new JTextField();
		txtNumeroDePortaClienteFisico.setBounds(153, 240, 250, 23);
		tabClientesFisicos.add(txtNumeroDePortaClienteFisico);
		txtNumeroDePortaClienteFisico.setColumns(10);

		txtCPFClienteFisico = new JTextField();
		txtCPFClienteFisico.setColumns(10);
		txtCPFClienteFisico.setBounds(153, 61, 250, 23);
		tabClientesFisicos.add(txtCPFClienteFisico);

		txtNomeClienteFisico = new JTextField();
		txtNomeClienteFisico.setColumns(10);
		txtNomeClienteFisico.setBounds(153, 121, 250, 23);
		tabClientesFisicos.add(txtNomeClienteFisico);

		txtLogradouroClienteFisico = new JTextField();
		txtLogradouroClienteFisico.setColumns(10);
		txtLogradouroClienteFisico.setBounds(153, 181, 250, 23);
		tabClientesFisicos.add(txtLogradouroClienteFisico);

		txtComplementoClienteFisico = new JTextField();
		txtComplementoClienteFisico.setColumns(10);
		txtComplementoClienteFisico.setBounds(153, 301, 250, 23);
		tabClientesFisicos.add(txtComplementoClienteFisico);

		txtCEPClienteFisico = new JTextField();
		txtCEPClienteFisico.setColumns(10);
		txtCEPClienteFisico.setBounds(153, 361, 250, 23);
		tabClientesFisicos.add(txtCEPClienteFisico);

		txtCelularClienteFisico = new JTextField();
		txtCelularClienteFisico.setColumns(10);
		txtCelularClienteFisico.setBounds(153, 421, 250, 23);
		tabClientesFisicos.add(txtCelularClienteFisico);

		JButton btnCadastrarClienteFisico = new JButton("Cadastrar Cliente Fisico");
		btnCadastrarClienteFisico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrarClienteFisico.setBounds(134, 492, 210, 37);
		tabClientesFisicos.add(btnCadastrarClienteFisico);

		JButton btnConsultarClienteFisico = new JButton("Consultar por CPF");
		btnConsultarClienteFisico.setBounds(496, 53, 151, 37);
		tabClientesFisicos.add(btnConsultarClienteFisico);

		JButton btnExcluirClienteFisico = new JButton("Excluir por CPF");
		btnExcluirClienteFisico.setBounds(694, 53, 151, 37);
		tabClientesFisicos.add(btnExcluirClienteFisico);

		JPanel tabClientesJuridicos = new JPanel();
		tabbedPane.addTab("Clientes Juridicos", null, tabClientesJuridicos, "Manter Clientes Juridicos");
		tabClientesJuridicos.setLayout(null);

		JLabel lblCNPJ = new JLabel("CNPJ:");
		lblCNPJ.setBounds(45, 63, 100, 23);
		tabClientesJuridicos.add(lblCNPJ);

		JLabel lblNomeFantasia = new JLabel("Nome Fantasia:");
		lblNomeFantasia.setBounds(45, 123, 100, 23);
		tabClientesJuridicos.add(lblNomeFantasia);

		JLabel lblLogradouroClienteJuridico = new JLabel("Logradouro:");
		lblLogradouroClienteJuridico.setBounds(45, 183, 100, 23);
		tabClientesJuridicos.add(lblLogradouroClienteJuridico);

		JLabel lblNumeroDePortaClienteJuridico = new JLabel("Numero de Porta:");
		lblNumeroDePortaClienteJuridico.setBounds(45, 243, 100, 23);
		tabClientesJuridicos.add(lblNumeroDePortaClienteJuridico);

		JLabel lblComplementoClienteJuridico = new JLabel("Complemento:");
		lblComplementoClienteJuridico.setBounds(45, 303, 100, 23);
		tabClientesJuridicos.add(lblComplementoClienteJuridico);

		JLabel lblCepClienteJuridico = new JLabel("CEP:");
		lblCepClienteJuridico.setBounds(45, 363, 100, 23);
		tabClientesJuridicos.add(lblCepClienteJuridico);

		JLabel lblTelefoneJuridico = new JLabel("Telefone:");
		lblTelefoneJuridico.setBounds(45, 423, 100, 23);
		tabClientesJuridicos.add(lblTelefoneJuridico);

		txtTelefoneClienteJuridico = new JTextField();
		txtTelefoneClienteJuridico.setColumns(10);
		txtTelefoneClienteJuridico.setBounds(155, 424, 250, 23);
		tabClientesJuridicos.add(txtTelefoneClienteJuridico);

		txtCEPClienteJuridico = new JTextField();
		txtCEPClienteJuridico.setColumns(10);
		txtCEPClienteJuridico.setBounds(155, 364, 250, 23);
		tabClientesJuridicos.add(txtCEPClienteJuridico);

		txtComplementoClienteJuridico = new JTextField();
		txtComplementoClienteJuridico.setColumns(10);
		txtComplementoClienteJuridico.setBounds(155, 304, 250, 23);
		tabClientesJuridicos.add(txtComplementoClienteJuridico);

		txtNumeroDePortaClienteJuridico = new JTextField();
		txtNumeroDePortaClienteJuridico.setColumns(10);
		txtNumeroDePortaClienteJuridico.setBounds(155, 243, 250, 23);
		tabClientesJuridicos.add(txtNumeroDePortaClienteJuridico);

		txtLogradouroClienteJuridico = new JTextField();
		txtLogradouroClienteJuridico.setColumns(10);
		txtLogradouroClienteJuridico.setBounds(155, 184, 250, 23);
		tabClientesJuridicos.add(txtLogradouroClienteJuridico);

		txtNomeClienteJuridico = new JTextField();
		txtNomeClienteJuridico.setColumns(10);
		txtNomeClienteJuridico.setBounds(155, 124, 250, 23);
		tabClientesJuridicos.add(txtNomeClienteJuridico);

		txtCNPJ = new JTextField();
		txtCNPJ.setColumns(10);
		txtCNPJ.setBounds(155, 64, 250, 23);
		tabClientesJuridicos.add(txtCNPJ);

		JButton btnConsultarClienteJuridico = new JButton("Consultar por CNPJ");
		btnConsultarClienteJuridico.setBounds(498, 56, 151, 37);
		tabClientesJuridicos.add(btnConsultarClienteJuridico);

		JButton btnExcluirClienteJuridico = new JButton("Excluir por CNPJ");
		btnExcluirClienteJuridico.setBounds(696, 56, 151, 37);
		tabClientesJuridicos.add(btnExcluirClienteJuridico);

		JButton btnCadastrarClienteJuridico = new JButton("Cadastrar Cliente Juridico");
		btnCadastrarClienteJuridico.setBounds(155, 545, 221, 37);
		tabClientesJuridicos.add(btnCadastrarClienteJuridico);

		JLabel lblEmailJuridico = new JLabel("Email:");
		lblEmailJuridico.setBounds(45, 483, 100, 23);
		tabClientesJuridicos.add(lblEmailJuridico);

		txtEmailJuridico = new JTextField();
		txtEmailJuridico.setColumns(10);
		txtEmailJuridico.setBounds(155, 484, 250, 23);
		tabClientesJuridicos.add(txtEmailJuridico);

		JPanel tabProdutos = new JPanel();
		tabbedPane.addTab("Produtos", null, tabProdutos, "Manter Produtos");
		tabProdutos.setLayout(null);

		JLabel lblCdgProduto = new JLabel("Codigo Identificador:");
		lblCdgProduto.setBounds(50, 68, 185, 28);
		tabProdutos.add(lblCdgProduto);

		JLabel lblNomeProduto = new JLabel("Produto:");
		lblNomeProduto.setBounds(50, 138, 185, 28);
		tabProdutos.add(lblNomeProduto);

		JLabel lblValorProduto = new JLabel("Valor:");
		lblValorProduto.setBounds(50, 208, 185, 28);
		tabProdutos.add(lblValorProduto);

		JLabel lblDescricaoProduto = new JLabel("Descricao:");
		lblDescricaoProduto.setBounds(50, 278, 185, 28);
		tabProdutos.add(lblDescricaoProduto);

		JLabel lblEstoqueProduto = new JLabel("Quantidade em Estoque:");
		lblEstoqueProduto.setBounds(50, 348, 185, 28);
		tabProdutos.add(lblEstoqueProduto);

		JButton btnCadastrarProduto = new JButton("Cadastrar");
		btnCadastrarProduto.setBounds(355, 473, 156, 53);
		tabProdutos.add(btnCadastrarProduto);

		JButton btnConsultarProduto = new JButton("Consultar por Codigo");
		btnConsultarProduto.setBounds(788, 43, 156, 53);
		tabProdutos.add(btnConsultarProduto);

		JButton btnExcluirProduto = new JButton("Excluir por Codigo");
		btnExcluirProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluirProduto.setBounds(1053, 43, 156, 53);
		tabProdutos.add(btnExcluirProduto);

		txtCdgProduto = new JTextField();
		txtCdgProduto.setColumns(10);
		txtCdgProduto.setBounds(228, 65, 482, 31);
		tabProdutos.add(txtCdgProduto);

		txtProduto = new JTextField();
		txtProduto.setColumns(10);
		txtProduto.setBounds(228, 135, 482, 31);
		tabProdutos.add(txtProduto);

		txtValorProduto = new JTextField();
		txtValorProduto.setColumns(10);
		txtValorProduto.setBounds(228, 205, 482, 31);
		tabProdutos.add(txtValorProduto);

		txtDescricaoProduto = new JTextField();
		txtDescricaoProduto.setColumns(10);
		txtDescricaoProduto.setBounds(228, 277, 482, 31);
		tabProdutos.add(txtDescricaoProduto);

		txtEstoqueProduto = new JTextField();
		txtEstoqueProduto.setColumns(10);
		txtEstoqueProduto.setBounds(228, 345, 482, 31);
		tabProdutos.add(txtEstoqueProduto);

		JLabel lblTpProduto = new JLabel("Tipo de Produto:");
		lblTpProduto.setBounds(50, 414, 185, 28);
		tabProdutos.add(lblTpProduto);

		JComboBox cbTpProduto = new JComboBox();
		cbTpProduto.setBounds(228, 417, 482, 25);
		tabProdutos.add(cbTpProduto);

		JTextArea taProduto = new JTextArea();
		taProduto.setBounds(788, 140, 400, 320);
		tabProdutos.add(taProduto);

		JPanel tabTipoProduto = new JPanel();
		tabbedPane.addTab("Tipos de Produtos", null, tabTipoProduto, "Manter Tipos de Produtos");
		tabTipoProduto.setLayout(null);

		JLabel lblNomeTiposProdutos = new JLabel("Tipo de Produto:");
		lblNomeTiposProdutos.setBounds(35, 150, 132, 31);
		tabTipoProduto.add(lblNomeTiposProdutos);

		JLabel lblDescricaoTiposProdutos = new JLabel("Descricao:");
		lblDescricaoTiposProdutos.setBounds(35, 245, 132, 31);
		tabTipoProduto.add(lblDescricaoTiposProdutos);

		txtTipoProduto = new JTextField();
		txtTipoProduto.setBounds(218, 150, 482, 31);
		tabTipoProduto.add(txtTipoProduto);
		txtTipoProduto.setColumns(10);

		JButton btnCadastrarTipoProduto = new JButton("Cadastrar");
		btnCadastrarTipoProduto.setBounds(218, 338, 156, 53);
		tabTipoProduto.add(btnCadastrarTipoProduto);

		JLabel lblCdgTipoProduto = new JLabel("Codigo Identificador:");
		lblCdgTipoProduto.setBounds(35, 68, 132, 31);
		tabTipoProduto.add(lblCdgTipoProduto);

		txtCdgTipoProduto = new JTextField();
		txtCdgTipoProduto.setColumns(10);
		txtCdgTipoProduto.setBounds(218, 68, 482, 31);
		tabTipoProduto.add(txtCdgTipoProduto);

		JButton btnConsultarTipoProduto = new JButton("Consultar por Codigo");
		btnConsultarTipoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarTipoProduto.setBounds(754, 56, 156, 53);
		tabTipoProduto.add(btnConsultarTipoProduto);

		JButton btnExcluirTipoProduto = new JButton("Excluir por Codigo");
		btnExcluirTipoProduto.setBounds(1004, 56, 156, 53);
		tabTipoProduto.add(btnExcluirTipoProduto);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(754, 136, 400, 300);
		tabTipoProduto.add(scrollPane_2);

		JTextArea taTipoProduto = new JTextArea();
		scrollPane_2.setViewportView(taTipoProduto);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(218, 234, 482, 53);
		tabTipoProduto.add(scrollPane_3);

		txtDescricaoTipoProduto = new JTextField();
		scrollPane_3.setViewportView(txtDescricaoTipoProduto);
		txtDescricaoTipoProduto.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 119, 345, 329);
		tabClientesFisicos.add(scrollPane);

		JTextArea taClienteFisico = new JTextArea();
		scrollPane.setViewportView(taClienteFisico);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(500, 119, 345, 329);
		tabClientesJuridicos.add(scrollPane_1);

		JTextArea taClienteJuridico = new JTextArea();
		scrollPane_1.setViewportView(taClienteJuridico);

		JPanel tabCarrinho = new JPanel();
		tabbedPane.addTab("Carrinho", null, tabCarrinho, "Montar Carrinho");
		tabCarrinho.setLayout(null);

		JLabel lblClienteCarrinho = new JLabel("Cliente: ");
		lblClienteCarrinho.setBounds(40, 68, 111, 29);
		tabCarrinho.add(lblClienteCarrinho);

		JLabel lblItemCarrinho = new JLabel("Item: ");
		lblItemCarrinho.setBounds(40, 151, 111, 29);
		tabCarrinho.add(lblItemCarrinho);

		JLabel lblQuantidadeCarrinho = new JLabel("Quantidade: ");
		lblQuantidadeCarrinho.setBounds(40, 230, 111, 29);
		tabCarrinho.add(lblQuantidadeCarrinho);

		JComboBox cbClienteCarrinho = new JComboBox();
		cbClienteCarrinho.setBounds(174, 69, 429, 26);
		tabCarrinho.add(cbClienteCarrinho);

		JComboBox cbItemCarrinho = new JComboBox();
		cbItemCarrinho.setBounds(174, 152, 429, 26);
		tabCarrinho.add(cbItemCarrinho);

		JButton btnAdicionarCarrinho = new JButton("Adicionar");
		btnAdicionarCarrinho.setBounds(268, 311, 159, 55);
		tabCarrinho.add(btnAdicionarCarrinho);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(40, 417, 588, 225);
		tabCarrinho.add(scrollPane_4);

		JTextArea taCarrinho = new JTextArea();
		scrollPane_4.setViewportView(taCarrinho);

		JButton btnRemoverCarrinho = new JButton("Remover");
		btnRemoverCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoverCarrinho.setBounds(689, 71, 159, 55);
		tabCarrinho.add(btnRemoverCarrinho);

		JButton btnCheckoutCarrinho = new JButton("Checkout");
		btnCheckoutCarrinho.setBounds(989, 71, 159, 55);
		tabCarrinho.add(btnCheckoutCarrinho);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(643, 167, 586, 223);
		tabCarrinho.add(scrollPane_5);

		JTextArea taCheckout = new JTextArea();
		scrollPane_5.setViewportView(taCheckout);

		JLabel lblCarrinho = new JLabel("Carrinho");
		lblCarrinho.setBounds(316, 388, 111, 29);
		tabCarrinho.add(lblCarrinho);

		JLabel lblCheckout = new JLabel("Checkout");
		lblCheckout.setBounds(901, 138, 111, 29);
		tabCarrinho.add(lblCheckout);

		txtQuantidadeCarrinho = new JTextField();
		txtQuantidadeCarrinho.setBounds(174, 234, 429, 26);
		tabCarrinho.add(txtQuantidadeCarrinho);
		txtQuantidadeCarrinho.setColumns(10);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(645, 418, 584, 221);
		tabCarrinho.add(scrollPane_6);

		JTextArea taVendasCarrinho = new JTextArea();
		scrollPane_6.setViewportView(taVendasCarrinho);

		JButton btnListarProdutos = new JButton("Listar Produtos");
		btnListarProdutos.setBounds(554, 473, 156, 53);
		tabProdutos.add(btnListarProdutos);

		ClienteController cF = new ClienteController(txtCPFClienteFisico, txtNomeClienteFisico,
				txtLogradouroClienteFisico, txtNumeroDePortaClienteFisico, txtComplementoClienteFisico,
				txtCEPClienteFisico, txtCelularClienteFisico, taClienteFisico);

		btnCadastrarClienteFisico.addActionListener(cF);
		btnConsultarClienteFisico.addActionListener(cF);
		btnExcluirClienteFisico.addActionListener(cF);

		ClienteJController cJ = new ClienteJController(txtCNPJ, txtNomeClienteJuridico, txtLogradouroClienteJuridico,
				txtNumeroDePortaClienteJuridico, txtComplementoClienteJuridico, txtCEPClienteJuridico,
				txtTelefoneClienteJuridico, txtEmailJuridico, taClienteJuridico);

		btnConsultarClienteJuridico.addActionListener(cJ);
		btnExcluirClienteJuridico.addActionListener(cJ);
		btnCadastrarClienteJuridico.addActionListener(cJ);

		TipoProdutoController tp = new TipoProdutoController(txtCdgTipoProduto, txtTipoProduto, txtDescricaoTipoProduto,
				taTipoProduto);

		btnCadastrarTipoProduto.addActionListener(tp);
		btnConsultarTipoProduto.addActionListener(tp);
		btnExcluirTipoProduto.addActionListener(tp);

		ProdutoController p = new ProdutoController(txtCdgProduto, txtProduto, txtValorProduto, txtDescricaoProduto,
				cbTpProduto, taProduto, txtEstoqueProduto);
		
		btnListarProdutos.addActionListener(p);
		btnCadastrarProduto.addActionListener(p);
		btnConsultarProduto.addActionListener(p);
		btnExcluirProduto.addActionListener(p);

		CarrinhoController c = new CarrinhoController(cbClienteCarrinho, cbItemCarrinho, txtQuantidadeCarrinho,
				taCarrinho, taCheckout, taVendasCarrinho);

		btnAdicionarCarrinho.addActionListener(c);
		btnRemoverCarrinho.addActionListener(c);
		btnCheckoutCarrinho.addActionListener(c);
	}
}
