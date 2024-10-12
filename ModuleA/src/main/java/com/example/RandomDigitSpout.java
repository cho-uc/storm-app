package com.example;

import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.utils.Utils;

import java.util.Random;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RandomDigitSpout extends BaseRichSpout
{
  // To output tuples from spout to the next stage bolt
  SpoutOutputCollector collector;
  private static final Logger logger = LogManager.getLogger(RandomDigitSpout.class);

  @Override
  public void nextTuple() 
  {

    Utils.sleep(100);
    final String[] words = new String[]{ "nathan", "mike", "jackson", 
      "golda", "bertels" };
    final Random rand = new Random();
    int nextVal = rand.nextInt(words.length);
    final String word = words[nextVal];
    // collector.emit(new Values(word));
    
    collector.emit(new Values(nextVal));
    logger.info("Spout emitting " + nextVal + " ....");
  }

  @Override
  public void open(Map<String, Object> conf, TopologyContext context, SpoutOutputCollector collector) {
      this.collector = collector;
  }

  @Override
  public void close() {

  }

  @Override
  public void ack(Object msgId) {

  }

  @Override
  public void fail(Object msgId) {

  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer)
  {
    // Tell Storm the schema of the output tuple for this spout.
    // It consists of a single column called 'random-digit'.
    outputFieldsDeclarer.declare(new Fields("random-digit"));
  }
}
