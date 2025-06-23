package amigocachorro.controller;

import amigocachorro.model.Servico;
import amigocachorro.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class ServicoController {
    private List<Servico> servicos;
    private Menu view;


    public ServicoController(List<Servico> servicos, Menu view) {
        this.servicos = servicos;
        this.view = view;
        inicializarServicosPadrao();
    }

    /**
     * Permite ao usuário selecionar o tipo de serviço
     */
    private String selecionarTipoServico() {
        view.exibirMensagem("\nTipos disponíveis:");
        view.exibirMensagem("1 - Banho");
        view.exibirMensagem("2 - Tosa");
        view.exibirMensagem("3 - Veterinário");
        view.exibirMensagem("4 - Hospedagem");

        int opcao = view.lerInt("Digite o número correspondente ao tipo: ");

        switch(opcao) {
            case 1: return "Banho";
            case 2: return "Tosa";
            case 3: return "Veterinário";
            case 4: return "Hospedagem";
            default: return "Outros";
        }
    }

    /**
     * Lista todos os serviços cadastrados
     */
    public void listarServicos() {
        if (servicos.isEmpty()) {
            view.exibirMensagem("\nNenhum serviço cadastrado!");
            return;
        }

        view.exibirMensagem("\n=== SERVIÇOS DISPONÍVEIS ===");
        for (int i = 0; i < servicos.size(); i++) {
            view.exibirMensagem((i+1) + " - " + servicos.get(i).toStringServico());
        }
    }

    /**
     * Busca um serviço pelo índice
     */
    public Servico buscarServico(int indice) {
        if (indice >= 0 && indice < servicos.size()) {
            return servicos.get(indice);
        }
        return null;
    }

    /**
     * Retorna a lista completa de serviços
     */
    public List<Servico> getTodosServicos() {
        return servicos;
    }

    /**
     * Cadastra serviços padrão (opcional)
     */
    private void inicializarServicosPadrao() {
        servicos.add(new Servico("Banho completo", "Banho", 50.0, "Banho com shampoo especial"));
        servicos.add(new Servico("Tosa higiênica", "Tosa", 40.0, "Tosa na região íntima e patas"));
        servicos.add(new Servico("Consulta veterinária", "Veterinário", 120.0, "Consulta básica com veterinário"));
    }

}
