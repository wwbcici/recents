# termuxLauncher

用于启动[termux-app](https://github.com/termux/termux-app)，由 `minimal apk by termux` 构建。可以在此[下载](https://github.com/wwbcici/recents/releases)已构建完成的apk。

## build by termux

构建流程：
- [编译源代码](#编译Java源代码)
- [生成dex](#生成dex)
- [打包](#打包apk)
- [签名](#签名apk)

### 构建环境：
- `pkg i git openjdk-17 aapt dx apksigner`
- [android-sdk](https://github.com/AndroidIDEOfficial/androidide-tools/releases/tag/sdk)

### 开始构建：

#### 编译Java源代码

`javac -source 8 -target 8 -cp /data/data/com.termux/files/home/android-sdk/platforms/android-33/android.jar Recents.java

指定编译目标为Java8，否则dx可能不兼容。
```err
PARSE ERROR:
unsupported class file version 61.0
```

jar包在 `android-sdk` 中包含。

#### 生成dex

Dalvik Executable 为Android虚拟机的可执行文件。

`dx --dex --no-strict --output=classes.dex Recents.class`

`--no-strict` 让其无视路径显示，否则必须按照常规项目结构存储文件。
```
PARSE ERROR:
class name (ww/start/recents/Recents) does not match path (Recents.class)
```

#### 打包apk

`mkidr dex && mv classes.dex dex/`
`aapt package -f  --target-sdk-version 30 -M AndroidManifest.xml  -F recents.apk dex/

-f 强制覆盖现有文件

> aapt前，需要将要打包的文件放入文件夹中

#### 签名apk

##### 生成密钥：
`keytool -genkeypair -v -keystore ww.start.recents.keystore -keyalg RSA -keysize 2048 -validity 100000 -alias ww`

- `-genkeypair` 指示 keytool 生成密钥
- `-keystore ww.start.recents.keystore` 输出文件（后缀可以定义）
- `-keyalg RSA` 密钥生成算法
- `-keysize 2048` 密钥位数
- `-validity 100000`  密钥有效期（单位：天）
- `-alias ww` 指定密钥对的别名为 ww。别名用于标识密钥对，以便后续的操作可以引用到这个密钥对。

> 本仓库提供的密钥`ww.start.recents.keystore`,其密码为`qwerty`

##### 签名
`apksigner sign --ks ww.start.recents.keystore --ks-key-alias ww --out recents_app.apk recents.apk`

