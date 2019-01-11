package com.example.satokota.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.params.Face;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.hardware.camera2.*;
import android.view.SurfaceView;
import android.view.TextureView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecogCamera extends AppCompatActivity {
//    private static final String TAG = "CameraFaceDetect";
//    //private Camera myCamera;
//
////    private SurfaceHolder.Callback mSurfaceListener =
////            new SurfaceHolder.Callback() {
////                public void surfaceCreated(SurfaceHolder holder) {
////                    // TODO Auto-generated method stub
////                    myCamera = Camera.open();
////                    try {
////                        myCamera.setPreviewDisplay(holder);
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
////
////                public void surfaceDestroyed(SurfaceHolder holder) {
////                    // TODO Auto-generated method stub
////                    myCamera.release();
////                    myCamera = null;
////                }
////
////                public void surfaceChanged(SurfaceHolder holder, int format, int width,
////                                           int height) {
////                    // TODO Auto-generated method stub
////                    myCamera.startPreview();
////
////                    // 顔検出用のリスナーを登録する
////                    myCamera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
////
////                        @Override
////                        // 顔が検出された時の処理を記述する
////                        public void onFaceDetection(Camera.Face[] faces, Camera camera) {
////                            Log.d(TAG, "faces count: " + faces.length);
////                            for (Camera.Face face : faces) {
////                                // サポートされていなければ-1が常に返ってくる
////                                Log.d(TAG, "face id: " + face.id);
////
////                                // 顔検出の信頼度 1から100までの値が入っており、100が顔として信頼度が一番高い
////                                Log.d(TAG, "face score: " + face.score);
////
////                                // 検出された顔の範囲
////                                Log.d(TAG, "face rect: " + face.rect.left + "," + face.rect.top + " - "
////                                        + face.rect.right + "," + face.rect.bottom);
////
////                                // 以下はサポートされていなければnullが入ってくる
////                                if (face.mouth != null) {
////                                    Log.d(TAG, "face mouth: " + face.mouth.x + "," + face.mouth.y);
////                                    Log.d(TAG, "face leftEye: " + face.leftEye.x + "," + face.leftEye.y);
////                                    Log.d(TAG, "face rightEye: " + face.rightEye.x + "," + face.rightEye.y);
////                                }
////                            }
////                        }
////                    });
////
////                    // カメラに顔検出開始を指示する
////                    try {
////                        myCamera.startFaceDetection();
////                    } catch (IllegalArgumentException e) {
////                        Log.e(TAG, "IllegalArgumentException.");
////                    } catch (RuntimeException e) {
////                        Log.e(TAG, "the method fails or the face detection is already running.");
////                    }
////                }
////            };
//
//    /** Called when the activity is first created. */
//////    @Override
//////    public void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_camera);
//////
//////        SurfaceView mySurfaceView = (SurfaceView)findViewById(R.id.surface_view);
//////        SurfaceHolder holder = mySurfaceView.getHolder();
//////        holder.addCallback(mSurfaceListener);
//////        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//////    }
//    private final static int REQUEST_PERMISSION = 1002;
//
//    private Handler callbackHandler;
//    private CameraDevice cameraDevice = null;
//    private ImageReader previewStream = null;
//    private CameraCaptureSession captureSession = null;
//
//    private CameraManager cameraMng = null;
//    private String frontCamId = "";
//    private SurfaceView surfaceView = null;
//    private TextureView mTextureView = null;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera);
//
//        mTextureView = (TextureView) findViewById(R.id.texture_view);
//        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
//            @Override
//            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
//                // 先ほどのカメラを開く部分をメソッド化した
//                try {
//                    open();
//                } catch (CameraAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
//
//            }
//
//            @Override
//            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
//                return true;
//            }
//
//            @Override
//            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//
//            }
//        });
//
//////        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
//////        surfaceView.getHolder().setFixedSize(640, 320);
//
//        // Callback thread.
//        HandlerThread callbackThread = new HandlerThread("callback-worker");
//        callbackThread.start();
//        callbackHandler = new Handler(callbackThread.getLooper());
//
//        try {
//            open();
//            //startDetection();
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void open() throws CameraAccessException {
//        System.out.println("tag1");
//        cameraMng = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//
//        String[] ids = cameraMng.getCameraIdList();
//////        String frontCamId = "";
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
//            System.out.println("error");
//            //error handle.
//        }
//
//        if (Build.VERSION.SDK_INT >= 23) {
//            System.out.println("tag2");
//            checkPermission(this);
//        }
//        else {
//            System.out.println("tag3");
//            cameraMng.openCamera(frontCamId, StateCallbackImpl, callbackHandler);
//        }
//    }
//
//    private CameraDevice.StateCallback StateCallbackImpl = new CameraDevice.StateCallback() {
//        @Override
//        public void onOpened(CameraDevice camera) {
//            cameraDevice = camera;
//
////            surfaceView = (SurfaceView) findViewById(R.id.surface_view);
////            ArrayList<Surface> surfaceList = new ArrayList();
////            surfaceList.add(surfaceView.getHolder().getSurface());
////            //List<Surface> streams = Arrays.asList(previewStream.getSurface());
////
////            try {
////                cameraDevice.createCaptureSession(
////                        surfaceList,
////                        new CaptureSessionCallback(),
////                        callbackHandler
////                );
////            } catch (CameraAccessException e) {
////                e.printStackTrace();
////            }
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
//////        ImageReader previewStream = ImageReader.newInstance(
//////                640,
//////                480,
//////                ImageFormat.PRIVATE,
//////                2);
//////        previewStream.setOnImageAvailableListener(
//////                ImageAvailableCallback,
//////                callbackHandler);
//
//        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
//        ArrayList<Surface> surfaceList = new ArrayList();
//        surfaceList.add(surfaceView.getHolder().getSurface());
//        //List<Surface> streams = Arrays.asList(previewStream.getSurface());
//
//        cameraDevice.createCaptureSession(
//                surfaceList,
//                new CaptureSessionCallback(),
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
//    private class CaptureSessionCallback extends CameraCaptureSession.StateCallback{
//        SurfaceTexture texture = mTextureView.getSurfaceTexture();
//
//        Surface surface = new Surface(texture);
//
//        @Override
//        public void onConfigured(CameraCaptureSession session) {
//            captureSession = session;
//
//            //request
//            try {
//                CaptureRequest.Builder builder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//                builder.addTarget(surfaceView.getHolder().getSurface());
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
//    }
//
//////    private CameraCaptureSession.StateCallback CaptureSessionCallback = new CameraCaptureSession.StateCallback() {
//////        @Override
//////        public void onConfigured(CameraCaptureSession session) {
//////            captureSession = session;
//////
//////            //request
//////            try {
//////                CaptureRequest.Builder builder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//////                builder.addTarget(previewStream.getSurface());
//////                builder.set(
//////                        CaptureRequest.CONTROL_MODE,
//////                        CaptureRequest.CONTROL_MODE_AUTO
//////                );
//////                builder.set(
//////                        CaptureRequest.CONTROL_AE_MODE,
//////                        CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE
//////                );
//////                builder.set(
//////                        CaptureRequest.STATISTICS_FACE_DETECT_MODE,
//////                        CaptureRequest.STATISTICS_FACE_DETECT_MODE_SIMPLE
//////                );
//////
//////                CaptureRequest request = builder.build();
//////
//////                session.setRepeatingRequest(
//////                        request,
//////                        CaptureCallback,
//////                        callbackHandler
//////                );
//////            } catch (CameraAccessException e) {
//////                e.printStackTrace();
//////            }
//////        }
//////
//////        @Override
//////        public void onConfigureFailed(CameraCaptureSession session) {
//////
//////        }
//////    };
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
//
//
//    // Runtime Permission check
//    private void checkPermission(Context context){
//        // 既に許可している
//        if (ActivityCompat.checkSelfPermission(context,
//                Manifest.permission.CAMERA) ==
//                PackageManager.PERMISSION_GRANTED){
//            try {
//                System.out.println("tag4");
//                cameraMng.openCamera(frontCamId, StateCallbackImpl, callbackHandler);
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        // 拒否していた場合
//        else{
//            System.out.println("tag5");
//            requestPermission(context);
//        }
//    }
//
//    // 許可を求める
//    private void requestPermission(Context context) {
//        Intent intent = getIntent();
//        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
//                Manifest.permission.CAMERA)) {
//            ActivityCompat.requestPermissions((Activity) context,
//                    new String[]{Manifest.permission.CAMERA},
//                    REQUEST_PERMISSION);
//
//        } else {
//            Toast toast = Toast.makeText(context,
//                    "許可されないとアプリが実行できません",
//                    Toast.LENGTH_SHORT);
//            toast.show();
//
//            ActivityCompat.requestPermissions((Activity) context,
//                    new String[]{Manifest.permission.CAMERA,},
//                    REQUEST_PERMISSION);
//
//        }
//    }
//
//    // 結果の受け取り
//    @SuppressLint("MissingPermission")
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//
//        Log.d("debug","onRequestPermissionsResult()");
//
//        if (requestCode == REQUEST_PERMISSION) {
//            // 使用が許可された
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                try {
//                    cameraMng.openCamera(frontCamId, StateCallbackImpl, callbackHandler);
//                } catch (CameraAccessException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                // それでも拒否された時の対応
//                Toast toast = Toast.makeText(this,
//                        "これ以上なにもできません", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        }
//    }

}
