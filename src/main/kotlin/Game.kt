package net.clueonic.stranded

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.setContentView

class Game : Activity() {

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GameLayout().setContentView(this)
    }
}
