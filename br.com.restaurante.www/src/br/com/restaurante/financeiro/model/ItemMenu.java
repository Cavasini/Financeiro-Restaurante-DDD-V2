package br.com.restaurante.financeiro.model;

public enum ItemMenu {
    PIZZA("Pizza Margherita", 25.00),
    HAMBURGUER("Hambúrguer Clássico", 15.00),
    REFRIGERANTE("Refrigerante", 5.00),
    SUCO("Suco Natural", 7.00),
    LASANHA("Lasanha Bolonhesa", 30.00),
    SALADA("Salada Caesar", 12.00),
    SORVETE("Sorvete de Chocolate", 10.00),
    CAFE("Café Expresso", 4.00),
    BOLO("Bolo de Cenoura", 8.50),
    FRANGO("Frango Grelhado", 20.00);

    private final String nome;
    private final double precoUnitario;

    ItemMenu(String nome, double precoUnitario) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }
}

