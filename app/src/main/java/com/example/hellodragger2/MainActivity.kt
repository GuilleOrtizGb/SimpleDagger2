package com.example.hellodragger2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import dagger.Component
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var info: Info

//    @Inject
//    lateinit var data: Itext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMagicBox.create().poke(this)

        var kk = info.text
        txt.setText(kk)


//-----------My implententaion -----------//
        var DefaultMessage = implementation()
        var GuilleMEssage= implementationGuille()
//
//        var mData= data(GuilleMEssage)
//        txt.setText(mData.getData())



//--------My Implementation Dagger2------------//
//        Toast.makeText(this,kk, LENGTH_LONG)

//
//        DaggerMagicBox2.create().inject(this)
        DaggerMagicBox.create().inject2(GuilleMEssage)

        //txt.setText(data.show())

    }
}

class Info @Inject constructor() {
    val text = "Hello Dagger 2"
}

@Component //where the instantiating my member variable  magic happends
interface MagicBox {
    //I also need to tell my magic box, it is there to perform it’s
    // magic on MainActivity. So to do that, I create a poke function
    // accepting MainActivity in my MagicBox.
    fun poke(app: MainActivity)//inject()
    fun inject2(text: Itext)
}

//-----------My Implementation------------//

//@Component
//interface MagicBox2 {
//    fun inject(app: MainActivity)
//}

class data @Inject constructor (var texto: Itext){
    fun getData(): String{
        return texto.show()
    }
}

interface Itext{
    fun show(): String
}

class implementation @Inject constructor(): Itext{
    override fun show(): String {
       return  "Hello implementation"
    }
}

class implementationGuille @Inject constructor(): Itext{
    override fun show(): String {
        return   "Hello Guille"
    }
}