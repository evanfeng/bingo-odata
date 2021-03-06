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
package bingo.odata.format;

import java.io.Writer;

import bingo.lang.Strings;
import bingo.lang.json.JSON;
import bingo.lang.json.JSONWriter;
import bingo.odata.ODataConstants.ContentTypes;
import bingo.odata.ODataWriterContext;
import bingo.odata.ODataObject;
import bingo.odata.ODataWriter;

public abstract class ODataJsonWriter<T extends ODataObject> implements ODataWriter<T> {
	
	public String getContentType() {
	    return ContentTypes.APPLICATION_JSON_UTF8;
    }

	public final void write(ODataWriterContext context,Writer out, T target) throws Throwable {
		JSONWriter writer = JSON.createWriter(out);
		
		String callback = context.getUrlInfo().getQueryOptions().getCallback();
		
		if(!Strings.isEmpty(callback)){
			writer.raw(callback + "(");
		}
		
		writer.startObject().name("d");
		
		write(context,JSON.createWriter(out),target);
		
		writer.endObject();
		
		if(!Strings.isEmpty(callback)){
			writer.raw(")"); 
		}
    }
	
	protected abstract void write(ODataWriterContext context,JSONWriter writer,T target) throws Throwable;
}