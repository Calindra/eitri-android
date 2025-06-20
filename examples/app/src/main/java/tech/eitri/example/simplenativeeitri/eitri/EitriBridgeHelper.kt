package tech.eitri.example.simplenativeeitri.eitri

import tech.calindra.eitri.android.EitriMachine
import tech.calindra.eitri.android.customNavigation.EitriMachineStack

class EitriBridgeHelper {

    fun exposeFunctions(eitriMachine: EitriMachine, eitriMachineStack: EitriMachineStack) {

        // simple fn to demonstrate exposedApis
        eitriMachine.bridge.exposeMethod("math", "sum") {
            val params = it.data
            params.getDouble("a") + params.getDouble("b")
        }
    }

}
