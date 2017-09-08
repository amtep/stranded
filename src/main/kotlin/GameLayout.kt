package net.clueonic.stranded

import android.view.View
import android.widget.TextView
import org.jetbrains.anko.*

val populationId = View.generateViewId()
val yearId = View.generateViewId()

class GameLayout : AnkoComponent<GameActivity> {

    override fun createView(ui: AnkoContext<GameActivity>) = with(ui) {
        verticalLayout {
            backgroundResource = R.drawable.windowframe

            textView {
                id = yearId
                text = "Year ?"
            }

            textView {
                id = populationId
                text = "Placeholder"
            }

            button("1 Year") {
                setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View) {
                        owner.advanceOneYear()
                    }
                })
            }
        }
    }
}

fun updateGameView(view: View?, model: GameModel) {
    if (view == null)
        return

    view.find<TextView>(populationId).text = model.population.describeLong()
    view.find<TextView>(yearId).text = "Landing year ${model.year}"
}
