package verify.poc.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
import com.microsoft.appcenter.espresso.Factory;
import com.microsoft.appcenter.espresso.ReportHelper;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ReportHelper reportHelper = Factory.getReportHelper();
    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void useAppContext() {

        reportHelper.label("Test Starting app");
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("verify.poc.myapplication", appContext.getPackageName());
    }

    @Test
    public void validateCrashButton() {



        // Just for viewing the results. Remove after use.
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.crashButton)).check(matches(isDisplayed()));
    }

    @After
    public void TearDown(){
        reportHelper.label("Test Stopping App");
    }
}
