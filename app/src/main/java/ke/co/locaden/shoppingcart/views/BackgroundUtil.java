package ke.co.locaden.shoppingcart.views;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.view.KeyEventDispatcher;

public class BackgroundUtil {
    private static final String TAG = "BackgroundUtil";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void scheduleBackgroundTask(Context context){
        try {
            ComponentName componentName = new ComponentName(context,MyJobService.class);
            JobInfo.Builder builder = new JobInfo.Builder(0,componentName);
            long timeInterval = 2 * 1000;
            builder.setPersisted(true);
            builder.setMinimumLatency(timeInterval);
            builder.setOverrideDeadline(timeInterval);
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
                jobScheduler.schedule(builder.build());
            }


        }catch (Exception e){
            Log.e(TAG,"Schedule Background Task");
        }
    }
}
