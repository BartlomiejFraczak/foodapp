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
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String hasloHash;

    public Uzytkownik() {
    }

    public Uzytkownik(int id, String login, String hasloHash) {
        this.id = id;
        this.login = login;
        this.hasloHash = hasloHash;
    }

    public Uzytkownik(String login, String hasloHash) {
        this.login = login;
        this.hasloHash = hasloHash;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" + "id=" + id + ", login=" + login + ", haslo=" + hasloHash + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHasloHash() {
        return hasloHash;
    }

    public void setHasloHash(String hasloHash) {
        this.hasloHash = hasloHash;
    }

}
