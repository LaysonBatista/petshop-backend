package amigocachorro.controller;


import amigocachorro.model.Animal;
import amigocachorro.model.Porte;
import amigocachorro.model.Proprietario;
import amigocachorro.model.Raca;
import amigocachorro.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class AnimalController  {
    private Menu view;
    private ArrayList<Animal> listaAnimais;
    private ArrayList<Proprietario> listaProprietarios;

    public AnimalController(Menu view, ArrayList<Proprietario> listaProprietarios, ArrayList<Animal> animais) {
        this.view = view;
        this.listaProprietarios = listaProprietarios;
        this.listaAnimais = animais;
    }

    /**
     * Permite ao usuário cadastrar um novo animal, vinculando-o a um proprietário existente.
     * Verifica se há tutores cadastrados antes de prosseguir.
     */
    public void cadastrarPet() {
        System.out.println("\n=== Cadastro de Animal ===");

        // Verifica se há tutores cadastrados
        if (listaProprietarios.isEmpty()) {
            System.out.println("!ERRO! Nenhum tutor cadastrado. Cadastre um tutor primeiro para vincular um animal.\n");
            return; }

        System.out.println("Escolha o tutor para este animal:");
        for (int i = 0; i < listaProprietarios.size(); i++) {
            System.out.println((i + 1) + " - " + listaProprietarios.get(i).getNomeProprietario() + " (CPF: " + listaProprietarios.get(i).getCpfProprietario() + ")");
        }

        int escolhaTutor = view.lerInt("Digite o número do tutor: ");
        if (escolhaTutor < 1 || escolhaTutor > listaProprietarios.size()) {
            System.out.println("Escolha de tutor inválida. Cadastro de animal cancelado.\n");
            return;
        }

        Proprietario proprietarioSelecionado = listaProprietarios.get(escolhaTutor - 1);

        // Validação do Nome do Animal
        String animalNome;
        do {
            animalNome = view.lerString("\nNome do animal: ").trim();
            if (animalNome.isEmpty()) {
                System.out.println("O nome não pode ser vazio!");
            } else if (!animalNome.matches("[\\p{L} ]+")) {
                System.out.println("O nome deve conter apenas letras e espaços!");
            } else {
                break;
            }
        } while (true);

        String especie = view.selecionarEspecie();
        Raca raca = view.selecionarRaca(especie);
        if (raca == null) return; // Trata erro se a raça for inválida
        Porte porte = view.selecionarPorte();

        // Validação da Pelagem
        String pelagem;
        do {
            pelagem = view.lerString("Pelagem (cor/tipo): ").trim();
            if (pelagem.isEmpty()) {
                System.out.println("A pelagem não pode ser vazia!");
            } else if (!pelagem.matches("[\\p{L} /]+")) {
                System.out.println("Use apenas letras, espaços e '/' para descrever a pelagem!");
            } else {
                break;
            }
        } while (true);

        int idade = -1;
        do {
            try {
                idade = view.lerInt("Idade (anos, 0-30): ");
                if (idade < 0 || idade > 30) {
                    System.out.println("Idade inválida! Digite um valor entre 0 e 30.");
                }
            } catch (Exception e) {
                System.out.println("Digite um número válido para a idade!");
            }
        } while (idade < 0 || idade > 30);

        // Validação do Sexo
        String sexo;
        do {
            sexo = view.lerString("Sexo (M/F): ").trim().toUpperCase();
            if (!sexo.matches("[MF]")) {
                System.out.println("Opção inválida! Digite M para Macho ou F para Fêmea.");
            } else {
                break;
            }
        } while (true);


        Animal novoAnimal = new Animal(animalNome, especie, raca, porte, pelagem, idade, sexo, proprietarioSelecionado);
        this.listaAnimais.add(novoAnimal);
        System.out.println("Animal cadastrado com sucesso!\n");
    }

    public List<Animal> getTodosAnimais() {
        return this.listaAnimais; // Ou a lista que você está usando
    }


}
