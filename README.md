keytool -genkeypair -v -keystore ww_start_recents.keystore -keyalg RSA -keysize 2048 -validity 100000 -alias ww


https://github.com/gradle/gradle
> https://github.com/gradle/gradle-completion

gradle


//gradle wrapper
//bash gradlew build


pkg i git openjdk-17 aapt dx apksigner



javac -source 8 -target 8 -cp /data/data/com.termux/files/home/android-sdk/platforms/android-33/android.jar Recents.java

指定编译目标为Java8，否则dx可能不兼容。
PARSE ERROR:
unsupported class file version 61.0


dx --dex --no-strict --output=classes.dex Recents.class

--no-strict让其无视路径显示
PARSE ERROR:
class name (ww/start/recents/Recents) does not match path (Recents.class)


mkidr dex && mv classes.dex dex/
aapt package -f  --target-sdk-version 30 -M AndroidManifest.xml  -F recents.apk dex/


apksigner sign --ks ww_start_recents.jks --ks-key-alias ww --out recents_app.apk recents.apk
