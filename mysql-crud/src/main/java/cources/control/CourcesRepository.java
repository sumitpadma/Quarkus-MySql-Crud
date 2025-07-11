package cources.control;


import cources.entity.CourcesEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CourcesRepository implements PanacheRepository<CourcesEntity> {
}
