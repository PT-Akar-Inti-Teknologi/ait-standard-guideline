package org.ait.project.guideline.example.modules.agenda.dto.request;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.ait.project.guideline.example.modules.agenda.dto.InviteeDTO;
import org.ait.project.guideline.example.shared.JsonTester;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import com.fasterxml.jackson.databind.DatabindException;

public class AgendaRequestJsonTest extends JsonTester<AgendaRequest> {


	@BeforeEach
	@Override
	protected void setupTest() throws IOException {
		// the JSON is given from the agreed contract between BE and FE
		setJsonTestType(JsonTestType.GIVEN);
		// object to test should be set manually here
		setObjectToTest(getObjectToUse());
		/// declare the Java Type to test for JSON
		setTypeToTest(AgendaRequest.class);
		super.setupTest();
	}

	// objects to be used should be set according to the given JSON, in the test resources folder
	private AgendaRequest getObjectToUse() {

		AgendaRequest object = new AgendaRequest();
		object.setDescription("Interview dengan sdr. Kandidat Rekrut");
		object.setTime(OffsetDateTime.of(2024, 6, 19, 12, 15, 26, 0, ZoneOffset.ofHours(7)
				));
		object.setTitle("User Interview - Kandidat Rekrut");

		List<InviteeDTO> inviteesList = new ArrayList<>();

		InviteeDTO invitee1 = new InviteeDTO();
		invitee1.setEmail("andika.akbar@akarinti.tech");
		invitee1.setName("Muhammad Andika Akbar Ishaq");
		inviteesList.add(invitee1);

		InviteeDTO invitee2 = new InviteeDTO();
		invitee2.setEmail("arseno.harahap@akarinti.tech");
		invitee2.setName("Arseno Harahap");
		invitee2.setPhone("081399760003");
		inviteesList.add(invitee2);

		InviteeDTO invitee3 = new InviteeDTO();
		invitee3.setEmail("andrew@akarinti.tech");
		invitee3.setName("Andrew Ongi");
		inviteesList.add(invitee3);

		InviteeDTO invitee4 = new InviteeDTO();
		invitee4.setEmail("kandidat@gmail.com");
		invitee4.setName("Kandidat Rekrut");
		inviteesList.add(invitee4);

		object.setInvitees(inviteesList);
		return object;
	}


	@Test
	public void serializationTest() throws JSONException, IOException {

		//get the expected JSON
		String expected = new String(getSourceJson().readAllBytes(), Charset.defaultCharset());

		// serialize the object to test, into JSON
		String actual = om.writeValueAsString(getObjectToTest());

		// compare the expected JSON provided, against the JSON written by the ObjectMapper 
		JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);

	}

	@Test
	public void deserializationTest() throws IOException, DatabindException {

		// deserialize/read the JSON into a POJO
		AgendaRequest actual = om.readValue(getSourceJson(), getTypeToTest());

		// get the expected object 
		AgendaRequest expected = getObjectToTest();


		// test the values using assertions
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getInvitees().size(), actual.getInvitees().size());

		for (int i = 0; i < expected.getInvitees().size(); i++) {
			InviteeDTO expectedInvitee = expected.getInvitees().get(i);
			InviteeDTO actualInvitee = actual.getInvitees().get(i);
			assertEquals(expectedInvitee.getEmail(), actualInvitee.getEmail());
			assertEquals(expectedInvitee.getName(), actualInvitee.getName());
			assertEquals(expectedInvitee.getPhone(), actualInvitee.getPhone());
		}

		assertEquals(expected.getTime().toInstant().getEpochSecond(), 
				     actual.getTime().toInstant().getEpochSecond());
	}

}

