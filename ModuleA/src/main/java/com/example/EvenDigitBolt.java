package com.example;

import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EvenDigitBolt extends BaseRichBolt 
{
  OutputCollector collector;
  private static final Logger logger = LogManager.getLogger(EvenDigitBolt.class);

  @Override
  public void execute(Tuple tuple) 
  {
    // Get the 1st column 'random-digit' from the tuple
    int randomDigit = tuple.getInteger(0);
    logger.info("Received from Spout: " + randomDigit + " ....");

    if (randomDigit % 2 == 0) {
      collector.emit(new Values(randomDigit));

    }
  }

  @Override
  public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector)
  {
    this.collector = collector;
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) 
  {
    // Tell Storm the schema of the output tuple for this bolt.
    // It consists of a single column called 'even-digit'
    declarer.declare(new Fields("even-digit"));
  }
}
