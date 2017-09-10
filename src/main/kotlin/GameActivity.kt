package net.clueonic.stranded

import android.app.Activity
import android.os.Bundle
import android.view.View
import org.jetbrains.anko.setContentView

// Kept globally until there's proper lifecycle management and save/restore
val gameModel = GameModel()

class GameActivity : Activity() {
    var view: View? = null

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            gameModel.population.generateSurvivors(
                dieroll(INITIAL_SURVIVORS_MIN, INITIAL_SURVIVORS_MAX)
            )
        }
        view = GameLayout().setContentView(this)
        updateGameView(view, gameModel)
    }

    fun advanceOneYear() {
        gameModel.population = Population.yearOlder(gameModel.population)
        gameModel.year += 1
        updateGameView(view, gameModel)
    }
}
