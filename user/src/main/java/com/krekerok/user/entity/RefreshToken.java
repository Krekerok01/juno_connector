package com.krekerok.user.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Builder
@ToString
@Table(name = "refresh_tokens")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refresh_tokens_seq")
    @SequenceGenerator(name = "refresh_tokens_seq", sequenceName = "refresh_tokens_seq", allocationSize = 1)
    private Long refreshTokenId;

    private String token;

    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

