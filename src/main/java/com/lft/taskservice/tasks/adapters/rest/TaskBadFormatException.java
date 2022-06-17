package com.lft.taskservice.tasks.adapters.rest;

public class TaskBadFormatException extends RuntimeException{

    public TaskBadFormatException() {
        super("Bad format of task!");
    }
}
