package com.example.tourism_app

import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class admindash : AppCompatActivity() {

    private val Imageback = 1

    private var Folder: StorageReference? = null
    var edname: EditText? = null

    var edaddress: EditText? = null
    var edcontact: EditText? = null
    var editem1: EditText? = null
    var editem1price: EditText? = null
    var editem2: EditText? = null
    var editem2price : EditText?=null
    var editem3 : EditText?=null
    var editem3price : EditText?=null


    var hname: String? = null

    var address: String? = null
    var contact: String? = null
    var item1: String? = null
    var item1price: String? = null
    var item2: String? = null
    var item2price :String?=null
    var item3:String?=null
    var item3price :String?=null
    var lat:String?=null
    var lng:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admindash)

        edname = findViewById(R.id.edname)
        edaddress = findViewById(R.id.edaddress)
        edcontact = findViewById(R.id.edmobile)
        editem1 = findViewById(R.id.editem1)
        editem1price = findViewById(R.id.edprice1)
        editem2 = findViewById(R.id.editem2)
        editem2price = findViewById(R.id.edprice2)
        editem3 = findViewById(R.id.editem3)
        editem3price = findViewById(R.id.edprice3)

        Folder = FirebaseStorage.getInstance().reference.child("ImageFolder")

    }

    fun UploadData(view: View?) {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, Imageback)
        storedata()
    }
    private fun storedata() {
        hname = edname!!.text.toString()
        address = edaddress!!.text.toString()
        contact = edcontact!!.text.toString()
        item1 = editem1!!.text.toString()
        item1price = editem1price!!.text.toString()
        item2=editem2!!.text.toString()
        item2price = editem2price!!.text.toString()
        item3 = editem3!!.text.toString()
        item3price= editem3price!!.text.toString()


        val geocode = Geocoder(this, Locale.getDefault())
        val addList = geocode.getFromLocationName(hname!!,1)
        lat = addList?.get(0)?.latitude.toString()
        lng  = addList?.get(0)?.longitude.toString()


        println(lat)
        println(lng)



    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Imageback) {
            if (resultCode == RESULT_OK) {
                val ImageData = data!!.data
                val Imagename = Folder!!.child("image" + ImageData!!.lastPathSegment)
                Imagename.putFile(ImageData).addOnSuccessListener {
                    Imagename.downloadUrl.addOnSuccessListener { uri ->
                        val Imagestore = FirebaseDatabase.getInstance().reference.child("Place")
                        val item = Items(
                            hname,address,contact,item1,item1price,item2,item2price,item3,item3price,uri.toString()

                        )
                        Imagestore.push().setValue(item)
                        Toast.makeText(applicationContext,"Uploaded", Toast.LENGTH_LONG).show()

//                                 HashMap<String, String> hashMap = new HashMap<>();
//                                 hashMap.put("imageurl", String.valueOf(uri));
//                                 Imagestore.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                     @Override
//                                     public void onSuccess(Void unused) {
//                                         Toast.makeText(uploadimage.this, "Upload", Toast.LENGTH_SHORT).show();
//                                     }
//                                 });
                    }
                }
            }
        }


    }
}