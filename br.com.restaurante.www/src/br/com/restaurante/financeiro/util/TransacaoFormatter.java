package br.com.restaurante.financeiro.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransacaoFormatter {

    private SimpleDateFormat formatoData;

    public TransacaoFormatter() {
        this.formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public String formatarTransacao(double valorPagamento, int numeroComanda) {
        String dataHora = formatoData.format(new Date());
        return String.format("Valor: %.2f, Comanda: %d, Data e Hora: %s",
                valorPagamento, numeroComanda, dataHora);
    }
}
