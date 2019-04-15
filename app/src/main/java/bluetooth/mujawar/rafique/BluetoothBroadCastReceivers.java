package bluetooth.mujawar.rafique;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * @author Rafique Mujawar
 * Date 06-04-2019
 */
public class BluetoothBroadCastReceivers {

  private Context mContext;
  private static BluetoothReceiverCallbacks mCallbacks;

  private BroadcastReceiver mConnectionStateChangedReceiver;
  private BroadcastReceiver mRequestEnableReceiver;
  private BroadcastReceiver mDiscoveryStartedReceiver;
  private BroadcastReceiver mDiscoveryFinishedReceiver;
  private BroadcastReceiver mLocalNameChangedReceiver;
  private BroadcastReceiver mScanModeChangedReceiver;
  private BroadcastReceiver mStateChangedReceiver;
  private BroadcastReceiver mAclConnectedReceiver;
  private BroadcastReceiver mAclDisconnectedReceiver;
  private BroadcastReceiver mAclDisconnectRequestedReceiver;
  private BroadcastReceiver mBondStateChangedReceiver;
  private BroadcastReceiver mClassChangedReceiver;
  private BroadcastReceiver mFoundReceiver;
  private BroadcastReceiver mNameChangedReceiver;
  private BroadcastReceiver mPairingRequestReceiver;
  private BroadcastReceiver mUuidReceiver;

  public BluetoothBroadCastReceivers(Context context, BluetoothReceiverCallbacks callbacks) {
    this.mContext = context;
    this.mCallbacks = callbacks;
  }

  public void registerBluetoothReceivers() {

    mConnectionStateChangedReceiver = new ConnectionStateChangedReceiver();
    mRequestEnableReceiver = new RequestEnableReceiver();
    mDiscoveryStartedReceiver = new DiscoveryStartedReceiver();
    mDiscoveryFinishedReceiver = new DiscoveryFinishedReceiver();
    mLocalNameChangedReceiver = new LocalNameChangedReceiver();
    mScanModeChangedReceiver = new ScanModeChangedReceiver();
    mStateChangedReceiver = new StateChangedReceiver();
    mAclConnectedReceiver = new AclConnectedReceiver();
    mAclDisconnectedReceiver = new AclDisconnectedReceiver();
    mAclDisconnectRequestedReceiver = new AclDisconnectRequestedReceiver();
    mBondStateChangedReceiver = new BondStateChangedReceiver();
    mClassChangedReceiver = new ClassChangedReceiver();
    mFoundReceiver = new FoundReceiver();
    mNameChangedReceiver = new NameChangedReceiver();
    mPairingRequestReceiver = new PairingRequestReceiver();
    mUuidReceiver = new UuidReceiver();

    IntentFilter filter_connection_state_changed =
        new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
    IntentFilter filter_request_enable = new IntentFilter(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    IntentFilter filter_discovery_started =
        new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
    IntentFilter filter_discovery_finished =
        new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
    IntentFilter filter_local_name_changed =
        new IntentFilter(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED);
    IntentFilter filter_scan_mode_changed =
        new IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
    IntentFilter filter_state_changed = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);

    IntentFilter filter_acl_connected = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
    IntentFilter filter_acl_disconnected =
        new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
    IntentFilter filter_acl_disconnect_requested =
        new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
    IntentFilter filter_bond_state_changed =
        new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
    IntentFilter filter_class_changed = new IntentFilter(BluetoothDevice.ACTION_CLASS_CHANGED);
    IntentFilter filter_found = new IntentFilter(BluetoothDevice.ACTION_FOUND);
    IntentFilter filter_name_changed = new IntentFilter(BluetoothDevice.ACTION_NAME_CHANGED);
    IntentFilter filter_pairing_request = new IntentFilter(BluetoothDevice.ACTION_PAIRING_REQUEST);
    IntentFilter filter_uuid = new IntentFilter(BluetoothDevice.ACTION_UUID);

    mContext.registerReceiver(mConnectionStateChangedReceiver, filter_connection_state_changed);
    mContext.registerReceiver(mRequestEnableReceiver, filter_request_enable);
    mContext.registerReceiver(mDiscoveryStartedReceiver, filter_discovery_started);
    mContext.registerReceiver(mDiscoveryFinishedReceiver, filter_discovery_finished);
    mContext.registerReceiver(mLocalNameChangedReceiver, filter_local_name_changed);
    mContext.registerReceiver(mScanModeChangedReceiver, filter_scan_mode_changed);
    mContext.registerReceiver(mStateChangedReceiver, filter_state_changed);
    mContext.registerReceiver(mAclConnectedReceiver, filter_acl_connected);
    mContext.registerReceiver(mAclDisconnectedReceiver, filter_acl_disconnected);
    mContext.registerReceiver(mAclDisconnectRequestedReceiver, filter_acl_disconnect_requested);
    mContext.registerReceiver(mBondStateChangedReceiver, filter_bond_state_changed);
    mContext.registerReceiver(mClassChangedReceiver, filter_class_changed);
    mContext.registerReceiver(mFoundReceiver, filter_found);
    mContext.registerReceiver(mNameChangedReceiver, filter_name_changed);
    mContext.registerReceiver(mPairingRequestReceiver, filter_pairing_request);
    mContext.registerReceiver(mUuidReceiver, filter_uuid);
  }

