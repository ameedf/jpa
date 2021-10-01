package com.ameed.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "clients", schema = "ameed")
public class Client extends AbstractEntity {

    @Column(name = "c_name", nullable = true, length = 255)
    private String contactName;

    @Column(name = "c_phone", nullable = true, length = 255)
    private String contactPhone;

    @Column(name = "name", nullable = true, length = 255)
    private String name;

}
