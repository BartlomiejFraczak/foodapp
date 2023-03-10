package bartlomiejfraczak.foodapp.controller;

import bartlomiejfraczak.foodapp.encje.Przepis;
import bartlomiejfraczak.foodapp.encje.PrzepisInfo;
import bartlomiejfraczak.foodapp.encje.PrzepisSzczegolowy;
import bartlomiejfraczak.foodapp.komunikacja.SpoonacularAPI;
import bartlomiejfraczak.foodapp.repo.dao.PrzepisDao;
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
        return SpoonacularAPI.getInstancja().searchRecipes(query, cuisine, diet, intolerances, includeIngredients, true);
    }

    @GetMapping("/szczegolowy")
    public PrzepisSzczegolowy getPrzepisSzczegolowy(
            @RequestParam(required = true) String przepisId,
            @RequestParam(required = true) String uzytkownikId
    ) {
        PrzepisSzczegolowy przepisSzczegolowy = SpoonacularAPI.getInstancja().getRecipeInformation(przepisId);
        PrzepisInfo pi = przepisDao.getPrzepisInfo(Integer.valueOf(uzytkownikId), Integer.valueOf(przepisId));
        if (pi != null) {
            przepisSzczegolowy.setNotatka(pi.getNotatka());
            przepisSzczegolowy.setUlubiony(pi.getUlubiony());
        } else {
            przepisSzczegolowy.setNotatka("");
            przepisSzczegolowy.setUlubiony(false);
        }
        return przepisSzczegolowy;
    }

    @GetMapping("/ulubione")
    public List<PrzepisSzczegolowy> ulubioneUzytkownika(
            @RequestParam(required = true) Integer uzytkownikId
    ) {
        List<String> ids = przepisDao.ulubioneUzytkownika(uzytkownikId);
        return SpoonacularAPI.getInstancja().getRecipeInformationBulk(ids);
    }

    @RequestMapping("/edytujinfo")
    public void edytujPrzepisInfo(
            @RequestParam(required = true) int uzytkownikId,
            @RequestParam(required = true) int przepisId,
            @RequestParam(required = true) boolean ulubiony,
            @RequestParam(required = true) String notatka
    ) {
        przepisDao.edytujPrzepisInfo(uzytkownikId, przepisId, ulubiony, notatka);
    }
}
