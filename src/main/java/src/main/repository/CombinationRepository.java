package src.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.main.entity.History;

import java.util.List;

public interface CombinationRepository extends JpaRepository<History, Integer> {

    // to retrieve request by ID
    History getRequestHistoryById(int id);
}
