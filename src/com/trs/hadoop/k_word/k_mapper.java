package com.trs.hadoop.k_word;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/** 
 * @author Smile 
 * @version 创建时间：2017年3月28日 下午5:09:28 
 */
public class k_mapper extends Mapper<IntWritable, Text, Text, IntWritable>{
	/*
	 * 疑问就是关于hadoop里面的参数
	 * http://www.cnblogs.com/robert-blue/p/4165602.html 参数可以自己定制
	 * IntWritable相当于int
	 * Text相当于String
	 * 继承的mapper的类型会影响默认方法值的类型
	 */
	//设置全局的变量
	   private static final IntWritable one = new IntWritable(1);
	   private Text word = new Text();
	//实现map的方法，map要做的事就是收录数据
	@Override
	protected void map(IntWritable key, Text value,
			Mapper<IntWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//是不是相当于 Text如果是文本每次就一条一条的读入
				String line=value.toString();
				//获取文本中的单词加入到map
				//一种是自己手动拆串，还有自动拆单词的StringTokenizer
				StringTokenizer ss=new StringTokenizer(value.toString());
				while (ss.hasMoreTokens())
				{//取值
				//System.out.println(ss.nextToken());
					//会发现类型不一致报错
					this.word.set(ss.nextToken());
					context.write(word, one);
					//不能简单点用string吗？
				}
				/*for (String word : line.split(" ")) {
					context.write(word.trim(), one);
				}*/
			//	super.map(key, value, context);

	}
	/*详细学习各个参数的用法及实际的意义
	 * Text ，常见的用法，将值set进去，相当于 String
	 * IntWritable int 发现他们和int及String的区别就是他们都是对象
	 * 2：了解下各个参数是输入输出的问题
	 * 
	 * 　Hadoop提供了如下内容的数据类型，这些数据类型都实现了WritableComparable接口，以便用这些类型定义的数据可以被序列化进行网络传输和文件存储，以及进行大小比较。
	    BooleanWritable：标准布尔型数值
	
	    ByteWritable：单字节数值
	
	    DoubleWritable：双字节数
	
	    FloatWritable：浮点数
	
	    IntWritable：整型数
	
	    LongWritable：长整型数
	
	    Text：使用UTF8格式存储的文本
	
	    NullWritable：当<key,value>中的key或value为空时使用
	 
	Job输出结果<key,value>的中key和value数据类型，因为结果是<单词,个数>，所以key设置为"Text"类型，相当于Java中String类型。Value设置为"IntWritable"，相当于Java中的int类型。
	*/
	/*TextInputFormat是Hadoop默认的输入方法，在TextInputFormat中，每个文件（或其一部分）都会单独地作为map的输入，而这个是继承自FileInputFormat的。之后，每行数据都会生成一条记录，每条记录则表示成<key,value>形式：
	 * 　每一种输入格式都有一种输出格式与其对应。默认的输出格式是TextOutputFormat，这种输出方式与输入类似，会将每条记录以一行的形式存入文本文件。不过，它的键和值可以是任意形式的，因为程序内容会调用toString()方法将键和值转换为String类型再输出。
	 * 接口是一个规范类型，它有4种形式的参数，分别用来指定map的输入key值类型、输入value值类型、输出key值类型和输出value值类型
	 * 
	 * */


}
