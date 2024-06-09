package model;

public class Pilha {
	No topo;

	public Pilha() {
		topo = null;
	}

	public boolean isEmpty() {
		if (topo == null) {
			return true;
		} else {
			return false;
		}
	}

	public void push(String e) {
		No elemento = new No();
		elemento.dado = e;
		if (isEmpty()) {
			topo = elemento;
		} else {
			elemento.proximo = topo;
			topo = elemento;
		}
	}

	public String pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("Pilha vazia");
		} else {
			String e = topo.dado;
			topo = topo.proximo;
			return e;
		}
	}

	public String top() throws Exception {
		if (isEmpty()) {
			throw new Exception("Pilha vazia");
		} else {
			return topo.dado;
		}
	}

	public int size() {
		int cont = 0;
		No aux = topo;
		while (aux != null) {
			cont++;
			aux = aux.proximo;
		}
		return cont;
	}
}