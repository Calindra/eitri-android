import Flutter
import UIKit
import Eitri

@main
@objc class AppDelegate: FlutterAppDelegate {
    
    private let CHANNEL = "tech.eitri/native_bridge"
    
    override func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        GeneratedPluginRegistrant.register(with: self)
    
        EitriService.shared.configure()
      
        let controller: FlutterViewController = window?.rootViewController as! FlutterViewController
        let methodChannel = FlutterMethodChannel(
            name: CHANNEL,
            binaryMessenger: controller.binaryMessenger
        )

        methodChannel.setMethodCallHandler { call, result in
            switch call.method {
            case "openEitriApp":
                EitriService.shared.runOnTop(
                    RunInput(slug: "eitri-doctor")
                )
            default:
                result(FlutterMethodNotImplemented)
            }
        }
      
        return super.application(application, didFinishLaunchingWithOptions: launchOptions)
    }
}
