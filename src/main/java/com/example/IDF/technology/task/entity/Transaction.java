package com.example.IDF.technology.task.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountFrom;

    private Long accountTo;

    private LocalDateTime date;

    private BigDecimal amount;

    private String currency;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean limitExceeded;

}


