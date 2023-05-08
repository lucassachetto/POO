package Util;

import javafx.scene.control.Alert;

public class AlertBanco {

    public AlertBanco(String title, String message, Alert.AlertType type) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    
}
