package harloader.repositories;


import harloader.models.HarArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarArchiveRepo extends JpaRepository<HarArchive, Long> {}
