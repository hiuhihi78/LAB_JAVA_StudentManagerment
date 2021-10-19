
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
class Manager {

    public static void displayMenu() {
        System.out.println("WELCOME TO STUDENT MANAGEMENT");
        System.out.println("--------------------------------------");
        System.out.println("        1.	Create");
        System.out.println("        2.	Find and Sort");
        System.out.println("        3.	Update/Delete");
        System.out.println("        4.	Report");
        System.out.println("        5.	Exit");
        System.out.println("(Please choose 1 to Create, 2 to Find and Sort, "
                + "3 to Update/Delete, 4 to Report and 5 to Exit program).");
    }

    public static void create(ArrayList<Student> list) {
        int count = 0;
        // loop until add 10 student
        while (true) {
            System.out.println("CREATE");
            System.out.println("--------------------------------------");
            String id = GetValue.getInputString("ID");
            String name = GetValue.getName(list, id);
            int semester = GetValue.getSemester(list, id);
            if (semester == -1) { // was enrolled all course in this semster
                System.out.println("");
                continue;
            }
            String course = GetValue.getCourse(list, id, semester);
            if (course == null) { // was enrolled this course
                System.out.println("");
                continue;
            }
            list.add(new Student(id, name, semester, course));
            System.out.println("Successfully!\n");
            count = count + 1;
            if (count > 2) { // check added 10 students
                boolean continueCreate = Validation.checkYesNo(
                        "Do you want to continue (Y/N)? Choose Y to continue,"
                        + " N to return main screen.");
                System.out.println("");
                if (continueCreate == true) {
                    continue;
                } else {
                    return;
                }
            }
        }
    }

    public static void findAndSort(ArrayList<Student> list) {
        System.out.println("FIND AND SORT");
        System.out.println("--------------------------------------");
        ArrayList<Student> listSearch = new ArrayList<>();
        String partOfName = GetValue.getInputStringCanEmpty("part of name");
        // traverse all element of list to add obj have name contians part of name into listsearch
        for (Student student : list) {
            if (student.getName().contains(partOfName)) {
                listSearch.add(student);
            }
        }
        if (listSearch.size() == 0) {
            System.out.println("Students is not found!\n");
        } else {
            Collections.sort(listSearch, new Comparator<Student>() {
                @Override
                public int compare(Student t, Student t1) {
                    if(t.getName().compareTo(t1.getName()) > 0){
                        return 1;
                    }else{
                        return -1;
                    }
                }
            });
            // display result
            String format = String.format("%-10s%-20s%-10s%-10s", "ID", "Name", "Semester", "Course");
            System.out.println(format);
            for (Student student : listSearch) {
                String id = student.getId();
                String name = student.getName();
                int semester = student.getSemester();
                String course = student.getCourse();
                String record = String.format("%-10s%-20s%-10d%-10s", id, name, semester, course);
                System.out.println(record);
            }
            System.out.println("");
        }
    }

    public static void updateOrDelete(ArrayList<Student> list) {
        ArrayList<Student> listUpdate = new ArrayList<>();
        ArrayList<Student> listDelete = new ArrayList<>();
        System.out.println("UPDATE OR DELETE");
        System.out.println("--------------------------------------");
        // input id
        String id = GetValue.getInputString("ID");
        // check student existed 
        boolean studentExisted = Validation.checkStudentExistedById(list, id);
        if (studentExisted == false) {
            System.out.println("Student with this id not existed!\n");
            return;
        }
        // get choice update or delete
        String choiceUpdateOrDelete = GetValue.getUpdateOrDelete();
        if (choiceUpdateOrDelete.equalsIgnoreCase("u")) {// update
            // get list student to update
            listUpdate = GetValue.getListStudentById(list, id);
            System.out.println("List student information can update: ");
            // get obj to update
            Student student = GetValue.getStudentToUpdateOrDelete(listUpdate);
            list.remove(student);
            String newId = GetValue.getInputString("ID");
            String name = GetValue.getName(list, newId);
            int semester = GetValue.getSemester(list, newId);
            String course = GetValue.getCourse(list, newId, semester);
            if (course == null) { // was enrolled this course
                list.add(student);
                System.out.println("Can not update!\n");
                return;
            }
            list.add(new Student(newId, name, semester, course));
            System.out.println("Successfully\n");
            return;
        } else { // delete
            // get list students to delete
            listDelete = GetValue.getListStudentById(list, id);
            System.out.println("List student information can delete: ");
            // get student to remove
            Student student = GetValue.getStudentToUpdateOrDelete(listDelete);
            list.remove(student);
            System.out.println("Successfully!\n");
            return;
        }

    }

    public static void report(ArrayList<Student> list) {
        System.out.println("REPORT");
        System.out.println("--------------------------------------");
        ArrayList<Report> listReport = new ArrayList<>();
        // traverse all element of list
        for (Student student : list) {
            String id = student.getId();
            String name = student.getName();
            String course = student.getCourse();
            int totalCourse = GetValue.getTotalCourseByIdCourse(list, id, course);
            // check student have enrolled a course two or more time in list
            boolean studentEnrollCourseDuplicate = Validation.checkStudentEnrollCourseDuplicate(list, student);
            // check a record of reportstudent is existed in listReport
            boolean reportStudentExisted = Validation.CheckReportStudentExist(listReport, student);
            // add report to listReport
            if (studentEnrollCourseDuplicate == false && reportStudentExisted == false) {
                listReport.add(new Report(id, name, course, 1));
            } else if (studentEnrollCourseDuplicate == false && reportStudentExisted == true) {
                continue;
            } else if (studentEnrollCourseDuplicate == true && reportStudentExisted == false) {
                listReport.add(new Report(id, name, course, totalCourse));
            } else {
                continue;
            }
        }
        // display result listReport
        String format = String.format("%-10s%-20s%-10s%-10s", "ID", "Name", "Course", "Total");
        System.out.println(format);
        if (listReport.size() == 0) {
            System.out.println("List is empty!");
            return;
        }
        for (Report report : listReport) {
            System.out.println(report);
        }
        System.out.println("");
    }

}
