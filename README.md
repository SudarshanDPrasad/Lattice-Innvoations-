# Lattice-Innvoations-

This project is used by the following companies:

* GitHub
* Recycler View
* View Pager
* Retrofit Library
* API
* MVVM Model 
* Coroutine Scope

# Tech Stack ✨

* [Android Studio](https://developer.android.com/studio)
* [Kotlin](https://kotlinlang.org/)


# Demo Link 

[![68747470733a2f2f656e637279707465642d74626e302e677374617469632e636f6d2f696d616765733f713d74626e3a414e643947635267774a637a3634327041376d4c52357534344f69724b534a6a66784f6f4f715762704e783776674450304e](https://user-images.githubusercontent.com/86509887/149654216-db60a1a8-948a-4d92-b4df-468749a381f3.jpg)](https://drive.google.com/file/d/1a7W0sCKpAghh4Y7KBV04JkSempBbxG7z/view?usp=sharing)

# Dependencies

    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.4.0'
    implementation 'androidx.navigation:navigation-ui-ktx:2.4.0'
    def anko_version = '0.10.0'

    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
    testImplementation 'junit:junit:'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    //navigation
    def nav_version = "2.3.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    implementation("androidx.navigation:navigation-compose:2.4.0-rc01")

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'

    //Retrofit
    def retrofit2_version = "2.9.0"
    def okhttp3_version = "4.9.0"
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.9.0'
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"
    implementation "com.squareup.okhttp3:okhttp:$okhttp3_version"
    implementation "com.squareup.okhttp3:logging-interceptor:$okhttp3_version"

    //hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    // ViewModel and LiveData
    def arch_version = '2.2.0-alpha01'
    implementation "androidx.lifecycle:lifecycle-extensions:$arch_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$arch_version"

    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'

    def paging_version = "3.1.0-alpha03"
    implementation "androidx.paging:paging-runtime-ktx:$paging_version"

    //Room
    implementation 'androidx.room:room-ktx:2.3.0'
    kapt 'androidx.room:room-compiler:2.3.0'
    annotationProcessor "com.google.dagger:dagger-android-processor:2.11"
