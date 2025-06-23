package tech.eitri.example.simplenativeeitri

import android.app.Application
import tech.calindra.eitri.android.publicModels.EitriMachineInstanceManager
import tech.eitri.example.simplenativeeitri.service.MainAppService

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize libraries, logging, DI frameworks, etc.

        val eitriMachineContext = EitriMachineInstanceManager.start()
        val appService = MainAppService.create(applicationContext, eitriMachineContext)
        appService.eitriService.configure(applicationContext)
    }
}