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
        System.out.print(prompt);
        return teclado.nextLine();
    }

    public double lerDouble(String prompt) {
        System.out.print(prompt);
        try {
            double valor = teclado.nextDouble();
            teclado.nextLine(); // Limpa o buffer
            return valor;
        } catch (InputMismatchException e) {
            teclado.nextLine();
            System.out.println("Valor inválido! Digite um número decimal.");
            return 0.0;
        }
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
                    atualizarCadastroTutor();
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

    private int lerOpcao() {
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

    /**
     * Permite ao usuário selecionar um proprietário existente e atualizar seus dados.
     */
    private void atualizarCadastroTutor() {
        System.out.println("\n=== Alterar Dados do Proprietário ===");
        if (listaProprietarios.isEmpty()) {
            System.out.println("Nenhum proprietário cadastrado para alterar.\n");
            return;
        }

        System.out.println("Proprietários disponíveis para alteração:");
        for (int i = 0; i < listaProprietarios.size(); i++) {
            System.out.println((i + 1) + " - " + listaProprietarios.get(i).getNomeProprietario() + " (CPF: " + listaProprietarios.get(i).getCpfProprietario() + ")");
        }
        System.out.print("Escolha o número do proprietário que deseja alterar: ");
        int escolha = lerOpcao();


        if (escolha < 1 || escolha > listaProprietarios.size()) {
            System.out.println("Proprietário inválido! Por favor, escolha um número válido.\n");
        } else {

            Proprietario p = listaProprietarios.get(escolha - 1);
            System.out.println("Alterando dados de: " + p.getNomeProprietario() + " (CPF: " + p.getCpfProprietario() + ")");
            System.out.println("Deixe em branco e pressione ENTER para manter o valor atual.");
            System.out.print("Novo nome (atual: " + p.getNomeProprietario() + "): ");
            String novoNome = teclado.nextLine();
            if (!novoNome.isEmpty()) {
                p.setNomeProprietario(novoNome);
            }

            System.out.print("Novo e-mail (atual: " + p.getEmailProprietario() + "): ");
            String novoEmail = teclado.nextLine();
            if (!novoEmail.isEmpty()) {
                p.setEmailProprietario(novoEmail);
            }

            System.out.print("Novo telefone (atual: " + p.getTelProprietario() + "): ");
            String novoTelefone = teclado.nextLine();
            if (!novoTelefone.isEmpty()) {
                p.setTelProprietario(novoTelefone);
            }

            System.out.print("Novo endereço (atual: " + p.getEndrProprietario() + "): ");
            String novoEndereco = teclado.nextLine();
            if (!novoEndereco.isEmpty()) {
                p.setEndrProprietario(novoEndereco);
            }

            System.out.println("Dados do proprietário alterados com sucesso!\n");
        }
    }

    public String selecionarEspecie() {
        System.out.println("\nEspécie do animal:");
        System.out.println("1 - Cachorro");
        System.out.println("2 - Gato");
        System.out.print("Digite a opção: ");
        int opcao = lerInt();
        return (opcao == 1) ? "Cachorro" : "Gato";
    }

    public Raca selecionarRaca(String especie) {
        Raca.exibirRacasPorEspecie(especie); // Chama o método do enum
        System.out.print("Digite o número da raça: ");
        int opcao = lerInt();
        try {
            return Raca.obterPorOpcao(opcao); // Converte o número para o enum Raca
        } catch (IllegalArgumentException e) {
            System.out.println("Raça inválida! " + e.getMessage());
            return null; // Ou lançar exceção para ser tratada no Controller
        }
    }

    public Porte selecionarPorte() {
        Porte porte = null;
        do {
            Porte.exibirOpcoes();
            System.out.print("Digite o número do porte: ");
            try {
                int opcao = teclado.nextInt();
                teclado.nextLine();
                porte = Porte.obterPorOpcao(opcao);
            } catch (Exception e) {
                teclado.nextLine();
                System.out.println("Opção inválida! Tente novamente.");
            }
        } while (porte == null);
        return porte;
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirServicos(List<Servico> servicos) {
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço disponível.");
            return;
        }

        System.out.println("\n=== SERVIÇOS ===");
        for (int i = 0; i < servicos.size(); i++) {
            System.out.println((i+1) + " - " + servicos.get(i).toStringServico());
        }
    }

}
