package com.example.android_dialog;

import android.app.AlertDialog;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
  private static final String DIALOG_CHANNEL = "com.example.android_dialog/dialog";

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
  super.configureFlutterEngine(flutterEngine);
    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), DIALOG_CHANNEL)
        .setMethodCallHandler(
          (call, result) -> {
      if (call.method.equals("showAlert")) {
        String message = call.argument("message");
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .create();
        alertDialog.show();
        result.success(null); // No data to return
      } else {
        result.notImplemented();
      }
          }
        );
  }
}
