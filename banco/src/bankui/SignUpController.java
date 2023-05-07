package bankui;

import javafx.fxml.FXML;
import java.io.IOException;

public class SignUpController {

    public void initialize() {
        
    }

    @FXML
    private void switchLogin() throws IOException {
        App.setRoot("login");
        App.setTitle("Banco - Entrar");
    }

    @FXML
    private void signUp() throws IOException {
        // Criar Conta
    }
}