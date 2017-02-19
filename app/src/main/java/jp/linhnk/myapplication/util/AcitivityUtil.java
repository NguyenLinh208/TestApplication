package jp.linhnk.myapplication.util;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by usr0200475 on 2017/02/16.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class AcitivityUtil {

    public static void startAcitivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }
}
