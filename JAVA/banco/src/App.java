public class App {
    public static void main(String[] args) throws Exception {
        
        Banco.connectDb();

        Usuario u1 = UsuarioHelper.getUsuario("Lucas", "123");
        ContaCorrente cc = new ContaCorrente(ContaHelper.getContaByUser(u1.getId(),"cc"));

        cc.verSaldo();

        ContaInvestimento ci = new ContaInvestimento(ContaHelper.getContaByUser(u1.getId(),"ci"));
        ci.verSaldo();

        cc.transferir(ci.getIdConta(), 50.00);

        cc.verSaldo();
        ci.verSaldo();

        cc.deposito(900);

        cc.saque(250);

        cc.verHistorico();
    }
}
