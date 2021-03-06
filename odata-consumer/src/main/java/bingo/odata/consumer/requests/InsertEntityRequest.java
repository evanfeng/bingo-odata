/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bingo.odata.consumer.requests;

import java.util.Map;

import bingo.lang.json.JSON;
import bingo.odata.consumer.ODataConsumerContext;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpMethods;


public class InsertEntityRequest extends Request {
	
	public InsertEntityRequest(ODataConsumerContext context, String serviceRoot) {
		super(context, serviceRoot);
	}

	private String entityType;
	private Map<String, Object> entity;

	public String getEntityType() {
		return entityType;
	}

	public InsertEntityRequest setEntityType(String entityType) {
		this.entityType = entityType;
		return this;
	}

	public Map<String, Object> getEntity() {
		return entity;
	}

	public InsertEntityRequest setEntity(Map<String, Object> entity) {
		this.entity = entity;
		return this;
	}

	@Override
	protected HttpContent generateContent() {
		String json = JSON.encode(entity, true);
		HttpContent content = ByteArrayContent.fromString(accept, json);
		return content;
	}

	@Override
	protected GenericUrl generateUrl() {
		String string = serviceRoot + entityType;
		string = addQueryString(string);
		GenericUrl url = new GenericUrl(string);
		return url;
	}

	@Override
	public String getMethod() {
		return HttpMethods.POST;
	}
	
	
}