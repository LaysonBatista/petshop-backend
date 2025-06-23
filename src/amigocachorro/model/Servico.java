package amigocachorro.model;

public class Servico {
    private String nomeServico;
    private String tipo;
    private double preco;
    private String detalhamento;

    public Servico(String n, String t, double p, String d) {
        this.nomeServico = n;
        this.tipo = t;
        this.preco = p;
        this.detalhamento = d;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public String toStringServico() {
        return "nome do serviço = " + nomeServico + ", tipo = " + tipo + ", preço = "
                + preco + ", detalhamento = " + detalhamento + " ";
    }
}
