package com.media.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String fullName;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private  String email;
    @Column(columnDefinition = "TEXT")
    private String bio;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "createdBy")
    private List<Post> post;
}
