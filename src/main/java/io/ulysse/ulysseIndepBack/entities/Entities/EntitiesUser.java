package io.ulysse.ulysseIndepBack.entities.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "t_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntitiesUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String userUUID; // auth0|xxxx

    private String userName;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phoneNumber;
    private String city;
    private String zipCode;
    private String address;

    // --- Audit ---
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
}