  public void unregisterBluetoothReceivers() {
    mContext.unregisterReceiver(mConnectionStateChangedReceiver);
    mContext.unregisterReceiver(mRequestEnableReceiver);
    mContext.unregisterReceiver(mDiscoveryStartedReceiver);
    mContext.unregisterReceiver(mDiscoveryFinishedReceiver);
    mContext.unregisterReceiver(mLocalNameChangedReceiver);
    mContext.unregisterReceiver(mScanModeChangedReceiver);
    mContext.unregisterReceiver(mStateChangedReceiver);
    mContext.unregisterReceiver(mAclConnectedReceiver);
    mContext.unregisterReceiver(mAclDisconnectedReceiver);
    mContext.unregisterReceiver(mAclDisconnectRequestedReceiver);
    mContext.unregisterReceiver(mBondStateChangedReceiver);
    mContext.unregisterReceiver(mClassChangedReceiver);
    mContext.unregisterReceiver(mFoundReceiver);
    mContext.unregisterReceiver(mNameChangedReceiver);
    mContext.unregisterReceiver(mPairingRequestReceiver);
    mContext.unregisterReceiver(mUuidReceiver);
  }


  static class ConnectionStateChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onConnectionStateChangedReceived(intent);
    }
  }


  static class RequestEnableReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onRequestEnableReceived(intent);
    }
  }

  static class DiscoveryStartedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

      mCallbacks.onDiscoveryStartedReceived(intent);
    }
  }

  static class DiscoveryFinishedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

      mCallbacks.onDiscoveryFinishedReceived(intent);
    }
  }

  static class LocalNameChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

      mCallbacks.onLocalNameChangedReceived(intent);
    }
  }

  static class ScanModeChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

      mCallbacks.onScanModeChangedReceived(intent);
    }
  }

  // Create a BroadcastReceiver for ACTION_FOUND.
  static class StateChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onStateChangedReceived(intent);
    }
  }

  static class AclConnectedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onAclConnectedReceived(intent);
    }
  }

  static class AclDisconnectedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onAclDisconnectedReceived(intent);
    }
  }

  static class AclDisconnectRequestedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onAclDisconnectRequestedReceived(intent);
    }
  }

  static class BondStateChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onBondStateChangedReceived(intent);
    }
  }

  static class ClassChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onClassChangedReceived(intent);
    }
  }

  static class FoundReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onFoundReceived(intent);
    }
  }

  static class NameChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onNameChangedReceived(intent);
    }
  }

  static class PairingRequestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onPairingRequestReceived(intent);
    }
  }

  static class UuidReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCallbacks.onUuidReceived(intent);
    }
  }
}
