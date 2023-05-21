import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        HelperKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
            NavigationView {
                HomeView()
            }.accentColor(.black)
		}
	}
}