/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.controller;

import bartlomiejfraczak.foodapp.encje.Uzytkownik;
import bartlomiejfraczak.foodapp.repo.dao.UzytkownikDao;
import bartlomiejfraczak.foodapp.util.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author thefr
 */
@RestController
public class LoginController {

    @Autowired
    private UzytkownikDao uzytkownikDao;

    @RequestMapping("/zaloguj")
    public Integer zaloguj(
            @RequestBody Uzytkownik uzytkownik
    ) {
        String hash = Hasher.getInstancja().hash(uzytkownik.getHasloHash());
        uzytkownik.setHasloHash(hash);
        return uzytkownikDao.zaloguj(uzytkownik);

    }

    @RequestMapping("/zarejestruj")
    public boolean zarejestruj(
            @RequestBody Uzytkownik uzytkownik
    ) {
        String hash = Hasher.getInstancja().hash(uzytkownik.getHasloHash());
        uzytkownik.setHasloHash(hash);
        if (uzytkownikDao.istniejeUzytkownik(uzytkownik)) {
            return false;
        } else {
            uzytkownikDao.zarejestruj(uzytkownik);
            return true;
        }
    }
}
