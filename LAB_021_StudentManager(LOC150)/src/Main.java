
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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        // loop until user want to exist
        while (true) {
            // display menu
            Manager.displayMenu();
            // choice option
            int choice = GetValue.getIntNumberInRange(1, 5, "your choice");
            switch (choice) {
                case 1:
                    // create
                    Manager.create(list);
                    break;
                case 2:
                    // find and sort
                    Manager.findAndSort(list);
                    break;
                case 3:
                    // updateOrDelete
                    Manager.updateOrDelete(list);
                    break;
                case 4:
                    // report
                    Manager.report(list);
                    break;
            }
        }
    }

}
