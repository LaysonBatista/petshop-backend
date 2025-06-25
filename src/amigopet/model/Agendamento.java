package amigopet.model;

public class Agendamento {
    private Servico servico;
    private String dataHora;
    private Animal animal;
    private Proprietario proprietario;

    private Pagamento pagamento;

    public Agendamento(Servico s, String d, Animal a, Proprietario p) {
        this.servico = s;
        this.dataHora = d;
        this.animal = a;
        this.proprietario = p;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    // Método auxiliar para obter dados formatados
    public String getDataHoraFormatada() {
        return dataHora; // Já está formatado como dd/MM/yyyy HH:mm
    }


    public String toString() {
        return String.format(
                "Serviço: %s | Data: %s | Pet: %s | Proprietário: %s",
                servico.getNomeServico(),
                dataHora,
                animal.getAnimalNome(),
                animal.getProprietario().getNomeProprietario()
        );
    }


}
