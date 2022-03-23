package br.com.projeto.authjwt.repositories.specs;

import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.User;
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
public class UserSpecification implements Specification<User> {

    private UserFilter allocationFilter;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Optional.ofNullable(allocationFilter.getUserName())
            .ifPresent(p -> predicates.add(criteriaBuilder.like(root.get("userName"), "%" + allocationFilter.getUserName() + "%")));
//        Optional.ofNullable(allocationFilter.getRg())
//            .ifPresent(p -> predicates.add(criteriaBuilder.equal(root.get("rg"), allocationFilter.getRg())));
//        Optional.ofNullable(allocationFilter.getCpf())
//            .ifPresent(p -> predicates.add(criteriaBuilder.equal(root.get("cpf"), allocationFilter.getCpf())));
//        Optional.ofNullable(allocationFilter.getDataInicio())
//            .ifPresent(p -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataNascimento"), p)));
//        Optional.ofNullable(allocationFilter.getDataFim())
//            .ifPresent(p -> predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataNascimento"), p)));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
