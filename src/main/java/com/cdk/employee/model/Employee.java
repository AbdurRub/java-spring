
package com.cdk.employee.model;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cdk.employee.converter.StringListConverter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * This is POJO class for Employee Table
 */
@Data
//@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@Entity
@NoArgsConstructor
@Table(name = "ema_employee", schema = "public")
@Accessors(chain = true)
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID employeeId;
	@Enumerated(EnumType.STRING)
	private EmployeeType employeeType;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]*$")
	@Size(min = 1, max = 15)
	private String firstName;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z]*$")
	@Size(min = 1, max = 15)
	private String lastName;
	private int age;
	private String experience;
	
	@Convert(converter = StringListConverter.class)
	@Column(columnDefinition = "text")
	private List<String> skills;
	private String dateOfBirth;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private List<Phone> phoneNumbers;

	private Double hourlyRate;
	private int hours;
	private Double salary;
}
