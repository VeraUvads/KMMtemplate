//
//  HomeViewModel.swift
//  iosApp
//
//  Created by mac on 21.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import Foundation

class HomeViewModel: ObservableObject { 
    private let repository: SampleRepository = SampleRepository()
    @Published var state: HomeComponents.State = .isLoading

    init() {
        loadData()
    }
    
    private func loadData() {
        repository.getSampleRemote(completionHandler:{ chatResponse, error in
            let choice = chatResponse?.choices[0] as? Choice
            print(choice)
            if let choiceUnwrap = choice {
                let items = choiceUnwrap.message.content
                    .replacingOccurrences(of: ".", with: "")
                    .components(separatedBy: CharacterSet.decimalDigits).joined()
                    .components(separatedBy: "\n")

                DispatchQueue.main.async {
                    self.state = .items(items)
                }
            }
        })
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
