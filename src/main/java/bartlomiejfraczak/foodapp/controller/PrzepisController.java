/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.controller;

import bartlomiejfraczak.foodapp.encje.Przepis;
import bartlomiejfraczak.foodapp.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapp.encje.Ulubiony;
import bartlomiejfraczak.foodapp.komunikacja.SpoonacularAPI;
import bartlomiejfraczak.foodapp.repo.dao.PrzepisDao;
import bartlomiejfraczak.foodapp.repo.dao.TekstDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author thefr
 */
@RestController
@RequestMapping("/przepis")
public class PrzepisController {

    @Autowired
    private PrzepisDao przepisDao;

    @GetMapping("/szukaj")
    public List<Przepis> getPrzepis(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String diet,
            @RequestParam(required = false) String intolerances,
            @RequestParam(required = false) String includeIngredients
    ) {
        return SpoonacularAPI.getInstancja().searchRecipes(query, cuisine, diet, intolerances, includeIngredients, true);// cuisine, diet, intolerances, includeIngredients, true);
    }

    @GetMapping("/szczegolowy")
    public PrzepisSzczegolowy getPrzepisSzczegolowy(
            @RequestParam(required = true) String id
    ) {
        return SpoonacularAPI.getInstancja().getRecipeInformation(id);
    }

    @GetMapping("/ulubione")
    public List<PrzepisSzczegolowy> ulubioneUzytkownika(
            @RequestParam(required = true) Integer uzytkownikId
    ) {
        List<String> ids = przepisDao.ulubioneUzytkownika(uzytkownikId);
        return SpoonacularAPI.getInstancja().getRecipeInformationBulk(ids);
    }

    @RequestMapping("/ulubione/dodaj")
    public void dodajDoUlubionych(
            @RequestParam(required = true) Integer uzytkownikId,
            @RequestParam(required = true) Integer przepisId
    ) {
        przepisDao.dodaj(new Ulubiony(uzytkownikId, przepisId));
    }
    
    @RequestMapping("/ulubione/usun")
    public void usunZUlubionych(
            @RequestParam(required = true) Integer uzytkownikId,
            @RequestParam(required = true) Integer przepisId
    ) {
        przepisDao.usunZUlubionych(uzytkownikId, przepisId);
    }

    @RequestMapping("/ulubione/czy")
    public boolean czyUzytkownikLubi(
            @RequestParam(required = true) Integer uzytkownikId,
            @RequestParam(required = true) Integer przepisId
    ){
        return przepisDao.czyUzytkownikLubi(uzytkownikId, przepisId);
        
    }
    
}
