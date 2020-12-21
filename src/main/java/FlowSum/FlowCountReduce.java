package FlowSum;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReduce extends Reducer<Text, FlowBean, Text, FlowBean> {

    FlowBean v = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
//        1.累加求和
        long sum_upflow = 0;
        long sum_downflow = 0;

        for(FlowBean value : values) {
            sum_upflow += value.getUpflow();
            sum_downflow += value.getDownflow();
        }

        FlowBean resultBean = new FlowBean(sum_upflow, sum_downflow);

//        2.写出
        context.write(key, resultBean);
    }
}
