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

package com.example;
// Change the package name to match your package

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ArrayHelper {
  Context context;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    
    public ArrayHelper(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
    }
    
    /**
     * Converts the provided ArrayList<String>
     * into a JSONArray and saves it as a single
     * string in the apps shared preferences
     * @param String key Preference key for SharedPreferences
     * @param array ArrayList<String> containing the list items
     */
    public void saveArray(String key, ArrayList<String> array) {
        JSONArray jArray = new JSONArray(array);
        editor.remove(key);
        editor.putString(key, jArray.toString());
        editor.commit();
    }
    
    /**
     * Loads a JSONArray from shared preferences
     * and converts it to an ArrayList<String>
     * @param String key Preference key for SharedPreferences
     * @return ArrayList<String> containing the saved values from the JSONArray
     */
    public ArrayList<String> getArray(String key) {
        ArrayList<String> array = new ArrayList<String>();
        String jArrayString = prefs.getString(key, "NOPREFSAVED");
        if (jArrayString.matches("NOPREFSAVED")) return getDefaultArray();
        else {
            try {
                JSONArray jArray = new JSONArray(jArrayString);
                for (int i = 0; i < jArray.length(); i++) {
                    array.add(jArray.getString(i));
                }
                return array;
            } catch (JSONException e) {
                return getDefaultArray();
            }
        }   
    }
}

//############################################################################

// shared preferences
   private SharedPreferences preferences;
   private SharedPreferences.Editor nsuserdefaults;

// setup persistent data
    preferences = this.getSharedPreferences("MyPreferences", MainActivity.MODE_PRIVATE);
    nsuserdefaults = preferences.edit();

    arrayOfMemberUrlsUserIsFollowing = new ArrayList<String>();
    //Retrieve followers from sharedPreferences
    Set<String> set = preferences.getStringSet("following", null);

    if (set == null) {
        // lazy instantiate array
        arrayOfMemberUrlsUserIsFollowing = new ArrayList<String>();
    } else {
        // there is data from previous run
        arrayOfMemberUrlsUserIsFollowing = new ArrayList<>(set);
    }

// convert arraylist to set, and save arrayOfMemberUrlsUserIsFollowing to nsuserdefaults
    Set<String> set = new HashSet<String>();
    set.addAll(arrayOfMemberUrlsUserIsFollowing);
    nsuserdefaults.putStringSet("following", set);
    nsuserdefaults.commit();


//############################################################################

1.Variable declaration i.e

  SharedPreferences shared;
  ArrayList<String> arrPackage;
2.Variable initialization i.e

 shared = getSharedPreferences("App_settings", MODE_PRIVATE);
 // add values for your ArrayList any where...
 arrPackage = new ArrayList<>();
3.Store value to sharedPreference using packagesharedPreferences():

 private void packagesharedPreferences() {
   SharedPreferences.Editor editor = shared.edit();
   Set<String> set = new HashSet<String>();
   set.addAll(arrPackage);
   editor.putStringSet("DATE_LIST", set);
   editor.apply();
   Log.d("storesharedPreferences",""+set);
 }
4.Retrive value of sharedPreference using retriveSharedValue():

 private void retriveSharedValue() {
   Set<String> set = shared.getStringSet("DATE_LIST", null);
   arrPackage.addAll(set);
   Log.d("retrivesharedPreferences",""+set);
 }

//############################################################################

//Retrieve the values
Set<String> set = myScores.getStringSet("key", null);

//Set the values
Set<String> set = new HashSet<String>();
set.addAll(listOfExistingScores);
scoreEditor.putStringSet("key", set);
scoreEditor.commit();

public void addTask(Task t) {
  if (null == currentTasks) {
    currentTasks = new ArrayList<task>();
  }
  currentTasks.add(t);

  // save the task list to preference
  SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
  Editor editor = prefs.edit();
  try {
    editor.putString(TASKS, ObjectSerializer.serialize(currentTasks));
  } catch (IOException e) {
    e.printStackTrace();
  }
  editor.commit();
}

public void onCreate() {
  super.onCreate();
  if (null == currentTasks) {
    currentTasks = new ArrayList<task>();
  }

  // load tasks from preference
  SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);

  try {
    currentTasks = (ArrayList<task>) ObjectSerializer.deserialize(prefs.getString(TASKS, ObjectSerializer.serialize(new ArrayList<task>())));
  } catch (IOException e) {
    e.printStackTrace();
  } catch (ClassNotFoundException e) {
    e.printStackTrace();
  }
}
