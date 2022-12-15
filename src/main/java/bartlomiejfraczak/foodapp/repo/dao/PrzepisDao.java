/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.repo.dao;

import bartlomiejfraczak.foodapp.encje.Ulubiony;
import bartlomiejfraczak.foodapp.repo.repo.PrzepisRepo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thefr
 */
@Service
public class PrzepisDao {

    @Autowired
    private PrzepisRepo repo;

    public void dodaj(Ulubiony ulubiony) {
        repo.save(ulubiony);
    }

    public List<String> ulubioneUzytkownika(Integer id) {
        String str = repo.ulubioneUzytkownika(id);
        if (str == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(str.split(","));
        }
    }

    public boolean czyUzytkownikLubi(Integer uzytkownikId, Integer przepisId) {
        return repo.czyUzytkownikLubi(uzytkownikId, przepisId);
    }

    public void usunZUlubionych(Integer uzytkownikId, Integer przepisId) {
        repo.usunZUlubionych(uzytkownikId, przepisId);
    }

}
