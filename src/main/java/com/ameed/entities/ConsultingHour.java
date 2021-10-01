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
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "consulting_hours", schema = "ameed")
public class ConsultingHour extends AbstractEntityWithDescription {
    private LocalDateTime end;

    private LocalDateTime start;

    @ManyToOne
    private Client client;
}
