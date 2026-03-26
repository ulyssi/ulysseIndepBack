package io.ulysse.ulysseIndepBack.entities.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_user_h")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntitiesUserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId; // id de EntitiesUser

    private String userUUID;
    private String userName;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phoneNumber;
    private String city;
    private String zipCode;
    private String address;

    // --- Audit historique ---
    @Column(nullable = false, updatable = false)
    private Timestamp archivedAt;

    private Integer archivedBy; // l'id du user qui effectue la modification
}