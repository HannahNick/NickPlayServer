package com.nick.spring17.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

    @Id
    String id;

    String name;

    String pwd;
}
