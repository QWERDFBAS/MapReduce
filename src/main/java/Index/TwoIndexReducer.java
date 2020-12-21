package Index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TwoIndexReducer extends Reducer<Text, Text, Text, Text> {

    Text v = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//        atguigu  --      a.txt	3

        StringBuilder sb = new StringBuilder();

        for(Text value : values) {
            sb.append(value.toString().replace("\t", "-->")).append("\t");
        }

        v.set(sb.toString());

        context.write(key, v);

    }
}
