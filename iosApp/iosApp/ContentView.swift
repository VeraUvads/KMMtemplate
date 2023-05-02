import SwiftUI
//import shared
import Koin

struct ContentView: View {
	@StateObject var homeViewModel = HomeViewModel()

        var body: some View {
            let state = homeViewModel.state

            switch state {
                case .isLoading:
                    ProgressView()
                case .isError:
                    Text("Что-то пошло не так")
                        .foregroundColor(Color.white)
                        .font(.title)
                        .fontWeight(.bold)
                case .items(let items):
                    HomeContent(items: items) { item in
                        homeViewModel.onReceiveAction(.OnItemClick(item))
                    }
            }
        }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
