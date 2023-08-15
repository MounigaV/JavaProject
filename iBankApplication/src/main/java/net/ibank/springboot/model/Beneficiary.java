package net.ibank.springboot.model;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "beneficiary")
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "account_id")
	@NotNull
	private long accountId;
	
	@Column(name = "bene_account_id")
	@NotNull
	private long beneAccountId;
	
	@Column(name = "bene_ifsccode")
	@NotNull
	private String beneIfsccode;
	
	@Column(name = "bene_name")
	@NotNull
	private String beneName;
	
	@Column(name = "status")
	@NotNull
	private String status;
	
	@CreationTimestamp
	private Date createdAt;
	
	@CreationTimestamp
	private Date updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getBeneAccountId() {
		return beneAccountId;
	}

	public void setBeneAccountId(long beneAccountId) {
		this.beneAccountId = beneAccountId;
	}

	public String getBeneIfsccode() {
		return beneIfsccode;
	}

	public void setBeneIfsccode(String beneIfsccode) {
		this.beneIfsccode = beneIfsccode;
	}

	public String getBeneName() {
		return beneName;
	}

	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	
	
}
