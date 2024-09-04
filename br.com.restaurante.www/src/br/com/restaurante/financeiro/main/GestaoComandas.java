package br.com.restaurante.financeiro.main;

import java.util.HashMap;
import java.util.Map;

import br.com.restaurante.financeiro.exception.RestauranteException;
import br.com.restaurante.financeiro.model.Comanda;
import br.com.restaurante.financeiro.model.ItemMenu;
import br.com.restaurante.financeiro.model.Pedido;

public class GestaoComandas{
	private Map<Integer, Comanda> comandas;
	private Financeiro financeiro = new Financeiro();
    
	public GestaoComandas() {
		comandas = new HashMap<>();
	}
	
	public void criarComanda(int numero) {
		comandas.put(numero, new Comanda(numero)); 
	}
	
	public Comanda getComandaByNum(int numero) {
		return comandas.get(numero);
	}
	
	public void removeComanda(int numero) {
		comandas.remove(numero);
	}
	
	 public void adicionarPedidoNaComanda(int numeroComanda, ItemMenu item, int quantidade) throws RestauranteException {
	        Comanda comanda = getComandaByNum(numeroComanda);

	        if (comanda == null) {
	            throw new RestauranteException("Comanda não encontrada para o número: " + numeroComanda);
	        }

	        Pedido pedido = new Pedido(item, quantidade);
	        comanda.adicionarPedido(pedido);
	        System.out.println("Pedido de " + item.getNome() + " adicionado com sucesso à comanda " + numeroComanda);
	    }
	 
	 public void realizarPagamentoDaComanda(int numeroComanda, double valorPagamento, boolean imprimirNotaFiscal) throws RestauranteException { //parametros e extension do RestauranteException
		 Comanda comanda = getComandaByNum(numeroComanda); // por meio do parametro numeroComanda, eu busco a comanda
		 
	        if (comanda == null) {
	            throw new RestauranteException("Comanda não encontrada para o número: " + numeroComanda); 
	        }
	        
	        if(valorPagamento <= 0.0) {
	        	throw new RestauranteException("Valor do Pagamento inválido: R$" + valorPagamento);
	        }
	        
	        financeiro.realizarPagamento(valorPagamento, comanda);
	        
	        if(imprimirNotaFiscal == true) {
	        	financeiro.imprimirNotaFiscal(comanda);
	        }
	        
	        
	        comanda.limparComanda();
	        
	        System.out.println("Comanda " + comanda.getNumero()+ " Liberada para uso!");
	 }
}
