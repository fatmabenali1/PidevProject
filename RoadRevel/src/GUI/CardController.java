package GUI;

import Entities.Panier;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CardController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField email;

    @FXML
    private TextField numcard;

    @FXML
    private Button btnPayetEtExtrairePDF;

    @FXML
    private Button goback;

    @FXML
    private TextField datecard;

    @FXML
    private TextField zip;

    @FXML
    private TextField prenom;

    @FXML
    private TextField ccv;

    @FXML
    void PayetEtExtrairePDF(ActionEvent event) {
        if(inputsControl())
        {
            
        
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Panier p = (Panier) stage.getUserData();
            Document doc = new Document();
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Ben Mahmoud\\Desktop\\Facture.pdf"));
                doc.open();
                doc.add(new Paragraph("Facture pour Mr/Mme  "+nom.getText()+" "+prenom.getText()));
                doc.add(new Paragraph("--------------"));
                doc.add(new Paragraph("--------------"));
                doc.add(new Paragraph("Total produit acheté = "+p.getNbr_prod()));
                doc.add(new Paragraph("Le type de vote panier = "+p.getType_panier()));
                doc.add(new Paragraph("Le montant payé = "+p.getMontant_panier()));
                Image img = Image.getInstance("C:\\Users\\Ben Mahmoud\\Documents\\NetBeansProjects\\Roadrevel\\src\\GUI\\paid.png");
                doc.add(img);
                doc.close();
               
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Paiement efectué avec succes!");
        alert.setContentText("Votre Paiement a été validé, merci de consulter votre fichier de facture dans votre Bureau ");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.isPresent()&& result.get()==ButtonType.OK)
        {
        Parent root = FXMLLoader.load(getClass().getResource("PaiementGUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        }
            
                

        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }}

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
      public boolean inputsControl(){
        if (nom.getText().trim().isEmpty() || prenom.getText().trim().isEmpty() || email.getText().trim().isEmpty()  ||datecard.getText().trim().isEmpty() ||zip.getText().trim().isEmpty()||numcard.getText().trim().isEmpty()||ccv.getText().trim().isEmpty()   )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("\n" +
                    "Veuillez vérifier les champs");
            alert.show();
            return false;
        }else if (!isValidEmail(email.getText()))
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("\n" +
                    "Email non valide");
            alert.show();
            return false;
        }
        else if (numcard.getText().matches("\\d{17}"))
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("\n" +
                    "numero de carte non valide");
            alert.show();
            return false;
        }
        else if (ccv.getText().matches("\\d{4}"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("\n" +
                    "CCV non valide");
            alert.show();
            return false;
        }
        return true;
    }
 private boolean isValidEmail(String text) {
        Pattern p =Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(text);
        if (m.find() && m.group().equals(text))
        {
            return true ;
        }
        return false;
        }
}
