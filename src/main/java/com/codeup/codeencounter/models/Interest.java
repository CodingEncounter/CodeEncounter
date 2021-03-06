package com.codeup.codeencounter.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="interests")
public class Interest implements Comparable<Interest> {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name="interest_name", unique = true, length = 25)
    private String name;

    public Interest() {

    }

    public Interest(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Interest interest = (Interest) obj;
        return name.equals(interest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Interest other) {
        return this.name.compareTo(other.name);
    }
}
