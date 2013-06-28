package bingo.odata.consumer.util;

import bingo.lang.Strings;
import bingo.meta.edm.EdmEntityType;
import bingo.odata.consumer.ODataConsumer;
import bingo.odata.consumer.ODataConsumerContext;
import bingo.odata.model.ODataKeyImpl;

public class ODataConsumerContextHelper {
	
	public static ODataConsumerContext initEntitySetContext(ODataConsumer consumer, String entitySet) {
		return initContext(consumer, entitySet, null, null);
	}
	
	public static ODataConsumerContext initEntityTypeContext(ODataConsumer consumer, String entityType) {
		return initEntityTypeContext(consumer, entityType, null);
	}
	
	public static ODataConsumerContext initEntityTypeContext(ODataConsumer consumer, String entityType, Object key) {
		return initContext(consumer, null, entityType, key);
	}
	
	private static ODataConsumerContext initContext(ODataConsumer consumer, String entitySet, String entityType, 
				Object key) {
		ODataConsumerContext context = new ODataConsumerContext(consumer.config());
		
		if(Strings.isNotBlank(entityType)) {
			context.setEntityType(consumer.services().findEntityType(entityType));
			context.setEntitySet(consumer.services().findEntitySet(context.getEntityType()));
		} else if(Strings.isNotBlank(entitySet)) {
			context.setEntitySet(consumer.services().findEntitySet(entitySet));
			context.setEntityType(consumer.services().findEntityType(context.getEntitySet().getEntityType().getName()));
		}
		
		if(Strings.isBlank(key)){
			context.setEntityKey(new ODataKeyImpl(key));
		}
		
		return context;
	}
}
