package com.example.julietoh.expressionpractice;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.affectiva.android.affdex.sdk.Frame;
import com.affectiva.android.affdex.sdk.detector.CameraDetector;
import com.affectiva.android.affdex.sdk.detector.Detector;
import com.affectiva.android.affdex.sdk.detector.Face;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Detector.FaceListener, Detector.ImageListener {

    // Variables for checking emotion
    private static final int CAMERA_PERMISSIONS_REQUEST = 42;  //value is arbitrary (between 0 and 255)
    boolean cameraPermissionsAvailable = false;
    boolean isFrontFacingCameraDetected = false;
    private CameraDetector detector = null;
    CameraDetector.CameraType cameraType;
    private SurfaceView cameraView; //SurfaceView used to display camera images
    private TextView emotionTextView; // Used for just testing for now

    // Variables for question
    private int mQuestionNumber = 0;
    private int mScore = 0;
    private QuestionsLibrary mQuestionsLibrary;
    private ImageView questionImageView;
    private String mCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
        mQuestionsLibrary = new QuestionsLibrary(this);
        updateQuestion();
        checkForCameraPermissions();
        determineCameraAvailability();
        initializeCameraDetector();
        detector.start();
    }

    /**
     * Displays next question
     */
    private void updateQuestion() {
        questionImageView.setBackgroundResource(mQuestionsLibrary.getQuestion(mQuestionNumber));
        mQuestionNumber++;
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
        detector = new CameraDetector(this, cameraType, cameraView, 1, Detector.FaceDetectorMode.LARGE_FACES);
        int rate = 10;
        detector.setMaxProcessRate(rate);
        detector.setSendUnprocessedFrames(true);
        detector.setFaceListener(this);
        detector.setImageListener(this);
        detector.setDetectAllExpressions(true);
        detector.setDetectAllEmotions(true);
        // detector.setOnCameraEventListener(this);
    }

    @Override
    public void onImageResults(List<Face> faces, Frame image, float timestamp) {
        //The follow code sample shows an example of how to retrieve metric values from the Face object
        if (faces == null)
            return; //frame was not processed

        if (faces.size() == 0)
            return; //no face found

        Face face = faces.get(0);
        int faceId = face.getId();

        //Get Emotions
        float joy = face.emotions.getJoy();
        float anger = face.emotions.getAnger();
        float disgust = face.emotions.getDisgust();
        float fear = face.emotions.getFear();
        float sadness = face.emotions.getSadness();

        //Some Expressions
        float smile = face.expressions.getSmile();
        float brow_furrow = face.expressions.getBrowFurrow();
        float brow_raise = face.expressions.getBrowRaise();

        // For testing purposes
        String displayString = getString(R.string.emotions_values,
                joy,
                anger,
                disgust,
                fear,
                sadness);

        emotionTextView.setText(displayString);

        // Check for correct emotion
        switch(mCorrectAnswer) {
            case "happy":
                if (joy > 10) {
                    // Mostly for testing
                    Toast.makeText(this,"Correct!",
                            Toast.LENGTH_SHORT).show();
                }
                updateQuestion();
                break;
        }

        // detector.stop();
        // Intent intent = new Intent(this, ScoreActivity.class);
        // startActivity(intent);

    }


    void initializeUI() {
        cameraView = findViewById(R.id.camera_view);
        questionImageView = findViewById(R.id.question_image);
        emotionTextView = findViewById(R.id.emotion_textview);
    }

    @Override
    public void onFaceDetectionStarted() {

    }

    @Override
    public void onFaceDetectionStopped() {
    }

}
