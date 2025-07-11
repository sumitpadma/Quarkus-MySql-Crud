package facultydetails.control;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import facultydetails.entity.FacultyDetailsEntity;


@ApplicationScoped
public class FacultyDetailsRepository implements PanacheRepository<FacultyDetailsEntity> {
}
