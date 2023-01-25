package bartlomiejfraczak.foodapp.repo.repo;

import bartlomiejfraczak.foodapp.encje.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thefr
 */
@Repository
public interface UzytkownikRepo extends JpaRepository<Uzytkownik, Integer> {

    @Query(value = "select * from public.istnieje_uzytkownik(:login)", nativeQuery = true)
    public boolean istniejeUzytkownik(@Param("login")String login);

    @Query(value = "select * from public.sprawdz_dane_logowania(:login, :hasloHash)", nativeQuery = true)
    public Integer zaloguj(@Param("login")String login, @Param("hasloHash")String hasloHash);
}
