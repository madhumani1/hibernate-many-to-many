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
public class CreateCoursesDemo {

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
			
			// get the instructor from db
			int theId=1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			// create some courses
			List<Course> tempCourses = new ArrayList<Course>();
			tempCourses.add(new Course("Maths"));
			tempCourses.add(new Course("Hindi"));
			tempCourses.add(new Course("Sports"));
			tempCourses.add(new Course("Tamil"));
			tempCourses.add(new Course("Malayalam"));
			tempCourses.add(new Course("Arts"));
			tempCourses.add(new Course("English"));
			tempCourses.add(new Course("Konkan"));
			tempCourses.add(new Course("Marathi"));
			
			
			for(Course course : tempCourses)	{
				// add courses to instructor
				tempInstructor.add(course);
				
				// save the courses
				session.save(course);
			}
			/*for(Course course : tempCourses)	{
				
			}*/
			
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
