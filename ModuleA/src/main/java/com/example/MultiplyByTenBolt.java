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

public class MultiplyByTenBolt extends BaseRichBolt 
{
  OutputCollector collector;
  private static final Logger logger = LogManager.getLogger(MultiplyByTenBolt.class);

  @Override
  public void execute(Tuple tuple) 
  {
    // Get 'even-digit' from the tuple.
    int evenDigit = tuple.getInteger(0);
    logger.info("Received even digit from Bolt: " + evenDigit + " ....");
    collector.emit(new Values(evenDigit * 10));
    
  }

  @Override
  public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector)
  {
    this.collector = collector;
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) 
  {
    declarer.declare(new Fields("even-digit-multiplied-by-ten"));
  }
}
