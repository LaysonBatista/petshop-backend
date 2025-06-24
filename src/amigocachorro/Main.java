package amigocachorro;

import amigocachorro.controller.AgendamentoController;
import amigocachorro.controller.AnimalController;
import amigocachorro.controller.ProprietarioController;
import amigocachorro.controller.ServicoController;
import amigocachorro.model.Animal;
import amigocachorro.model.Proprietario;
import amigocachorro.model.Servico;
import amigocachorro.view.Menu;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Proprietario> listaProprietarios = new ArrayList<>();
        ArrayList<Animal> listaAnimais = new ArrayList<>();
        ArrayList<Servico> listaServicos = new ArrayList<>();

        // Cria a View (Menu) com as dependências necessárias
        Menu menu = new Menu(listaProprietarios, listaAnimais);

        // Cria o controller com as dependências
        ProprietarioController proprietarioController = new ProprietarioController(listaProprietarios, menu);
        AnimalController animalController = new AnimalController(menu, listaProprietarios, listaAnimais);
        ServicoController servicoController = new ServicoController(listaServicos, menu);
        AgendamentoController agendamentoController = new AgendamentoController(menu, animalController, servicoController);


        // Configura o controller no Menu
        menu.configurarProprietarioControllers(proprietarioController);
        menu.configurarAgendamentoController(agendamentoController);
        menu.configurarServicoController(servicoController);

        // Inicia o sistema
        System.out.println("\n=== SISTEMA DE GERENCIAMENTO PET SHOP ===");
        menu.iniciar();
    }
}
