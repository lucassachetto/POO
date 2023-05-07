package bankui;

import javafx.fxml.FXML;
import java.io.IOException;

public class LoginController {

    public void initialize() {

    }

    @FXML
    private void switchSignUp() throws IOException {
        App.setRoot("signup");
        App.setTitle("Banco - Criar Conta");
    }

    @FXML
    private void login() throws IOException {
        // Login
    }
}