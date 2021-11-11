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

/**
 * @author 15197
 *
 */
public class DeleteCourseAndReviewsDemo {

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
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try	{
			// now use the session object to save/retrieve Java objects
			// start transaction
			session.beginTransaction();
			
			// create a course
			// Course tempCourse = new Course("Pacman - How To Score One Million Points");
			
			// get the course from session db
			int theId=2;
			System.out.println("Reading Course");
			Course tempCourse = session.get(Course.class, theId);
			
			// print the course
			System.out.println("Deleting the course ... ");
			System.out.println("tempCourse: " +tempCourse);

			// print the course reviews
			System.out.println("Reviews: " + tempCourse.getReviews());
			
			// delete the course
			session.delete(tempCourse);
			
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
