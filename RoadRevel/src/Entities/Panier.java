/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author fatma
 */
public class Panier {
    private int id_panier ;
    private int nbr_prod ;
    private String type_panier;
    private float montant_panier;
    public Panier() {
    }

    public Panier(int id_panier, int nbr_prod, String type_panier,float montant_panier) {
        this.id_panier = id_panier;
        this.nbr_prod = nbr_prod;
        this.type_panier = type_panier;
        this.montant_panier = montant_panier;
    }

    public float getMontant_panier() {
        return montant_panier;
    }

    public void setMontant_panier(float montant_panier) {
        this.montant_panier = montant_panier;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getNbr_prod() {
        return nbr_prod;
    }

    public void setNbr_prod(int nbr_prod) {
        this.nbr_prod = nbr_prod;
    }

    public String getType_panier() {
        return type_panier;
    }

    public void setType_panier(String type_panier) {
        this.type_panier = type_panier;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", nbr_prod=" + nbr_prod + ", type_panier=" + type_panier + '}';
    }

   
    

    
}
