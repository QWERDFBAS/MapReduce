package FindFriends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class FindFriendsTwoMapper extends Mapper<LongWritable, Text, Text, Text> {

    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /**
         * A	I,K,C,B,G,F,H,O,D,
         * B	A,F,J,E,
         *
         * A-B	E C
         * A-C	D F
         * A-D	E F
         * A-E	D B C
         * A-F	O B C D E
         * A-G	F E C D
         * A-H	E C D O
         * A-I	O
         * A-J	O B
         * A-K	D C
         * A-L	F E D
         * A-M	E F
         */

        String line = value.toString();

        String[] fields = line.split("\t");

        String person = fields[0];
        String[] friends = fields[1].split(",");

        Arrays.sort(friends);

        for(int i = 0; i < friends.length - 1; i++) {
            for(int j = i + 1; j < friends.length; j++) {
                k.set(friends[i] + "-" + friends[j]);
                v.set(person);
                context.write(k, v);
            }
        }
    }
}
