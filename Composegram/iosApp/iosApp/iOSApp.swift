import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinInitializer_iosKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
