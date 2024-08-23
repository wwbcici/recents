package ww.start.recents;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class Recents extends Activity {
  @Override
  protected void onCreate(Bundle b) {
    super.onCreate(b);

    String classname = null, packagename = null;

    switch (Build.BRAND) {
      case "HANVON":
        packagename = "com.hanvon.recents";
        classname = "com.hanvon.recents.MainActivity";
        break;

      case "OPPO":
        packagename = "com.android.chrome";
        classname = "com.google.android.apps.chrome.Main";
    }

    if (packagename != null && classname != null)
      startActivity(new Intent().setClassName(packagename, classname));
    finishAndRemoveTask();
  }
}

