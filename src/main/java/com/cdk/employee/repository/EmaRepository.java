package com.cdk.employee.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cdk.employee.model.Employee;

/**
 * This is Jpa Repository for Employee class
 */
@Repository
public interface EmaRepository extends JpaRepository<Employee, UUID>, JpaSpecificationExecutor<Employee> {

}