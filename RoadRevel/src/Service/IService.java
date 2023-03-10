/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fatma
 */
public interface IService <T> {
    void ajouter(T t) throws SQLException;
    List<T> afficher();
    void modifier(T t);
    void supprimer(T t);
}
