/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
class Report extends Student{
    private int total;

    public Report(String id, String name, String course, int total) {
        super(id, name, 0, course);
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        String s = String.format("%-10s%-20s%-10s%-10d", super.getId(), super.getName(), super.getCourse(), total);
        return s;
    }
    
}
