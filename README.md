# VirtualFoodAssist
Metropolia Mobile App Sensor Course Project

The main idea of VirtualFoodAssist project is for users to use phone camera app to scan food pictures and search for recipes for it. For each food (for example pasta or pizza) has its own 3D model. It also tell users where are the near food stores after scanning.

---

*Week 1 schedule*  

For very first week we applied:  
* project skeleton (Main menu view)
* augmented reality
* info page

---  

**Getting started**  

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

Project members:  
* Kendy Nguyen
* Mikael Meinander