/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.repo.repo;

import org.springframework.stereotype.Repository;
import bartlomiejfraczak.foodapp.encje.Tekst;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author thefr
 */
@Repository
public interface TekstRepo extends JpaRepository<Tekst,Integer> {
    
}
