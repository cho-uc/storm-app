package com.example;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FileWritingBolt extends BaseRichBolt {
    private static final Logger logger = LogManager.getLogger(FileWritingBolt.class);

    private BufferedWriter writer;
    private String filePath;
    OutputCollector collector;

    public FileWritingBolt(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void cleanup() {
        try {
            writer.close();
        } catch (IOException e) {
            logger.error("Failed to close writer!");
        }
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, 
      OutputCollector collector) {
        this.collector = collector;

        try {
            writer = new BufferedWriter(new FileWriter(filePath));
        } catch (IOException e) {
            logger.error("Failed to open a file for writing.", e);
        }
    }

    @Override
    public void execute(Tuple tuple) {
        int finalDigit = tuple.getInteger(0);
        try {
            writer.write(Integer.toString(finalDigit));
            writer.newLine();
            logger.info("Writing to file:  " + finalDigit + " ....");
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // Nothing to be implemented as this is last bolt of the topology
    }

}