package amigocachorro.model;

public enum Raca {

    // Cachorros
    LABRADOR("Labrador", "Cachorro"),
    GOLDEN("Golden Retriever", "Cachorro"),
    POODLE("Poodle", "Cachorro"),
    // Gatos
    PERSA("Persa", "Gato"),
    SIAMES("Siamês", "Gato"),
    // SRDs
    SRD_CACHORRO("SRD (Cachorro)", "Cachorro"),
    SRD_GATO("SRD (Gato)", "Gato");

    private String nomeExibicao;
    private String especie;

    Raca(String nomeExibicao, String especie) {
        this.nomeExibicao = nomeExibicao;
        this.especie = especie;
    }

    // Método para filtrar raças por espécie
    public static void exibirRacasPorEspecie(String especie) {
        System.out.println("\nRaças de " + especie + ":");
        int count = 1;
        for (Raca raca : values()) {
            if (raca.especie.equalsIgnoreCase(especie)) {
                System.out.println(count + " - " + raca.nomeExibicao);
                count++;
            }
        }
    }

    // Método para obter raça filtrada por opção numérica
    public static Raca obterRacaPorOpcaoEEspecie(int opcao, String especie) {
        int count = 1;
        for (Raca raca : values()) {
            if (raca.especie.equalsIgnoreCase(especie)) {
                if (count == opcao) return raca;
                count++;
            }
        }
        throw new IllegalArgumentException("Opção inválida!");
    }

    // Método para converter int em Raca
    public static Raca obterPorOpcao(int opcao) {
        if (opcao < 1 || opcao > values().length) {
            throw new IllegalArgumentException("Opção deve estar entre 1 e " + values().length);
        }
        return values()[opcao - 1];
    }
}
