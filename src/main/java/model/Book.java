package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Book {

    @JsonProperty("id")
    int id;
    @JsonProperty("title")
    String title;
    @JsonProperty("author")
    int authorId;
    @JsonProperty("released")
    int released;

}
