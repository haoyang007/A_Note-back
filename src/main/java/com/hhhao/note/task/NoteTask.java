package com.hhhao.note.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 
 * @author haoy
 *
 */
@Configuration
@Component
@EnableScheduling
public class NoteTask {
    public void task() {
        System.out.println("task run!");
    }
}
