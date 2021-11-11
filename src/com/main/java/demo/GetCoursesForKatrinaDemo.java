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
public class GetCoursesForKatrinaDemo {

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
			
			// get the student from database
			int studentId = 5;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("Courses: " + ((tempStudent!=null)?tempStudent.getCourses():"null"));
			
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
