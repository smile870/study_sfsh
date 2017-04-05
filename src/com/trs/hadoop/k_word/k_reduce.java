package com.trs.hadoop.k_word;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/** 
 * @author Smile 
 * @version ����ʱ�䣺2017��3��29�� ����9:35:51 
 */
public class k_reduce extends Reducer<Text, IntWritable, Text, IntWritable>{
	/*
	 * map�Ľ������Text, IntWritable����ģ�Reducer����ҲҪText, IntWritable����ʽ
	 */
    private IntWritable result = new IntWritable();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2)
			throws IOException, InterruptedException {
		//1������Ҫ֪��map������������ݣ�map�ϲ��ĳ̶ȣ�
		  //��Ͻ���֪����key��key
		//value ���б���ô����ֻ��Ҫ���б���Ӽ���
		int sum=0;
		for (IntWritable val : values) {
			sum+=val.get();
		}
		this.result.set(sum);
		arg2.write(key, result);
	}

}
