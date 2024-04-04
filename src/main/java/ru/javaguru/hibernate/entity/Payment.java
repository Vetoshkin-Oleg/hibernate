package ru.javaguru.hibernate.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
}