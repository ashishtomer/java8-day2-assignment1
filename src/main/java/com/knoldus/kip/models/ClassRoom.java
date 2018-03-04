package com.knoldus.kip.models;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The class-room class.
 */
@Value
@Builder
public class ClassRoom {

    private int roomId;
    private Optional<List<Student>> studentList;

    /**
     * @return The students who have no subjects in `this` classroom.
     */
    public final List<Student> getStudentsWithNoSubjects() {
        return studentList.orElseGet(ArrayList::new)
                .stream()
                .filter(student -> student
                        .getSubjects()
                        .orElseGet(ArrayList::new)
                        .size() == 0)
                .collect(Collectors.toList());
    }

    /**
     * @return The subjects of students in `this` classroom.
     */
    public final List<String> getSubjectsOfStudents() {
        return studentList.orElseGet(ArrayList::new)
                .stream()
                .map(student -> student
                        .getSubjects()
                        .orElseGet(ArrayList<String>::new))
                .flatMap(List<String>::stream)
                .collect(Collectors.toList());
    }

    /**
     * Print the "Hello student" to each student in `this` classroom.
     */
    public final void sayHelloToStudents() {
        studentList.orElseGet(ArrayList::new)
                .forEach(student -> System.out.println("Hello Student - "
                        + student.getName()));
    }
}
