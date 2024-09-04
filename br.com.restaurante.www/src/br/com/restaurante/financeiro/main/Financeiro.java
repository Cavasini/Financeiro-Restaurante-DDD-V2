package br.com.restaurante.financeiro.main;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;

import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.restaurante.financeiro.exception.RestauranteException;
import br.com.restaurante.financeiro.model.Comanda;
import br.com.restaurante.financeiro.model.Pedido;
import br.com.restaurante.financeiro.util.NotaFiscalFormatter;
import br.com.restaurante.financeiro.util.TransacaoFormatter;

public class Financeiro {	
    private final NotaFiscalFormatter formatter;
    private final TransacaoFormatter transacaoFormatter;
    

    public Financeiro() {
        this.formatter = new NotaFiscalFormatter();
        this.transacaoFormatter = new TransacaoFormatter();
    }
	
    
    
    public void realizarPagamento(double valorPagamento, Comanda comanda) throws RestauranteException {
    	double troco = valorPagamento - comanda.getTotal();
        
        if (troco < 0) {
            throw new RestauranteException("Valor insuficiente para realizar o pagamento. Está faltando: R$" + troco*-1);
        }

        System.out.println("Pagamento realizado com sucesso! Troco: " + troco);
        
        
        registrarTransacao(comanda.getTotal(), comanda.getNumero());
    }
	
	
    public void imprimirNotaFiscal(Comanda comanda) throws RestauranteException {
        if (comanda == null || comanda.getPedidos().isEmpty()) {
            System.out.println("Tentativa de gerar nota fiscal com comanda inválida ou vazia.");
            throw new RestauranteException("Comanda inválida ou vazia. Não é possível gerar a nota fiscal.");
        }

        String directoryName = "notas_fiscais";
        Path directoryPath = Paths.get(directoryName);

        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
                System.out.println("Diretório " + directoryName + " criado com sucesso.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o diretório das notas fiscais: " + e.getMessage());
            throw new RestauranteException("Erro ao criar o diretório das notas fiscais.", e);
        }

        String fileName = "nota_fiscal_" + System.currentTimeMillis() + ".txt";
        Path filePath = directoryPath.resolve(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            String notaFiscal = formatter.formatarNotaFiscal(comanda);
            writer.write(notaFiscal);
            System.out.println("Nota fiscal gerada com sucesso: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao gerar a nota fiscal: " + e.getMessage());
            throw new RestauranteException("Erro ao gerar a nota fiscal.", e);
        }

        System.out.println("Nota fiscal gerada com sucesso! Arquivo salvo em: " + filePath.toAbsolutePath());
        
        abrirArquivo(filePath);
    }
    
    private void registrarTransacao(double valorPagamento, int numeroComanda) throws RestauranteException {
        String directoryName = "transacoes";
        Path directoryPath = Paths.get(directoryName);

        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
                System.out.println("Diretório " + directoryName + " criado com sucesso.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o diretório das transações: " + e.getMessage());
            throw new RestauranteException("Erro ao criar o diretório das transações.", e);
        }

        String fileName = "transacoes.txt";
        Path filePath = directoryPath.resolve(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            String linha = transacaoFormatter.formatarTransacao(valorPagamento, numeroComanda);
            writer.write(linha);
            writer.newLine();
            System.out.println("Transação registrada com sucesso: " + linha);
        } catch (IOException e) {
            System.out.println("Erro ao registrar a transação: " + e.getMessage());
            throw new RestauranteException("Erro ao registrar a transação.", e);
        }
    }
    
    
    private void abrirArquivo(Path filePath) throws RestauranteException {
        if (!Desktop.isDesktopSupported()) {
            throw new RestauranteException("Desktop não suportado.");
        }

        Desktop desktop = Desktop.getDesktop();

        try {
            File arquivo = filePath.toFile();
            if (arquivo.exists()) {
                desktop.open(arquivo);
                System.out.println("Arquivo aberto com sucesso: " + filePath.toAbsolutePath());
            } else {
                throw new RestauranteException("O arquivo não existe: " + filePath.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new RestauranteException("Erro ao tentar abrir o arquivo: " + e.getMessage(), e);
        }
    }
}
