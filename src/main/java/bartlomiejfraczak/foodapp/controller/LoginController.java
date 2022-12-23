/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.controller;

import bartlomiejfraczak.foodapp.encje.Uzytkownik;
import bartlomiejfraczak.foodapp.repo.dao.UzytkownikDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author thefr
 */
@RestController
//@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UzytkownikDao uzytkownikDao;

    @RequestMapping("/zaloguj")
    public Integer zaloguj(
            @RequestBody Uzytkownik uzytkownik
    ) {
        return uzytkownikDao.zaloguj(uzytkownik);

    }

    @RequestMapping("/zarejestruj")
    public boolean zarejestruj(
            @RequestBody Uzytkownik uzytkownik
    ) {
        if (uzytkownikDao.istniejeUzytkownik(uzytkownik)) {
            return false;
        } else {
            uzytkownikDao.zarejestruj(uzytkownik);
            return true;
        }
    }
}
