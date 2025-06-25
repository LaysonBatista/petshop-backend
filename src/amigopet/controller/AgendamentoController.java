package amigopet.controller;

import amigopet.model.*;
import amigopet.view.Menu;

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
    public Agendamento agendarServico() {
        view.exibirMensagem("\n=== NOVO AGENDAMENTO ===");

        // 1. Seleciona animal
        Animal animal = selecionarAnimal();
        if (animal == null) return null;

        // 2. Seleciona serviço
        Servico servico = selecionarServico();
        if (servico == null) return null;

        // 3. Captura data/hora
        String dataHora = solicitarDataHora();
        if (dataHora == null) return null;

        // 4. Cria e armazena o agendamento
        Agendamento novoAgendamento = new Agendamento(
                servico,
                dataHora,
                animal,
                animal.getProprietario()
        );

        agendamentos.add(novoAgendamento);
        view.exibirMensagem("\nAgendamento realizado com sucesso!");
        return novoAgendamento; // Retorna o agendamento criado
    }


    public void finalizarAgendamento(Agendamento agendamento) {
        if (agendamento == null) {
            view.exibirMensagem("❌ Erro: Agendamento inválido!");
            return;
        }

        // Mostrar opções de pagamento
        view.exibirMensagem("\n=== FORMAS DE PAGAMENTO ===");
        view.exibirMensagem("1 - Cartão de Crédito/Débito");
        view.exibirMensagem("2 - PIX");

        int opcao = 0;
        boolean opcaoValida = false;

        // Validação da opção de pagamento
        while (!opcaoValida) {
            try {
                opcao = view.lerInt("Selecione a forma de pagamento (1-2): ");
                if (opcao >= 1 && opcao <= 3) {
                    opcaoValida = true;
                } else {
                    view.exibirMensagem("❌ Opção inválida! Digite 1 ou 2.");
                }
            } catch (Exception e) {
                view.exibirMensagem("❌ Entrada inválida! Digite um número.");
            }
        }

        Pagamento pagamento = null;
        PagamentoController pagamentoController = new PagamentoController();

        try {
            switch(opcao) {
                case 1: // Cartão
                    String numero = "";
                    boolean cartaoValido = false;
                    while (!cartaoValido) {
                        numero = view.lerString("Número do cartão (16 dígitos, sem espaços): ").replaceAll("[^0-9]", "");
                        if (numero.length() == 16 && numero.matches("\\d+")) {
                            cartaoValido = true;
                        } else {
                            view.exibirMensagem("❌ Número inválido! Deve conter 16 dígitos.");
                        }
                    }

                    String bandeira = "";
                    boolean bandeiraValida = false;
                    while (!bandeiraValida) {
                        bandeira = view.lerString("Bandeira (VISA/MASTER/ELO): ").toUpperCase();
                        if (bandeira.matches("VISA|MASTER|ELO")) {
                            bandeiraValida = true;
                        } else {
                            view.exibirMensagem("❌ Bandeira inválida! Escolha entre VISA, MASTER ou ELO.");
                        }
                    }

                    pagamento = pagamentoController.selecionarFormaPagamento(
                            agendamento.getServico().getPreco(),
                            agendamento,
                            "cartao",
                            numero + "," + bandeira
                    );
                    break;

                case 2: // PIX
                    String chavePix = "";
                    boolean pixValido = false;
                    while (!pixValido) {
                        chavePix = view.lerString("Chave PIX (CPF/Email/Telefone/Chave Aleatória): ").trim();
                        if (!chavePix.isEmpty() && chavePix.length() >= 11) {
                            pixValido = true;
                        } else {
                            view.exibirMensagem("❌ Chave PIX inválida! Deve ter pelo menos 11 caracteres.");
                        }
                    }

                    pagamento = pagamentoController.selecionarFormaPagamento(
                            agendamento.getServico().getPreco(),
                            agendamento,
                            "pix",
                            chavePix
                    );
                    break;
            }

            // Processamento do pagamento
            if (pagamento != null) {
                agendamento.setPagamento(pagamento);
                // Gera o recibo
                PagamentoController.gerarRecibo(agendamento);
                if (pagamentoController.processarPagamento(pagamento)) {
                    view.exibirMensagem("\n✅ Pagamento realizado com sucesso!");
                    view.exibirMensagem(pagamento.toStringPagamento());

                    // Opcional: emitir comprovante
                    view.exibirMensagem("\n📄 Comprovante disponível no sistema.");
                } else {
                    view.exibirMensagem("❌ Falha no processamento do pagamento!");
                }
            }

        } catch (Exception e) {
            view.exibirMensagem("⚠️ Erro inesperado: " + e.getMessage());

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

}
