package com.liefern.webservices.models;

import org.json.JSONObject;

import com.liefern.constants.Constants;


public abstract class WebServiceModel {
	protected boolean success = true;
	protected String message;
	
	/**
	 * Parses the response
	 * @param responce
	 * @throws Exception
	 */
	public abstract void parseJSON(JSONObject jsonObject) throws Exception;
	
	public String getMessage() {
		return message;
	}
	public boolean isError() {
		return !success;
	}
	
	/**
	 * Performs basic parsing which will be needed in all web services.
	 * Fetches success and message values in response JSON
	 * @param jsonObject
	 */
	public void parse(JSONObject jsonObject) {
		success = jsonObject.has(Constants.ERROR) ? false : true;
		
		if(success == false) {
			JSONObject errorJSON = jsonObject.optJSONObject(Constants.ERROR);
			message = errorJSON.optString(Constants.MESSAGE);
		}
	}
}
