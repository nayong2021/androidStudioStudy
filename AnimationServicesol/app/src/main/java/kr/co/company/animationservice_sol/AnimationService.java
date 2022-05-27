package kr.co.company.animationservice_sol;

import android.animation.ValueAnimator;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.animation.OvershootInterpolator;

public class AnimationService extends Service {
    public static final int ANIMATION_START = 1;
    public static final int ANIMATION_STOP = 0;
    ValueAnimator rotateAnim;
    Messenger valMessenger;
    public AnimationService() {
        rotateAnim = ValueAnimator.ofFloat(0, 360);
        rotateAnim.setDuration(2000);
        rotateAnim.addUpdateListener(valueAnimator -> {
            float angle = (float)valueAnimator.getAnimatedValue();
            Message msg = Message.obtain();
            msg.obj = angle;
            try {
                valMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        valMessenger = (Messenger)intent.getExtras().get("ValueMessenger");
        int command = (int)intent.getExtras().get("command");
        if(command == ANIMATION_START){
            String tensionString = intent.getExtras().get("tension").toString();
            float tension = 0;
            if (tensionString.length() != 0){
                tension = Float.parseFloat(tensionString);
            }
            rotateAnim.setInterpolator(new OvershootInterpolator(tension));
            rotateAnim.start();
        }
        else if (command == ANIMATION_STOP && rotateAnim.isStarted()){
            rotateAnim.cancel();
            Message msg = Message.obtain();
            msg.obj = (float)0;
            try {
                valMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}