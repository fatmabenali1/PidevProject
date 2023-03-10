package GUI;

import Entities.Panier;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PanierController implements Initializable{

    @FXML
    private Button payer;

    @FXML
    private Button goback;

    @FXML
    private Label nbrprod;

    @FXML
    private Label typepanier;
     @FXML
    private Label mntpanier;
    
     Panier p1 = new Panier();
    @FXML
    void btnpayer(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Card.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setUserData(p1);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PaiementGUI.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    @FXML
    void showpanier(MouseEvent event) {
    {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Panier p = (Panier) stage.getUserData();
        nbrprod.setText(String.valueOf(p.getNbr_prod()));
        typepanier.setText(p.getType_panier());
        mntpanier.setText(p.getMontant_panier()+"Dt");
        p1.setMontant_panier(p.getMontant_panier());
        p1.setNbr_prod(p.getNbr_prod());
        p1.setType_panier(p.getType_panier());
    }

}
}
