import Foundation
import UIKit
import Eitri

/**
 * EitriService is a suggested service to manage the EitriMachine and EitriMachineStack.
 *
 * It's responsible for configuring the EitriMachine and exposing the necessary Eitri APIs for the app.
 *
 * It's also wrapping the asynchronous nature of the EitriMachine configuration and run methods
 * to make it easier to use in an older native iOS application.
 * If you are a modern architecture, you might want to adapt this service to fit your needs.
 */
class EitriService {
    
    // TODO: Replace with the current app version or marketing version
    let appVersion = "1.0.0"
        
    // TODO: Replace with a scheme property and use a real environment key provided by the Eitri team
    let envKey = "b73b5d05-183a-4630-b442-71d2b61cfaaa"
    
    let BACKGROUND_COLOR_FOR_TRANSITIONS = "#222"
    
    static let shared = EitriService()
    
    let mainEitriMachine: EitriMachine
    private let eitriMachineStack: EitriMachineStack

    
    private init() {
        let eitriContext = EitriMachineInstanceManager.start()
        mainEitriMachine = eitriContext.mainMachine
        eitriMachineStack = eitriContext.mainStack
    }
    
    func configure() {
        Task {
            do {
                let configureInput = ConfigureInput(
                    version: Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "1.0.0",
                    environmentKey: self.envKey,
                    debugMode: self.isEitriDevEnabled(),
                    theme: ThemeConfig(
                        light: Theme(backgroundColor: BACKGROUND_COLOR_FOR_TRANSITIONS),
                        dark: Theme(backgroundColor: BACKGROUND_COLOR_FOR_TRANSITIONS)
                    )
                )
                try await self.mainEitriMachine.configure(input: configureInput)
            } catch {
                print("error initializing EitriPlayer", error)
            }
            
            try? mainEitriMachine.bridge.exposeNativeNavigationApi(eitriMachineStack: eitriMachineStack)
            EitriBridgeHelper.shared.exposeFunctions(eitriMachine: mainEitriMachine)
        }
    }
    
    func runOnTop(_ options: RunInput) {
        Task { @MainActor in
            do {
                try await self.eitriMachineStack.createReplicaInstance().run(options: options)
            } catch {
                print("runOnTop.error", error)
            }
        }
    }
    
    func isEitriDevEnabled() -> Bool {
        // TODO: Define your rules. Production builds typically set this to false to disable Eitri debugging.
        return true
    }
    
}
