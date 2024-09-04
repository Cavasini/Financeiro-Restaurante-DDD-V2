package br.com.restaurante.financeiro.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.restaurante.financeiro.exception.RestauranteException;

public class Comanda {
	private int numero;
	private List<Pedido> pedidos;
	private double total = 0.0;
	
	public Comanda(int numero) {
		pedidos = new ArrayList();
		this.numero = numero;
	}
	
	public void adicionarPedido(Pedido pedido) throws RestauranteException {
				
        if (pedido.getItem() == null || pedido.getItem().trim().isEmpty()) {
            throw new RestauranteException("Item não pode ser nulo ou vazio.");
        }
        if (pedido.getQuantidade() <= 0) {
            throw new RestauranteException("A quantidade deve ser maior que zero.");
        }
        if (pedido.getPrecoUnitario() <= 0) {
            throw new RestauranteException("O preço unitário deve ser maior que zero.");
        }        
    		pedidos.add(pedido);
    		total+= pedido.getTotal();
	}
	

	public void limparComanda() throws RestauranteException {
		if(pedidos.size() == 0) {
			throw new RestauranteException("Essa comanda já está vazia!");
		}
		pedidos.clear();
	}

	
	public double getTotal() {
		return total;
	}
	
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public int getNumero() {
    	return numero;
    }
    
	
}
