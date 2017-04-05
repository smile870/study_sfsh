package com.trs.hadoop.k_word;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/** 
 * @author Smile 
 * @version 创建时间：2017年3月29日 上午9:35:51 
 */
public class k_reduce extends Reducer<Text, IntWritable, Text, IntWritable>{
	/*
	 * map的结果是以Text, IntWritable输出的，Reducer最终也要Text, IntWritable的形式
	 */
    private IntWritable result = new IntWritable();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2)
			throws IOException, InterruptedException {
		//1：首先要知道map输出过来的内容，map合并的程度，
		  //结合解释知道了key是key
		//value 是列表，那么我们只需要将列表相加即可
		int sum=0;
		for (IntWritable val : values) {
			sum+=val.get();
		}
		this.result.set(sum);
		arg2.write(key, result);
	}

}
