import SwiftUI
import Koin

@main
struct iOSApp: App {
    init() {
        startKoin {
            modules(commonModules())
        }
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
