
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
class GetValue {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntNumberInRange(int from, int to, String msg) {
        int result = 0;
        // loop until user input correct
        while (true) {
            result = getInputPositiveInt(msg);
            if (result < from || result > to) {
                System.err.println("Invalid of " + msg + ", " + msg
                        + " must be in range [" + from + " - " + to + "]!");
            } else {
                return result;
            }
        }
    }

    public static String getInputString(String msg) {
        String result = null;
        // loop until user input correct
        while (true) {
            System.out.print("Enter " + msg + ": ");
            result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Invalid of " + msg + ", " + msg + " must be not empty!");
            } else {
                return result;
            }
        }
    }

    public static int getInputPositiveInt(String msg) {
        int result = 0;
        // loop until user input correct
        while (true) {
            try {
                String temp = getInputString(msg);
                result = Integer.parseInt(temp);
                if (result < 0) {
                    System.err.println("Invalid of " + msg + ", " + msg + " must be positive interger!");
                    continue;
                } else if (result == 0) {
                    System.err.println("Invalid of " + msg + ", " + msg + " must be greater than 0!");
                } else {
                    return result;
                }
            } catch (Exception e) {
                System.err.println("Invalid of " + msg + ", " + msg + " must be positive interger!");
            }
        }
    }

    public static String getName(ArrayList<Student> list, String id) {
        String result = null;
        // loop until user input correct
        while (true) {
            result = getInputString("name");
            // check student existed by id
            boolean studenExisted = Validation.checkStudentExistedById(list, id);
            if (studenExisted == true) {
                Student student = GetValue.getStudentById(list, id);
                String studentName = student.getName();
                if (result.equalsIgnoreCase(studentName)) {
                    return result;
                } else {
                    System.err.println("Invalid of name, name of this student must be " + studentName + "!");
                    continue;
                }
            } else {
                return result;
            }
        }
    }

    public static int getSemester(ArrayList<Student> list, String id) {
        int semester = getInputPositiveInt("semester");
        int count = 0;
        // check in this semester was learn all course
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id) && student.getSemester() == semester) {
                count = count + 1;
            }
        }
        if (count >= 3) {
            System.out.println("This semester was errolled all course!");
            return -1;
        } else {
            return semester;
        }
    }

    public static String getCourse(ArrayList<Student> list, String id, int semester) {
        String course = null;
        // loop until user input correct
        while (true) {
            course = getInputString("course");
            if (course.equalsIgnoreCase("java") || course.equalsIgnoreCase(".net") || 
                    course.endsWith("c/c++")) {
                // check was add this course
                for (Student student : list) {
                    if (student.getId().equalsIgnoreCase(id) && 
                            student.getSemester() == semester && 
                            student.getCourse().equalsIgnoreCase(course)) {
                        System.out.println("This course was errolled in this semester!");
                        return null;
                    }
                }
            } else {
                System.err.println("Invalid of course, course must be Java, .Net or C/C++!");
                continue;
            }

            return course;
        }
    }

    public static Student getStudentById(ArrayList<Student> list, String id) {
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public static String getInputStringCanEmpty(String msg) {
        System.out.print("Enter " + msg + ": ");
        return scanner.nextLine().trim();
    }

    public static String getUpdateOrDelete() {
        String input = null;
        //loop until get vaild value
        while (true) {
            System.out.println("Do you want to update (U) or delete (D) student."
                    + "\nIf user chooses U, the program allows user updating."
                    + " Choose D for deleting student");
            input = scanner.nextLine().trim();
            if (input != "") {
                if (input.equalsIgnoreCase("U")) {//return true if input u/U
                    return "U";
                }
                if (input.equalsIgnoreCase("D")) {//return false if input d/D
                    return "D";
                }
            } else {
                System.err.println("Invalid of choice! Please enter u/U or d/D!");
            }
        }
    }

    public static ArrayList<Student> getListStudentById(ArrayList<Student> list, String id) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id)) {
                result.add(student);
            }
        }
        return result;
    }

    public static void getDisplayToUpdateOrDelete(ArrayList<Student> listUpdate) {
        String format = String.format("   %-10s%-20s%-10s%-10s", "ID", "Name", "Semester", "Course");
        System.out.println(format);
        for (int i = 0; i < listUpdate.size(); i++) {
            System.out.println((i + 1) + ") " + listUpdate.get(i).toString());
        }
    }

    public static Student getStudentToUpdateOrDelete(ArrayList<Student> list) {
        getDisplayToUpdateOrDelete(list);
        int index = getIntNumberInRange(1, list.size(), "your choice") - 1;
        for (int i = 0; i < list.size(); i++) {
            if (index == i) {
                return list.get(i);
            }
        }
        return null;
    }
    
    public static int getIndexNumberStudentInList(ArrayList<Student> list, Student student) {
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(student)){
                return i;
            }
        }
        return -1;
    }

    public static int getTotalCourseByIdCourse(ArrayList<Student> list, String id, String course) {
        int count = 0;
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(id) && student.getCourse().equalsIgnoreCase(course)) {
                count = count + 1;
            }
        }
        return count;
    }

   

}
