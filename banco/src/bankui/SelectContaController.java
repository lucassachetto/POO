package bankui;

import java.io.IOException;
import java.util.ArrayList;

import Util.AlertBanco;
import Util.BancoException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Conta;
import main.ContaHelper;

public class SelectContaController {

    @FXML private GridPane gridContas;
    @FXML private Label userName;
    @FXML private Button btnsair;
    @FXML private Button btnContaCorrente;
    @FXML private Button btnContaInvestimento;
    private ArrayList<Conta> contas;

    private void atualizar() throws BancoException {
        userName.setText(App.getUser().getName());

        contas = ContaHelper.getContaByUserId(App.getUser().getId());

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
                try {
                    acessaConta(conta);
                } catch (IOException e1) {
                    
                    
                }
            });
           
            gridContas.add(btAcessa,2,row);
            row ++;
        }
    }

    @FXML
    public static void switchConta() throws IOException {
        App.setRoot("conta");
        App.setTitle("Banco - Conta");
    }

    @FXML
    private void voltar() throws IOException {
        App.setRoot("login");
        App.setTitle("Banco - Login");
    }

    @FXML
    private void criarContaCorrente() throws IOException, BancoException {
        if (contas.size() == 0) {
            try {
                ContaHelper.novaConta(App.getUser().getId(), "cc");
                atualizar();
            } catch (Exception e) {
                new AlertBanco("Erro ao Criar Conta",e.getMessage(), Alert.AlertType.ERROR);
            }
            
        } else {
            new AlertBanco("Erro ao Criar Conta","É permitido apenas uma conta corrente por usuário!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void criarContaInvestimento() throws IOException, BancoException {
        Boolean hasCc = false;
        for (Conta conta : contas) {
                if (conta.getTipo() == "Conta Corrente") {
                    hasCc = true;
                }
        }

        if (hasCc) {
            try {
                ContaHelper.novaConta(App.getUser().getId(), "ci");
                atualizar();
            } catch (Exception e) {
                new AlertBanco("Erro ao Criar Conta",e.getMessage(), Alert.AlertType.ERROR);
            }
            
        } else {
            new AlertBanco("Erro ao Criar Conta","É necessário uma conta corrente, para abrir uma conta investimento", Alert.AlertType.ERROR);
        }
    }

    public void initialize() throws IOException, BancoException {

       atualizar();
    }

    private static void acessaConta(Conta conta) throws IOException {

        App.setContaSelecionada(conta);
        switchConta();
    }
}
