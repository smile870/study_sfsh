package com.trs.hadoop.k_word;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/** 
 * @author Smile 
 * @version ����ʱ�䣺2017��3��28�� ����5:09:28 
 */
public class k_mapper extends Mapper<IntWritable, Text, Text, IntWritable>{
	/*
	 * ���ʾ��ǹ���hadoop����Ĳ���
	 * http://www.cnblogs.com/robert-blue/p/4165602.html ���������Լ�����
	 * IntWritable�൱��int
	 * Text�൱��String
	 * �̳е�mapper�����ͻ�Ӱ��Ĭ�Ϸ���ֵ������
	 */
	//����ȫ�ֵı���
	   private static final IntWritable one = new IntWritable(1);
	   private Text word = new Text();
	//ʵ��map�ķ�����mapҪ�����¾�����¼����
	@Override
	protected void map(IntWritable key, Text value,
			Mapper<IntWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//�ǲ����൱�� Text������ı�ÿ�ξ�һ��һ���Ķ���
				String line=value.toString();
				//��ȡ�ı��еĵ��ʼ��뵽map
				//һ�����Լ��ֶ��𴮣������Զ��𵥴ʵ�StringTokenizer
				StringTokenizer ss=new StringTokenizer(value.toString());
				while (ss.hasMoreTokens())
				{//ȡֵ
				//System.out.println(ss.nextToken());
					//�ᷢ�����Ͳ�һ�±���
					this.word.set(ss.nextToken());
					context.write(word, one);
					//���ܼ򵥵���string��
				}
				/*for (String word : line.split(" ")) {
					context.write(word.trim(), one);
				}*/
			//	super.map(key, value, context);

	}
	/*��ϸѧϰ�����������÷���ʵ�ʵ�����
	 * Text ���������÷�����ֵset��ȥ���൱�� String
	 * IntWritable int �������Ǻ�int��String������������Ƕ��Ƕ���
	 * 2���˽��¸����������������������
	 * 
	 * ��Hadoop�ṩ���������ݵ��������ͣ���Щ�������Ͷ�ʵ����WritableComparable�ӿڣ��Ա�����Щ���Ͷ�������ݿ��Ա����л��������紫����ļ��洢���Լ����д�С�Ƚϡ�
	    BooleanWritable����׼��������ֵ
	
	    ByteWritable�����ֽ���ֵ
	
	    DoubleWritable��˫�ֽ���
	
	    FloatWritable��������
	
	    IntWritable��������
	
	    LongWritable����������
	
	    Text��ʹ��UTF8��ʽ�洢���ı�
	
	    NullWritable����<key,value>�е�key��valueΪ��ʱʹ��
	 
	Job������<key,value>����key��value�������ͣ���Ϊ�����<����,����>������key����Ϊ"Text"���ͣ��൱��Java��String���͡�Value����Ϊ"IntWritable"���൱��Java�е�int���͡�
	*/
	/*TextInputFormat��HadoopĬ�ϵ����뷽������TextInputFormat�У�ÿ���ļ�������һ���֣����ᵥ������Ϊmap�����룬������Ǽ̳���FileInputFormat�ġ�֮��ÿ�����ݶ�������һ����¼��ÿ����¼���ʾ��<key,value>��ʽ��
	 * ��ÿһ�������ʽ����һ�������ʽ�����Ӧ��Ĭ�ϵ������ʽ��TextOutputFormat�����������ʽ���������ƣ��Ὣÿ����¼��һ�е���ʽ�����ı��ļ������������ļ���ֵ������������ʽ�ģ���Ϊ�������ݻ����toString()����������ֵת��ΪString�����������
	 * �ӿ���һ���淶���ͣ�����4����ʽ�Ĳ������ֱ�����ָ��map������keyֵ���͡�����valueֵ���͡����keyֵ���ͺ����valueֵ����
	 * 
	 * */


}
