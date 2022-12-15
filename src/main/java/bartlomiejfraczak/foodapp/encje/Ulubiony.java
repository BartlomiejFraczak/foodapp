/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.encje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author thefr
 */
@Entity
public class Ulubiony {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int uzytkownikId;
    private int przepisId;

    public Ulubiony(int uzytkownikId, int przepisId) {
        this.uzytkownikId = uzytkownikId;
        this.przepisId = przepisId;
    }

    public Ulubiony() {
    }

    public Ulubiony(int id, int uzytkownikId, int przepisId) {
        this.id = id;
        this.uzytkownikId = uzytkownikId;
        this.przepisId = przepisId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUzytkownikId() {
        return uzytkownikId;
    }

    public void setUzytkownikId(int uzytkownikId) {
        this.uzytkownikId = uzytkownikId;
    }

    public int getPrzepisId() {
        return przepisId;
    }

    public void setPrzepisId(int przepisId) {
        this.przepisId = przepisId;
    }

}
