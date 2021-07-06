package tests.me.kvq.supertrailspro.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import me.kvq.supertrailspro.utils.Counter;

public class CounterTest {

	@Test
	public void test() {
		Counter counter = new Counter();
		assertEquals(0, counter.get());
		assertEquals(0, counter.getAndAdd());
		assertEquals(1, counter.get());
		
		counter = new Counter(10, 15);
		counter.add(5);
		assertEquals(15, counter.get());
		counter.add(2);
		assertEquals(11, counter.get());
	}

}
