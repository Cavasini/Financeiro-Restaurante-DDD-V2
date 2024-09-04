package br.com.restaurante.financeiro.test;

import br.com.restaurante.financeiro.exception.RestauranteException;
import br.com.restaurante.financeiro.main.Financeiro;
import br.com.restaurante.financeiro.main.GestaoComandas;
import br.com.restaurante.financeiro.model.Comanda;
import br.com.restaurante.financeiro.model.ItemMenu;
import br.com.restaurante.financeiro.model.Pedido;

public class Teste {

	public static void main(String[] args) throws RestauranteException {
		
		GestaoComandas gestao = new GestaoComandas();
		
		gestao.criarComanda(1);
		gestao.criarComanda(2);
		gestao.criarComanda(3);
		gestao.criarComanda(4);
		gestao.criarComanda(5);
		
		gestao.adicionarPedidoNaComanda(1, ItemMenu.HAMBURGUER, 3);
		gestao.adicionarPedidoNaComanda(1, ItemMenu.LASANHA, 2);
		gestao.adicionarPedidoNaComanda(1, ItemMenu.SUCO, 2);
		
		gestao.realizarPagamentoDaComanda(1, 130, true);
	
		
	
	}

}
