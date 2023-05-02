import java.util.ArrayList;
import java.util.HashMap;

public class Banco {
    
    private static int lastUserId = 0;
    private static int lastContaId = 0;
    private static HashMap<Integer, ContaCorrente> contasCorrente = new HashMap<Integer, ContaCorrente>();
    private static HashMap<Integer, ContaCorrente> contasInvestimento = new HashMap<Integer, ContaCorrente>();

    public ContaCorrente novaContaCorrente(int idUser) {
        ContaCorrente novaConta = new ContaCorrente(idUser);
        contasCorrente.put(idUser, novaConta);
        return novaConta;
    }

    public ContaCorrente novaContaInvestimento(int idUser) {
        ContaCorrente novaConta = new ContaCorrente(idUser);
        contasCorrente.put(idUser, novaConta);
        return novaConta;
    }

    public static int getNewId() {
        return lastUserId += 1;
    }

    public static int getContaId() {
        return lastContaId += 1;
    }
}
