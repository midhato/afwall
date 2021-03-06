/**
 * Detect the connectivity changes ( for roaming changes)
 * 
 * Copyright (C) 2011-2012  Umakanthan Chandran
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Umakanthan Chandran
 * @version 1.0
 */
package dev.ukanth.ufirewall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConnectivityChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, Intent intent) {
		try {

			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo[] ni = cm.getAllNetworkInfo();
			for (NetworkInfo info : ni) {
				if (ni != null) {
					if (info.getType() == ConnectivityManager.TYPE_MOBILE)
						if (info.isConnectedOrConnecting() && info.isRoaming()) {
							Api.applyIptablesRules(context, false);
						}
				}
			}
		} catch (Exception e) {
			Log.d("Exception in ConnectivityChangeReceiver",
					e.getLocalizedMessage());
		}
	}
}
