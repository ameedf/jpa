package com.ameed.app;

import com.ameed.entities.CourseParticipant;

import java.util.List;

public class DemoApp {
    public static void main(String[] args) {
        Dao dao = new Dao();
        new DataInitializer(dao).initialize();
        List<CourseParticipant> participants = dao.list(
                "SELECT p FROM CourseParticipant p WHERE" +
                        " p.course.description LIKE '%JavaScript%'" +
                        " AND p NOT IN (" +
                        "SELECT mp.participant FROM MeetingParticipant mp" +
                        " WHERE mp.meeting.course = p.course)", CourseParticipant.class);
        System.out.println(participants.size());
    }
}
