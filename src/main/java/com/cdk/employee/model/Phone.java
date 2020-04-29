
package com.cdk.employee.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * This is POJO class for Employee Table
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "ema_phone")
@Accessors(chain = true)
public class Phone {
	public enum Type {
		HOME, WORK, MOBILE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID phoneId;
	@Column(name ="employee_id")
	private UUID employeeId;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	private String phoneNumber;
}
