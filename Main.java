public class Main {
    public static void main(String[] args) {
        Usuario u1 = new Usuario("joao");
       
        ContaCorrente c1 = new ContaCorrente(u1.getId());

        c1.deposito(500.50);
        c1.saque(250);
        c1.verSaldo();
        c1.saque(500);
        c1.verHistorico();
    }
}
