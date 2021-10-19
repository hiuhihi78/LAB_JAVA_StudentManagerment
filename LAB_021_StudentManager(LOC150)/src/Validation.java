
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
class Validation {

    public static boolean checkYesNo(String msg) {
        String choice = null;
        // loop until user input correct
        while (true) {
            System.out.println(msg);
            choice = GetValue.getInputString("your choice");
            if (choice.equalsIgnoreCase("y")) {
                return true;
            }
            if (choice.equalsIgnoreCase("n")) {
                return false;
            }
            System.err.println("Invalid of choice, you must enter Y/y or N/n!");
        }
    }

    public static boolean checkStudentExistedById(ArrayList<Student> list, String id) {
        for(Student student : list){
            if(student.getId().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkStudentEnrollCourseDuplicate(ArrayList<Student> list, Student student) {
        int count = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().equalsIgnoreCase(student.getId()) && 
                    list.get(i).getCourse().equalsIgnoreCase(student.getCourse())){
                count = count + 1;
            }
        }
        if(count <= 1){
            return false;
        }else{
            return true;
        }
    }

    public static boolean CheckReportStudentExist(ArrayList<Report> list, Student student) {
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId().equalsIgnoreCase(student.getId()) && 
                    list.get(i).getCourse().equalsIgnoreCase(student.getCourse())){
                return true;
            }
        }
        return false;
    }
    
}
