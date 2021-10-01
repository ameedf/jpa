package com.ameed.app;

import com.ameed.entities.Client;
import com.ameed.entities.ConsultingHour;
import com.ameed.entities.Course;
import com.ameed.entities.CourseMeeting;
import com.ameed.entities.CourseParticipant;
import com.ameed.entities.MeetingParticipant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public class DataInitializer {
    private final Random random;
    private final Dao dao;

    public DataInitializer(Dao dao) {
        this.dao = dao;
        random = new Random();
    }

    public void initialize() {
        deleteAllData();
        List<Client> clients = createClients();

        createAllCourseData(clients.get(0));
        createAllConsultingHoursData(clients.get(1));
    }

    private void deleteAllData() {
        Stream.of(MeetingParticipant.class,
                  CourseMeeting.class,
                  CourseParticipant.class,
                  Course.class,
                  ConsultingHour.class,
                  Client.class)
                .forEach(dao::deleteAll);
    }

    private void createAllConsultingHoursData(Client client) {
        List<ConsultingHour> consultingHours = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            LocalDateTime start = LocalDateTime.now().plusWeeks(i);
            consultingHours.add(ConsultingHour.builder()
                                        .description("consulting-" + i)
                                        .client(client)
                                        .start(start)
                                        .end(start.plusHours(3))
                                        .build());
        }
        consultingHours.forEach(dao::save);
    }

    private void createAllCourseData(Client client) {
        List<Course> courses = createCourses(client);

        courses.forEach(course -> {
            List<CourseParticipant> courseParticipants = createCourseParticipants(course);
            List<CourseMeeting> meetings = createCourseMeetings(course);
            createMeetingParticipants(courseParticipants, meetings);
        });
    }

    private List<CourseMeeting> createCourseMeetings(Course course) {
        List<CourseMeeting> meetings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            LocalDateTime start = LocalDateTime.now().plusWeeks(i);
            meetings.add(CourseMeeting.builder()
                                 .course(course)
                                 .start(start)
                                 .end(start.plusHours(random.nextInt(4) + 1))
                                 .build()
            );
        }
        meetings.forEach(dao::save);
        return meetings;
    }

    private List<CourseParticipant> createCourseParticipants(Course course) {
        List<CourseParticipant> courseParticipants = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            courseParticipants.add(CourseParticipant.builder()
                                           .course(course)
                                           .name("participant-of-" + course.getId() + "-" + i)
                                           .phone("052-" + i)
                                           .build());
        }
        courseParticipants.forEach(dao::save);
        return courseParticipants;
    }

    private void createMeetingParticipants(List<CourseParticipant> potentialParticipants, List<CourseMeeting> meetings) {

        List<MeetingParticipant> meetingParticipants = new LinkedList<>();
        Set<Integer> indices = new HashSet<>();
        int max = Math.min(random.nextInt(potentialParticipants.size()) + 5, potentialParticipants.size());
        while (indices.size() < max) {
            indices.add(random.nextInt(potentialParticipants.size()));
        }

        meetings.forEach(
                meeting -> indices.forEach(
                        index -> meetingParticipants.add(
                                MeetingParticipant.builder()
                                        .meeting(meeting)
                                        .participant(potentialParticipants.get(index))
                                        .build())));
        meetingParticipants.forEach(dao::save);
    }

    private List<Course> createCourses(Client client) {
        List<Course> courses = new ArrayList<>();
        courses.add(Course.builder().description("Java for developers").client(client).build());
        courses.add(Course.builder().description("JavaScript for beginners").client(client).build());
        courses.forEach(dao::save);
        return courses;
    }

    private List<Client> createClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(Client.builder().name("Galil").build());
        clients.add(Client.builder().name("Malam").build());
        clients.forEach(dao::save);
        return clients;
    }
}
