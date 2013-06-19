package bingo.odata.consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import bingo.odata.ODataServices;
import bingo.odata.model.ODataEntity;
import bingo.odata.model.ODataEntitySet;


public class UsageDemo extends ServerTestBase{

	private static final String ODATA_V3_SAMPLE_SERVICE = "http://services.odata.org/V3/OData/OData.svc/";
	
	private static final String LOCALHOST_SERVICE = "http://localhost:8080/demo";

	ODataConsumer consumer = new ODataConsumerImpl(LOCALHOST_SERVICE);

//	@Test
//	public void testInsert() throws Throwable {
//		Map<String, Object> user = new HashMap<String, Object>();
//		user.put("username", "admin");
//		user.put("password", "111111");
//		
//		ODataConsumer consumer = new ODataConsumerImpl("http://172.58.102.7/odata/");
//		
//		// insert
//		assertEquals(1, consumer.insertEntity("User", user));
//		
//		List<Map<String, Object>> list = consumer.queryEntity("user")
//				.filter(new Filter().eq("username", "admin"))
//				.listMap();
//		assertEquals(1, list.size());
//		assertEquals("111111", list.get(0).get("password"));
//		
//		//modify
//		user.put("password", "222222");
//		consumer.updateEntity(user);
//
//		list = consumer.queryEntity("user")
//				.filter(new Filter().eq("username", "admin"))
//				.listMap();
//		assertEquals(1, list.size());
//		assertEquals("222222", list.get(0).get("password"));
//		
//		// delete
//		consumer.deleteEntity("user", list.get(0).get("id"));
//		
//		list = consumer.queryEntity("user")
//				.filter(new Filter().eq("username", "admin"))
//				.listMap();
//		assertTrue(Collections.isEmpty(list));
//	}
	
//	@Test // TODO Json entity reader.
	public void retrieveEntitySet() {
		ODataEntitySet entitySet = consumer.retrieveEntitySet("Products");
		assertNotNull(entitySet);
	}
	
	@Test
	public void retrieveEntity() {
		ODataEntity entity = consumer.retrieveEntity("Product", "123456");
		assertNotNull(entity);
		System.out.println(entity);
	}
	
	@Test
	public void insertEntity() {
		Map<String, Object> product = new HashMap<String, Object>();
		product.put("ID", "987654321");
		product.put("name", "chenkai");
		product.put("description", "postbychenkai");
		
		int result = consumer.insertEntity("Product", product);
		assertEquals(1, result);
	}
	
	@Test
	public void deleteEntity() {
		int result = consumer.deleteEntity("Product", "123456");
		assertEquals(1, result);
	}
	
	@Test
	public void updateEntity() {
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("name", "new name here");
		int result = consumer.updateEntity("Product", "123456", fields);
		assertEquals(1, result);
	}
	
	@Test
	public void getMetadataDocument() {
		ODataServices service = consumer.retrieveServiceMetadata();
		assertNotNull(service);
	}
	
	public void getServiceDocument() {
	}

}
