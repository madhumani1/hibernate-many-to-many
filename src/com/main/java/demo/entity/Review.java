/**
 * 
 */
package com.main.java.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 15197
 * Review class to connect with Course class 1 to many uni
 */
@Entity
@Table(name="review")
public class Review {
	// define our fields
	// annotate fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="comment")
	private String comment;

	// constructors
	/**
	 * 
	 */
	public Review() {
		
	}
	
	/**
	 * @param comment
	 */
	public Review(String comment) {
		this.comment = comment;
	}
	
	// getters/setters
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	// toString()
	@Override
	public String toString() {
		return "Review [id=" + id + ", comment=" + comment + "]";
	}
}
