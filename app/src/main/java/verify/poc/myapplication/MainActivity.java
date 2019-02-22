package verify.poc.myapplication;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        AppCenter.start(getApplication(), "f5cf20cc-758a-4d66-ad02-06e51d2cd66d", Analytics.class, Crashes.class);

      //  Fabric.with(this, new Crashlytics());

        resetKey();
       forceACrash();
        forceANR();
        setUserId();

    }

    public void setKeysBasic(String key) {
        // [START crash_set_keys_basic]
        Crashlytics.setString(key, "foo" /* string value */);

        Crashlytics.setBool(key, true /* boolean value */);

        Crashlytics.setDouble(key, 1.0 /* double value */);

        Crashlytics.setFloat(key, 1.0f /* float value */);

        Crashlytics.setInt(key, 1 /* int value */);
        // [END crash_set_keys_basic]
    }

    public void resetKey() {
        // [START crash_re_set_key]
        Crashlytics.setInt("current_level", 4);
        Crashlytics.setString("last_UI_action", "landing_screen");
        // [END crash_re_set_key]
    }

    public void logReportAndPrint() {
        // [START crash_log_report_and_print]
        Crashlytics.log(Log.DEBUG, "tag", "message");
        // [END crash_log_report_and_print]
    }

    public void logReportOnly() {
        // [START crash_log_report_only]
        Crashlytics.log("message");
        // [END crash_log_report_only]
    }

    public void enableAtRuntime() {
        // [START crash_enable_at_runtime]
        Fabric.with(this, new Crashlytics());
        // [END crash_enable_at_runtime]
    }

    public void setUserId() {
        // [START crash_set_user_id]
        Crashlytics.setUserIdentifier("abcbnk100436");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                crashapp();
            }
        }, 5000);
        // [END crash_set_user_id]
    }

    public void methodThatThrows() throws Exception {
        throw new Exception();
    }

    public void logCaughtEx() {
        // [START crash_log_caught_ex]
        try {
            methodThatThrows();
        } catch (Exception e) {
            Crashlytics.logException(e);
            // handle your exception here
        }
        // [END crash_log_caught_ex]
    }

    public void enableDebugMode() {
        // [START crash_enable_debug_mode]
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)  // Enables Crashlytics debugger
                .build();
        Fabric.with(this, new Crashlytics());
        // [END crash_enable_debug_mode]
    }

    public void crashapp(){

    }

    public void forceACrash() {
        // [START crash_force_crash]
        final Button crashButton = (Button) findViewById(R.id.crashButton);
        //crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                crashapp();
            }
        });

//        addContentView(crashButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.FILL_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
        // [END crash_force_crash]
    }

    public void forceANR() {
        // [START crash_force_crash]
        Button anrButton = (Button)findViewById(R.id.anrButton);
       // anrButton.setText("ANR!");
        anrButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                while(true) {}
            }
        });

//        addContentView(anrButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.FILL_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
        // [END crash_force_crash]
    }

}
