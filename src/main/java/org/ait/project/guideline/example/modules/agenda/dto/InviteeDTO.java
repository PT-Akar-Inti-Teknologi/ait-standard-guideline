package org.ait.project.guideline.example.modules.agenda.dto;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteeDTO {

	@Email
	private String email;
	
	private String name;
	
	@JsonProperty("phone_no")
	private String phone;
}
