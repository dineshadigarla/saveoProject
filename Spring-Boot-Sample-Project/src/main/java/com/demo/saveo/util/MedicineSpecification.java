package com.demo.saveo.util;

import com.demo.saveo.dto.SearchCriteria;
import com.demo.saveo.model.Medicine;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MedicineSpecification implements Specification<Medicine> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate
            (Root<Medicine> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase("gt")) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("lt")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("eq")) {
               return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        else if(criteria.getOperation().equalsIgnoreCase("like")){
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }

        }
        return null;
    }


}
