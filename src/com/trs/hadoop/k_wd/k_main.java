package com.trs.hadoop.k_wd;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

/** 
 * @author Smile 
 * @version 创建时间：2017年3月29日 下午5:38:08 
 */
public class k_main {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		System.out.println("如果修改呢？");
		Configuration conf=new Configuration();
		Job job=new Job(conf, "hello wd");
		job.setJarByClass(k_main.class);
		job.setMapperClass(k_map.class);
		//job.setCombinerClass(cls);
		job.setReducerClass(k_reduce.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(DoubleWritable.class);
		FileInputFormat.addInputPath(job, new Path("hdfs://192.168.32.129:9000/user/root/input/wd.txt"));
		FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.32.129:9000/user/root/output"));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
