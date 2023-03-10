/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entities.Panier;
import Entities.Produit;
import Tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



/**
 *
 * @author fatma
 */
public class PanierService {
 Connection cnx = MaConnexion.getInstance().getCnx();
     public void ajouter(Panier pa) throws SQLException {
    String query = "INSERT INTO PANIER(nbr_prod,type_panier,montant_panier) VALUES(?,?,?)";    
                PreparedStatement ste = cnx.prepareStatement(query);
                try
                {
                    ste.setInt(1, pa.getNbr_prod());
                    ste.setString(2, pa.getType_panier());
                    ste.setFloat(3, pa.getMontant_panier());
                    ste.executeUpdate();
                    System.out.println("Panier Added Successfully");
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }

    }
    
}
