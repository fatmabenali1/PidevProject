package GUI;

import Entities.Produit;
import Service.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class GestionProduitController implements Initializable {
     private final ObservableList<Produit> dataList = FXCollections.observableArrayList();
    
   @FXML
    private TableColumn<?, ?> colNomProduit;

    @FXML
    private TableColumn<?, ?> colPriceProduit;

    @FXML
    private TableColumn<?, ?> colQtProduit;

    @FXML
    private TextField nomproduit;

      @FXML
    private TextField priceproduit;

    @FXML
    private TextField Qtproduit;

    @FXML
    private Button btnAjouterProduit;

    @FXML
    private TableView<Produit> tabProd;

    @FXML
    private Button btnModifierProduit;

    @FXML
    private Button btnSupprimerProduit;

    @FXML
    private TextField rechercherparnom;
    
    @FXML
    private Button goback;
    Produit produit = new Produit();
    ProduitService PS = new ProduitService();
    ObservableList<Produit> listProduit = PS.afficher();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showProduitFiltred();
        
    }
    @FXML
    void AjouterProduit(ActionEvent event) throws SQLException {
       
            if(inputsControl())
            {
                produit.setName_prod(nomproduit.getText());
                produit.setPrice_prod(priceproduit.getText());
                produit.setQuant_prod(Integer.parseInt(Qtproduit.getText()));
                PS.ajouter(produit);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Produit Ajoutée !");
                alert.setContentText("Produit Ajoutée Avec Succés!");
                alert.show();
                afficher();
            }
        
    }

    @FXML
    void ModifierProduit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("Voulez vous vraiment modifier ce Produit?");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent()&& result.get()==ButtonType.OK)
        {
            Produit p = tabProd.getSelectionModel().getSelectedItem();
            p.setName_prod(nomproduit.getText());
            p.setPrice_prod(priceproduit.getText());
            p.setQuant_prod(Integer.parseInt(Qtproduit.getText()));
            if(inputsControl())
            {
                PS.modifier(p);
            }
        }
        afficher();
    }

    @FXML
    void SupprimerProduit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("Voulez vous vraiment supprimer ce Produit?");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent()&& result.get()==ButtonType.OK)
        {
            Produit p = tabProd.getSelectionModel().getSelectedItem();
            PS.supprimer(p);
            
        }
       afficher();
    }

    @FXML
    void rechercherparnom(ActionEvent event) {

    }
    @FXML
    void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PaiementGUI.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public boolean inputsControl(){
        if (nomproduit.getText().trim().isEmpty() || priceproduit.getText().trim().isEmpty() || Qtproduit.getText().trim().isEmpty()  )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("\n" +
                    "Veuillez vérifier les champs");
            alert.show();
            return false;
        }
        return true;
    }
 public void showProduitFiltred() {
        try {
             colNomProduit.setCellValueFactory(new PropertyValueFactory<>("name_prod"));
             colPriceProduit.setCellValueFactory(new PropertyValueFactory<>("price_prod"));
             colQtProduit.setCellValueFactory(new PropertyValueFactory<>("quant_prod"));
             tabProd.setItems(listProduit);
             dataList.addAll(listProduit);
             FilteredList<Produit> filteredData = new FilteredList<>(listProduit, b -> true);
             rechercherparnom.textProperty().addListener((observable, oldValue, newValue) -> {
                 filteredData.setPredicate(produit -> {
                  if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                  String lowerCaseFilter = newValue.toLowerCase();
                  
                 if (produit.getName_prod().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.

                    } else if (String.valueOf(produit.getPrice_prod()).indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else if (String.valueOf(produit.getQuant_prod()).indexOf(lowerCaseFilter) != -1)
                        return true;

                    else
                        return false; // Does not match.
                });
            });
             // 3. Wrap the FilteredList in a SortedList.
            SortedList<Produit> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            //  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tabProd.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tabProd.setItems(sortedData);
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    protected void afficher(){
        ObservableList<Produit> list = PS.afficher();
        colNomProduit.setCellValueFactory(new PropertyValueFactory<>("name_prod"));
        colPriceProduit.setCellValueFactory(new PropertyValueFactory<>("price_prod"));
        colQtProduit.setCellValueFactory(new PropertyValueFactory<>("quant_prod"));
        tabProd.setItems(list);
    }

    
    @FXML
    public void showselecteditem(MouseEvent event) {
            Produit p = tabProd.getSelectionModel().getSelectedItem();
            nomproduit.setText(p.getName_prod());
            priceproduit.setText(p.getPrice_prod());
            String x = String.valueOf(p.getQuant_prod());
            Qtproduit.setText(x);
    }
}
