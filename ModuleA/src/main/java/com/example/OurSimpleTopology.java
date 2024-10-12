package com.example;

import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;

import com.example.RandomDigitSpout;
import com.example.EvenDigitBolt;
import com.example.MultiplyByTenBolt;
import com.example.FileWritingBolt;

public class OurSimpleTopology { 

  public static void main(String[] args) throws Exception
  {
    TopologyBuilder builder = new TopologyBuilder();

    // Attach the random digit spout to the topology.
    // Use just 1 thread for the spout.
    builder.setSpout("random-digit-spout", new RandomDigitSpout());

    // Connect the even digit bolt to our spout. 
    // The bolt will use 2 threads and the digits will be randomly
    // shuffled/distributed among the 2 threads.
    // The third parameter is formally called the parallelism hint.
    builder.setBolt("even-digit-bolt", new EvenDigitBolt(), 2)
           .shuffleGrouping("random-digit-spout");

    // Connect the multiply-by-10 bolt to our even digit bolt.
    // This bolt will use 4 threads, among which data from the
    // even digit bolt will be shuffled/distributed randomly.
    builder.setBolt("multiplied-by-ten-bolt", new MultiplyByTenBolt(), 4)
           .shuffleGrouping("even-digit-bolt");

    String filePath = "/data/ModuleA.dump";
    FileWritingBolt file = new FileWritingBolt(filePath);
    builder.setBolt("file-bolt", file)
      .shuffleGrouping("multiplied-by-ten-bolt");

    // Create a configuration object.
    Config conf = new Config();
    conf.setNumWorkers(2);
    StormSubmitter.submitTopology("our-simple-topology", conf, builder.createTopology());

    // Total number of tasks =
    // 1 + 2 + 4 + 1 + 2 acker tasks = 10 tasks
    // This can be divided into 2 workers @ 5 tasks
    // Total memory = 10 x 128 (default) = 1280 MB
  }
}