# Material Design Example App

This is a small example app to showcase how to implement the Material Design Guidelines in your app.

## Colors

To define the colors used in the app a `colors.xml` file was created using the Material Design [Color Tool](https://material.io/color/). 

````xml
<!--?xml version="1.0" encoding="UTF-8"?-->
<resources>
  <color name="primaryColor">#424242</color>
  <color name="primaryLightColor">#6d6d6d</color>
  <color name="primaryDarkColor">#1b1b1b</color>
  <color name="secondaryColor">#bf360c</color>
  <color name="secondaryLightColor">#f9683a</color>
  <color name="secondaryDarkColor">#870000</color>
  <color name="primaryTextColor">#ffffff</color>
  <color name="secondaryTextColor">#ffffff</color>
</resources>
````

The colors file has to be placed in the `values` resource directory.

## Android Support Libraries

To use special design widgets / elements the android design support library has to be included.

To achieve this the following line has to be added to the `build.gradle` file at app level.

````groovy
implementation "com.android.support:design:<VERSION>"
````

The version has to match the targetSdkVersion. The complete file looks like this:

````xml
apply plugin: 'com.android.application'

android {
    compileSdkVersion 27
    defaultConfig {
        applicationId "de.thm.materialdesignexample"
        minSdkVersion 15
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            postprocessing {
                removeUnusedCode false
                removeUnusedResources false
                obfuscate false
                optimizeCode false
                proguardFile 'proguard-rules.pro'
            }
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation "com.android.support:design:27.1.1"
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}
````

You can find a list of the library versions [here](https://developer.android.com/topic/libraries/support-library/revisions.html).

## Themes

Afterwards themes have to be set to define the style of the app. The themes are created in the `styles.xml` file which is also located in the `values` directory.

````xml
<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/primaryColor</item>
        <item name="colorPrimaryDark">@color/primaryDarkColor</item>
        <item name="colorAccent">@color/secondaryColor</item>

        <item name="android:textAppearanceSmallPopupMenu">
            @style/myPopupMenuTextAppearanceSmall
        </item>
        <item name="android:textAppearanceLargePopupMenu">
            @style/myPopupMenuTextAppearanceLarge
        </item>
    </style>

    <style name="ToolbarCustomTheme" parent="AppTheme">
        <item name="android:textColorPrimary">@color/primaryTextColor</item>
        <!-- This sets the color of the back arrow if there is one -->
        <item name="colorControlNormal">@color/primaryTextColor</item>
    </style>

    <!-- Add styles for the popup menu so that its text color is black
    and it has the right fontsize -->
    <style name="myPopupMenuTextAppearanceSmall"
        parent="@android:style/TextAppearance.DeviceDefault.Widget.PopupMenu.Small">
        <item name="android:textColor">@color/primaryDarkColor</item>
        <item name="android:textSize">12sp</item>
    </style>
    <style name="myPopupMenuTextAppearanceLarge"
        parent="@android:style/TextAppearance.DeviceDefault.Widget.PopupMenu.Large">
        <item name="android:textColor">@color/primaryDarkColor</item>
        <item name="android:textSize">20sp</item>
    </style>

</resources>
````

The base theme used does not include an ActionBar because we will implement a custom toolbar.

## Toolbar

To implement a custom toolbar it has to be defined in a layout file. The `toolbar.xml` is placed in the `layout` directory.

````xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.Toolbar
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_height="?attr/actionBarSize"
    android:layout_width="match_parent"
    android:id="@+id/toolbar"
    android:background="@color/primaryColor"
    android:elevation="4dp"
    android:theme="@style/ToolbarCustomTheme"
    android:popupTheme="@style/AppTheme" />
````

To use the toolbar in activities it has to be included in the activity layout file. 

See the `activity_layout.xml` for reference:

````xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <include layout="@layout/toolbar" />

    <ListView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/listView"
        android:layout_marginTop="?attr/actionBarSize"/>

    <android.support.design.widget.FloatingActionButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/floatingButton"
        android:layout_gravity="bottom|end"
        android:layout_margin="16dp"
        android:src="@drawable/ic_add_white_24dp"
        app:layout_anchor="@id/listView"
        app:layout_anchorGravity="bottom|right|end"
        />

</android.support.design.widget.CoordinatorLayout>
````

Now the toolbar must be set as ActionBar for the Activity:

````java
//MainActivity
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_layout);
    listView = findViewById(R.id.listView);
    listView.setOnItemClickListener(new ListItemListener());
    button = findViewById(R.id.floatingButton);
    button.setOnClickListener(new FABListener());

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

}
````

Now if you create an OptionsMenu or call `setDisplayHomeAsUpEnabled(true)` the items and the arrow for back navigation will be added to our toolbar.

## FloatingActionButton

The FloatingActionButton is included in the support design library. It has to be used together with a CoordinatorLayout because it is placed absolute and requires an anchor. See the layout file for the activity as reference:

````xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <include layout="@layout/toolbar" />

    <ListView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/listView"
        android:layout_marginTop="?attr/actionBarSize"/>

    <android.support.design.widget.FloatingActionButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/floatingButton"
        android:layout_gravity="bottom|end"
        android:layout_margin="16dp"
        android:src="@drawable/ic_add_white_24dp"
        app:layout_anchor="@id/listView"
        app:layout_anchorGravity="bottom|right|end"
        />

</android.support.design.widget.CoordinatorLayout>
````

It can now be accessed like any other button in the activity.