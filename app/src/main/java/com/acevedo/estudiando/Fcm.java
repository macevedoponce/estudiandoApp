package com.acevedo.estudiando;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.util.Random;

public class Fcm extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e("token","mi token es:"+s);

    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from =remoteMessage.getFrom();
        if (remoteMessage.getData().size()>0){
            String titulo=remoteMessage.getData().get("titulo");
            String detalle=remoteMessage.getData().get("detalle");
            String foto=remoteMessage.getData().get("foto");

            mayorqueoreo(titulo,detalle,foto);
        }
    }


    private void mayorqueoreo(String titulo, String detalle, String foto) {
        String id="Estudiando";
        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,id);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc=new NotificationChannel(id,"Estudiando", NotificationManager.IMPORTANCE_HIGH);
            nc.setShowBadge(true);
            assert nm!=null;
            nm.createNotificationChannel(nc);
       }
        try {
            Bitmap imf_foto= Picasso.get().load(foto).get();
            builder.setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(titulo)
                    .setSmallIcon(R.drawable.logo_blanco)
                    .setContentText(detalle)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(imf_foto).bigLargeIcon(null))
                    //.setContentIntent(clicknoti())
                    .setContentInfo("Estudiando");

            Random random=new Random();
            int idNotity =random.nextInt(8000);

            assert nm !=null;
            nm.notify(idNotity,builder.build());
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public PendingIntent clicknoti(){
        Intent nf=new Intent(this, TareasEstudianteActivity.class);// a donde mandar cuando se pulse la notificación
        nf.putExtra("id","1"); //recibe info en la actividad señalada
        nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this,0,nf,0);
    }

}
