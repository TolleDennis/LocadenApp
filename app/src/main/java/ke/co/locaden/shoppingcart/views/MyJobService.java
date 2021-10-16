package ke.co.locaden.shoppingcart.views;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
private static final String TAG = "MyJobService";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG,"This is the Best Car Shop App");

        BackgroundUtil.scheduleBackgroundTask(this);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG,"ENJOY");
        return true;
    }
}
