package amigocachorro.controller;

import amigocachorro.model.Agendamento;
import amigocachorro.model.Animal;
import amigocachorro.model.Proprietario;
import amigocachorro.model.Servico;
import amigocachorro.view.Menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoController {

    private List<Agendamento> agendamentos;
    private Menu view;
    private AnimalController animalController;
    private ServicoController servicoController;

    public AgendamentoController(Menu view,
                                 AnimalController animalController,
                                 ServicoController servicoController) {
        this.view = view;
        this.animalController = animalController;
        this.servicoController = servicoController;
        this.agendamentos = new ArrayList<>();
    }

    /**
     * Agenda um novo serviço
     */
    public void agendarServico() {
        view.exibirMensagem("\n=== NOVO AGENDAMENTO ===");

        // 1. Seleciona animal
        Animal animal = selecionarAnimal();
        if (animal == null) return;

        // 2. Seleciona serviço
        Servico servico = selecionarServico();
        if (servico == null) return;

        // 3. Captura data/hora
        String dataHora = solicitarDataHora();
        if (dataHora == null) return;

        // 4. Cria e armazena o agendamento
        Agendamento novoAgendamento = new Agendamento(
                servico,
                dataHora,
                animal,
                animal.getProprietario()
        );

        agendamentos.add(novoAgendamento);
        view.exibirMensagem("\nAgendamento realizado com sucesso!");
        view.exibirMensagem(novoAgendamento.toString());
    }

    /**
     * Lista todos os agendamentos
     */
    public void listarAgendamentos() {
        if (agendamentos.isEmpty()) {
            view.exibirMensagem("\nNenhum agendamento cadastrado.");
            return;
        }

        view.exibirMensagem("\n=== AGENDAMENTOS ===");
        for (int i = 0; i < agendamentos.size(); i++) {
            view.exibirMensagem((i+1) + " - " + agendamentos.get(i).toString());
        }
    }

    /**
     * Permite ao usuário selecionar um animal cadastrado
     */
    private Animal selecionarAnimal() {
        List<Animal> animais = animalController.getTodosAnimais();

        if (animais == null || animais.isEmpty()) {
            view.exibirMensagem("Nenhum animal cadastrado. Cadastre um animal primeiro.");
            return null;
        }

        view.exibirMensagem("\nSelecione o animal:");
        for (int i = 0; i < animais.size(); i++) {
            Animal animal = animais.get(i);
            String nomeAnimal = animal.getAnimalNome(); // Verifique se existe getNome() na classe Animal
            view.exibirMensagem((i+1) + " - " + nomeAnimal);
        }

        int opcao = view.lerInt("\nDigite o número do animal: ") - 1;

        if (opcao < 0 || opcao >= animais.size()) {
            view.exibirMensagem("Opção inválida!");
            return null;
        }

        return animais.get(opcao);
    }

    /**
     * Permite ao usuário selecionar um serviço cadastrado
     */
    private Servico selecionarServico() {
        servicoController.listarServicos();
        List<Servico> servicos = servicoController.getTodosServicos();

        if (servicos == null || servicos.isEmpty()) {
            view.exibirMensagem("Nenhum serviço cadastrado. Cadastre um serviço primeiro.");
            return null;
        }

        int opcao = view.lerInt("\nDigite o número do serviço: ") - 1;

        if (opcao < 0 || opcao >= servicos.size()) {
            view.exibirMensagem("Opção inválida!");
            return null;
        }

        return servicos.get(opcao);
    }

    /**
     * Solicita e valida a data/hora do agendamento
     */
    private String solicitarDataHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (true) {
            try {
                String input = view.lerString("Data e hora (dd/MM/aaaa HH:mm): ");
                LocalDateTime dataHora = LocalDateTime.parse(input, formatter);
                return dataHora.format(formatter);
            } catch (DateTimeParseException e) {
                view.exibirMensagem("Formato inválido! Use dd/MM/aaaa HH:mm");
            }
        }
    }

    /**
     * Retorna todos os agendamentos
     */
    public List<Agendamento> getTodosAgendamentos() {
        return new ArrayList<>(agendamentos); // Retorna cópia para evitar modificações externas
    }
}
