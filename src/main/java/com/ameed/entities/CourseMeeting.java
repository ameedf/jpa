package com.ameed.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "course_meetings", schema = "ameed")
public class CourseMeeting extends AbstractEntity {
    private LocalDateTime end;
    private LocalDateTime start;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "meeting")
    @ToString.Exclude
    private Collection<MeetingParticipant> participants;

}
