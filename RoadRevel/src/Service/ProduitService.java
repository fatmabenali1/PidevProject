/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entities.Produit;
import Tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author fatma
 */
public class ProduitService implements IService<Produit>{

        Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Produit p) throws SQLException {
    String query = "INSERT INTO PRODUIT(name_prod,price_prod,quant_prod) VALUES(?,?,?)";    
                PreparedStatement ste = cnx.prepareStatement(query);
                try
                {
                    ste.setString(1, p.getName_prod());
                    ste.setString(2, p.getPrice_prod());
                    ste.setInt(3, p.getQuant_prod());
                    ste.executeUpdate();
                    System.out.println("Produit Added Successfully");
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }

    }

    @Override
    public ObservableList<Produit> afficher() {
        ObservableList<Produit> listProduit = FXCollections.observableArrayList();
        String query = "SELECT * FROM PRODUIT";
            try 
            {
                Statement ste = cnx.createStatement();
                ResultSet rs = ste.executeQuery(query);
                    while(rs.next())
                    {
                        Produit p = new Produit();
                        p.setId_prod(rs.getInt("id_prod"));
                        p.setName_prod(rs.getString("name_prod"));
                        p.setPrice_prod(rs.getString("price_prod"));
                        p.setQuant_prod(rs.getInt("quant_prod"));
                        listProduit.add(p);
                    }
            }
            catch (SQLException e )
            {
                e.getMessage();
            }
            return listProduit;

    }

    @Override
    public void modifier(Produit P) {
           String query = "UPDATE PRODUIT SET name_prod = '" + P.getName_prod()+ "', price_prod = '" +
                P.getPrice_prod()+ "', quant_prod = '" + P.getQuant_prod() + "' WHERE id_prod = " + P.getId_prod()+ "";;
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Produit Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Produit P) {
       String query = "DELETE FROM PRODUIT WHERE id_PROD = " + P.getId_prod() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Produit Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
}
