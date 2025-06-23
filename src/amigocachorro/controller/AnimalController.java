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
        String animalNome = view.lerString("Nome do animal: ");
        String especie = view.selecionarEspecie();
        Raca raca = view.selecionarRaca(especie);
        if (raca == null) return; // Trata erro se a raça for inválida
        Porte porte = view.selecionarPorte();
        String pelagem = view.lerString("Pelagem: ");
        int idade = view.lerInt("Idade (anos): ");
        String sexo = view.lerString("Sexo: ");


        Animal novoAnimal = new Animal(animalNome, especie, raca, porte, pelagem, idade, sexo, proprietarioSelecionado);
        this.listaAnimais.add(novoAnimal);
        System.out.println("Animal cadastrado com sucesso!\n");
    }

    public List<Animal> getTodosAnimais() {
        return this.listaAnimais; // Ou a lista que você está usando
    }


}
