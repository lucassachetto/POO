package bankui;

import java.io.IOException;

import Util.AlertBanco;
import Util.BancoException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.Conta;
import main.ContaCorrente;
import main.ContaHelper;
import main.ContaInvestimento;
import main.Emprestimo;

public class ContaController {

    @FXML private Label lblSaldo;
    @FXML private Label idConta;
    @FXML private Label lblmaxemp;
    @FXML private Label lblPago;
    @FXML private Label lblEmprestado;
    @FXML private Label lblStatus;
    @FXML private Button btndeposito;
    @FXML private Button btnsaque;
    @FXML private Button btnpix;
    @FXML private Button btnemprestimo;
    @FXML private TextArea historicot;
    @FXML private TextField cpf;
    @FXML private TextField valor;
    @FXML private TextField idcontadestino;
    @FXML private TextField valoremp;
    @FXML private GridPane gridemprestimos;

    

    
    
    private Conta c;
    private ContaCorrente cc;
    private ContaInvestimento ci;

    private void reload() throws BancoException {

        c = ContaHelper.getContaById(c.getIdConta());
     
        if (c.getTipo() == "Conta Corrente") {
            cc = new ContaCorrente(c);
        } else {
            ci = new ContaInvestimento(c);
        }

        loadFunctions();
        loadContaValues(c);
    }

    private void loadFunctions() throws BancoException {
        
        if (c.getTipo() != "Conta Corrente") {
            btndeposito.setDisable(true);
            btnsaque.setDisable(true);
            btnpix.setDisable(true);
            btnemprestimo.setDisable(true);
            lblmaxemp.setText("");
        } else {
            lblmaxemp.setText(""+cc.maxValorEmprestimo());
            btndeposito.setDisable(false);
            btnsaque.setDisable(false);
            btnpix.setDisable(false);
            btnemprestimo.setDisable(false);

            gridemprestimos.getChildren().clear();

            gridemprestimos.add(lblStatus,0,0);    
            gridemprestimos.add(lblEmprestado,1,0);
            gridemprestimos.add(lblPago,2,0);

            int row = 1;
            for (Emprestimo emprestimo : cc.getEmprestimos()) {
                

                Label lb = new Label();
                lb.setText(emprestimo.getStatus());
                gridemprestimos.add(lb,0,row);

                Label lb2 = new Label();
                lb2.setText(""+emprestimo.getValorEmprestado());
                gridemprestimos.add(lb2,1,row);

                Label lb3 = new Label();
                lb3.setText(""+emprestimo.getValorPago());
                gridemprestimos.add(lb3,2,row);

                row ++;
            }
        }
    }

    private void loadContaValues(Conta c) { 
        lblSaldo.setText(""+c.getSaldo());
        idConta.setText(""+c.getIdConta());
        historicot.setText(c.verHistorico());
    }

    public void initialize() throws BancoException {

        c = App.getContaSelecionada();
        
        reload();
    }

    @FXML
    private void depositar() throws BancoException,IOException {

        try {
            cc.deposito(Double.valueOf(valor.getText()));
            reload();
        } catch (Exception e) {
            new AlertBanco("Falha ao depositar", e.getMessage(), Alert.AlertType.ERROR);
        }
        
    }

    @FXML
    private void sacar() throws BancoException,IOException {
        try {
            cc.saque(Double.valueOf(valor.getText()));
            reload();
        } catch (Exception e) {
            new AlertBanco("Falha ao sacar", e.getMessage(), Alert.AlertType.ERROR);
        }
       
    }

    @FXML
    private void pix() throws BancoException,IOException {
        try {
            cc.pix(cpf.getText() ,Double.valueOf(valor.getText()));
            reload();
        } catch (Exception e) {
            new AlertBanco("Falha ao fazer PIX", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void transferir() throws BancoException,IOException {
        try {
            c.transferenciaInterna(Long.valueOf(idcontadestino.getText()) ,Double.valueOf(valor.getText()));
            reload();
        } catch (Exception e) {
            new AlertBanco("Falha ao fazer transferencia", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void emprestar() throws BancoException,IOException {
        try {
            cc.fazerEmprestimo(Double.valueOf(valoremp.getText()));
            reload();
        } catch (Exception e) {
            new AlertBanco("Falha ao fazer emprestimo", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void pagarEmprestimo() throws BancoException,IOException {
        try {
            cc.pagarEmprestimo(Double.valueOf(valoremp.getText()));
            reload();
        } catch (Exception e) {
            new AlertBanco("Falha ao pagar emprestimo", e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void sair() throws IOException {
        App.setRoot("selectconta");
        App.setTitle("Banco - Selecionar Conta");
    }
}
