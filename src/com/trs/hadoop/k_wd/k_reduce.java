package com.trs.hadoop.k_wd;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/** 
 * @author Smile 
 * @version 创建时间：2017年3月29日 下午5:38:18 
 */
public class k_reduce extends Reducer<IntWritable, DoubleWritable, IntWritable, DoubleWritable>{
	
	@Override
	protected void reduce(
			IntWritable arg0,
			Iterable<DoubleWritable> arg1,
			Reducer<IntWritable, DoubleWritable, IntWritable, DoubleWritable>.Context arg2)
			throws IOException, InterruptedException {
		
		double	maxn1 =0;
		for (DoubleWritable doubleWritable : arg1) {
			maxn1 = Math.max(maxn1, doubleWritable.get());
			/*if(maxn.get()<doubleWritable.get()){
				maxn.set(doubleWritable.get());
			}*/
		}
		arg2.write(arg0, new DoubleWritable(maxn1));
	}
}
