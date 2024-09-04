package br.com.restaurante.financeiro.util;

import br.com.restaurante.financeiro.model.Comanda;
import br.com.restaurante.financeiro.model.Pedido;

public class NotaFiscalFormatter {

    public String formatarNotaFiscal(Comanda comanda) {
        StringBuilder notaFiscal = new StringBuilder();
        notaFiscal.append("----------- NOTA FISCAL -----------\n");
        notaFiscal.append(String.format("%-25s %-8s %-12s %-12s\n", "Item", "Quant", "Preço Unit", "Total"));

        for (Pedido pedido : comanda.getPedidos()) {
            notaFiscal.append(String.format("%-25s %-8d %-12.2f %-12.2f\n",
                    pedido.getItem(),
                    pedido.getQuantidade(),
                    pedido.getPrecoUnitario(),
                    pedido.getTotal()));
        }

        notaFiscal.append("-----------------------------------\n");
        notaFiscal.append("Total da Comanda: ").append(String.format("%.2f", comanda.getTotal())).append("\n");
        notaFiscal.append("Obrigado pela sua preferência!\n");

        return notaFiscal.toString();
    }
}
