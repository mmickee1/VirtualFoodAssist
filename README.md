# VirtualFoodAssist
Metropolia Mobile App Sensor Course Project

The main idea of VirtualFoodAssist project is for users to use phone camera app to scan food pictures and search for recipes for it. For each food (for example pasta or pizza) has its own 3D model which will pop up to screen as AR view. It also tell users where are the near food stores after scanning.

---

*Week 1 schedule*  

For very first week we applied:  
* project skeleton (Main menu view)
* augmented reality
* info page

---  

**Getting started**  

For this project we used [Android Studio](https://developer.android.com/studio/ "Android Studio") and [Kotlin](https://kotlinlang.org/ "Kotlin") language.  
  
Requirements for this app is an Android device which has API level 24 or higher which is Android version (Nougat) 7.0 - 7.1.2 or newer.

In MainActivity has methods that call other classes  

```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_camera.setOnClickListener {
            Log.d("clicked", "Camera")
            setContentView(R.layout.splash)
            // Opens a splash screen for 2,5 seconds before opening a camera view
            val background = object : Thread() {
                override fun run() {
                    try {
                        Thread.sleep(2500)

                        val intent = Intent(baseContext, AugmentedReality::class.java)
                        startActivity(intent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            background.start()
        }

        txt_info.setOnClickListener {
            Log.d("clicked", "info")
            supportFragmentManager.beginTransaction().replace(R.id.main_container_child, Info()).addToBackStack(null).commit()
            //  .add(detailFragment, "detail")... better back navigation?
        }

        txt_map.setOnClickListener {
            Log.d("clicked", "Map")
            supportFragmentManager.beginTransaction().replace(R.id.main_container_child, Location()).addToBackStack(null).commit()
        }

        txt_history.setOnClickListener {
            Log.d("clicked", "History")
            supportFragmentManager.beginTransaction().replace(R.id.main_container_child, History()).addToBackStack(null).commit()
        }
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStackImmediate()
    }
    
```  

This way the MainActivity class looks more clear and readable for human eyes and rest of the code is happening in own classes.  

---

*Week 1 result*

![week1](https://user-images.githubusercontent.com/23027158/46583620-7c8cad00-ca62-11e8-8cc5-194e547547b1.jpg)

---  

*Week 2 schedule*  

For second week we applied:  
* Location/map
* Databse (History)
* Sensors (Compass)
* Added talkback and multilanguage features

---  

For week two we improved the layout and accessibilites. Depending on device language, the app will automatically switch to that language. At the moment we have english and finnish version. Improvements were also made on the info page, since we got feedbacks that the text fonts are too small to read. 

---  

*Week 2 result*  

![week2](https://user-images.githubusercontent.com/23027158/46583678-37b54600-ca63-11e8-853d-4d7c91ef1738.jpg)

![week2b](https://user-images.githubusercontent.com/23027158/46583706-a1cdeb00-ca63-11e8-8506-4b2b5d925f09.jpg)

---  

**Credits**
* [poly.google.com](https://poly.google.com/)
* [developer.android.com](https://developer.android.com/)

---  

**Authors**
* Kendy Nguyen
* Mikael Meinander