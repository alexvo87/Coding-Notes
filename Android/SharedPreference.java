

public class Prefs {
    // this singleton is a workaround for an Android bug:
    // two SharedPreferences objects do not see changes in each other.
    private static SharedPreferences theSingletone;
    public static SharedPreferences get(Activity from) {
        //PreferenceManager.getDefaultSharedPreferences(getContext());
        if (theSingletone == null) {
            theSingletone = from.getApplicationContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        }
        return theSingletone;
    }
}


// Xóa phần tử ra khỏi SharedPreferences
SharedPreferences mySPrefs =PreferenceManager.getDefaultSharedPreferences(this);
SharedPreferences.Editor editor = mySPrefs.edit();
editor.remove(String key);
editor.apply();