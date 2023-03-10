/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author fatma
 *
 */
public class Produit {
    private int id_prod ;
    private String name_prod ;
    private String price_prod;
    private int quant_prod;

    public Produit() {
    }

    public Produit(int id_prod, String name_prod, String price_prod, int quant_prod) {
        this.id_prod = id_prod;
        this.name_prod = name_prod;
        this.price_prod = price_prod;
        this.quant_prod = quant_prod;
    }
    

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getName_prod() {
        return name_prod;
    }

    public void setName_prod(String name_prod) {
        this.name_prod = name_prod;
    }

    public String getPrice_prod() {
        return price_prod;
    }

    public void setPrice_prod(String price_prod) {
        this.price_prod = price_prod;
    }

    public int getQuant_prod() {
        return quant_prod;
    }

    public void setQuant_prod(int quant_prod) {
        this.quant_prod = quant_prod;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_prod=" + id_prod + ", name_prod=" + name_prod + ", price_prod=" + price_prod + ", quant_prod=" + quant_prod + '}';
    }
    
}
