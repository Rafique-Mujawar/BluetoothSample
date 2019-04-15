package bluetooth.mujawar.rafique;

import android.content.Intent;

/**
 * @author Rafique Mujawar
 * Date 06-04-2019
 */
public interface BluetoothReceiverCallbacks {
  void onConnectionStateChangedReceived(Intent intent);

  void onRequestEnableReceived(Intent intent);

  void onDiscoveryStartedReceived(Intent intent);

  void onDiscoveryFinishedReceived(Intent intent);

  void onLocalNameChangedReceived(Intent intent);

  void onScanModeChangedReceived(Intent intent);

  void onStateChangedReceived(Intent intent);

  void onAclConnectedReceived(Intent intent);

  void onAclDisconnectedReceived(Intent intent);

  void onAclDisconnectRequestedReceived(Intent intent);

  void onBondStateChangedReceived(Intent intent);

  void onClassChangedReceived(Intent intent);

  void onFoundReceived(Intent intent);

  void onNameChangedReceived(Intent intent);

  void onPairingRequestReceived(Intent intent);

  void onUuidReceived(Intent intent);
}
