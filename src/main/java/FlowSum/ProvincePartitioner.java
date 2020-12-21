package FlowSum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class ProvincePartitioner extends Partitioner<Text, FlowBean> {


    @Override
    public int getPartition(Text key, FlowBean value, int numPartitions) {

        String prePhoneNum = key.toString().substring(0, 3);

        int partition = 4;

        if(prePhoneNum.equals("136")) {
            partition = 0;
        } else if(prePhoneNum.equals("137")) {
            partition = 1;
        } else if(prePhoneNum.equals("138")) {
            partition = 2;
        } else if(prePhoneNum.equals("139")) {
            partition = 3;
        }
        return partition;
    }
}
