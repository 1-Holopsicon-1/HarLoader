package harloader.repositories;


import harloader.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsRepo extends JpaRepository<Request, Long>{}
