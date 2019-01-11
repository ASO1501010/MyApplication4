package com.example.satokota.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class title extends AppCompatActivity implements View.OnClickListener {

    private Button login, newteacher, newstudents, testbutton;
    //private BootstrapButton bootstrap;

    private RecogCamera mCamera;

//    private Handler callbackHandler;
//    private CameraDevice cameraDevice = null;
//    private ImageReader previewStream = null;
//    private CameraCaptureSession captureSession = null;

//    public void init() {
//        // Callback thread.
//        HandlerThread callbackThread = new HandlerThread("callback-worker");
//        callbackThread.start();
//        callbackHandler = new Handler(callbackThread.getLooper());
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ここで1秒間スリープし、スプラッシュを表示させたままにする。
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        // スプラッシュthemeを通常themeに変更する
        setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_title);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        newteacher = (Button) findViewById(R.id.newteacher);
        newteacher.setOnClickListener(this);

        newstudents = (Button) findViewById(R.id.newstudents);
        newstudents.setOnClickListener(this);

//        testbutton = (Button)findViewById(R.id.test_button);
//        testbutton.setOnClickListener(this);

//        bootstrap = findViewById(R.id.bootstrap_button);
//        bootstrap.setOnClickListener(this);
    }

//    public void open(Context context) throws CameraAccessException {
//        CameraManager cameraMng = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
//
//        String[] ids = cameraMng.getCameraIdList();
//        String frontCamId = "";
//        for (String id : ids) {
//            CameraCharacteristics camChars = cameraMng.getCameraCharacteristics(id);
//            if (camChars.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT) {
//                //Front.
//                frontCamId = id;
//                break;
//            }
//        }
//
//        if (frontCamId.isEmpty()) {
//            //error handle.
//        }
//
//        //Request open.
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        cameraMng.openCamera(frontCamId, StateCallbackImpl, callbackHandler);
//    }
//
//    private CameraDevice.StateCallback StateCallbackImpl = new CameraDevice.StateCallback() {
//        @Override
//        public void onOpened(CameraDevice camera) {
//            cameraDevice = camera;
//        }
//
//        @Override
//        public void onDisconnected(CameraDevice camera) {
//
//        }
//
//        @Override
//        public void onError(CameraDevice camera, int error) {
//
//        }
//    };
//
//    public void startDetection() throws CameraAccessException {
//        //stream
//        ImageReader stream = ImageReader.newInstance(
//                640,
//                480,
//                ImageFormat.PRIVATE,
//                2);
//        stream.setOnImageAvailableListener(
//                ImageAvailableCallback,
//                callbackHandler);
//
//        List<Surface> streams = Arrays.asList(stream.getSurface());
//
//        cameraDevice.createCaptureSession(
//                streams,
//                CaptureSessionCallback,
//                callbackHandler
//        );
//    }
//
//    private ImageReader.OnImageAvailableListener ImageAvailableCallback = new ImageReader.OnImageAvailableListener() {
//        @Override
//        public void onImageAvailable(ImageReader reader) {
//            Image image = reader.acquireLatestImage();
//            image.close();
//        }
//    };
//
//    private CameraCaptureSession.StateCallback CaptureSessionCallback = new CameraCaptureSession.StateCallback() {
//        @Override
//        public void onConfigured(CameraCaptureSession session) {
//            captureSession = session;
//
//            //request
//            try {
//                CaptureRequest.Builder builder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//                builder.addTarget(previewStream.getSurface());
//                builder.set(
//                        CaptureRequest.CONTROL_MODE,
//                        CaptureRequest.CONTROL_MODE_AUTO
//                );
//                builder.set(
//                        CaptureRequest.CONTROL_AE_MODE,
//                        CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE
//                );
//                builder.set(
//                        CaptureRequest.STATISTICS_FACE_DETECT_MODE,
//                        CaptureRequest.STATISTICS_FACE_DETECT_MODE_SIMPLE
//                );
//
//                CaptureRequest request = builder.build();
//
//                session.setRepeatingRequest(
//                        request,
//                        CaptureCallback,
//                        callbackHandler
//                );
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onConfigureFailed(CameraCaptureSession session) {
//
//        }
//    };
//
//    private CameraCaptureSession.CaptureCallback CaptureCallback = new CameraCaptureSession.CaptureCallback() {
//        @Override
//        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
//            super.onCaptureCompleted(session, request, result);
//
//            Face[] faces = result.get(CaptureResult.STATISTICS_FACES);
//
//            if(faces != null){
//                if(faces.length > 0){
//
//                }
//            }
//        }
//    };
//
//    private void stopFaceDetection() throws CameraAccessException {
//        captureSession.stopRepeating();
//    }
//
//    private void close(){
//        cameraDevice.close();
//        cameraDevice = null;
//    }

    //ボタンクリック時
    public void onClick(View v) {
        if(v== login){
            //新規登録(生徒)画面への遷移
            Intent intent = new Intent(this, login.class);
            startActivityForResult(intent, 0);
            //新規登録(担任)画面への遷移
        }else if(v== newteacher){
            Intent intent = new Intent(this, sign_up_teacher.class);
            startActivityForResult(intent, 0);
            //ログイン画面への遷移
        }else if(v== newstudents){
            Intent intent = new Intent(this, sign_up_student.class);
            startActivityForResult(intent, 0);
//        }else if(v== bootstrap){
//            //mCamera = new RecogCamera();
//            Intent intent = new Intent(this, RecogCamera.class);
//            startActivityForResult(intent, 0);
//            try {
//                mCamera.open(this);
//                mCamera.startDetection();
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
        }else if(v== testbutton){
            Intent intent = new Intent(this, RecogCamera.class);
            startActivityForResult(intent, 0);
        }

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    // ダイアログ表示など特定の処理を行いたい場合はここに記述
                    // 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}
