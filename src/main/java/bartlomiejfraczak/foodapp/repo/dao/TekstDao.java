/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.repo.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bartlomiejfraczak.foodapp.encje.Tekst;
import bartlomiejfraczak.foodapp.repo.repo.TekstRepo;

/**
 *
 * @author thefr
 */
@Service
public class TekstDao {

    @Autowired
    private TekstRepo repo;

    public void post(Tekst tekst) {
        repo.save(tekst);
    }

    public List<Tekst> getTeksty() {
        List<Tekst> teksty = new ArrayList<>();
        repo.findAll()
                .forEach(teksty::add);
        return teksty;
    }
}
