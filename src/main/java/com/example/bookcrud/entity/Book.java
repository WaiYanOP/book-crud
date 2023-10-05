package com.example.bookcrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book extends IdClass {

    private String title;
    private double price;
    private String imageUrl;
    private String publisher;
    @ManyToOne
    private Author author;


}
