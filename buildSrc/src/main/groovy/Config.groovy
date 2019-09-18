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
            plugin                       : [
                    gradle: "com.android.tools.build:gradle:3.5.0",
                    kotlin: "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version",
            ],

            androidx                     : [
                    appcompat        : 'androidx.appcompat:appcompat:1.1.0',
                    core_ktx         : 'androidx.core:core-ktx:1.1.0',
                    constraintlayout : 'androidx.constraintlayout:constraintlayout:2.0.0-beta2',
                    drawerlayout     : 'androidx.drawerlayout:drawerlayout:1.0.0',
                    legacy_support_v4: 'androidx.legacy:legacy-support-v4:1.0.0',
                    material         : 'com.google.android.material:material:1.0.0',
                    multidex         : 'com.android.support:multidex:1.0.3'
            ],

            kotlin                       : [
                    kotlin                 : "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version",
                    // kotlin协程
                    kotlinx_coroutines_core: 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0'
            ],


            junit                        : 'junit:junit:4.12',
            runner                       : 'androidx.test:runner:1.2.0',
            espresso_core                : 'androidx.test.espresso:espresso-core:3.2.0',



            utilcodex                    : 'com.blankj:utilcodex:1.25.3',

            rxjava3                      : 'io.reactivex.rxjava3:rxjava:3.0.0-RC1',
            rxandroid2                   : 'io.reactivex.rxjava2:rxandroid:2.1.1',
            retrofit2                    : 'com.squareup.retrofit2:retrofit:2.6.1',
            //版本号与retrofit版本号一致
            converter_gson               : 'com.squareup.retrofit2:converter-gson:2.6.1',
            adapter_rxjava2              : 'com.squareup.retrofit2:adapter-rxjava2:2.6.1',

            logging_interceptor          : 'com.squareup.okhttp3:logging-interceptor:4.2.0',
            butterknife                  : 'com.jakewharton:butterknife:10.2.0',
            butterknife_compiler         : 'com.jakewharton:butterknife-compiler:10.2.0',
            gson                         : 'com.google.code.gson:gson:2.8.5',
            leakcanary                   : 'com.squareup.leakcanary:leakcanary-android:2.0-beta-3',
            BaseRecyclerViewAdapterHelper: 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.45',
            banner                       : 'com.youth.banner:banner:1.4.10',
            glide                        : 'com.github.bumptech.glide:glide:4.9.0',
            glide_compiler               : 'com.github.bumptech.glide:compiler:4.9.0',
            qmui                         : 'com.qmuiteam:qmui:1.4.0',
            immersionbar                 : 'com.gyf.immersionbar:immersionbar:3.0.0-beta05',
            immersionbar_ktx             : 'com.gyf.immersionbar:immersionbar-ktx:3.0.0-beta05',
            statusbarutil                : 'com.jaeger.statusbarutil:library:1.5.1'


    ]
}