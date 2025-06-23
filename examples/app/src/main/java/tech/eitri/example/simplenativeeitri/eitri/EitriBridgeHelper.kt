package tech.eitri.example.simplenativeeitri.eitri

import tech.calindra.eitri.android.EitriMachine
import tech.calindra.eitri.android.customNavigation.EitriMachineStack

/**
 * EitriBridgeHelper is a utility class to expose functions to eitri-apps.
 * 
 * It contains methods to expose custom APIs that can be used in the Eitri environment.
 * 
 * This is where you can define your custom functions that will be available in the eitri-apps.
 */
class EitriBridgeHelper {

    fun exposeFunctions(eitriMachine: EitriMachine, eitriMachineStack: EitriMachineStack) {

        // simple fn to demonstrate exposedApis
        eitriMachine.bridge.exposeMethod("math", "sum") {
            val params = it.data
            params.getDouble("a") + params.getDouble("b")
        }
    }

}
