package amigocachorro.controller;

import amigocachorro.model.Proprietario;
import amigocachorro.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class ProprietarioController {
    private List<Proprietario> proprietarios;
    private Menu view;

    public ProprietarioController(List<Proprietario> proprietarios, Menu view) {
        this.proprietarios = proprietarios;
        this.view = view;
    }

    /**
     * Cadastra um novo proprietário
     */
    public void cadastrarProprietario() {
        view.exibirMensagem("\n=== CADASTRO DE PROPRIETÁRIO ===");

        String cpf = view.lerString("CPF: ");
        if (validarCPF(cpf)) {
            if (cpfJaCadastrado(cpf)) {
                view.exibirMensagem("CPF já cadastrado no sistema!");
                return;
            }
        } else {
            view.exibirMensagem("CPF inválido!");
            return;
        }

        String nome = solicitarNomeValido();
        if (nome == null) return;

        String email = solicitarEmailValido();
        if (email == null) return;

        String telefone = solicitarTelefoneValido();
        if (telefone == null) return;

        String endereco = solicitarEnderecoValido();
        if (endereco == null) return;

        Proprietario novoProprietario = new Proprietario(cpf, nome, email, telefone, endereco);
        proprietarios.add(novoProprietario);
        view.exibirMensagem("\nProprietário cadastrado com sucesso!");
    }

    /**
     * Lista todos os proprietários cadastrados
     */
    public void listarProprietarios() {
        if (proprietarios.isEmpty()) {
            view.exibirMensagem("\nNenhum proprietário cadastrado.");
            return;
        }

        view.exibirMensagem("\n=== PROPRIETÁRIOS CADASTRADOS ===");
        for (int i = 0; i < proprietarios.size(); i++) {
            view.exibirMensagem((i+1) + " - " + proprietarios.get(i).toString());
        }
    }


    /**
     * Valida formato do CPF (apenas formato, não dígitos verificadores)
     */
    public boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}");
    }

    /**
     * Verifica se CPF já está cadastrado
     */
    public boolean cpfJaCadastrado(String cpf) {
        return proprietarios.stream()
                .anyMatch(p -> p.getCpfProprietario().equals(cpf));
    }


    /**
     * Atualiza dados de um proprietário
     */
    public void atualizarCadastroTutor() {
        System.out.println("\n=== Alterar Dados do Proprietário ===");
        if (proprietarios.isEmpty()) {
            System.out.println("Nenhum proprietário cadastrado para alterar.\n");
            return;
        }

        System.out.println("Proprietários disponíveis para alteração:");
        for (int i = 0; i < proprietarios.size(); i++) {
            System.out.println((i + 1) + " - " + proprietarios.get(i).getNomeProprietario() + " (CPF: " + proprietarios.get(i).getCpfProprietario() + ")");
        }
        System.out.print("Escolha o número do proprietário que deseja alterar: ");
        int escolha = view.lerOpcao();


        if (escolha < 1 || escolha > proprietarios.size()) {
            System.out.println("Proprietário inválido! Por favor, escolha um número válido.\n");
        } else {

            Proprietario p = proprietarios.get(escolha - 1);
            System.out.println("\nAlterando dados de: " + p.getNomeProprietario() + " (CPF: " + p.getCpfProprietario() + ")");
            System.out.println("Deixe em branco e pressione ENTER para manter o valor atual.");
            System.out.print("Novo nome (atual: " + p.getNomeProprietario() + "): ");
            String novoNome = view.lerString();
            if (!novoNome.isEmpty()) {
                p.setNomeProprietario(novoNome);
            }

            System.out.print("Novo e-mail (atual: " + p.getEmailProprietario() + "): ");
            String novoEmail = view.lerString();
            if (!novoEmail.isEmpty()) {
                p.setEmailProprietario(novoEmail);
            }

            System.out.print("Novo telefone (atual: " + p.getTelProprietario() + "): ");
            String novoTelefone = view.lerString();
            if (!novoTelefone.isEmpty()) {
                p.setTelProprietario(novoTelefone);
            }

            System.out.print("Novo endereço (atual: " + p.getEndrProprietario() + "): ");
            String novoEndereco = view.lerString();
            if (!novoEndereco.isEmpty()) {
                p.setEndrProprietario(novoEndereco);
            }

            System.out.println("Dados do proprietário alterados com sucesso!\n");
        }
    }

    // Métodos auxiliares para validação de campos
    private String solicitarNomeValido() {
        while (true) {
            try {
                String nome = view.lerString("Nome completo (ou 'cancelar' para sair): ").trim();

                if (nome.equalsIgnoreCase("cancelar")) {
                    view.exibirMensagem("Operação cancelada.");
                    return null;
                }

                if (nome.isEmpty()) {
                    view.exibirMensagem("Nome não pode ser vazio!");
                    continue;
                }

                if (!nome.matches("[\\p{L} ]+")) {
                    view.exibirMensagem("Nome deve conter apenas letras e espaços!");
                    continue;
                }

                return nome;

            } catch (Exception e) {
                view.exibirMensagem("Erro ao processar nome: " + e.getMessage());
            }
        }
    }

    private String solicitarEmailValido() {
        while (true) {
            try {
                String email = view.lerString("E-mail (ou 'cancelar' para sair): ").trim().toLowerCase();

                if (email.equalsIgnoreCase("cancelar")) {
                    view.exibirMensagem("Operação cancelada.");
                    return null;
                }

                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    view.exibirMensagem("Formato de e-mail inválido! Exemplo: exemplo@dominio.com");
                    continue;
                }

                return email;

            } catch (Exception e) {
                view.exibirMensagem("Erro ao processar e-mail: " + e.getMessage());
            }
        }
    }

    private String solicitarTelefoneValido() {
        while (true) {
            try {
                String telefone = view.lerString("Telefone com DDD (somente números, ou 'cancelar' para sair): ")
                        .replaceAll("[^0-9]", "");

                if (telefone.equalsIgnoreCase("cancelar")) {
                    view.exibirMensagem("Operação cancelada.");
                    return null;
                }

                if (telefone.length() < 10 || telefone.length() > 11) {
                    view.exibirMensagem("Telefone inválido! Digite DDD + número (10 ou 11 dígitos)");
                    continue;
                }

                return telefone;

            } catch (Exception e) {
                view.exibirMensagem("Erro ao processar telefone: " + e.getMessage());
            }
        }
    }

    private String solicitarEnderecoValido() {
        while (true) {
            try {
                String endereco = view.lerString("Endereço (Rua, Nº, Bairro, Cidade - ou 'cancelar' para sair): ").trim();

                if (endereco.equalsIgnoreCase("cancelar")) {
                    view.exibirMensagem("Operação cancelada.");
                    return null;
                }

                if (endereco.isEmpty()) {
                    view.exibirMensagem("Endereço não pode ser vazio!");
                    continue;
                }

                return endereco;

            } catch (Exception e) {
                view.exibirMensagem("Erro ao processar endereço: " + e.getMessage());
            }
        }
    }



}
