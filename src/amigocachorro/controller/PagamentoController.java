package amigocachorro.controller;

import amigocachorro.model.Agendamento;
import amigocachorro.model.Pagamento;
import amigocachorro.model.PagamentoCartao;
import amigocachorro.model.PagamentoPix;

public class PagamentoController {
    public Pagamento selecionarFormaPagamento(double valor, Agendamento agendamento, String tipoPagamento, String dadosAdicionais) {
        switch (tipoPagamento.toLowerCase()) {
            case "cartao":
                String[] dadosCartao = dadosAdicionais.split(",");
                return new PagamentoCartao(valor, agendamento, dadosCartao[0], dadosCartao[1]);

            case "pix":
                return new PagamentoPix(valor, agendamento, dadosAdicionais);

            default:
                throw new IllegalArgumentException("Forma de pagamento inválida");
        }
    }

    public boolean processarPagamento(Pagamento pagamento) {
        return pagamento.processarPagamento();
    }
}
