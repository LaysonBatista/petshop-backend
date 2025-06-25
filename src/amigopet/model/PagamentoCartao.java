package amigopet.model;

public class PagamentoCartao extends Pagamento {
    private String numeroCartao;
    private String bandeira;

    public PagamentoCartao(double valor, Agendamento agendamento, String numeroCartao, String bandeira) {
        super(valor, agendamento);
        this.numeroCartao = numeroCartao;
        this.bandeira = bandeira;
    }
    @Override
    public String getTipoPagamento() {
        return "Cartão " + bandeira;
    }

    @Override
    public boolean processarPagamento() {
        return true; // Simulação - retornaria false se falhar
    }

    @Override
    public String toStringPagamento() {
        return super.toStringPagamento() +
                "\n\nDetalhes do Cartão:" +
                "\nBandeira: " + bandeira +
                "\nNúmero: " + numeroCartao.substring(0, 4) + "****" + numeroCartao.substring(12);
    }
}
