import SwiftUI
import shared

class HomeViewModel: ObservableObject { // TODO перенести в другой класс
    private let repository: SampleRepository =  SampleRepository() // TODO попытаться добавить Koin
    @Published var state: HomeComponents.State = .isLoading

//     private let stateFlow = MutableStateFlow<HomeComponents.State>(.isLoading)
//     private var cancellable: AnyCancellable?

    init() {
        loadData()
//         cancellable = stateFlow
//             .sink { [weak self] newState in
//                 self?.state = newState
//             }
    }
    private func loadData() {
//         Task {
//             do {
//                 throw "Some Error"
//                 let sampleResponse = try await sampleRepository.getSampleRemote()
//                             .choices.first().message.content
//                             .replacingOccurrences(of: regex, with: "", options: .regularExpression)
//                             .replacingOccurrences(of: ". ", with: "")
//                             .components(separatedBy: "\n")
//                 stateFlow.value = .init(items: sampleResponse)
//             } catch {
//                 stateFlow.value = .isError
//             }
//         }
    }
//
//     func onReceiveAction(_ action: HomeComponents.Action) {
//         switch action
//         {
//             case .OnItemClick(let item):
//                 // Handle item click
//                 break
//         }
//     }

//     deinit {
//         cancellable?.cancel()
//     }
}

enum HomeComponents {
    enum State {
        case isLoading
        case isError
//         case items([String])
    }

    enum Action {
        case OnItemClick(String)
    }
}

struct ContentView: View {
	@StateObject var homeViewModel = HomeViewModel()

    var body: some View {
         let state = homeViewModel.state
         switch state {
            case .isLoading:
                ProgressView()
            case .isError:
                Text("Что-то пошло не так")
//             case .items(let items):
//                 HomeContent(items: items) { item in homeViewModel.onReceiveAction(.OnItemClick(item)) }
         }

    }
}
