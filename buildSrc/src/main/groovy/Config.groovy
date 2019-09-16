/**
 * create on 19/09/11
 * gradle 配置管理
 * @author ListenerGao
 */
class Config {

    protected static applicationId = 'com.listenergao.wanandroid'
    protected static compileSdkVersion = 29
    protected static buildToolsVersion = '29.0.2'
    protected static minSdkVersion = 19
    protected static targetSdkVersion = 29

    protected static versionCode = 1
    protected static versionName = '1.0'

    protected static kotlin_version = '1.3.50'


    protected static depConfig = [
            plugin  : [
                    gradle: "com.android.tools.build:gradle:3.5.0",
                    kotlin: "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version",
            ],

            androidx: [
                    appcompat        : 'androidx.appcompat:appcompat:1.1.0',
                    core_ktx         : 'androidx.core:core-ktx:1.1.0',
                    constraintlayout : 'androidx.constraintlayout:constraintlayout:2.0.0-beta2',
                    drawerlayout     : 'androidx.drawerlayout:drawerlayout:1.0.0',
                    legacy_support_v4: 'androidx.legacy:legacy-support-v4:1.0.0',
                    material         : 'com.google.android.material:material:1.0.0',
                    multidex         : 'com.android.support:multidex:1.0.3'
            ],

            kotlin  : [
                    kotlin                 : "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version",
                    // kotlin协程
                    kotlinx_coroutines_core: 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0'
            ]
    ]
}