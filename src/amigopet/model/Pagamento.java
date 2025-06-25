package amigopet.model;

import java.time.LocalDate;

public abstract class Pagamento {
    private LocalDate data;
    private double valor;
    private Agendamento agendamento;

    public abstract String getTipoPagamento();

    public Pagamento(double valor, Agendamento agendamento) {
        this.data = LocalDate.now(); // Data atual automaticamente
        this.valor = valor;
        this.agendamento = agendamento;
    }

    // Método abstrato que cada subclasse deve implementar
    public abstract boolean processarPagamento();

    // Getters
    public LocalDate getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    // Template Method para formato comum de exibição
    public String toStringPagamento() {
        return "Data: " + data.toString() +
                "\nValor: R$ " + String.format("%.2f", valor) +
                "\nForma de Pagamento: " + this.getClass().getSimpleName() +
                "\n\nDetalhes do Agendamento:\n" + agendamento.toString();
    }




}
