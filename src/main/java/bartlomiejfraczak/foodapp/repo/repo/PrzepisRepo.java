
package bartlomiejfraczak.foodapp.repo.repo;

import bartlomiejfraczak.foodapp.encje.PrzepisInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thefr
 */
@Repository
public interface PrzepisRepo extends JpaRepository<PrzepisInfo, Integer> {

    @Query(value = "select count(*) "
            + "from public.edytuj_przepis_info(:uzytkownikId, :przepisId, :ulubiony, :notatka)", nativeQuery = true)
    public void edytujPrzepisInfo(
            @Param("uzytkownikId") int uzytkownikId,
            @Param("przepisId") int przepisId,
            @Param("ulubiony") boolean ulubiony,
            @Param("notatka") String notatka);

    @Query(value = "select * from public.ulubione_uzytkownika(:uzytkownikId)", nativeQuery = true)
    public String ulubioneUzytkownika(@Param("uzytkownikId") Integer uzytkownikId);
    
    @Query(value = "select * "
            + "from public.przepis_info p "
            + "where p.uzytkownik_id = :uzytkownikId and p.przepis_id = :przepisId",
            nativeQuery = true)
    public PrzepisInfo findByUzytkownikIdAndPrzepisId(Integer uzytkownikId, Integer przepisId);

}
