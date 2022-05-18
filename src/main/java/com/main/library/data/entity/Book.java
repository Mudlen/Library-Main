package com.main.library.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "book")
public class Book{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "format")
    private String format;

    @Column(name = "static_path")
    private String staticPath;

    @Column(name = "simple_name")
    private String simpleName;
}
