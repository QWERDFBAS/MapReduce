package Compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;


public class TestCompress {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        compress(" ", "org.apache.hadoop.io.compress.BZip2Codec");

        decompress("");

    }

    public static void compress(String path, String method) throws IOException, ClassNotFoundException {
//        1.获取输入流
        FileInputStream fis = new FileInputStream(new File(path));
        Class codeclass = Class.forName(method);
        Configuration conf;
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeclass, new Configuration());

//        2.获取输出流
        FileOutputStream fos = new FileOutputStream(new File(path + codec.getDefaultExtension()));
//        压缩
        CompressionOutputStream cos = codec.createOutputStream(fos);

//        3.流的对拷
        IOUtils.copyBytes(fis, cos, 1024*1024, false);

//        4.关闭资源
        IOUtils.closeStream(cos);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);

    }


    public static void decompress(String path) throws IOException {

//        1.检查是否可以解压缩
        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
        CompressionCodec codec = factory.getCodec(new Path(path));

        if(codec == null) {
            System.out.println("Wrong!!!");
            return ;
        }

//        2。获取输入流
        FileInputStream fis = new FileInputStream(new File(path));
        CompressionInputStream cis = codec.createInputStream(fis);

//        3，获取输出流
        FileOutputStream fos = new FileOutputStream(new File(path + ".decode"));

//        4.流的对拷
        IOUtils.copyBytes(cis, fos, 1024*1024, false);

//        5.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(cis);
        IOUtils.closeStream(fis);
    }
}
