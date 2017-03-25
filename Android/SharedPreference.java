// this singleton is a workaround for an Android bug:
// two SharedPreferences objects do not see changes in each other.
public class Prefs {
    private static SharedPreferences theSingletone;
    public static SharedPreferences get(Activity from) {
        //PreferenceManager.getDefaultSharedPreferences(getContext());
        if (theSingletone == null) {
            theSingletone = from.getApplicationContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        }
        return theSingletone;
    }
}

//############################################################################

// Xóa phần tử ra khỏi SharedPreferences
SharedPreferences mySPrefs =PreferenceManager.getDefaultSharedPreferences(this);
SharedPreferences.Editor editor = mySPrefs.edit();
editor.remove(String key);
editor.apply();

//############################################################################

Set<String> s = new HashSet<String>(sharedPrefs.getStringSet("key", new HashSet<String>()));

//############################################################################

SharedPreferences.Editor editor = sharedPrefs.edit();
Set<String> oldSet = sharedPrefs.getStringSet("key", new HashSet<String>());

//############################################################################

//make a copy, update it and save it
Set<String> newStrSet = new HashSet<String>();    
newStrSet.add(new_element);
newStrSet.addAll(oldSet);
editor.putStringSet("key",newStrSet); edit.commit();

//############################################################################

private static SharedPreferences sharedPreferences = context.getSharedPreferences(STORE_FILE_NAME, Context.MODE_PRIVATE);

private static SharedPreferences.Editor editor = sharedPreferences.edit();

public <T> void setList(String key, List<T> list) {
    Gson gson = new Gson();
    String json = gson.toJson(list);

    set(key, json);
}

public static void set(String key, String value) {
    editor.putString(key, value);
    editor.commit();
}

//############################################################################

Listtasks = new ArrayList<String>();
Set<String> tasksSet = new HashSet<String>(Listtasks);
PreferenceManager.getDefaultSharedPreferences(context)
    .edit()
    .putStringSet("tasks_set", tasksSet)
    .commit();

Set<String> tasksSet = PreferenceManager.getDefaultSharedPreferences(context)
    .getStringSet("tasks_set", new HashSet<String>());
List<String> tasksList = new ArrayList<String>(tasksSet);

//############################################################################

private static final String PREFS_TAG = "SharedPrefs";
private static final String PRODUCT_TAG = "MyProduct";

private List<Product> getDataFromSharedPreferences(){
    Gson gson = new Gson();
    List<Product> productFromShared = new ArrayList<>();
    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
    String jsonPreferences = sharedPref.getString(PRODUCT_TAG, "");    

    Type type = new TypeToken<List<Product>>() {}.getType();
    productFromShared = gson.fromJson(jsonPreferences, type);

    return preferences;
}

private void setDataFromSharedPreferences(Product curProduct){
    Gson gson = new Gson();
    String jsonCurProduct = gson.toJson(curProduct);

    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();

    editor.putString(PRODUCT_TAG, jsonCurProduct);
    editor.commit();
}

private void addInJSONArray(Product productToAdd){

    Gson gson = new Gson();
    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);

    String jsonSaved = sharedPref.getString(PRODUCT_TAG, "");
    String jsonNewproductToAdd = gson.toJson(productToAdd);

    JSONArray jsonArrayProduct= new JSONArray();

    try {
        if(jsonSaved.length()!=0){
            jsonArrayProduct = new JSONArray(jsonSaved);
        }
        jsonArrayProduct.put(new JSONObject(jsonNewproductToAdd));
    } catch (JSONException e) {
        e.printStackTrace();
    }

    //SAVE NEW ARRAY
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putString(PRODUCT_TAG, jsonArrayProduct);
    editor.commit();
}

//############################################################################


//############################################################################