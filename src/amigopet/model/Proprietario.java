package amigopet.model;

public class Proprietario {
    private String cpfProprietario;
    private String nomeProprietario;
    private String emailProprietario;
    private String telProprietario;
    private String endrProprietario;


    public Proprietario(String cpfProprietario, String nomeProprietario, String emailProprietario, String telProprietario,
                        String endrProprietario) {
        this.cpfProprietario = cpfProprietario;
        this.nomeProprietario = nomeProprietario;
        this.emailProprietario = emailProprietario;
        this.telProprietario = telProprietario;
        this.endrProprietario = endrProprietario;
    }


    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public void setCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getEmailProprietario() {
        return emailProprietario;
    }

    public void setEmailProprietario(String emailProprietario) {
        this.emailProprietario = emailProprietario;
    }

    public String getTelProprietario() {
        return telProprietario;
    }

    public void setTelProprietario(String telProprietario) {
        this.telProprietario = telProprietario;
    }

    public String getEndrProprietario() {
        return endrProprietario;
    }

    public void setEndrProprietario(String endrProprietario) {
        this.endrProprietario = endrProprietario;
    }


    @Override
    public String toString() {
        return "Proprietario: CPF: " + cpfProprietario + ", Nome: " + nomeProprietario
                + ", E-mail: " + emailProprietario + ", Telefone: " + telProprietario + ", Endereço: "
                + endrProprietario + ".";
    }
}
