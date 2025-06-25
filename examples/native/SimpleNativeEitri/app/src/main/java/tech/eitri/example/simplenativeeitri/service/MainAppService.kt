package tech.eitri.example.simplenativeeitri.service

import android.content.Context
import tech.calindra.eitri.android.EitriMachine
import tech.calindra.eitri.android.customNavigation.EitriMachineStack
import tech.calindra.eitri.android.publicModels.EitriMachineInstanceManagerContext
import tech.eitri.example.simplenativeeitri.eitri.EitriService
import kotlin.jvm.Throws


/**
 * MainAppService is just an example. Create your own control structure using 
 * inversion of control and dependency injection to manage your EitriService instance.
 *
 * It is a singleton, so it can be accessed from anywhere in the app.
 * It's just a sample service to demonstrate how to create and keep an EitriService instance.
 *
 * @property eitriService The EitriService instance.
 */
class MainAppService(
    val eitriService: EitriService,
) {
    companion object {

        private var instance: MainAppService? = null

        @Throws(Exception::class)
        fun getInstance(): MainAppService {
            val innerInstance = instance ?: throw Exception("AppService.not.initialized")
            return innerInstance
        }

        fun create(
            context: Context,
            eitriMachineContext: EitriMachineInstanceManagerContext
        ) : MainAppService {

            if (instance != null) {
                throw Exception("AppService.already.initialized")
            }

            val appService = createAppService(
                context,
                eitriMachineContext.mainMachine,
                eitriMachineContext.mainStack
            )

            instance = appService

            return appService
        }

        private fun createAppService(
            context: Context,
            eitriMachine: EitriMachine,
            eitriMachineStack: EitriMachineStack
        ) : MainAppService {

            val eitriService = EitriService(eitriMachine, eitriMachineStack)

            return MainAppService(
                eitriService,
            )
        }
    }
}
