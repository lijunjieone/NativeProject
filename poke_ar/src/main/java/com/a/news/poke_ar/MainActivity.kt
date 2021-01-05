package com.a.news.poke_ar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.core.Pose
import com.google.ar.core.Session
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.ux.ArFragment
import com.skydoves.pokedexar.extensions.findFragmentAs
import com.skydoves.pokedexar_core.ModelRenderer
import com.skydoves.pokedexar_core.PokemonModels

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(findFragmentAs<ArFragment>(R.id.arFragment)) {
            planeDiscoveryController.hide()
//        val text = TextView(requireContext())
//      text.text = "test"
            planeDiscoveryController.setInstructionView(null)
            arSceneView.planeRenderer.isVisible =true
            arSceneView.scene.addOnUpdateListener {
                onUpdate(it)

                // checks the state of the AR frame is Tracking.
                val arFrame = arSceneView.arFrame ?: return@addOnUpdateListener
                if (arFrame.camera?.trackingState != TrackingState.TRACKING) {
                    return@addOnUpdateListener
                }

                // initialize the global anchor with default rendering models.
                arSceneView.session?.let { session ->
                    initializeModels(this, session)
                }
            }
        }

    }

    private fun initializeModels(arFragment: ArFragment, session: Session) {
        if (session.allAnchors.isEmpty()) {
            val pose = Pose(floatArrayOf(0f, 0f, -1f), floatArrayOf(0f, 0f, 0f, 1f))
            session.createAnchor(pose).apply {
                val garden = PokemonModels.getGarden()
                ModelRenderer.renderObject(this@MainActivity, garden) { renderable ->
                    ModelRenderer.addGardenOnScene(arFragment, this, renderable, garden)
                }

                val pokemon = PokemonModels.getPokemonByName("bulbasaur")
                    .copy(localPosition = PokemonModels.DEFAULT_POSITION_DETAILS_POKEMON)
                ModelRenderer.renderObject(this@MainActivity, pokemon) { renderable ->
                    ModelRenderer.addPokemonOnScene(arFragment, this, renderable, pokemon)
                }
            }
        }
    }

}