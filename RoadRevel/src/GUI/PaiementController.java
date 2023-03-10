package GUI;

import Entities.Panier;
import Entities.Produit;
import Service.PanierService;
import Service.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaiementController implements Initializable {
 @FXML
    private TableColumn<?, ?> colNomProduit;
@FXML
    private TableView<Produit> tabProd;
    @FXML
    private TableColumn<?, ?> colPriceProduit;
  @FXML
    private Label Countpanier;
    @FXML
    private TableColumn<?, ?> colQtProduit;
    @FXML
    private Button btnAjouterAuPanier;

    @FXML
    private Button btnAjouterProduit;
    int count=0;
    @FXML
    private Button btnPayer;
    ProduitService PS = new ProduitService();
    PanierService PaS = new PanierService();
    private final ObservableList<Produit> dataList = FXCollections.observableArrayList();
    ObservableList<Produit> listProduit = PS.afficher();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       afficher();
    }
    @FXML
    void AjouterPanier(ActionEvent event) {
        Produit p = tabProd.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Panier!");
            alert.setContentText("Produit du nom "+p.getName_prod()+"a été ajouté avec succès ");
            alert.show();
        Countpanier.setText(String.valueOf(Integer.parseInt(Countpanier.getText())+1));
        count = count+Integer.valueOf(p.getPrice_prod());
        
    }

    @FXML
    void AjouterProduit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

  

    protected void afficher(){
        ObservableList<Produit> list = PS.afficher();
        colNomProduit.setCellValueFactory(new PropertyValueFactory<>("name_prod"));
        colPriceProduit.setCellValueFactory(new PropertyValueFactory<>("price_prod"));
        colQtProduit.setCellValueFactory(new PropertyValueFactory<>("quant_prod"));
        tabProd.setItems(list);
    }
    @FXML
    void Afficherpanier(MouseEvent event) throws IOException, SQLException {
        if(Countpanier.getText().equals("0"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Panier!");
            alert.setContentText("Panier vide ! ");
            alert.show();
        }
        else
        {
        Panier p = new Panier();
        int x = Integer.parseInt(Countpanier.getText());
            
        p.setNbr_prod(x);
        if(x==1 || x==2|| x==3)
        {
            
            p.setType_panier("SMALL");
        }else if(x==4 || x==5|| x==6)
        {
             
             p.setType_panier("MEDIUEM");
        }else 
        {
             
             p.setType_panier("BIG");
        }
        p.setMontant_panier(count);
        Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setUserData(p);
        PaS.ajouter(p);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
       
    }

}


