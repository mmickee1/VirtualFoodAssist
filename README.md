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

For this project we used [Android Studio](https://developer.android.com/studio/ "Android Studio") and Kotlin language.

In MainActivity has methods that call other classes  

```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_camera.setOnClickListener {
            Log.d("clicked", "Camera")
            val intent = Intent(this, AugmentedReality::class.java)
            startActivity(intent)
        }

        txt_info.setOnClickListener {
            Log.d("clicked", "info")
            supportFragmentManager.beginTransaction().replace(R.id.main_container_child, Info()).addToBackStack(null).commit()
        }

        txt_map.setOnClickListener {
            Log.d("clicked", "Map")
            supportFragmentManager.beginTransaction().replace(R.id.main_container_child, Location()).addToBackStack(null).commit()
            //val intent = Intent(this, Location::class.java)
            //startActivity(intent)
        }

        txt_history.setOnClickListener {
            Log.d("clicked", "History")
        }

    }
    
```  

This way the MainActivity class looks more clear and readable for human eyes and rest of the code is happening in own classes.  

---

*Week 1 result*

![alt text](![387352a2-2cbd-46bf-ba98-176280926957](https://user-images.githubusercontent.com/23027158/46259482-bd287b80-c4e2-11e8-9398-4865a0164631.jpg)

![alt text](![1a11a352-622a-4aaa-863d-c9b24e6cc28a](https://user-images.githubusercontent.com/23027158/46259510-0678cb00-c4e3-11e8-9150-0f78c76afbe5.jpg)

![alt text](![8946272e-0a4a-4742-832c-679b4881a2ca](https://user-images.githubusercontent.com/23027158/46259516-1bedf500-c4e3-11e8-9c0c-0845d1c846aa.jpg)

---

Project members:  
* Kendy Nguyen
* Mikael Meinander