package co.wgmartinez.bigdata.kakfa.bigdatakafka;

import co.wgmartinez.bigdata.kakfa.bigdatakafka.producer.SimplePartitioner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BigdataKafkaApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void partitionIdIsBelowUpperbound(){
		SimplePartitioner simplePartitioner = new SimplePartitioner(3);
		int partition = simplePartitioner.partition(null,2147483647L,null,null,null,null);

		System.out.println("Partition = " + partition);
	}

}

