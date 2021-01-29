package com.training.SampleApplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Setter
@Getter
@Entity
@Table/*(name = "Book")*/
@NoArgsConstructor
public class Book {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JsonProperty("title")
    String title;

    @JsonProperty("author")
    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @JsonManagedReference
    Author author;

    @JsonProperty("released")
    int released;

}
