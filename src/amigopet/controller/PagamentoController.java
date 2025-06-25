package amigopet.controller;

import amigopet.model.Agendamento;
import amigopet.model.Pagamento;
import amigopet.model.PagamentoCartao;
import amigopet.model.PagamentoPix;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static void gerarRecibo(Agendamento agendamento) {
        if (agendamento.getPagamento() == null) {
            throw new IllegalArgumentException("Agendamento não possui pagamento associado");
        }

        String nomeArquivo = "recibos\\recibo_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                ".txt";

        try {

            // Garante que o diretório exista
            new java.io.File("recibos").mkdirs();

            FileWriter writer = new FileWriter(nomeArquivo);

            // Cabeçalho
            writer.write("=== RECIBO ===\n");
            writer.write("Data emissão: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n");
            writer.write("Valor: R$ " +
                    String.format("%.2f", agendamento.getServico().getPreco()) + "\n");
            writer.write("Forma de Pagamento: " +
                    agendamento.getPagamento().getTipoPagamento() + "\n\n");

            // Detalhes do Agendamento
            writer.write("Detalhes do Agendamento:\n");
            writer.write("Serviço: " + agendamento.getServico().getNomeServico() + "\n");
            writer.write("Data: " + agendamento.getDataHoraFormatada() + "\n");
            writer.write("Pet: " + agendamento.getAnimal().getAnimalNome() + "\n");
            writer.write("Proprietário: " +
                    agendamento.getProprietario().getNomeProprietario() + "\n\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("Erro ao gerar recibo: " + e.getMessage());
            throw new RuntimeException("Falha ao gerar recibo", e);
        }
    }
}
