package tests.me.kvq.supertrailspro.utils;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import me.kvq.supertrailspro.utils.STJson;

public class STJsonTest {

	static String stringjson;
	@BeforeClass
	public static void get() {
		stringjson = "{\"mode\":6,\"keeper\":{\"0\":\"f\",\"1\":null,\"2\":null,\"3\":null,\"4\":null},\"pattern\":\"test_pattern\",\"w1\":0,\"id\":50,\"w2\":0,\"w3\":4}";
		System.out.println("done");
	}
	
	@Test
	public void test() {
		
		try {
			STJson testjson = new STJson(stringjson);
			STJson testsub = new STJson("{}"); testsub.setString("testkey", "testvalue");
			testjson.setSubJson("subjsontest",testsub);
			
			assertEquals(testjson.readInt("mode"), 6);
			assertEquals(testjson.readString("pattern"), "test_pattern");
			assertNotNull(testjson.getSubJson("keeper"));
			assertNotNull(testjson.getSubJson("subjsontest"));

			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
