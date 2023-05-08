package bankui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.UsuarioHelper;

import java.io.IOException;

import Util.AlertBanco;
import Util.BancoException;

public class LoginController {

    @FXML private TextField tfLoginEmail;
    @FXML private PasswordField pfLoginSenha;
    
    public void initialize() {

    }

    @FXML
    private void switchSignUp() throws IOException {
        App.setRoot("signup");
        App.setTitle("Banco - Criar Conta");
    }

    @FXML
    public static void switchSelectConta() throws IOException, BancoException {
        App.setRoot("selectconta");
        App.setTitle("Banco - Selecionar Conta");
    }

    @FXML
    private void login() throws IOException {
        
        // Login
        try {

            App.setUser(UsuarioHelper.getUsuario(tfLoginEmail.getText(), pfLoginSenha.getText()));
            switchSelectConta();
            
        } catch (Exception e) {

            new AlertBanco("Falha de autenticação", e.getMessage(), Alert.AlertType.ERROR);
        }
       
    }
}