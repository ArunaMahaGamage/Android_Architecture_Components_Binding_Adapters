# Binding adapters

Binding adapters are responsible for making the appropriate framework calls to set values. One example is setting a property value like calling the setText() method. Another example is setting an event listener like calling the setOnClickListener() method.

The Data Binding Library allows you to specify the method called to set a value, provide your own binding logic, and specify the type of the returned object by using adapters.

# Add Fallowing line to App level Build.gradle

      buildFeatures {
          dataBinding true
      }
      
      plugins {
          // Add kotlin-kapt
          id 'kotlin-kapt'
      }
      
      // Optionally
      apply plugin: 'kotlin-kapt'
      
*. 'kotlin-kapt' need to set as pluguns. There can be set in two ways. above have explain that 2 ways. if we not set 'kotlin-kapt'. our @BindingAdapter dosent set correctly.

# Add below line in MainActivity

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bind Models
        var user = User.instance
        binding.user = user

        // Bind Events
        binding.handlers = MyHandlers()

        user.firstName.set("Aruna")
        user.imageUrl.set("https://avatars.githubusercontent.com/u/25635071?s=400&u=1de9ce133f26b8c9d6605da4cba5eb5c3d444923&v=4")
             
# Add Below Layout to activity_main.xml

        <layout xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
            xmlns:android="http://schemas.android.com/apk/res/android">
            <data>
                <variable name="user" type="com.example.bindingadapters.User"/>
                <variable name="handlers" type="com.example.bindingadapters.MyHandlers"/>
            </data>

            <androidx.constraintlayout.widget.ConstraintLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                tools:context=".MainActivity">

                <TextView
                    android:id="@+id/textView"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="@{user.firstName}"
                    app:layout_constraintBottom_toTopOf="@+id/imageView2"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintLeft_toLeftOf="parent"
                    app:layout_constraintRight_toRightOf="parent"
                    app:layout_constraintTop_toTopOf="parent" />

                <ImageView
                    android:id="@+id/imageView2"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:contentDescription="TODO"
                    app:error="@{@drawable/ic_launcher_background}"
                    app:imageUrl="@{user.imageUrl}"
                    app:layout_constraintBottom_toTopOf="@+id/imageView3"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toBottomOf="@+id/textView" />

                <ImageView
                    android:id="@+id/imageView3"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:contentDescription="TODO"
                    app:error="@{@drawable/ic_launcher_background}"
                    app:layout_constraintBottom_toTopOf="@+id/button"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toBottomOf="@+id/imageView2"
                    app:loadProfileUrl="@{user.imageUrl}" />

                <Button
                    android:id="@+id/button"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:onClick="@{handlers::changeProfile}"
                    android:text="Please Click"
                    app:layout_constraintBottom_toBottomOf="parent"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintHorizontal_bias="0.5"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toBottomOf="@+id/imageView3" />

            </androidx.constraintlayout.widget.ConstraintLayout>
        </layout>
        
# Add Below lines to Util class

        class Utils {

            // Layout Load time run ones
            companion object {
                @JvmStatic
                @BindingAdapter("imageUrl", "error")
                fun loadImage(view: ImageView, url: String, error: Drawable) {
                    Picasso.get().load(url).error(error).into(view)
                }

                @JvmStatic
                @BindingAdapter("loadProfileUrl", "error")
                fun loadProfileUrl(view: ImageView, url: String, error: Drawable) {
                    Picasso.get().load(url).error(error).into(view)
                }
            }
        }
        
# Add Fallowing line to User class

        class User {
            // Singleton
            companion object {
                private var user: User? = null
                val instance: User
                    get() {
                        if (user == null) {
                            user = User()
                        }
                        return user!!
                    }
            }

            // Observers
            val firstName = ObservableField<String>()
            val imageUrl = ObservableField<String>()
        }
        
# Add Following Line to MyHandlers class to click event

        class MyHandlers {

            fun changeProfile(view: View) {
                var user = User.instance
                user.firstName.set("Gamage")
            }
        }
        
to get best understand download and run this repo. also read google document
https://developer.android.com/topic/libraries/data-binding/binding-adapters

