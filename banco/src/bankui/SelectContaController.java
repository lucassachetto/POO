package bankui;

import java.io.IOException;
import java.util.ArrayList;

import Util.BancoException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Conta;
import main.ContaHelper;
import main.Usuario;

public class SelectContaController {

    @FXML private GridPane gridContas;
    @FXML private Label userName;

    public void initialize() throws BancoException {

        Usuario u = App.getUser();

        System.out.println(u.getName());

        userName.setText(u.getName());

        ArrayList<Conta> contas = ContaHelper.getContaByUserId(u.getId());

        int row = 0;
        for (Conta conta : contas) {

            Label lb = new Label();
            lb.setText("Id:"+conta.getIdConta()+"\n"+conta.getTipo());
            gridContas.add(lb,0,row);

            Label lb2 = new Label();
            lb2.setText("Saldo: "+conta.getSaldo());
            gridContas.add(lb2,1,row);

            Button btAcessa = new Button();
            btAcessa.setText("Acessar");
            btAcessa.setOnAction(e -> {
                acessaConta(conta.getIdConta());
            });
           
            gridContas.add(btAcessa,2,row);
            row ++;
        }
    }

    private static void acessaConta(Long idConta) {
        System.out.println(idConta);
    }
}
