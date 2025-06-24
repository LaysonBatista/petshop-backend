package amigocachorro.view;

import amigocachorro.controller.AgendamentoController;
import amigocachorro.controller.AnimalController;
import amigocachorro.controller.ProprietarioController;
import amigocachorro.controller.ServicoController;
import amigocachorro.model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner teclado;
    private ArrayList<Proprietario> listaProprietarios;
    private ArrayList<Animal> listaAnimais;
    private AnimalController animalController;
    private ServicoController servicoController;
    private AgendamentoController agendamentoController;
    private ProprietarioController proprietarioController;


    public Menu(ArrayList<Proprietario> proprietarios, ArrayList<Animal> animais) {
        this.teclado = new Scanner(System.in);
        this.listaProprietarios = proprietarios;
        this.listaAnimais = animais;
        this.animalController = new AnimalController(this, listaProprietarios, listaAnimais);
    }


    // Métodos de Leitura de dados
    public String lerString(String prompt) {
        String texto;
        do {
            System.out.print(prompt);
            texto = teclado.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Entrada inválida. Digite novamente.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    public String lerString() {
        return teclado.nextLine();
    }

    // Ler int com parâmetro polimorfismo
    public int lerInt(String prompt) {
        System.out.print(prompt);
        try {
            int valor = teclado.nextInt();
            teclado.nextLine(); // Limpa o buffer
            return valor;
        } catch (InputMismatchException e) {
            teclado.nextLine(); // Limpa o buffer
            System.out.println("Erro: Digite um número válido!");
            return -1; // Valor padrão para erro (pode ajustar conforme necessidade)
        }
    }

    public int lerInt() {
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine(); // Limpa o buffer
            return opcao;
        } catch (InputMismatchException e) {
            teclado.nextLine(); // Limpa o buffer
            System.out.println("Erro: Digite um número válido!");
            return -1; // Retorna -1 para indicar erro
        }
    }

    public String lerDataHora(String prompt) {
        System.out.print(prompt);
        return teclado.nextLine();
    }

    public void configurarProprietarioControllers(ProprietarioController proprietarioController) {
        this.proprietarioController = proprietarioController;
    }
    public void configurarProprietarioController(ProprietarioController controller) {
        this.proprietarioController = controller;
    }

    public void configurarAnimalController(AnimalController controller) {
        this.animalController = controller;
    }

    public void configurarServicoController(ServicoController controller) {
        this.servicoController = controller;
    }

    public void configurarAgendamentoController(AgendamentoController controller) {
        this.agendamentoController = controller;
    }


    public void iniciar() {
        // Verificação de segurança
        if (proprietarioController == null) {
            System.err.println("Erro: Controller não configurado!");
            return;
        }

        int opcao;
        System.out.println(" === BEM VINDO AO MEU AMIGO PET ===");

        do {
            exibirOpcoesMenu();
            opcao = lerOpcao();


            switch (opcao) {
                case 1:
                    proprietarioController.cadastrarProprietario(); // Chama o método de cadastro de proprietário
                    break;
                case 2:
                    animalController.cadastrarPet(); // Chama o método de cadastro de animal
                    break;
                case 3:
                    // Primeiro agenda o serviço e obtém o agendamento criado
                    try {
                        Agendamento novoAgendamento = agendamentoController.agendarServico();
                        if (novoAgendamento != null) {
                            agendamentoController.finalizarAgendamento(novoAgendamento);
                        }
                    } catch (Exception e) {
                        System.out.println("Erro durante o agendamento: " + e.getMessage());
                    }
                    break;
                case 4:
                    exibirCadastros();
                    break;
                case 5:
                    proprietarioController.atualizarCadastroTutor();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida (0-8).\n");
            }
        } while (opcao != 0);


        teclado.close();
    }

    /**
     * Exibe as opções disponíveis no menu para o usuário.
     */
    private void exibirOpcoesMenu() {
        System.out.println("\n=== MENU PETSHOP ===");
        System.out.println("Escolha as opções abaixo de acordo com o serviço desejado!");
        System.out.println("1 - CADASTRAR PROPRIETÁRIO");
        System.out.println("2 - CADASTRAR PET");
        System.out.println("3 - AGENDAR SERVIÇO");
        System.out.println("4 - EXIBIR CADASTRO");
        System.out.println("5 - ATUALIZAR CADASTRO DE PROPRIETÁRIO");
        System.out.println("0 - SAIR");
        System.out.print("Digite sua opção: ");
    }

    public int lerOpcao() {
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException e) {

            System.out.println("Entrada inválida. Por favor, digite um número correspondente à opção.");
            teclado.nextLine();
            return -1;
        }
    }


    /**
     * Exibe os detalhes de todos os proprietários e animais cadastrados no sistema.
     */
    private void exibirCadastros() {
        System.out.println("\n=== Exibindo Cadastros ===");

        // Verifica se há algum cadastro antes de tentar exibir
        if (listaProprietarios.isEmpty() && listaAnimais.isEmpty()) {
            System.out.println("Nenhum cadastro para exibir.\n");
            return;
        }

        System.out.println("--- Tutores Cadastrados ---");
        if (listaProprietarios.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
        } else {
            for (Proprietario p : listaProprietarios) {
                System.out.println(p.toString());
            }
        }

        System.out.println("\n--- Animais Cadastrados ---");
        if (listaAnimais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            for (Animal a : listaAnimais) {
                System.out.println(a.toString());
            }
        }
        System.out.println("---------------------------\n");
    }


    public String selecionarEspecie() {
        int opcao = 0;
        do {
            System.out.println("\nEspécie do animal:");
            System.out.println("1 - Cachorro");
            System.out.println("2 - Gato");
            System.out.print("Digite a opção (1-2): ");

            try {
                String input = teclado.nextLine().trim();

                // Verifica se a entrada está vazia
                if (input.isEmpty()) {
                    System.out.println("Erro: Nenhuma opção digitada!");
                    continue;
                }

                opcao = Integer.parseInt(input);

                if (opcao == 1 || opcao == 2) {
                    return (opcao == 1) ? "Cachorro" : "Gato";
                } else {
                    System.out.println("Opção inválida! Digite 1 para Cachorro ou 2 para Gato.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números (1 ou 2)!");
            }
        } while (true);
    }

    public Raca selecionarRaca(String especie) {
        Raca.exibirRacasPorEspecie(especie); // Mostra as opções disponíveis

        Raca racaSelecionada = null;
        boolean valido = false;

        do {
            System.out.print("Digite o número da raça: ");
            int opcao = lerInt();

            if (opcao == -1) { // LerInt retorna -1 se houve erro na digitação
                System.out.println("Entrada inválida. Digite um número correspondente à raça.");
                continue;
            }

            try {
                racaSelecionada = Raca.obterRacaPorOpcaoEEspecie(opcao, especie);
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Raça inválida! " + e.getMessage());
            }
        } while (!valido);

        return racaSelecionada;
    }

    public Porte selecionarPorte() {
        Porte porte = null;
        do {
            Porte.exibirOpcoes();
            System.out.print("Digite o número do porte: ");
            try {
                String input = teclado.nextLine(); // Lê toda a linha como String
                int opcao = Integer.parseInt(input); // Tenta converter para int
                porte = Porte.obterPorOpcao(opcao);

                if (porte == null) {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números!");
            } catch (Exception e) {
                System.out.println("Opção inválida! Tente novamente.");
            }
        } while (porte == null);
        return porte;
    }



    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

}
