package org.ait.project.guideline.example.modules.agenda.dto.request;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import org.ait.project.guideline.example.modules.agenda.dto.InviteeDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaRequest {
	
	@JsonFormat(timezone = "Asia/Jakarta", 
			    shape = Shape.STRING,
			    pattern = "yyyy-MM-dd HH:mm:ssZZZ")
	private OffsetDateTime time;

	private String title;
	
	private String description;
	
	private List<InviteeDTO> invitees;
	
}
