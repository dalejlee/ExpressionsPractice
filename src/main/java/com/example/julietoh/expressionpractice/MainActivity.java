package com.example.julietoh.expressionpractice;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.affectiva.android.affdex.sdk.detector.CameraDetector;
import com.affectiva.android.affdex.sdk.detector.Detector;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSIONS_REQUEST = 42;  //value is arbitrary (between 0 and 255)
    boolean cameraPermissionsAvailable = false;
    boolean isFrontFacingCameraDetected = false;
    private CameraDetector detector = null;
    CameraDetector.CameraType cameraType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkForCameraPermissions();
        determineCameraAvailability();
        initializeCameraDetector();
    }

    /**
     * Assume device has front facing camera for now
     */
    void determineCameraAvailability() {
        PackageManager manager = getPackageManager();
        isFrontFacingCameraDetected = manager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);

        //set default camera settings
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //restore the camera type settings
        String cameraTypeName = sharedPreferences.getString("cameraType", CameraDetector.CameraType.CAMERA_FRONT.name());
        if (cameraTypeName.equals(CameraDetector.CameraType.CAMERA_FRONT.name())) {
            cameraType = CameraDetector.CameraType.CAMERA_FRONT;
        }
    }

    private void checkForCameraPermissions() {
        cameraPermissionsAvailable =
                ContextCompat.checkSelfPermission(
                        getApplicationContext(),
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        if (!cameraPermissionsAvailable) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                // showPermissionExplanationDialog(CAMERA_PERMISSIONS_REQUEST);
            } else {
                // No explanation needed, we can request the permission.
                requestCameraPermissions();
            }
        }
    }

    private void requestCameraPermissions() {
        if (!cameraPermissionsAvailable) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSIONS_REQUEST);

            // CAMERA_PERMISSIONS_REQUEST is an app-defined int constant that must be between 0 and 255.
            // The callback method gets the result of the request.
        }
    }

    void initializeCameraDetector() {
        /* Put the SDK in camera mode by using this constructor. The SDK will be in control of
         * the camera. If a SurfaceView is passed in as the last argument to the constructor,
         * that view will be painted with what the camera sees.
         */
        detector = new CameraDetector(this, cameraType, cameraView, (multiFaceModeEnabled ? MAX_SUPPORTED_FACES : 1), Detector.FaceDetectorMode.LARGE_FACES);
        detector.setImageListener(this);
        detector.setFaceListener(this);
        detector.setOnCameraEventListener(this);
    }


}
