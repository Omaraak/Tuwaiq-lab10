package com.example.lab10.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 4)
    @Column(columnDefinition = "varchar 15 not null")
    private String title;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    @NotEmpty
    @Column(nullable = false)
    private String location;

    @NotNull
    @Positive
    @Column(nullable = false)
    private int salary;

    @JsonFormat(pattern = "yyyy/mm/dd")
    private LocalDateTime postingDate;
}
