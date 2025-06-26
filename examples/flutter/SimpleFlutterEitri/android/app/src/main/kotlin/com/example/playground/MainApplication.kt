package com.example.playground

import android.app.Application
import com.example.playground.service.MainAppService
import tech.calindra.eitri.android.publicModels.EitriMachineInstanceManager

class MainApplication: Application() {
    companion object {
        private lateinit var mainAppService: MainAppService
    }

    override fun onCreate() {
        super.onCreate()
        val eitriMachineContext = EitriMachineInstanceManager.start()
        mainAppService = MainAppService.create(applicationContext, eitriMachineContext)
        mainAppService.eitriService.configure(applicationContext)
    }



}