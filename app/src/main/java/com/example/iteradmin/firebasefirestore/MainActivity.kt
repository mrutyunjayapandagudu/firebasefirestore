package com.example.iteradmin.firebasefirestore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db= FirebaseFirestore.getInstance()
        val tt1=findViewById<EditText>(R.id.name)
        val tt2=findViewById<EditText>(R.id.branch)
        val tt3=findViewById<EditText>(R.id.city)
        val bt=findViewById<Button>(R.id.save)
        val bt1=findViewById<Button>(R.id.load)
        val tt=findViewById<TextView>(R.id.tt)
        bt.setOnClickListener {
            val  n=tt1.text.toString()
            val  c=tt2.text.toString()
            val  b=tt3.text.toString()
            val map= hashMapOf<String, String>(
                    "tt1" to n,"tt2" to c,"tt3" to b
            )
            db.collection("users").add(map)
                    .addOnSuccessListener {
                        documentReference ->
                        Toast.makeText(this,"added to fire store",Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener(){
                        Toast.makeText(this,"something went worng",Toast.LENGTH_LONG).show()
                    }
        }
        bt1.setOnClickListener {
                db.collection("users").get()
                    .addOnSuccessListener { querySnapshot ->
                        for (d in querySnapshot.documents) {
                            val tt1: String = d.data?.get("name").toString()
                            Toast.makeText(this, tt1, Toast.LENGTH_SHORT).show()
                        }
                    }
                        .addOnFailureListener{
                            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
                        }
        }


    }
}
