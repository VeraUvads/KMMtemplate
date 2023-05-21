//
//  HomeViewModel.swift
//  iosApp
//
//  Created by mac on 21.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared

class HomeViewModel: ObservableObject { // TODO перенести в другой класс
    private let repository: SampleRepository =  SampleRepository() // TODO попытаться добавить Koin
    @Published var state: HomeComponents.State = .isLoading

//     private let stateFlow = MutableStateFlow<HomeComponents.State>(.isLoading)
//     private var cancellable: AnyCancellable?

    init() {
        loadData()
//         cancellable = state
//             .sink { [weak self] newState in
//                 self?.state = newState
//             }
    }
    private func loadData() {
        print("loadData started")
        repository.getSampleRemote(completionHandler:{ chatResponse, error in
//               print(result)
//               print(error)
//         if let firstChoice = chatResponse!.choices.first,
//            let messageDto = firstChoice.message as? MessageDto {
//                // Access the MessageDto object here
//                // Example:
//                let role = messageDto.role
//                let content = messageDto.content
//                // ...
//         }

                if let messageDto = chatResponse?.choices.first as? Choice? {
                        let message = messageDto?.message
                        let content = message?.content.components(separatedBy: "\n")
                        self.state = .items(content!)
//                        let role = messageDto.role
//                        let content = messageDto.content
                }

//                let message = sampleResponse {
//                     let items = message.content.replacingOccurrences(of: ". ", with: "").components(separatedBy: "\n")
//                }
//                let items = sampleResponse.message.content.replacingOccurrences(of: ". ", with: "").components(separatedBy: "\n")

//                .choices.first().message.content
//                                                 .replacingOccurrences(of: regex, with: "", options: .regularExpression)
//                                                 .replacingOccurrences(of: ". ", with: "")
//                                                 .components(separatedBy: "\n")

        })
        print("loadData stopped")
//         DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) {
//             // do stuff 42 seconds later
//             state = .isError
//         }
//         let timer = Timer.scheduledTimer(withTimeInterval: 20.0, repeats: false) { (timer) in
//             self.state = .isError
//         }
//                 sampleRepository?.getSampleRemote(id: id!, completionHandler: { note, error in
//                         self.noteTitle = note?.title ?? ""
//                         self.noteContent = note?.content ?? ""
//                         self.noteColor = note?.colorHex ?? Note.Companion().generateRandomColor()
//                     })
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
        case items([String])
    }

    enum Action {
        case OnItemClick(String)
    }
}
