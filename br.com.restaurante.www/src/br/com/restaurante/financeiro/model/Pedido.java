package br.com.restaurante.financeiro.model;

public class Pedido {
	private ItemMenu item;
	private int quantidade;
	
	public Pedido(ItemMenu item, int quantidade) {
		this.item = item;
		this.quantidade = quantidade;
	}
	
	public String getItem() {
		return item.getNome();
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public double getPrecoUnitario() {
		return item.getPrecoUnitario();
	}
	
	public double getTotal() { 
		return quantidade*item.getPrecoUnitario();
	}
	
	
	public void adicionarQuantidade(int quantidade) {
		this.quantidade += quantidade;
		
	}
	
	
}
