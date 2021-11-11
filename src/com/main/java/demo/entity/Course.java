/**
 * 
 */
package com.main.java.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author 15197
 *	1 Instructor for 1 or more courses
 */
@Entity
@Table(name="course")
public class Course {
	// define our fields
	// annotate fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	// course has many to 1 relationship with instructor
	// do NOT apply cascading DELETE
	// cascade type - if you delete a course, do NOT delete instructor
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	/**
	 * JoinColumn tells Hibernate to look at the course_id in review table 
	 * and use this info to help find associated reviews for a course.
	 * 
	 * Also, add support for cascading
	 */
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Review> reviews;
	
	// jointable - tells hibernate to look at the course_id in the course_student table
	// for other side (inverse join) look at the student_id columnt in the course student
	// Use this info to find relationship between course and student
	@ManyToMany
	@JoinTable(name="course_student",
				joinColumns=@JoinColumn(name="course_id"),
				inverseJoinColumns=@JoinColumn(name="student_id") )
	private List<Student> students;
	
	// define our Instructor
	/**
	 * 
	 */
	public Course() {
		//
	}
	
	/**
	 * @param title
	 */
	public Course(String title) {
		this.title = title;
	}

	
	// define getters and setters
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the instructor
	 */
	public Instructor getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor the instructor to set
	 */
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * add convenience methods for bidirectional relationship
	 */
	public void add(Review tempReview)	{
		if(tempReview == null) {
			reviews = new ArrayList<>();
		}
		reviews.add(tempReview);
		//tempReview.setCourse(this);
	}
	
	// add a convenience method
	public void addStudent(Student theStudent) {
		if (students == null) {
			students = new ArrayList<>();
		}
		students.add(theStudent);
	}

	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
}
