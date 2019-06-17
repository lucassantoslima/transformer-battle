/*
* Copyright 2018 Builders
*************************************************************
*Nome     : JsonUtil.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package com.aequilibrium.util;

import com.aequilibrium.exception.ApiJsonException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;


public class JsonUtil {
	
	public static final String FORMATO_DATA_HORA_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssX";

	private static final Gson GSON = new GsonBuilder().setDateFormat(FORMATO_DATA_HORA_ISO_8601).create();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object toObject(final String json, final Class clazz) throws ApiJsonException {
		Object objRequest = null;
		try {
			objRequest = GSON.fromJson(json, clazz);
		} catch (final JsonParseException e) {
			throw new ApiJsonException(e);
		}
		return objRequest;
	}

	public static String fromObject(final Object json) {
		return GSON.toJson(json);
	}

}
