package net.clueonic.stranded

import org.jetbrains.anko.*

class GameLayout : AnkoComponent<Game> {

    override fun createView(ui: AnkoContext<Game>) = with(ui) {
        textView {
            backgroundResource = R.drawable.windowframe
            text = "Placeholder"
        }
    }
}
