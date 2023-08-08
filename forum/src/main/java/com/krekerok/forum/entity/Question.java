package com.krekerok.forum.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "questions")
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questionsSeqGenerator")
    @SequenceGenerator(name = "questionsSeqGenerator", sequenceName = "questions_seq", allocationSize = 1)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Column(name = "opening_date")
    private LocalDateTime openingDate;

    @Column(name = "closing_date")
    private LocalDateTime closingDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;
}