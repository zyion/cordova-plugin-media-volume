
package net.zyion.mediavolume;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.util.Log;
import android.media.AudioManager;
import android.content.Context;

public class MediaVolume extends CordovaPlugin {

    public static final String TAG = "net.zyion.mediavolume.MediaVolume";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "Initializing Media Volume");
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("get")) {
            getVolume(callbackContext);
            return true;
        }
        else if (action.equals("set")) {
            setVolume(args, callbackContext);
            return true;
        }
        else if (action.equals("up")) {
            adjustVolume(AudioManager.ADJUST_RAISE, callbackContext);
            return true;
        }
        else if (action.equals("down")) {
            adjustVolume(AudioManager.ADJUST_LOWER, callbackContext);
            return true;
        }
        else if (action.equals("mute")) {
            adjustVolume(AudioManager.ADJUST_MUTE, callbackContext);
            return true;
        }
        else if (action.equals("unmute")) {
            adjustVolume(AudioManager.ADJUST_UNMUTE, callbackContext);
            return true;
        }
        else {
            callbackContext.error("Invalid action: " + action);
            return false;
        }
    }

    private void getVolume(final CallbackContext callbackContext) throws JSONException {
        AudioManager audioManager = (AudioManager) this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);

        int volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        boolean muted = audioManager.isStreamMute(AudioManager.STREAM_MUSIC);
        boolean fixed = audioManager.isVolumeFixed();

        JSONObject obj = new JSONObject();
        obj.put("volume", new Integer(volume));
        obj.put("max", new Integer(max));
        obj.put("muted", new Boolean(muted));
        obj.put("fixed", new Boolean(fixed));

        PluginResult result = new PluginResult(PluginResult.Status.OK, obj);
        callbackContext.sendPluginResult(result);
    }

    private void setVolume(JSONArray args, final CallbackContext callbackContext) throws JSONException {
        AudioManager audioManager = (AudioManager) this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);

        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volume = args.getInt(0);
        if (volume > max) volume = max;
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_SHOW_UI);

        JSONObject obj = new JSONObject();
        obj.put("volume", new Integer(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)));
        obj.put("muted", new Boolean(audioManager.isStreamMute(AudioManager.STREAM_MUSIC)));

        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, obj));
    }

    private void adjustVolume(int direction, final CallbackContext callbackContext) throws JSONException {
        AudioManager audioManager = (AudioManager) this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, direction, AudioManager.FLAG_SHOW_UI);

        int volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        boolean muted = audioManager.isStreamMute(AudioManager.STREAM_MUSIC);

        JSONObject obj = new JSONObject();
        obj.put("volume", new Integer(volume));
        obj.put("muted", new Boolean(muted));

        PluginResult result = new PluginResult(PluginResult.Status.OK, obj);
        callbackContext.sendPluginResult(result);
    }

}
