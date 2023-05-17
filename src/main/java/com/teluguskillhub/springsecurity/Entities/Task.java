package com.teluguskillhub.springsecurity.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "task_name")
    private String taskName;

    //FetchType.LAZY - When we get Task from DB then User is fetched later ie., only after fetching Task Details first.
    //FetchType.EAGER - When we get Task from DB then User is also fetched then and there itself.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //name of foreign key column in tasks table is user_id
//    JoinColumn == foreign key column = column that refers to other table
    private User user;
}
