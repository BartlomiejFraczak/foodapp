/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.repo.dao;

import bartlomiejfraczak.foodapp.encje.Uzytkownik;
import bartlomiejfraczak.foodapp.repo.repo.UzytkownikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thefr
 */
@Service
public class UzytkownikDao {

    @Autowired
    private UzytkownikRepo repo;

    public boolean istniejeUzytkownik(Uzytkownik uzytkownik) {
        return repo.istniejeUzytkownik(uzytkownik.getLogin());
    }

    public boolean istniejeUzytkownik(String uzytkownikLogin) {
        return repo.istniejeUzytkownik(uzytkownikLogin);
    }

    public void zarejestruj(Uzytkownik uzytkownik) {
        repo.save(uzytkownik);
    }

    public Integer zaloguj(Uzytkownik uzytkownik) {
        return repo.zaloguj(uzytkownik.getLogin(), uzytkownik.getHasloHash());
    }

}
