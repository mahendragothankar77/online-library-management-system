package com.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String author;

	@NotBlank
	private String category;

	@NotNull
	private Integer totalQuantity;

	private Integer availableQuantity;

	@Enumerated(EnumType.STRING)
	private BookStatus status; // AVAILABLE / NOT_AVAILABLE

	@PrePersist
	public void init() {
		this.availableQuantity = totalQuantity;
		this.status = BookStatus.AVAILABLE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Long id, @NotBlank String name, @NotBlank String author, @NotBlank String category,
			@NotNull Integer totalQuantity, @NotNull Integer availableQuantity, @NotNull BookStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.category = category;
		this.totalQuantity = totalQuantity;
		this.availableQuantity = availableQuantity;
		this.status = status;
	}

}
