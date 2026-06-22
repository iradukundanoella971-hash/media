package com.media.repository;
import com.media.entity.Author;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByUsername(String username);

    Optional<Author> findByEmail(String email);

    boolean existsByUsername(String email);

    list<Author> findByCreateAtBetween(
            LocalDateTime start,
            LocalDateTime end);
}


