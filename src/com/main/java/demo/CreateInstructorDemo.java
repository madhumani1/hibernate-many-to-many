/**
 * 
 */
package com.main.java.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.main.java.demo.entity.Course;
import com.main.java.demo.entity.Instructor;
import com.main.java.demo.entity.InstructorDetail;
import com.main.java.demo.entity.Review;
import com.main.java.demo.entity.Student;

/**
 * @author 15197
 *
 */
public class CreateInstructorDemo {

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
			// create an Instructor object
			Instructor tempInstructor = new Instructor("Neil", "Martis","neil.martis@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://usa.com","America!!!");
			//Instructor tempInstructor = new Instructor("Madhukar", "Mani","madhumani1@gmail.com");
			//InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com","Playing Chess!!!");
			
			//Instructor tempInstructor = new Instructor("Priyanka", "Bhadran","prinku4u@gmail.com");
			//InstructorDetail tempInstructorDetail = new InstructorDetail("http://instagram.com","Photography!!!");
			
			//Instructor tempInstructor = new Instructor("Neil", "Martis","neil.martis@gmail.com");
			//InstructorDetail tempInstructorDetail = new InstructorDetail("http://usa.com","America!!!");
			
			// associate the objects together
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start transaction
			session.beginTransaction();
			
			// save the Instructor
			// this will also save the details object because of CascadeType.ALL
			System.out.println("Saving Instructor: "+tempInstructor);
			session.save(tempInstructor);
			
			// commit the transaction
			session.getTransaction().commit();
		}	finally	{
			if(session.isOpen())	{
				session.close();
			}
			factory.close();
		}

	}

}
