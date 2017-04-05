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
 * @version ����ʱ�䣺2017��3��29�� ����9:23:42 
 */
public class k_wordcount {
	/*
	 * ���Ƚ���һ��Job�ĳ�ʼ�����̡�main��������Jobconf������MapReduce Job���г�ʼ����Ȼ�����setJobName()�����������Job����Job���к�������������ڸ�����ҵ�Job���Ա���JobTracker��Tasktracker��ҳ���ж�����м��ӡ�
    JobConf conf = new JobConf(WordCount. class ); conf.setJobName("wordcount" ); 
	 */
	/*
	 * ͬ־�ǼӰѾ����һ����
	 */
	/**
	 * 1�����ó�ʼ��
	 * 2�����������ļ�
	 * 3������ִ������
	 * 4����������ļ�
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		/*
		 * Job�ĳ�ʼ�����̡�main��������Jobconf������MapReduce Job���г�ʼ����
		 * Ȼ�����setJobName()�����������Job����Job���к�������������ڸ�����ҵ�Job��
		 * �Ա���JobTracker��Tasktracker��ҳ���ж�����м��ӡ�
		 */
		Configuration conf=new Configuration();
		Job job=new Job(conf, "helloword workcount");
		job.setJarByClass(k_wordcount.class);
		//����ִ������
		job.setMapperClass(k_mapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(k_reduce.class);
		/*����ʲô������,�������Ѿ�����Ϊʲô��Ҫ����һ��
		 * ��������Job������<key,value>����key��value�������ͣ�
		          ��Ϊ�����<����,����>������key����Ϊ"Text"���ͣ��൱��Java��String���͡�
		   Value����Ϊ"IntWritable"���൱��Java�е�int���͡�
		*/
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//������������ļ�
		FileInputFormat.addInputPath(job, new Path("hdfs://192.168.32.129:9000/user/root/input/k_wordcount.txt"));
		FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.32.129:9000/user/root/output"));
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}
	
	

	
	
	
	
	
	

}
