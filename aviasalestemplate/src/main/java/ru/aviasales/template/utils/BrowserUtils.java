package ru.aviasales.template.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;

import ru.aviasales.template.BrowserActivity;
import ru.aviasales.template.ui.fragment.BrowserFragment;

public class BrowserUtils {

	public static void onOpenBrowser(@Nullable Activity activity,
	                                 @Nullable String url,
	                                 @Nullable String title,
	                                 boolean showLoadingDialog) {
		if (activity == null || url == null || title == null) return;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Utils.getPreferences(activity)
					.edit()
					.putString(BrowserFragment.URL, url)
					.putString(BrowserFragment.TITLE, title)
					.commit();
			launchInternalBrowser(activity, showLoadingDialog);
		} else {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(url));
			activity.startActivity(intent);
		}
	}

	private static void launchInternalBrowser(Activity activity, boolean showLoadingDialog) {
		Intent intent = new Intent(activity, BrowserActivity.class);
		intent.putExtra(BrowserActivity.SHOW_LOADING_DIALOG, showLoadingDialog);
		activity.startActivity(intent);
	}
}
