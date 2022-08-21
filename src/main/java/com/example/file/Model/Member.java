package com.example.file.Model;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Member {
	  private String id;
	    private String password;
	    private String name;
	    private GenderType gender;
	    private LocalDate birth;
	    private String email;


}
