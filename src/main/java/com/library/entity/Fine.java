package com.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private BookIssue issue;

	private int amount;

	public Fine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fine(Long id, BookIssue issue, int amount) {
		super();
		this.id = id;
		this.issue = issue;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BookIssue getIssue() {
		return issue;
	}

	public void setIssue(BookIssue issue) {
		this.issue = issue;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
