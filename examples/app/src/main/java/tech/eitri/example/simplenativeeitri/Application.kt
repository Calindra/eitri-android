package tech.eitri.example.simplenativeeitri

import android.app.Application
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.calindra.eitri.android.publicModels.EitriMachineInstanceManager
import tech.eitri.example.simplenativeeitri.service.MainAppService

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize libraries, logging, DI frameworks, etc.

        val eitriMachineContext = EitriMachineInstanceManager.start()
        val appService = MainAppService.create(applicationContext, eitriMachineContext)
        CoroutineScope(Dispatchers.IO).launch {
            appService.eitriService.configure(applicationContext)
        }
    }
}