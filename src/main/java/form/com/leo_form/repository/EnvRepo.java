package form.com.leo_form.repository;

import form.com.leo_form.controler.env;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnvRepo extends JpaRepository<env, Long> {

    @Query("""
        SELECT e.id, e.name, e.lastname
        FROM env e
    """)
    List<Object[]> buscarNomes();
}