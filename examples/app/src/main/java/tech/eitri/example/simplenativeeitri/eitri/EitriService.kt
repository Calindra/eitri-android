package tech.eitri.example.simplenativeeitri.eitri

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.calindra.eitri.android.EitriMachine
import tech.calindra.eitri.android.customNavigation.EitriMachineStack
import tech.calindra.eitri.android.publicModels.ConfigureInput
import tech.calindra.eitri.android.publicModels.RunInput
import tech.calindra.eitri.android.publicModels.Theme
import tech.calindra.eitri.android.publicModels.ThemeConfig

const val TAG = "EitriService"

// TODO: Replace with the current app version or marketing version
const val APP_VERSION = "1.0.0"

// TODO: Replace with a scheme property and use a real environment key provided by the Eitri team
const val ENV_KEY = "b73b5d05-183a-4630-b442-71d2b61cfaaa"


/**
 * EitriService is a suggested service to manage the EitriMachine and EitriMachineStack.
 *
 * It's responsible for configuring the EitriMachine and exposing the necessary Eitri APIs for the app.
 *
 * It's also wrapping the asynchronous nature of some EitriMachine methods to make it easier to use
 * in an older native iOS application.
 *
 * If you are using a modern architecture that supports coroutines, you might want to adapt
 * this service to fit your needs.
 */
class EitriService (
    val mainEitriMachine: EitriMachine,
    val eitriMachineStack: EitriMachineStack
    ) {

    fun configure(ctx: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            mainEitriMachine.bridge.exposeNativeNavigationApi(eitriMachineStack)

            EitriBridgeHelper().exposeFunctions(mainEitriMachine, eitriMachineStack)

            mainEitriMachine.configure(
                ConfigureInput(
                    context = ctx,
                    version = APP_VERSION,
                    environmentKey = ENV_KEY,
                    debugMode = isEitriDevEnabled(),
                    theme = ThemeConfig(
                        Theme("#1E1E1E"),
                        Theme("#1E1E1E")
                    )
                )
            )
        }
    }

    fun runOnTop(runInput: RunInput) {
        CoroutineScope(Dispatchers.Main).launch() {
            try {
                eitriMachineStack.createReplicaInstance().run(runInput)
            } catch (e: Exception) {
                Log.e("MyActivity", "eitriRunError", e)
            }
        }
    }

    private fun isEitriDevEnabled(): Boolean {
        // TODO: Define your rules. Production builds typically set this to false to disable Eitri debugging.
        return true
    }

}
