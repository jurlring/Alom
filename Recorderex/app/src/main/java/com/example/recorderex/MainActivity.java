package com.example.recorderex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera;
    private MediaRecorder mediaRecorder;
    private Button record;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean recording = false;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TedPermission.with(this)
                .setPermissionListener(permission)
                .setRationaleMessage("녹화를 위하여 권한을 허용해주세요")//팝업에 대한 권한
                .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요")
                .setPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO)
                .check();

        record=(Button)findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recording){
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    camera.lock();
                    recording=false;
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"녹화가 시작되었습니다.",Toast.LENGTH_SHORT).show();
                            try {
                                mediaRecorder= new MediaRecorder();
                                camera.unlock();
                                mediaRecorder.setCamera(camera);
                                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                                mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                                mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_1080P));
                                mediaRecorder.setOrientationHint(90);//촬영 각도
                                mediaRecorder.setOutputFile("/sdcard/test.mp4");
                                mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
                                mediaRecorder.prepare();
                                mediaRecorder.start();
                                recording=true;
                            } catch (Exception e){
                                e.printStackTrace();
                                mediaRecorder.release();
                            }
                        }
                    });
                }
            }
        });


    }

    PermissionListener permission = new PermissionListener() {
        @Override
        public void onPermissionGranted() {//permission이 다 허용이 되었을 때
            Toast.makeText(MainActivity.this,"권한 허가",Toast.LENGTH_SHORT).show();

            camera=Camera.open();
            camera.setDisplayOrientation(90);
            surfaceView=(SurfaceView)findViewById(R.id.surface);
            surfaceHolder=surfaceView.getHolder();
            surfaceHolder.addCallback(MainActivity.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {//거부된게 있을 때때
            Toast.makeText(MainActivity.this,"권한 거부",Toast.LENGTH_SHORT).show();
        }    };

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {//생성됐을때
        refreshCamara(camera);
    }

    private void refreshCamara(Camera camera) {
        if (surfaceHolder.getSurface()==null)
            return;
        try{

        } catch(Exception e){
            e.printStackTrace();
        }

        setCamera(camera);
    }

    private void setCamera(Camera camera) {
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {//변화가 일어날 때
        refreshCamara(camera);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}