package com.accolite.Entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "AuditInfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditInfo
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long request_id;
	String status;
	String request;
	String response;
	Timestamp create_ts;
	Timestamp update_ts;
}
