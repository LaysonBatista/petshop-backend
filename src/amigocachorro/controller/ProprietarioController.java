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

        String nome = view.lerString("Nome completo: ");
        String email = view.lerString("E-mail: ");
        String telefone = view.lerString("Telefone: ");
        String endereco = view.lerString("Endereço: ");

        Proprietario novoProprietario = new Proprietario(cpf, nome, email, telefone, endereco);
        proprietarios.add(novoProprietario);
        view.exibirMensagem("Proprietário cadastrado com sucesso!");
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
     * Busca um proprietário pelo CPF
     */
    public Proprietario buscarPorCpf(String cpf) {
        for (Proprietario proprietario : proprietarios) {
            if (proprietario.getCpfProprietario().equals(cpf)) {
                return proprietario;
            }
        }
        return null;
    }

    /**
     * Valida formato do CPF (apenas formato, não dígitos verificadores)
     */
    private boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}");
    }

    /**
     * Verifica se CPF já está cadastrado
     */
    private boolean cpfJaCadastrado(String cpf) {
        return proprietarios.stream()
                .anyMatch(p -> p.getCpfProprietario().equals(cpf));
    }

    /**
     * Retorna a lista completa de proprietários
     */
    public List<Proprietario> getTodosProprietarios() {
        return new ArrayList<>(proprietarios); // Retorna cópia para evitar modificações externas
    }

    /**
     * Atualiza dados de um proprietário
     */
    public void atualizarProprietario(String cpf) {
        Proprietario proprietario = buscarPorCpf(cpf);
        if (proprietario == null) {
            view.exibirMensagem("Proprietário não encontrado!");
            return;
        }

        view.exibirMensagem("\nEditando proprietário: " + proprietario.getNomeProprietario());

        String novoNome = view.lerString("Novo nome (" + proprietario.getNomeProprietario() + "): ");
        if (!novoNome.isEmpty()) {
            proprietario.setNomeProprietario(novoNome);
        }

        String novoEmail = view.lerString("Novo e-mail (" + proprietario.getEmailProprietario() + "): ");
        if (!novoEmail.isEmpty()) {
            proprietario.setEmailProprietario(novoEmail);
        }

        String novoTelefone = view.lerString("Novo telefone (" + proprietario.getTelProprietario() + "): ");
        if (!novoTelefone.isEmpty()) {
            proprietario.setTelProprietario(novoTelefone);
        }

        String novoEndereco = view.lerString("Novo endereço (" + proprietario.getEndrProprietario() + "): ");
        if (!novoEndereco.isEmpty()) {
            proprietario.setEndrProprietario(novoEndereco);
        }

        view.exibirMensagem("Dados atualizados com sucesso!");
    }
}
