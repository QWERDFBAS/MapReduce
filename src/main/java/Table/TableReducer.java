package Table;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;



public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {

        ArrayList<TableBean> orderinfo = new ArrayList<>();
        TableBean pdinfo = new TableBean();

        for(TableBean tablebean : values) {
            if("order".equals(tablebean.getFlag())) {
                TableBean temp = new TableBean();

                try {
                    BeanUtils.copyProperties(temp, tablebean);
                    orderinfo.add(temp);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    BeanUtils.copyProperties(pdinfo, tablebean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        for(TableBean tableBean : orderinfo) {
            tableBean.setPname(pdinfo.getPname());
            context.write(tableBean, NullWritable.get());
        }
    }
}
