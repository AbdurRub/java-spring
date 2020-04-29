package com.cdk.employee.repository;

import java.text.MessageFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


import com.cdk.employee.model.Employee;
import com.cdk.employee.model.SearchCriteria;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeSpecs implements Specification<Employee>{

	private SearchCriteria criteria;
	
	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } 
        else if (criteria.getOperation().equalsIgnoreCase("=<")) {
            return criteriaBuilder.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThan(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
	}

   public static Specification<Employee> getPhoneType(String phoneType) {
       return (root, query, builder) -> builder.like(root.join("phoneNumbers").get("type"), contains(phoneType));
   }

   private static String contains(String expression) {
       return MessageFormat.format("%{0}%", expression);
   };
   
}