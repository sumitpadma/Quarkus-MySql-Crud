package department.control;

import department.entity.DepartmentEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartmentRepository implements PanacheRepository<DepartmentEntity> {
}
