package com.onlinestudentmanagement.studentapplication.implement;

import com.onlinestudentmanagement.studentapplication.domain.*;
import com.onlinestudentmanagement.studentapplication.rep.ICourseRep;
import com.onlinestudentmanagement.studentapplication.rep.IDepartmentRep;
import com.onlinestudentmanagement.studentapplication.rep.IFacultyRep;
import com.onlinestudentmanagement.studentapplication.rep.IStudentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class StudentImplement implements CommandLineRunner {
    private final IStudentRep studentRep;
    private final ICourseRep courseRep;
    private final IFacultyRep facultyRep;
    private final IDepartmentRep departmentRep;

    public StudentImplement(IStudentRep studentRep, ICourseRep courseRep, IFacultyRep facultyRep, IDepartmentRep departmentRep) {
        this.studentRep = studentRep;
        this.courseRep = courseRep;
        this.facultyRep = facultyRep;
        this.departmentRep = departmentRep;
    }

    @Override
    public void run(String... args) throws Exception {
        Course c1 = new Course("C01", "Advanced Software Modeling", 3, 45000.0);
        Course c2 = new Course("C02", "Web Technology", 4, 60000.0);
        Course c3 = new Course("C03", "Data Structure and Algorthm", 5, 75000.0);
        Course c4 = new Course("C04", "Software Testing Techniques", 3, 45000.0);
        Course c5 = new Course("C05", "Introduction to Bible", 2, 30000.0);
        Course c6 = new Course("C06", "Introduction to Accounting", 3, 45000.0);
        Course c7 = new Course("C07", "Mobile Computing", 4, 60000.0);
        Course c8 = new Course("C08", "Digital Computer and Fundamentals", 4, 60000.0);

        courseRep.save(c1);
        courseRep.save(c2);
        courseRep.save(c3);
        courseRep.save(c4);
        courseRep.save(c5);
        courseRep.save(c6);
        courseRep.save(c7);
        courseRep.save(c8);

Faculty faculty = new Faculty("Information Technology");
Faculty faculty1 = new Faculty("Education");
Faculty faculty2 = new Faculty("Finance");
facultyRep.save(faculty);
facultyRep.save(faculty1);
facultyRep.save(faculty2);

Department department = new Department("Software Engineering",faculty);
Department department1 = new Department("Marketing",faculty2);
Department department2 = new Department("Networking",faculty);
departmentRep.save(department);
departmentRep.save(department1);
departmentRep.save(department2);

Student student1 = new Student("albert",Egender.MALE,LocalDate.of(1992,01,05),"Kigali", department);
//c1.getStudents().add(student1);
//student1.getCourses().add(c3);
//courseRep.save(c3);
studentRep.save(student1);

        System.out.println("the number of course is "+courseRep.count());
        System.out.println("the number of faculty is "+facultyRep.count());



    }
}
