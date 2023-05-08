package main;
public class ContaInvestimento extends Conta {
    public ContaInvestimento(Conta c) {
        super(c.getIdUser(), c.getIdConta(), c.getSaldo(),"ci");
    }
}
