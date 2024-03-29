package repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.EmployeeModel;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID> {

}
