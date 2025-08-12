package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.entities.BaseEntity;

import java.util.Set;


@ToString
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "email")
    String email;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "image")
    String image;

    @Column(name = "address")
    String address;

    @Column(name = "phone")
    String phone;

    @Column(name = "deleted")
    Boolean deleted = false;

    @Column(name = "role_id")
    private Set<Long> roleId;
}
