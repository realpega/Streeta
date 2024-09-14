# Streeta - Open Source Women Safety Project


## How does the app work?

Streeta requires just 3 basic permissions to work full-fledgedly!

`ACCESS_FINE_LOCATION`: Allows the app to access precise location data using GPS.

`SEND_SMS`: Enables the app to send SMS messages.

When you open the app for the first time, it asks you to enter a number to which the `Emergency SMS` with the location data is sent.

Once you add a number, it's saved forever and the SMS will be sent automatically at the time of Emergency. In case you've entered an `Incorrect Number`, simply clear the App Data by holding down Streeta's App Launcher, going to App info, and then tapping on `Clear Data`.

The `Launcher Activity` is `NumberActivity` and when the `UPDATE` button is clicked, a `SharedPreferences` component is used to save the entered Phone Number to app's internal memory. This is saved locally and CANNOT be transmitted to others even if you share the `APK` file to another device. The `UPDATE` button then checks if the entered number is not empty or less than 10 digits. On passing these conditions, the `MainActivity` is launched.

On the first start-up of the app, the `MainActivity` asks for the `ACCESS_FINE_LOCATION` permission.
For `Android 11 (Red Velvet Cake)` & above, there are two different permission choices based on precision: `Precise` & `Approximate`. As to get the Exact Location during Emergency, granting the `Precise` permission is preferred.

## Build Steps:


### `Android Studio`

If you want to build the app without changing the Gradle version, you can use <a href="https://redirector.gvt1.com/edgedl/android/studio/install/2021.1.1.20/android-studio-2021.1.1.20-windows.exe">Android Studio Bumblebee | 2021.1.1</a>

If you want to build the app on the latest version of Gradle, you need to download the latest version of <a href="https://developer.android.com/studio">Android Studio</a> along with the latest version of <a href="https://services.gradle.org/distributions/gradle-8.10.1-bin.zip">Gradle</a>.

Remember to modify the `XML` files to comply with the new `ConstraintLayout` format and modify the `JAVA` files accordingly to deal with the Deprecated APIs.

### `Sketchware`

Install the latest version of <a href="https://github.com/Sketchware-Pro/Sketchware-Pro/releases/download/v6.4.0-rc05/Sketchware.Pro.v6.4.0-rc05-minApi26.apk">Sketchware</a> and tap on the `Restore Projects` option. From your device's Internal Storage, select the <a href="https://github.com/realpega/Streeta/releases/download/v1/Streeta-v1.swb">`Streeta.swb`</a> file. It will automatically import the complete project. Open it, and configure the `Build Settings` beside the `Run` button in the bottom-right corner of the screen if needed. Finally, tap on the `Run` button to build the `APK` file. It may ask you for the `Install from Unknown Sources` permission, just enable it and you'll be ready to install the app.
