package net.clueonic.stranded

import android.view.View
import android.widget.TextView
import org.jetbrains.anko.*

val populationId = View.generateViewId()

class GameLayout : AnkoComponent<GameActivity> {

    override fun createView(ui: AnkoContext<GameActivity>) = with(ui) {
        textView {
            id = populationId
            backgroundResource = R.drawable.windowframe
            text = "Placeholder"
        }
    }
}

fun updateGameView(view: View?, model: GameModel) {
    if (view == null)
        return

    view.find<TextView>(populationId).text = model.population.describeLong()
}
