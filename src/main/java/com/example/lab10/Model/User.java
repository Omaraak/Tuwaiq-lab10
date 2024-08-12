package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.context.annotation.Primary;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 4)
    @Pattern(regexp = "(a-zA-Z)")

    @Column(nullable = false, length = 15)
    private String name;

    @NotEmpty
    @Email

    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty

    @Column(columnDefinition = "varchar not null")
    private String password;

    @NotNull
    @Positive
    @Range(min = 21)
    @Column(columnDefinition = "int not null check(age>21)")
    private int age;

    @NotEmpty
    @Pattern(regexp = "(job_seeker|employer)")
    @Column(columnDefinition = "varchar not null check(role='job_seeker' or 'employer')")
    private String role;
}
