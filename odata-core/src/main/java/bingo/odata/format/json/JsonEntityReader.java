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
package bingo.odata.format.json;

import java.util.Map;
import java.util.Map.Entry;

import bingo.lang.Collections;
import bingo.lang.json.JSONObject;
import bingo.meta.edm.EdmEntityType;
import bingo.meta.edm.EdmNavigationProperty;
import bingo.meta.edm.EdmProperty;
import bingo.meta.edm.EdmType;
import bingo.odata.ODataErrors;
import bingo.odata.ODataReaderContext;
import bingo.odata.format.ODataJsonReader;
import bingo.odata.model.ODataEntity;
import bingo.odata.model.ODataEntityBuilder;
import bingo.odata.model.ODataKeyImpl;

public class JsonEntityReader extends ODataJsonReader<ODataEntity> {

	@Override
    protected ODataEntity read(ODataReaderContext context, JSONObject json) {
		if(json.isNull() || json.isArray()){
			throw ODataErrors.badRequest("invalid json content");
		}
		
		ODataEntityBuilder builder = new ODataEntityBuilder(context.getEntitySet(), context.getEntityType());
		EdmEntityType entityType = context.getEntityType();
		
		Map<String,Object> map = json.map();
		
		if(map.entrySet().size() == 1 && map.keySet().iterator().next().equals("d")) {
			map = (Map<String,Object>)map.get("d");
		}
		
		for(Entry<String, Object> entry : map.entrySet()){
			String name = entry.getKey();

			if(name.equals("__metadata")){
				continue;
			}
			
			EdmProperty p = entityType.findProperty(name);
			
			if(null != p){
				Object value = entry.getValue();
				
				//TODO : support ComplexType
				
				EdmType type = p.getType();
				if(type.isSimple()){
					if(entityType.getKeys().toSet().contains(name)) {
						builder.setKey(new ODataKeyImpl(value));
					} else builder.addProperty(name,JsonReaderUtils.readValue(type.asSimple(),value));
				}else{
					throw ODataErrors.notImplemented("complex type not implemented");
				}
				
				continue;
			}
			
			EdmNavigationProperty np = entityType.findNavigationProperty(name);
			
			if(null != np){
				
				//TODO : support Links
			
				continue;
			}
			
			throw ODataErrors.badRequest("Unknow property '{0}'",name);
		}
		
		return builder.build();
    }
}
