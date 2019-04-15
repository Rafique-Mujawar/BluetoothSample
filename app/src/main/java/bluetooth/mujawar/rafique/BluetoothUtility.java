package bluetooth.mujawar.rafique;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;

/**
 * @author Rafique Mujawar
 * Date 06-04-2019
 */
public class BluetoothUtility {

  /**
   * Method to enable device as discoverable
   * NOTE: If Bluetooth has not been enabled on the device, then making the device discoverable
   * automatically enables Bluetooth.
   *
   * @param context          {@link Context}
   * @param durationInSecond time is seconds
   */
  public static void enableDiscoverableForTime(Context context, int durationInSecond) {
    Intent discoverableIntent =
        new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
    discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, durationInSecond);
    //Even startActivityForResult not onActivityResult will be called in case with request code
    // as durationInSecond
    context.startActivity(discoverableIntent);
  }

}
