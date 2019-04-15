package bluetooth.mujawar.rafique;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

public class BlueToothMainActivity extends AppCompatActivity implements
    BluetoothReceiverCallbacks, View.OnClickListener {

  public static final int REQUEST_BLUETOOTH_ENABLE = 9876;
  private static final String TAG = BlueToothMainActivity.class.getName();
  private BluetoothBroadCastReceivers mReceivers;
  private BluetoothHeadset mBluetoothHeadsetProxy;
  private BluetoothAdapter mBluetoothAdapter;
  private BluetoothProfile.ServiceListener mProfileListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.i(TAG, "onCreate: ");
    setContentView(R.layout.activity_blue_tooth_main);
    mReceivers = new BluetoothBroadCastReceivers(this, this);
    mReceivers.registerBluetoothReceivers();
    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    initViews();
  }

  private void initViews() {
    findViewById(R.id.tvCheckPairedDevices).setOnClickListener(this);
    findViewById(R.id.tvEnableBluetooth).setOnClickListener(this);
    findViewById(R.id.tvIsBluetoothSupported).setOnClickListener(this);
    findViewById(R.id.tvSetupBluetooth).setOnClickListener(this);
  }

  private void checkPairedDevices() {
    Log.i(TAG, "checkPairedDevices: ");
    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
    if (pairedDevices.size() > 0) {
      // There are paired devices. Get the name and address of each paired device.
      for (BluetoothDevice device : pairedDevices) {
        Log.i(TAG, "checkPairdDevices: Device Name:" + device.getName());
        Log.i(TAG, "checkPairdDevices: DEVICE_TYPE:" + device.getType());
        Log.i(TAG, "checkPairdDevices: Bond Sate:" + device.getBondState());
        Log.i(TAG, "checkPairdDevices: MAC address:" + device.getAddress());
        Log.i(TAG, "checkPairdDevices: details: " + device.toString());
        Log.i(TAG, "******************************************************: ");
      }
    } else {
      Log.i(TAG, "checkPairedDevices: No Devices paired previously");
      Toast.makeText(this, "No Devices paired previously", Toast.LENGTH_SHORT).show();
    }
  }


  private void setUpBlueTooth() {
    Log.i(TAG, "setUpBlueTooth: ");
    Toast.makeText(this, "setting up bluetooth", Toast.LENGTH_SHORT).show();
    // Get the default adapter
    mProfileListener = new ProfileListener();
// Establish connection to the proxy.
    mBluetoothAdapter.getProfileProxy(this, mProfileListener, BluetoothProfile.HEADSET);
// Close proxy connection after use.
    //bluetoothAdapter.closeProfileProxy(mBluetoothHeadsetProxy);
  }

  @Override
  protected void onDestroy() {
    mReceivers.unregisterBluetoothReceivers();
    super.onDestroy();
  }


  /**
   * Called when a view has been clicked.
   *
   * @param v The view that was clicked.
   */
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tvCheckPairedDevices:
        checkPairedDevices();
        break;
      case R.id.tvEnableBluetooth:
        if (mBluetoothAdapter.isEnabled()) {
          Log.i(TAG, "Bluetooth is Enabled: ");
          Toast.makeText(getApplicationContext(), "Bluetooth is Enabled: ", Toast.LENGTH_SHORT)
              .show();
        } else {
          Log.i(TAG, "Bluetooth is not enabled: ");
          Toast.makeText(getApplicationContext(), "Bluetooth is not enabled: ", Toast
              .LENGTH_SHORT).show();
          //Need to enable BLueTooth
          Intent intent = new Intent();
          intent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
          startActivityForResult(intent, REQUEST_BLUETOOTH_ENABLE);
        }
        break;
      case R.id.tvIsBluetoothSupported:
        if (mBluetoothAdapter == null) {
          Log.i(TAG, "This device does not support bluetooth: ");
          // Device doesn't support Bluetooth
          Toast.makeText(this, "This device does not support bluetooth", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(this, "This device supports bluetooth", Toast.LENGTH_SHORT).show();
        }
        break;
      case R.id.tvSetupBluetooth:
        setUpBlueTooth();
        break;
    }
  }

  @Override
  public void onConnectionStateChangedReceived(Intent intent) {
    Log.i(TAG, "onConnectionStateChangedReceived: ");
  }

  @Override
  public void onRequestEnableReceived(Intent intent) {
    Log.i(TAG, "onRequestEnableReceived: ");
  }

  @Override
  public void onDiscoveryStartedReceived(Intent intent) {
    Log.i(TAG, "onDiscoveryStartedReceived: ");
  }

  @Override
  public void onDiscoveryFinishedReceived(Intent intent) {
    Log.i(TAG, "onDiscoveryFinishedReceived: ");
  }

  @Override
  public void onLocalNameChangedReceived(Intent intent) {
    Log.i(TAG, "onLocalNameChangedReceived: ");
    if(intent.hasExtra(BluetoothAdapter.EXTRA_LOCAL_NAME)){
      Toast.makeText(this,"New Name:"+intent.getStringExtra(BluetoothAdapter.EXTRA_LOCAL_NAME),
          Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onScanModeChangedReceived(Intent intent) {
    Log.i(TAG, "onScanModeChangedReceived: ");
  }

  @Override
  public void onStateChangedReceived(Intent intent) {
    Log.i(TAG, "onStateChangedReceived: ");
    if (null != intent && intent.hasExtra(BluetoothAdapter.EXTRA_STATE)) {
      Log.i(TAG, "onReceive: state of bluetooth:");
      if (BluetoothAdapter.STATE_TURNING_ON == intent.getIntExtra(BluetoothAdapter
          .EXTRA_PREVIOUS_STATE, 0)) {
        Log.i(TAG, "onReceive: STATE_TURNING_ON");
      } else if (BluetoothAdapter.STATE_TURNING_OFF == intent.getIntExtra(BluetoothAdapter
          .EXTRA_PREVIOUS_STATE, 0)) {
        Log.i(TAG, "onReceive: STATE_TURNING_OFF ");
      }
    }

    if (null != intent && intent.hasExtra(BluetoothAdapter.EXTRA_PREVIOUS_STATE)) {
      Log.i(TAG, "onReceive: previous sate of bluetooth:");
      if (BluetoothAdapter.STATE_ON == intent.getIntExtra(BluetoothAdapter
          .EXTRA_PREVIOUS_STATE, 0)) {
        Log.i(TAG, "onReceive: previously STATE_ON");
      } else if (BluetoothAdapter.STATE_OFF == intent.getIntExtra(BluetoothAdapter
          .EXTRA_PREVIOUS_STATE, 0)) {
        Log.i(TAG, "onReceive: previously STATE_OFF ");
      }
    }
  }

  @Override
  public void onAclConnectedReceived(Intent intent) {
    Log.i(TAG, "onAclConnectedReceived: ");
  }

  @Override
  public void onAclDisconnectedReceived(Intent intent) {
    Log.i(TAG, "onAclDisconnectedReceived: ");
  }

  @Override
  public void onAclDisconnectRequestedReceived(Intent intent) {
    Log.i(TAG, "onAclDisconnectRequestedReceived: ");
  }

  @Override
  public void onBondStateChangedReceived(Intent intent) {
    Log.i(TAG, "onBondStateChangedReceived: ");
  }

  @Override
  public void onClassChangedReceived(Intent intent) {
    Log.i(TAG, "onClassChangedReceived: ");
  }

  @Override
  public void onFoundReceived(Intent intent) {
    Log.i(TAG, "onFoundReceived: ");
    String action = intent.getAction();
    if (BluetoothDevice.ACTION_FOUND.equals(action)) {
      // Discovery has found a device. Get the BluetoothDevice
      // object and its info from the Intent.
      BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
      Log.i(TAG, "ActionFoundReceiver: Device Name:" + device.getName());
      Log.i(TAG, "ActionFoundReceiver: DEVICE_TYPE:" + device.getType());
      Log.i(TAG, "ActionFoundReceiver: Bond Sate:" + device.getBondState());
      Log.i(TAG, "ActionFoundReceiver: MAC address:" + device.getAddress());
      Log.i(TAG, "ActionFoundReceiver: details: " + device.toString());
    }
  }

  @Override
  public void onNameChangedReceived(Intent intent) {
    Log.i(TAG, "onNameChangedReceived: ");
  }

  @Override
  public void onPairingRequestReceived(Intent intent) {
    Log.i(TAG, "onPairingRequestReceived: ");
  }

  @Override
  public void onUuidReceived(Intent intent) {
    Log.i(TAG, "onUuidReceived: ");
  }

  class ProfileListener implements BluetoothProfile.ServiceListener {

    /**
     * Called to notify the client when the proxy object has been
     * connected to the service.
     *
     * @param profile - One of { #HEALTH}, {#HEADSET} or { #A2DP}
     * @param proxy   - One of {BluetoothHealth}, {@link BluetoothHeadset} or {
     *                BluetoothA2dp}
     */
    @Override
    public void onServiceConnected(int profile, BluetoothProfile proxy) {
      if (profile == BluetoothProfile.HEADSET) {
        mBluetoothHeadsetProxy = (BluetoothHeadset) proxy;
      }
    }

    /**
     * Called to notify the client that this proxy object has been
     * disconnected from the service.
     *
     * @param profile - One of { #HEALTH}, { #HEADSET} or { #A2DP}
     */
    @Override
    public void onServiceDisconnected(int profile) {
      if (profile == BluetoothProfile.HEADSET) {
        mBluetoothHeadsetProxy = null;
      }
    }
  }
}
