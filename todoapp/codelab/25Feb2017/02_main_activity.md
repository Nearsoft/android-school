[image]: http://i.imgur.com/SFPihLo.png

# Main Activity
El codigo que debemos tener en `MainActivity.java` debe ser el siguiente ó algo muy parecido:

````java
package com.nearsoft.android.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}

````

El metodo de `onCreate(Bundle savedInstanceState)` sirve como punto de entrada de nuestra aplicación de Android.

## Actividad Principal

Para ver que **Activity** es el punto de entrada de nuestra aplicación debemos ir al archivo `AndroidManifest.xml`

````xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.nearsoft.android.todoapp">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
````

Un **Activity** principal siempre tendrá el **IntentFilter**:

```xml
<category android:name="android.intent.category.LAUNCHER" />
```

Si corremos esta aplicación en cualquier Android device ó emulador, deberemos ver lo siguiente:

![][image]
