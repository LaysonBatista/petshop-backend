package amigocachorro.model;

public enum Porte {
    PEQUENO("Pequeno (até 10kg)"),
    MEDIO("Médio (11kg a 25kg)"),  // Opção adicionada/ajustada
    GRANDE("Grande (acima de 25kg)");

    private String descricao;

    Porte(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para exibir opções (já existente)
    public static void exibirOpcoes() {
        System.out.println("\nPortes disponíveis:");
        for (int i = 0; i < values().length; i++) {
            System.out.println((i + 1) + " - " + values()[i].getDescricao());
        }
    }

    // Método para obter porte por opção (já existente)
    public static Porte obterPorOpcao(int opcao) {
        if (opcao < 1 || opcao > values().length) {
            throw new IllegalArgumentException("Opção inválida!");
        }
        return values()[opcao - 1];
    }
}
