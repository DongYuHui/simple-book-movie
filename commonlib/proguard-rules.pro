# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Softwares\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#===================================================================================================

# Common Proguard

-optimizationpasses 5           # 指定代码的压缩级别
-dontusemixedcaseclassnames     # 是否使用大小写混合
-dontpreverify                  # 混淆时是否做预校验
-verbose                        # 混淆时是否记录日志
-ignorewarnings                 # 混淆时去掉警告

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

#===================================================================================================

#保护泛型
-keepattributes Signature
-dontwarn android.support.**

#保护注解
-keepattributes *Annotation*
-keepattributes InnerClasses
-keep class java.lang.annotation.**{*;}
-keep class java.lang.annotation.ElementType{*;}
-keep class  java.lang.annotation.Retention{*;}
-keep class  java.lang.annotation.RetentionPolicy{*;}
-keep class  java.lang.annotation.Target{*;}
###############
-keep class **.R$* {
 *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

#保持自定义控件指定规则的方法不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#保持自定义控件指定规则的方法不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#保持枚举 enum 不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#需要序列化和反序列化的类不能被混淆（注：Java反射用到的类也不能被混淆）
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保护WebView对HTML页面的API不被混淆
-keep class **.Webview2JsInterface { *; }

#如果你的项目中用到了webview的复杂操作 ，最好加入
-keepclassmembers class * extends android.webkit.WebViewClient {
     public void *(android.webkit.WebView,java.lang.String,android.graphics.Bitmap);
     public boolean *(android.webkit.WebView,java.lang.String);
}

#如果你的项目中用到了webview的复杂操作 ，最好加入
-keepclassmembers class * extends android.webkit.WebChromeClient {
     public void *(android.webkit.WebView,java.lang.String);
}

#===================================================================================================

# EventBus

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
# Only required if you use AsyncExecutor
#-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
#    <init>(java.lang.Throwable);
#}

#===================================================================================================

# RxJava

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#===================================================================================================

# Retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

#===================================================================================================

## GSON 2.2.4 specific rules ##

# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
#-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*
-keepattributes EnclosingMethod
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

#===================================================================================================

# OkHttp3

-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#===================================================================================================

# ButterKnife

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#===================================================================================================

# RetroLambda

-dontwarn java.lang.invoke.*

#===================================================================================================

# autovalue

-dontwarn javax.lang.**
#-dontwarn javax.tools.**
#-dontwarn javax.annotation.**
-dontwarn autovalue.shaded.com.**
-dontwarn com.google.auto.value.**

#===================================================================================================

# depends on application

-keep class com.kyletung.commonlib.http.** { *; }
-keep class com.kyletung.commonlib.view.** { *; }
-keep class com.kyletung.commonlib.utils.** { *; }

#===================================================================================================

# Only requried if you want to see where the error or exception happens
#-keepattributes SourceFile,LineNumberTable