public class App {
    public static void main(String[] args) throws Exception {
        
        Banco.connectDb();

        Usuario u1 = UsuarioHelper.getUsuario("Lucas", "123");
        Usuario u2 = UsuarioHelper.getUsuario("teste","456");
        ContaCorrente cc2 = new ContaCorrente(ContaHelper.getContaByUserId(u2.getId(), "cc"));
        ContaCorrente cc = new ContaCorrente(ContaHelper.getContaByUserId(u1.getId(), "cc"));

        ContaInvestimento ci = new ContaInvestimento(ContaHelper.getContaByUserId(u1.getId(), "ci"));
        
        //cc.pix("555469", 200.00);
        
        cc.verHistorico();
        ci.verHistorico();
        cc2.verHistorico();
        Banco.disconnectDb();
    }
}
