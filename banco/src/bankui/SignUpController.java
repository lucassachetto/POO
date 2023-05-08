package bankui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.UsuarioHelper;

import java.io.IOException;

import Util.AlertBanco;
import Util.BancoException;

public class SignUpController {

    @FXML private TextField tfSignUpNome;
    @FXML private PasswordField pfSignUpSenha;
    @FXML private TextField tfSignUpCpf;

    public void initialize() {
        
    }

    @FXML
    private void switchLogin() throws IOException {
        App.setRoot("login");
        App.setTitle("Banco - Entrar");
    }

    @FXML
    public static void switchSelectConta() throws IOException, BancoException {
        App.setRoot("selectconta");
        App.setTitle("Banco - Selecionar Conta");
    }

    @FXML
    private void signUp() throws IOException {
        
        try {
            App.setUser(UsuarioHelper.novoUsuario(tfSignUpNome.getText(), pfSignUpSenha.getText(), tfSignUpCpf.getText()));
            switchSelectConta();

        } catch (Exception e) {
            new AlertBanco("Falha ao registrar", e.getMessage(), Alert.AlertType.ERROR);
        }

    }
}