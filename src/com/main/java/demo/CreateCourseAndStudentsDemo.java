/**
 * 
 */
package com.main.java.demo;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.query.dsl.impl.ConnectedMoreLikeThisQueryBuilder.MoreLikeThisToEntityContentAndTerminationImpl;

import com.main.java.demo.entity.Course;
import com.main.java.demo.entity.Instructor;
import com.main.java.demo.entity.InstructorDetail;
import com.main.java.demo.entity.Review;
import com.main.java.demo.entity.Student;

/**
 * @author 15197
 *
 */
public class CreateCourseAndStudentsDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("emp.hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try	{
			// now use the session object to save/retrieve Java objects
			// start transaction
			session.beginTransaction();
			
			// create a course
			Course tempCourse = new Course("Bengali");
			//int theId=9;
			//Course tempCourse = session.get(Course.class, 9);
			
			// save the course - ONLY if you create course
			System.out.println("\nSaving the course ...");
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);
			
			// create the students
			Student tempStudent1 = new Student("Aishwarya", "Rai", "Aiswarya.Rai@gmail.com");
			Student tempStudent2 = new Student("Sushmita", "Sen", "Sushmita.Sen@gmail.com");
			
			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			// save the students
			System.out.println("\nSaving students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students: " + tempCourse.getStudents());
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}	finally	{
			if(session.isOpen())	{
				session.close();
			}
			factory.close();
		}

	}

}
