package bartlomiejfraczak.foodapp.repo.dao;

import bartlomiejfraczak.foodapp.encje.PrzepisInfo;
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

    public void edytujPrzepisInfo(int uzytkownikId, int przepisId, boolean ulubiony, String notatka) {
        repo.edytujPrzepisInfo(uzytkownikId, przepisId, ulubiony, notatka);
    }

    public PrzepisInfo getPrzepisInfo(Integer uzytkownikId, Integer przepisId) {
        return repo.findByUzytkownikIdAndPrzepisId(uzytkownikId, przepisId);
    }

    public List<String> ulubioneUzytkownika(Integer id) {
        String str = repo.ulubioneUzytkownika(id);
        if (str == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(str.split(","));
        }
    }
}
