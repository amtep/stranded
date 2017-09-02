package net.clueonic.stranded

import android.app.Activity
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.os.Bundle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class Game : Activity() {

    protected var view: GLSurfaceView? = null
    protected val renderer = Game.Renderer()

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = GLSurfaceView(this)
        if (BuildConfig.DEBUG) {
            view?.setDebugFlags(GLSurfaceView.DEBUG_CHECK_GL_ERROR or GLSurfaceView.DEBUG_LOG_GL_CALLS)
        }
        view?.setEGLContextClientVersion(2) // OpenGL ES 2.0
        view?.setEGLConfigChooser(/* needDepth= */false)
        view?.setRenderer(renderer)
        view?.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY)
    }

    override fun onPause() {
        super.onPause()
        view?.onPause()
    }

    override fun onResume() {
        super.onResume()
        view?.onResume()
    }

    class Renderer : GLSurfaceView.Renderer {
        override fun onDrawFrame(gl: GL10) {
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        }

        override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
            GLES20.glViewport(0, 0, width, height)
        }

        override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
            // nothing yet
        }
    }
}
