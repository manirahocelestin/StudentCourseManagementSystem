package com.onlinestudentmanagement.studentapplication.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "student")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "student_name")
    private String names;
    @Column(name = "gender")
    private Egender gender;
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "address")
    private String address;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            })
    @JoinTable(name = "Student_course", joinColumns = {
            @JoinColumn(name = "student_id") }, inverseJoinColumns = {
            @JoinColumn(name = "course_id") })
    private Set<Course> courses = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Student() {
    }

    public Student(Long id, String names, Egender gender, LocalDate dob, String address, Department department) {
        this.id = id;
        this.names = names;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.department = department;
    }

    public Student(String names, Egender gender, LocalDate dob, String address, Department department) {
        this.names = names;
        this.gender = gender;
        this.dob = dob;
        this.address = address;

        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Egender getGender() {
        return gender;
    }

    public void setGender(Egender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void registerCourse(Course course) {
        courses.add(course);
    }
    public void removeCourse(Course course){

        courses.remove(course);
    }
    public boolean isExist(String code){
        boolean isexist=false;
        for (Course c : courses) {
            if(c.getCode().equals(code))
                isexist = true;
        }
        return isexist;
    }
    public int numberOfCourses(){

        return courses.size();
    }
    public int numberOfCredits(){
        int total=0;
        for (Course course : courses) {
            total+= course.getCredit();
        }
        return total;
    }
    public String totalPayment(){
        int fee=0;
        for (Course course : courses) {
            fee+= course.getFees();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###.##Frw");

        return formatter.format(fee);
    }
    public String converFees(Double fee){
        DecimalFormat formatter = new DecimalFormat("###,###,###.##Frw");

        return formatter.format(fee);
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


