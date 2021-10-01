package com.ameed.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "meeting_participants", schema = "ameed")
public class MeetingParticipant extends AbstractEntity {
    @ManyToOne
    private CourseMeeting meeting;

    @ManyToOne
    private CourseParticipant participant;

}
