import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Banco.connectDb();

        int op = 0;
        Usuario currentUser = null;
        Scanner sc = new Scanner(System.in);

        while (op != -1) {
            System.out.println("------------- MENU -------------\n");

            //PRINTA OPCOES
            //LOGIN 
            if (currentUser == null) {
                System.out.println("0. Login\n");

            } else { // LOGADO

            }
            op = sc.nextInt();

            switch(op) {
                case 0:
                    System.out.println("Digite seu usuario e senha\n");
                    String usuario = sc.nextLine();
                    String senha = sc.nextLine();
                    
                    currentUser = UsuarioHelper.getUsuario(usuario, senha);

                    if (currentUser == null) {
                        System.out.println("Usuario nao encontrado!");
                    }

                break;

                case 1:
                break;

                case 2:

                break;

            }

        }

        Banco.disconnectDb();
    }
}
