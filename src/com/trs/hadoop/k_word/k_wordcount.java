package com.trs.hadoop.k_word;

import java.io.IOException;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

/** 
 * @author Smile 
 * @version 创建时间：2017年3月29日 上午9:23:42 
 */
public class k_wordcount {
	/*
	 * 首先讲解一下Job的初始化过程。main函数调用Jobconf类来对MapReduce Job进行初始化，然后调用setJobName()方法命名这个Job。对Job进行合理的命名有助于更快地找到Job，以便在JobTracker和Tasktracker的页面中对其进行监视。
    JobConf conf = new JobConf(WordCount. class ); conf.setJobName("wordcount" ); 
	 */
	/*
	 * 同志们加把劲最后一搏了
	 */
	/**
	 * 1：设置初始化
	 * 2：设置输入文件
	 * 3：设置执行流程
	 * 4：设置输出文件
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		/*
		 * Job的初始化过程。main函数调用Jobconf类来对MapReduce Job进行初始化，
		 * 然后调用setJobName()方法命名这个Job。对Job进行合理的命名有助于更快地找到Job，
		 * 以便在JobTracker和Tasktracker的页面中对其进行监视。
		 */
		Configuration conf=new Configuration();
		Job job=new Job(conf, "helloword workcount");
		job.setJarByClass(k_wordcount.class);
		//设置执行流程
		job.setMapperClass(k_mapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(k_reduce.class);
		/*这是什么？？？,类型里已经有了为什么还要设置一次
		 * 接着设置Job输出结果<key,value>的中key和value数据类型，
		          因为结果是<单词,个数>，所以key设置为"Text"类型，相当于Java中String类型。
		   Value设置为"IntWritable"，相当于Java中的int类型。
		*/
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//设置输入输出文件
		FileInputFormat.addInputPath(job, new Path("hdfs://192.168.32.129:9000/user/root/input/k_wordcount.txt"));
		FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.32.129:9000/user/root/output"));
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}
	
	

	
	
	
	
	
	

}
