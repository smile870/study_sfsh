package com.trs.hadoop.k_wd;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;

/** 
 * @author Smile 
 * @version ����ʱ�䣺2017��3��29�� ����5:37:52 
 */
public class k_map extends Mapper<IntWritable, Text, IntWritable, DoubleWritable> {

	IntWritable intw=new IntWritable();
	DoubleWritable doub=new DoubleWritable();
@Override
protected void map(
		IntWritable key,
		Text value,
		Mapper<IntWritable, Text, IntWritable, DoubleWritable>.Context context)
		throws IOException, InterruptedException {
	//��ȡ��
	String line=value.toString();
	intw.set(Integer.getInteger(line.split(";")[0]));
	doub.set(Double.valueOf(line.split(";")[1]).doubleValue());
	context.write(intw, doub);      
}
	

}
