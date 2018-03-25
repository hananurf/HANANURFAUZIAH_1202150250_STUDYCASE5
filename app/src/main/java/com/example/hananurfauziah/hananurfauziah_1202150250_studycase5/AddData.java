package com.example.hananurfauziah.hananurfauziah_1202150250_studycase5;

/**
 * Created by Hana Nur Fauziah on 25/03/2018.
 */

public class AddData {

    String todo, desc, priority;

    public AddData(String todo, String desc, String priority){
        this.todo=todo;
        this.desc=desc;
        this.priority=priority;
    }

    public String getTodo() { return todo; } // merupakan method setter dan getter untuk TO DO
    public void setTodo(String todo) { this.todo = todo; }

    public String getDesc() {
        return desc;
    } // merupakan method setter dan getter untuk description
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPriority() { return priority; } // merupakan method setter dan getter untuk priority
    public void setPrior(String priority) {
        this.priority = priority;
    }
}