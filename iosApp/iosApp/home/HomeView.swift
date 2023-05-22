//
//  HomeView.swift
//  iosApp
//
//  Created by mac on 21.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

import SwiftUI

struct HomeView: View {

	@StateObject var homeViewModel = HomeViewModel()

        var body: some View {
            let state = homeViewModel.state
                switch state {
                case .isLoading:
                    ProgressView()
                case .isError:
                    Text("Что-то пошло не так")
                case.items(let items):
                    VStack(spacing: 16) {
                        Text("Выберите тему")
                            .font(.title)
                            .fontWeight(.bold)
                        
                        List(items.indices, id: \.self) { index in
                            HStack(spacing: 8) {
                                Text(String(index + 1))
                                    .font(.system(size: 24, weight: .bold))
                                    .frame(width: 32, height: 32)
                                
                                Text(items[index])
                                    .font(.system(size: 16))
                                
                                Spacer()
                            }
                        }
                    }
                    .padding()
                }
        }

}


