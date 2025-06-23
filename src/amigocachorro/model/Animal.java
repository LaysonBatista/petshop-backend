package amigocachorro.model;

public class Animal {
    private String animalNome;
    private String especie;
    private Raca raca;
    private Porte porte;
    private String pelagem;
    private int idade;
    private String sexo;
    private Proprietario proprietario;


    public Animal(String animalNome, String especie, Raca raca, Porte porte, String pelagem, int idade, String sexo, Proprietario proprietario) {
        this.animalNome = animalNome;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
        this.pelagem = pelagem;
        this.idade = idade;
        this.sexo = sexo;
        this.proprietario = proprietario;
    }


    public String getAnimalNome() {
        return animalNome;
    }

    public void setAnimalNome(String animalNome) {
        this.animalNome = animalNome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public String getPelagem() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    // Método para exibir informações do animal e do proprietário
    @Override
    public String toString() {
        return "Animal: " + animalNome + " (" + especie + ", " + raca + ", " + porte + ", " + pelagem + ", " + idade + " anos, " + sexo + ")\n"
                + proprietario.toString();
    }
}
