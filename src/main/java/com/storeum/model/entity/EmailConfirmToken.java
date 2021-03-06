package com.storeum.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "email_confirm_token")
@ToString
public class EmailConfirmToken {

    @Id
    @SequenceGenerator(name = "ect_seq", sequenceName = "ec_token_sequence", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ect_seq")
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private boolean isConfirmed;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private User user;
}
