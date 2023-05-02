import Koin
import shared
import Combine

class HomeViewModel: ObservableObject {
    private let repository: SampleRepository by inject()
    @Published var state: HomeComponents.State = .isLoading

    private let stateFlow = MutableStateFlow<HomeComponents.State>(.isLoading)
    private var cancellable: AnyCancellable?

    init() {
        loadData()
        cancellable = stateFlow
            .sink { [weak self] newState in
                self?.state = newState
            }
    }

    private func loadData() {
        async {
            do {
                let sampleResponse = try await sampleRepository.getSampleRemote()
                            .choices.first().message.content
                            .replacingOccurrences(of: regex, with: "", options: .regularExpression)
                            .replacingOccurrences(of: ". ", with: "")
                            .components(separatedBy: "\n")
                stateFlow.value = .init(items: sampleResponse)
            } catch {
                stateFlow.value = .init(isError: true)
            }
        }
    }

    func onReceiveAction(_ action: HomeComponents.Action) {
        switch action
        {
            case .OnItemClick(let item):
                // Handle item click
                break
        }
    }

    deinit {
        cancellable?.cancel()
    }
}

enum HomeComponents {
    enum State {
        case isLoading
        case isError
        case items([String])
    }

    enum Action {
        case OnItemClick(String)
    }
}