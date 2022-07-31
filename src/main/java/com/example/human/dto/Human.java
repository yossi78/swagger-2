package com.example.human.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;




@Data
public class Human {

    @ApiModelProperty(notes = "The id of the user",example = "1")
    private Long id;

    @ApiModelProperty(notes = "The name of the user",example = "Yossi")
    private String name;

    @ApiModelProperty(notes = "The age of the user",example = "43")
    private int age;

    public Human() {
    }

    public Human(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAll(Long id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }



}

