package com.example.hellodragger2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier

const val LOVE = "Love"
const val HATE = "Hate"

class MainActivity : AppCompatActivity() {

    @Inject @field:Named(LOVE)
    lateinit var infoLove: Info

    @Inject @field:Named(HATE)
    lateinit var infoHate: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMagicBox.create().inject(this)

        txt.setText("${infoHate.text} ${infoLove.text}")
    }
}

@Module
class Bag{
    @Provides @Named(LOVE)
    fun sayLoveDagger2(): Info{
        return Info("Love Dagger 2")
    }
    @Provides @Named(HATE)
    fun sayHateDagger2(): Info{
        return Info("Hate Dagger 2")
    }
}

class Info(val text: String)

@Component (modules = [Bag::class])
interface MagicBox {
    //I also need to tell my magic box, it is there to perform it’s
    // magic on MainActivity. So to do that, I create a poke function
    // accepting MainActivity in my MagicBox.
    fun inject(app: MainActivity)//inject()
}