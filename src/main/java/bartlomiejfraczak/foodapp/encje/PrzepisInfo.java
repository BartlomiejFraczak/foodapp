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
public class PrzepisInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int uzytkownikId;
    private int przepisId;
    private boolean ulubiony;
    private String notatka;

    public PrzepisInfo() {
    }

    public PrzepisInfo(int id, int uzytkownikId, int przepisId, boolean ulubiony, String notatka) {
        this.id = id;
        this.uzytkownikId = uzytkownikId;
        this.przepisId = przepisId;
        this.ulubiony = ulubiony;
        this.notatka = notatka;
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

    public boolean getUlubiony() {
        return ulubiony;
    }

    public void setUlubiony(boolean ulubiony) {
        this.ulubiony = ulubiony;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }
    
    

}
