package srcapp.myshadow.service;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

public class Utils extends AppCompatActivity {
    /**
     * Function for calling using phone
     * @param telNumber -- string number of phone
     */
    public void telephoneCall(String telNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel" + telNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(dialIntent);
    }

    /**
     * Function for making sms messages
     * @param smsBody -- sms body
     * @param smsText -- sms text
     */
    public void smsMaker(String smsBody, String smsText){
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra(smsBody, smsText);
        startActivity(smsIntent);
    }

    /**
     * Function for getting contact from book
     */
    public void getContactFromBook(){
        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivity(pickIntent);
    }

    /**
     * Function for opening url in internet
     * @param urlForOpen -- url from internet
     */
    public void openUrlBrowser(String urlForOpen){
        Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlForOpen));
        startActivity(browseIntent);
    }

    /**
     * Function for opening map using latitude and longitude
     * @param latitude -- latitude
     * @param longitude -- longitude
     */
    public void mapOpen(String latitude, String longitude){
        String uri = "geo:" + latitude + "," + longitude;
        Intent mapIntent = new Intent((Intent.ACTION_VIEW), Uri.parse(uri));
    }

    /**
     * Function for image shooting
     */
    public void imageCapture(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(cameraIntent);
    }

    /**
     * Android function
     * @param context -- context
     * @param intent -- intent
     * @return
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(
                intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * Skype calling function
     * @param skype
     * @param context
     */
    public void skypeCall(String skype, Context context){
        final String SKYPE_PATH_GENERAL = "com.skype.raider";
        final String SKYPE_PATH_OLD = "com.skype.raider.contactsync.ContactSkypeOutCallStartActivity";
        final String SKYPE_PATH_NEW = "com.skype.raider.Main";

        Intent skypeIntent = new Intent().setAction("android.intent.action.CALL_PRIVILEGED");
        skypeIntent.setData(Uri.parse("tel:" + skype));

        if (isIntentAvailable(context, skypeIntent.setClassName(SKYPE_PATH_GENERAL, SKYPE_PATH_NEW))) {
            context.startActivity(skypeIntent);
        } else if (isIntentAvailable(context, skypeIntent.setClassName(SKYPE_PATH_GENERAL, SKYPE_PATH_OLD))) {
            context.startActivity(skypeIntent);
        } else {
            Toast.makeText(context, "Приложение Skype не установлено.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Text speecher function
     */
    public void textSpeech(){
        Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak please");
        startActivity(speechIntent);

    }
}
