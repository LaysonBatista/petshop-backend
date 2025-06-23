package amigocachorro.model;

public class PagamentoPix extends Pagamento{
    private String chavePix;

    public PagamentoPix(double valor, Agendamento agendamento, String chavePix) {
        super(valor, agendamento);
        this.chavePix = chavePix;
    }

    @Override
    public boolean processarPagamento() {
        // Lógica específica para PIX
        return true;
    }

    @Override
    public String toStringPagamento() {
        return super.toStringPagamento() +
                "\n\nDetalhes do PIX:" +
                "\nChave: " + chavePix;
    }
}
