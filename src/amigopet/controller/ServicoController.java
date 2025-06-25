package amigopet.controller;

import amigopet.model.Servico;
import amigopet.view.Menu;

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
     * Retorna a lista completa de serviços
     */
    public List<Servico> getTodosServicos() {
        return servicos;
    }

    /**
     * Cadastra serviços padrões
     */
    private void inicializarServicosPadrao() {
        servicos.add(new Servico("Banho completo", "Banho", 50.0, "Banho com shampoo especial"));
        servicos.add(new Servico("Tosa higiênica", "Tosa", 40.0, "Tosa na região íntima e patas"));
        servicos.add(new Servico("Consulta veterinária", "Veterinário", 120.0, "Consulta básica com veterinário"));
    }

}
