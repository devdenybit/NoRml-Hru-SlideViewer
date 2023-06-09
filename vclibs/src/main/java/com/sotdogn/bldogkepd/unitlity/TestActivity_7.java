package com.sotdogn.bldogkepd.unitlity;

import static com.luciada.modids.MyAdZOne.app_DataIOSocketFail;
import static com.luciada.modids.MyAdZOne.app_DeveloperOption_Check_Mode;
import static org.webrtc.SessionDescription.Type.ANSWER;
import static org.webrtc.SessionDescription.Type.OFFER;
import static io.socket.client.Socket.EVENT_CONNECT;
import static io.socket.client.Socket.EVENT_DISCONNECT;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.sotdogn.bldogkepd.R;
import com.sotdogn.bldogkepd.activties.MainHomeActivity;
import com.sotdogn.bldogkepd.utility.DevModeOptionCheck;
import com.luciada.modids.AESSUtils;
import com.luciada.modids.MyAdZOne;

import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.DataChannel;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SessionDescription;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.SurfaceViewRenderer;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class TestActivity_7 extends AppCompatActivity {
    private static final String TAG = "TestActivity_7";
    private static final int RC_CALL = 111;
    public static final String VIDEO_TRACK_ID = "ARDAMSv0";
    public static final int VIDEO_RESOLUTION_WIDTH = 1280;
    public static final int VIDEO_RESOLUTION_HEIGHT = 720;
    public static final int FPS = 30;
    private Socket socket;
    private boolean isInitiator;
    private boolean isChannelReady;
    private boolean isStarted;
    private boolean rep = false;
    String app_DataIOSocketFails;

    MediaConstraints audioConstraints;
    MediaConstraints videoConstraints;
    MediaConstraints sdpConstraints;
    VideoSource videoSource;
    VideoTrack localVideoTrack;
    AudioSource audioSource;
    AudioTrack localAudioTrack;
    SurfaceTextureHelper surfaceTextureHelper;
    VideoCapturer videoCapturer;

    private PeerConnection peerConnection;
    private EglBase rootEglBase;
    private PeerConnectionFactory factory;
    private VideoTrack videoTrackFromCamera;

    AudioManager audioManager;
    CountDownTimer myCountdownTimer;

    SurfaceViewRenderer surfaceView, surfaceView2;
    ImageView cancelConnect;
    TextView animText, cntrDown;

    ImageView audioSpeaker, audioMute;
    LinearLayout connectionMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView( R.layout.activity_test_7);


        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
        window.setStatusBarColor(Color.BLACK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);//  set status text dark
        }

        if(app_DeveloperOption_Check_Mode.equalsIgnoreCase("true")){
            DevModeOptionCheck.getInstance(this).DevMode_Check();
        }

        MyAdZOne.getInstance(this).ads_NativeCall(findViewById(R.id.native_container));
        MyAdZOne.getInstance(this).showBanner(findViewById(R.id.banner_container));

        surfaceView = findViewById(R.id.surface_view);
        surfaceView2 = findViewById(R.id.surface_view2);

        cancelConnect = findViewById(R.id.cancel_connect);

        animText = findViewById(R.id.anim_text);
        cntrDown = findViewById(R.id.cntr_down);

        audioSpeaker = findViewById(R.id.audio_speaker);
        audioMute = findViewById(R.id.audio_mute);

        connectionMask = findViewById(R.id.connection_mask);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(50);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        animText.startAnimation(anim);

        myCountdownTimer = new CountDownTimer(31000, 1000) {

            public void onTick(long millisUntilFinished) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                cntrDown.setText("00:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                myCountdownTimer.cancel();
                Toast.makeText(TestActivity_7.this, "No User Found!", Toast.LENGTH_SHORT).show();
                next_call();
            }
        }.start();

        myCountdownTimer.start();

        cancelConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        MyAdZOne.getInstance(this).ads_NativeCall(findViewById(R.id.native_container));
        MyAdZOne.getInstance(this).showBanner(findViewById(R.id.banner_container));
    }

    public void next_call() {
        startActivity(new Intent(TestActivity_7.this, MainHomeActivity.class).putExtra("rate", true));
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onDestroy() {
        if (socket != null) {
            sendMessage("bye");
            socket.disconnect();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        try {
            TestActivity_7.this.runOnUiThread(new Runnable() {
                public void run() {
                    int Vibration = 200;
                    myCountdownTimer.cancel();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(Vibration, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v.vibrate(Vibration);
                    }
                    Toast.makeText(TestActivity_7.this, "Call Disconnected", Toast.LENGTH_SHORT).show();
                }
            });
            if (socket != null) {
                sendMessage("bye");
                socket.disconnect();
            }
            if (peerConnection != null) {
                peerConnection.dispose();
            }
            audioManager = null;
            next_call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterPermissionGranted(RC_CALL)
    private void start() {

        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        if (EasyPermissions.hasPermissions(this, perms)) {
            connectToSignallingServer();

            initializeSurfaceViews();

            initializePeerConnectionFactory();

            createVideoTrackFromCameraAndShowIt();

            initializePeerConnections();

            startStreamingVideo();

        } else {
            EasyPermissions.requestPermissions(this, "Need some permissions", RC_CALL, perms);
        }
    }

    private void connectToSignallingServer() {
         try {
            app_DataIOSocketFails = AESSUtils.Logd(app_DataIOSocketFail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            socket = IO.socket(app_DataIOSocketFails);
            socket.on(EVENT_CONNECT, args -> {
                socket.emit("create or join", "foo");
            }).on("ipaddr", args -> {
            }).on("created", args -> {
                isInitiator = true;
            }).on("full", args -> {
            }).on("join", args -> {
                isChannelReady = true;

            }).on("joined", args -> {
                isChannelReady = true;
            }).on("log", args -> {
                for (Object arg : args) {
                }
            }).on("message", args -> {
            }).on("message", args -> {
                try {
                    if (args[0] instanceof String) {
                        String message = (String) args[0];
                        if (message.equals("got user media")) {
                            maybeStart();
                        }
                    } else {
                        JSONObject message = (JSONObject) args[0];
                        if (message.getString("type").equals("offer")) {
                            if (!isInitiator && !isStarted) {
                                maybeStart();
                            }
                            peerConnection.setRemoteDescription(new SimpleSdpObserver(), new SessionDescription(OFFER, message.getString("sdp")));
                            doAnswer();
                        } else if (message.getString("type").equals("answer") && isStarted) {
                            peerConnection.setRemoteDescription(new SimpleSdpObserver(), new SessionDescription(ANSWER, message.getString("sdp")));
                        } else if (message.getString("type").equals("candidate") && isStarted) {
                            IceCandidate candidate = new IceCandidate(message.getString("id"), message.getInt("label"), message.getString("candidate"));
                            peerConnection.addIceCandidate(candidate);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }).on("disconnected", args -> {
                onBackPressed();
            }).on(EVENT_DISCONNECT, args -> {
            });
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void doAnswer() {
        peerConnection.createAnswer(new SimpleSdpObserver() {
            @Override
            public void onCreateSuccess(SessionDescription sessionDescription) {
                peerConnection.setLocalDescription(new SimpleSdpObserver(), sessionDescription);
                JSONObject message = new JSONObject();
                try {
                    message.put("type", "answer");
                    message.put("sdp", sessionDescription.description);
                    sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new MediaConstraints());
    }

    private void maybeStart() {

        if (!isStarted && isChannelReady) {
            isStarted = true;
            if (isInitiator) {
                doCall();
            }
        }
    }

    private void doCall() {
        MediaConstraints sdpMediaConstraints = new MediaConstraints();
        sdpMediaConstraints.mandatory.add(
                new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        sdpMediaConstraints.mandatory.add(
                new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "true"));
        peerConnection.createOffer(new SimpleSdpObserver() {
            @Override
            public void onCreateSuccess(SessionDescription sessionDescription) {
                peerConnection.setLocalDescription(new SimpleSdpObserver(), sessionDescription);
                JSONObject message = new JSONObject();
                try {
                    message.put("type", "offer");
                    message.put("sdp", sessionDescription.description);
                    sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, sdpMediaConstraints);
    }

    private void sendMessage(Object message) {
        socket.emit("message", message);
    }

    private void initializeSurfaceViews() {
        rootEglBase = EglBase.create();
        surfaceView.init(rootEglBase.getEglBaseContext(), null);
        surfaceView.setEnableHardwareScaler(true);
        surfaceView.setMirror(true);

        surfaceView2.init(rootEglBase.getEglBaseContext(), null);
        surfaceView2.setEnableHardwareScaler(true);
        surfaceView2.setMirror(true);

        //add one more
    }

    private void initializePeerConnectionFactory() {
        PeerConnectionFactory.initializeAndroidGlobals(this, true, true, true);
        factory = new PeerConnectionFactory(null);
        factory.setVideoHwAccelerationOptions(rootEglBase.getEglBaseContext(), rootEglBase.getEglBaseContext());
    }

    private void createVideoTrackFromCameraAndShowIt() {
        audioConstraints = new MediaConstraints();
        videoCapturer = createVideoCapturer();
        VideoSource videoSource = factory.createVideoSource(videoCapturer);
        videoCapturer.startCapture(VIDEO_RESOLUTION_WIDTH, VIDEO_RESOLUTION_HEIGHT, FPS);

        videoTrackFromCamera = factory.createVideoTrack(VIDEO_TRACK_ID, videoSource);
        videoTrackFromCamera.setEnabled(true);
        videoTrackFromCamera.addRenderer(new VideoRenderer(surfaceView));

        //create an AudioSource instance
        audioSource = factory.createAudioSource(audioConstraints);
        localAudioTrack = factory.createAudioTrack("101", audioSource);

    }

    private void initializePeerConnections() {
        peerConnection = createPeerConnection(factory);
    }

    private void startStreamingVideo() {
        MediaStream mediaStream = factory.createLocalMediaStream("ARDAMS");
        mediaStream.addTrack(videoTrackFromCamera);
        mediaStream.addTrack(localAudioTrack);
        peerConnection.addStream(mediaStream);

        sendMessage("got user media");
    }

    private PeerConnection createPeerConnection(PeerConnectionFactory factory) {
        ArrayList<PeerConnection.IceServer> iceServers = new ArrayList<>();
        String URL = "stun:stun.l.google.com:19302";
        iceServers.add(new PeerConnection.IceServer(URL));

        PeerConnection.RTCConfiguration rtcConfig = new PeerConnection.RTCConfiguration(iceServers);
        MediaConstraints pcConstraints = new MediaConstraints();

        PeerConnection.Observer pcObserver = new PeerConnection.Observer() {
            @Override
            public void onSignalingChange(PeerConnection.SignalingState signalingState) {
            }

            @Override
            public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
            }

            @Override
            public void onIceConnectionReceivingChange(boolean b) {
            }

            @Override
            public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
            }

            @Override
            public void onIceCandidate(IceCandidate iceCandidate) {
                JSONObject message = new JSONObject();

                try {
                    if (!rep) {
                        rep = true;
                        TestActivity_7.this.runOnUiThread(new Runnable() {
                            public void run() {
                                int Vibration = 200;
                                myCountdownTimer.cancel();
                                Toast.makeText(TestActivity_7.this, "Call Connected", Toast.LENGTH_SHORT).show();
                                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    v.vibrate(VibrationEffect.createOneShot(Vibration, VibrationEffect.DEFAULT_AMPLITUDE));
                                } else {
                                    //deprecated in API 26
                                    v.vibrate(Vibration);
                                }
                            }
                        });
                    }

                    TestActivity_7.this.runOnUiThread(new Runnable() {
                        public void run() {
                            connectionMask.setVisibility(View.GONE);
                        }
                    });

                    message.put("type", "candidate");
                    message.put("label", iceCandidate.sdpMLineIndex);
                    message.put("id", iceCandidate.sdpMid);
                    message.put("candidate", iceCandidate.sdp);
                    sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onIceCandidatesRemoved(IceCandidate[] iceCandidates) {
            }

            @Override
            public void onAddStream(MediaStream mediaStream) {
                VideoTrack remoteVideoTrack = mediaStream.videoTracks.get(0);
                AudioTrack remoteAudioTrack = mediaStream.audioTracks.get(0);
                remoteAudioTrack.setEnabled(true);
                remoteVideoTrack.setEnabled(true);
                remoteVideoTrack.addRenderer(new VideoRenderer(surfaceView2));
            }

            @Override
            public void onRemoveStream(MediaStream mediaStream) {
            }

            @Override
            public void onDataChannel(DataChannel dataChannel) {
            }

            @Override
            public void onRenegotiationNeeded() {
            }
        };

        return factory.createPeerConnection(rtcConfig, pcConstraints, pcObserver);
    }

    private VideoCapturer createVideoCapturer() {
        VideoCapturer videoCapturer;
        if (useCamera2()) {
            videoCapturer = createCameraCapturer(new Camera2Enumerator(this));
        } else {
            videoCapturer = createCameraCapturer(new Camera1Enumerator(true));
        }
        return videoCapturer;
    }

    private VideoCapturer createCameraCapturer(CameraEnumerator enumerator) {
        final String[] deviceNames = enumerator.getDeviceNames();

        for (String deviceName : deviceNames) {
            if (enumerator.isFrontFacing(deviceName)) {
                VideoCapturer videoCapturer = enumerator.createCapturer(deviceName, null);

                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }

        for (String deviceName : deviceNames) {
            if (!enumerator.isFrontFacing(deviceName)) {
                VideoCapturer videoCapturer = enumerator.createCapturer(deviceName, null);

                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }

        return null;
    }

    private void switchCamera() {
        if (videoCapturer != null) {
            if (videoCapturer instanceof CameraVideoCapturer) {
                CameraVideoCapturer cameraVideoCapturer = (CameraVideoCapturer) videoCapturer;
                cameraVideoCapturer.switchCamera(null);
            } else {
                // Will not switch camera, video capturer is not a camera
            }
        }
    }

    public void switchCameraEvent(View view) {
        switchCamera();
    }

    public void speakerONOFFEvent(View view) {
        if (!audioManager.isSpeakerphoneOn()) {
            audioSpeaker.setImageResource(R.drawable.on_speker);
            audioManager.setSpeakerphoneOn(true);
            audioManager.setStreamSolo(AudioManager.STREAM_VOICE_CALL, true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE, AudioManager.MODE_IN_CALL);
            }
        } else {
            audioSpeaker.setImageResource(R.drawable.off_speker);
            audioManager.setSpeakerphoneOn(false);
            audioManager.setStreamSolo(AudioManager.STREAM_VOICE_CALL, false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.MODE_IN_CALL);
            }
        }
    }

    public void audioMuteEvent(View view) {
        audioManager.setMode(AudioManager.MODE_IN_CALL);
        if (audioManager.isMicrophoneMute()) {
            audioMute.setImageResource(R.drawable.mic_on);
            audioManager.setMicrophoneMute(false);
        } else {
            audioMute.setImageResource(R.drawable.mic_off);
            audioManager.setMicrophoneMute(true);
        }
    }

    private boolean useCamera2() {
        return Camera2Enumerator.isSupported(this);
    }

    public void CanaclToCall(View view) {
        onBackPressed();
    }

    public void TBtnReportUser(View view) {

        CheckBox c1, c2, c3, c4, c5, c6, c7;

        Dialog dialog = new Dialog(this, R.style.Transparent);
        dialog.setContentView(R.layout.report_dailog);

        c1 = dialog.findViewById(R.id.r1);
        c2 = dialog.findViewById(R.id.r2);
        c3 = dialog.findViewById(R.id.r3);
        c4 = dialog.findViewById(R.id.r4);
        c5 = dialog.findViewById(R.id.r5);
        c6 = dialog.findViewById(R.id.r6);
        c7 = dialog.findViewById(R.id.r7);

        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ((TextView) dialog.findViewById(R.id.reyes)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (c1.isChecked() || c2.isChecked() || c3.isChecked() || c4.isChecked() || c5.isChecked() || c6.isChecked() || c7.isChecked()) {
                    Toast.makeText(TestActivity_7.this, "Report Submitted", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    dialog.dismiss();
                } else {
                    Toast.makeText(TestActivity_7.this, "Select Report Reason", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();

        ((TextView) dialog.findViewById(R.id.reblack)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (c1.isChecked() || c2.isChecked() || c3.isChecked() || c4.isChecked() || c5.isChecked() || c6.isChecked() || c7.isChecked()) {
                    Toast.makeText(getApplicationContext(), "User Blocked", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Select Block Reason", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

}
