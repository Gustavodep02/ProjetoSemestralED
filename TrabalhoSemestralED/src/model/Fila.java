package model;
import model.NoFila;
public class Fila<T> {
	NoFila<T> inicio;
	NoFila<T> fim;
	
	public Fila() {
        inicio = null;
        fim = null;
    }
	
	public boolean isEmpty() {
        if (inicio == null && fim == null) {
            return true;
        }
        return false;
    }
	public void insert(T valor) {
        NoFila<T> elemento = new NoFila<>();
        elemento.dado = valor;
        elemento.proximo = null;
        if (inicio == null) {
            inicio = elemento;
            fim = elemento;
            elemento.proximo = null;
        } else {
        	if (inicio == fim && inicio != null) {
				fim = elemento;
				inicio.proximo = fim;
				fim.proximo = null;
			}else {	
            fim.proximo = elemento;
            elemento.proximo = null;
            fim = elemento;
        }}
    }
	
	public T remove() throws Exception {
        if(isEmpty()) {
            throw new Exception("Fila vazia");
        }
        NoFila<T> auxiliar = inicio;
        if (inicio == fim && inicio != null) {
            inicio = null;
            fim = null;
        } else {
        	inicio = inicio.proximo;
        }
        System.out.println("Removido: " + auxiliar.dado.toString());
        return auxiliar.dado;

	}
	public void list() throws Exception{
		if(isEmpty()) {
            throw new Exception("Fila vazia");
	}
		NoFila<T> auxiliar = inicio;
		while(auxiliar != null) {
            System.out.println(auxiliar.toString()+"=>");
            auxiliar = auxiliar.proximo;
        }
		System.out.println("NULL");
}
	public int size(){
		int cont = 0;
		if(!isEmpty()) {
			NoFila<T> auxiliar = inicio;
            while(auxiliar != null) {
                cont++;
                auxiliar = auxiliar.proximo;
            }
		}
        return cont;    
	}
}
