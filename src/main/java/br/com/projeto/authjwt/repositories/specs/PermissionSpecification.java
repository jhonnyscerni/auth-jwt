package br.com.projeto.authjwt.repositories.specs;

import br.com.projeto.authjwt.filter.PermissionFilter;
import br.com.projeto.authjwt.models.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class PermissionSpecification implements Specification<Permission> {

    private PermissionFilter allocationFilter;

    @Override
    public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(allocationFilter.getNome())
            .ifPresent(p -> predicates.add(criteriaBuilder.like(root.get("nome"), "%" + allocationFilter.getNome() + "%")));
        Optional.ofNullable(allocationFilter.getDescricao())
            .ifPresent(p -> predicates.add(criteriaBuilder.like(root.get("descricao"), "%" + allocationFilter.getDescricao() + "%")));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
