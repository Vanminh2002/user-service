package com.example.userservice.permisson;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.BaseEntity;

@Entity
@Data
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
