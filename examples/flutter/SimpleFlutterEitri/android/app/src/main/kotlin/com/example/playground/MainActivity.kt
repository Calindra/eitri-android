package com.example.playground

import androidx.annotation.NonNull
import com.example.playground.service.MainAppService
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import tech.calindra.eitri.android.publicModels.EitriMachineInstanceManager
import tech.calindra.eitri.android.publicModels.RunInput

class MainActivity : FlutterActivity() {
    private val CHANNEL = "tech.eitri/native_bridge"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        val eitriMachineContext = EitriMachineInstanceManager.start()

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            when (call.method) {
                "openEitriApp" -> {
                    MainAppService.getInstance().eitriService.runOnTop(
                        RunInput(slug = "eitri-doctor", context = this)
                    )
                    result.success(null)
                }
                else -> result.notImplemented()
            }
        }
    }
}
