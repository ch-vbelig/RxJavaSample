package com.example.rxjavasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hello(arrayOf("alex", "sasha"))
    }

    private fun hello(args: Array<String>) {
        var name: String by Delegates.observable("<no name>") { prop, old, new ->
            println("$old -> $new")
        }
        name = "Vladok"
        name = "ElBerg"
//        val obs: Observable<String> = Observable.fromArray(*args)
//
//        obs
//                .map { it.toUpperCase() }
//                .subscribe({s ->
//                            println(s)
//                            text_view.text = "Hello $s"})
//        obs.
        val obs = PublishSubject.create<String>()
        obs
            .map { it.toUpperCase() }
            .subscribe(
                { s ->
                    println("Hello $s")
                    text_view.text = "Hello $s"
                },
                { error -> println("Error") },
                { println("Completed") })
//                .subscribe ({ s ->
//                    println("Hello $s")
//                    text_view.text = "Hello $s"},
//                        {})

        obs.onNext("Belig")
        obs.onComplete()
        obs.onNext("Sasha")


    }
}