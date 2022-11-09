package org.ait.project.guideline.example.shared;

import java.io.IOException;

import org.json.JSONException;

import com.fasterxml.jackson.databind.DatabindException;

public interface JsonTestInterface {

	public void serializationTest() throws JSONException, IOException;
	
	public void deserializationTest() throws IOException, DatabindException;
	
	
}
