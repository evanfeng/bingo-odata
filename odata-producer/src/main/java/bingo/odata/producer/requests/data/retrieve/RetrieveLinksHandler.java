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
package bingo.odata.producer.requests.data.retrieve;

import bingo.lang.Strings;
import bingo.meta.edm.EdmEntitySet;
import bingo.meta.edm.EdmEntityType;
import bingo.meta.edm.EdmNavigationProperty;
import bingo.odata.ODataErrors;
import bingo.odata.ODataRequest;
import bingo.odata.ODataResponse;
import bingo.odata.model.ODataKey;
import bingo.odata.producer.ODataProducerContext;
import bingo.odata.producer.requests.ODataRequestRouter;
import bingo.odata.producer.requests.data.EntityRequestHandlerBase;

public class RetrieveLinksHandler extends EntityRequestHandlerBase {

	@Override
    protected void doHandleEntity(ODataProducerContext context, 
    							  ODataRequest request, 
    							  ODataResponse response, 
    							  EdmEntitySet entitySet,
    							  EdmEntityType entityType,
    							  ODataKey key) throws Throwable {
		
		String navPropertyName = context.getUrlInfo().getPathParameter(ODataRequestRouter.ENTITY_NAV_PROP_NAME);
		if(Strings.isEmpty(navPropertyName)){
			throw ODataErrors.badRequest("The nagivation property name of EntityType '{0}' must not be empty",entityType.getName());
		}
		
		EdmNavigationProperty navProperty = entityType.findNavigationProperty(navPropertyName);
		if(null == navProperty){
			throw ODataErrors.notFound("The navigation property '{0}' not found in EntityType '{1}'",navPropertyName,entityType.getName());
		}
    }
	
	protected void doHandleEntityLink(ODataProducerContext context, 
    							  ODataRequest request, 
    							  ODataResponse response, 
    							  EdmEntitySet entitySet,
    							  EdmEntityType entityType,
    							  ODataKey key,
    							  EdmNavigationProperty navProperty) throws Throwable {
		
		//TODO : doHandleEntityLink
		
	}
}