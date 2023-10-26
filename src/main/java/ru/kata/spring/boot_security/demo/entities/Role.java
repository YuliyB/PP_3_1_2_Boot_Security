package ru.kata.spring.boot_security.demo.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> usersWithThatRole;

    public Role() {
    }

    public Role(long id) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }

    public Set<User> getUsersWithThatRole() {
        return usersWithThatRole;
    }

    public void setUsersWithThatRole(Set<User> usersWithThatRole) {
        this.usersWithThatRole = usersWithThatRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name) && Objects.equals(usersWithThatRole, role.usersWithThatRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, usersWithThatRole);
    }
}
